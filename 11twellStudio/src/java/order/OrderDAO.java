/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import seat.SeatDTO;
import show.ShowDAO;
import user.UserDAO;
import utils.DBUtils;

/**
 *
 * @author ASUS
 */
public class OrderDAO {

    public boolean checkSeatExist(int showID, int seatID) {
        String sql = " SELECT o.showID, od.seatID FROM [Order] o JOIN [OrderDetail] od \n"
                + " ON o.orderID = od.orderID \n"
                + " WHERE showID = ? AND od.seatID = ? ";
        try {

            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, showID);
            ps.setInt(2, seatID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //STATUS 0: PENDING, STATUS 1: PAID
    public boolean insertOrder(int showID, int userID) {
        String sql = "INSERT INTO [dbo].[Order]\n"
                + "           ([userID]\n"
                + "           ,[showID]\n"
                + "           ,[orderDetailQuantity]\n"
                + "           ,[orderTotalPrice]\n"
                + "           ,[orderCreatedAt]\n"
                + "           ,[orderStatus])"
                + "     VALUES\n"
                + "           (?,?,0,0,GETDATE(),0) ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userID);
            st.setInt(2, showID);
            st.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Integer> getOrderIDByShowIDAndUserID(int userID, int showID) {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT [orderID]    \n"
                + "  FROM [dbo].[Order] "
                + "  WHERE userID = ? AND showID = ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userID);
            st.setInt(2, showID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("orderID"));

            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public OrderDTO getOrderByOrderID(int orderID) {
        String sql = "SELECT * \n"
                + "  FROM [dbo].[Order] "
                + "  WHERE orderID =  ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, orderID);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setOrderID(rs.getInt("orderID"));
                orderDTO.setUser(new UserDAO().getUser(rs.getInt("userID")));
                orderDTO.setShow(new ShowDAO().getShow(rs.getInt("showID")));
                orderDTO.setOrderDetailQuantity(rs.getInt("orderDetailQuantity"));
                orderDTO.setOrderTotalPrice(rs.getDouble("orderTotalPrice"));
                orderDTO.setSeatIDList(getListOfSeat(rs.getInt("orderID")));
                orderDTO.setOrderStatus(rs.getInt("orderStatus"));
                return orderDTO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer getOrderStatusByOrderID(int orderID) {
        String sql = "SELECT orderStatus \n"
                + "  FROM [dbo].[Order] "
                + "  WHERE orderID =  ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, orderID);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {

                return rs.getInt("orderStatus");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertOrderDetails(int orderID, int seatID, double price) {
        String sql = "INSERT INTO [dbo].[OrderDetail]\n"
                + "           ([orderID]\n"
                + "           ,[seatID]\n"
                + "           ,[orderDetailPrice])\n"
                + "     VALUES\n"
                + "           (?, ?, ?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, orderID);
            st.setInt(2, seatID);
            st.setDouble(3, price);
            st.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateTotalOrderPrice(int orderID) {
        String sql = "UPDATE [dbo].[Order]\n"
                + "   SET \n"
                + "      [orderDetailQuantity] = (SELECT COUNT(*) FROM [OrderDetail] WHERE orderID = ?)\n"
                + "      ,[orderTotalPrice] = (SELECT SUM(orderDetailPrice) FROM [OrderDetail] condition WHERE orderID = ?)\n"
                + "\n"
                + "     \n"
                + " WHERE orderID = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, orderID);
            st.setInt(2, orderID);
            st.setInt(3, orderID);

            st.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateOrderStatusSucces(int orderID) {
        String sql = "UPDATE [dbo].[Order]\n"
                + "   SET \n"
                + "      [orderStatus] = 1 \n"
                + " WHERE orderID = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, orderID);

            st.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteOrder(int orderID) {
        String sql = "DELETE FROM [dbo].[Order]\n"
                + "      WHERE orderID = ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, orderID);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteOrderDetail(int orderID) {
        String sql = "DELETE FROM OrderDetail\n"
                + "WHERE orderID = ?;";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, orderID);

            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<SeatDTO> getListOfSeat(int orderID) {
        List<SeatDTO> list = new ArrayList<>();
        String sql = "SELECT od.[seatID], s.[seatName]   \n"
                + "                FROM [dbo].[OrderDetail] od join [dbo].[Seat] s\n"
                + "                  ON OD.seatId = s.seatID  WHERE  orderID = ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, orderID);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SeatDTO seat = new SeatDTO();
                seat.setSeatID(rs.getInt("seatID"));
                seat.setSeatName(rs.getString("seatName"));

                list.add(seat);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Integer> getListOfSeatID(int orderID) {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT [seatID]    \n"
                + "  FROM [dbo].[OrderDetail] "
                + "  WHERE orderID = ?  ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, orderID);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("seatID"));

            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<OrderDTO> getAllOrderOfUser(int userID) {
        List<OrderDTO> list = new ArrayList<>();
        String sql = "SELECT [orderID]\n"
                + "      ,[userID]\n"
                + "      ,[showID]\n"
                + "      ,[orderDetailQuantity]\n"
                + "      ,[orderTotalPrice]\n"
                + "      ,[orderCreatedAt]\n"
                + "      ,[orderStatus]\n"
                + "  FROM [dbo].[Order] WHERE userID = ? ORDER BY  [orderID] DESC";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setOrderID(rs.getInt("orderID"));
                orderDTO.setUser(new UserDAO().getUser(rs.getInt("userID")));
                orderDTO.setShow(new ShowDAO().getShow(rs.getInt("showID")));
                orderDTO.setOrderDetailQuantity(rs.getInt("orderDetailQuantity"));
                orderDTO.setOrderTotalPrice(rs.getDouble("orderTotalPrice"));
                orderDTO.setSeatIDList(getListOfSeat(rs.getInt("orderID")));
                orderDTO.setOrderStatus(rs.getInt("orderStatus"));
                list.add(orderDTO);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<OrderDTO> searchOrderList(String sortBy, int userID) {
        List<OrderDTO> list = new ArrayList<>();

        String sql = "SELECT * FROM [Order] Where userID = ? ORDER BY orderID " + sortBy;

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userID);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setOrderID(rs.getInt("orderID"));
                orderDTO.setUser(new UserDAO().getUser(rs.getInt("userID")));
                orderDTO.setShow(new ShowDAO().getShow(rs.getInt("showID")));
                orderDTO.setOrderDetailQuantity(rs.getInt("orderDetailQuantity"));
                orderDTO.setOrderTotalPrice(rs.getDouble("orderTotalPrice"));
                orderDTO.setSeatIDList(getListOfSeat(rs.getInt("orderID")));
                orderDTO.setOrderStatus(rs.getInt("orderStatus"));
                list.add(orderDTO);
            }

            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<OrderDTO> getAllOrder(String date, String mail, int status, int index, int pageSize) {
        String sql = "select * from [Order] o join [User] u on o.userID=u.userID WHERE 1=1 ";

        if (date != null && !date.isEmpty()) {
            sql += " AND [orderCreatedAt] LIKE '%" + date + "%' ";
        }
        if (mail != null && !mail.isEmpty()) {
            sql += " AND [userEmail] LIKE '%" + mail + "%' ";
        }
        if (status != 3) {
            sql += " AND [orderStatus] = " + status;
        }
        if (pageSize != -1) {
            sql += "ORDER BY o.orderID DESC  OFFSET ? ROWS FETCH NEXT ? ROWS ONLY  ";
        }

        List<OrderDTO> list = new ArrayList<>();

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            if (pageSize != -1) {
                statement.setInt(1, (index - 1) * pageSize);
                statement.setInt(2, pageSize);
            }

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setOrderID(rs.getInt("orderID"));
                orderDTO.setUser(new UserDAO().getUser(rs.getInt("userID")));
                orderDTO.setShow(new ShowDAO().getShow(rs.getInt("showID")));
                orderDTO.setOrderDetailQuantity(rs.getInt("orderDetailQuantity"));
                orderDTO.setOrderTotalPrice(rs.getDouble("orderTotalPrice"));
                orderDTO.setSeatIDList(getListOfSeat(rs.getInt("orderID")));
                orderDTO.setOrderCreatedAt(rs.getString("orderCreatedAt"));
                orderDTO.setOrderStatus(rs.getInt("orderStatus"));
                list.add(orderDTO);
            }

            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public int counlAllOrder() {
        String sql = "select count(*) from [order]";
        int count = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public String getOrderCreatedAt(int orderID) {
        String sql = " select orderCreatedAt from [Order] where orderID = ? ";

        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1, orderID);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                return rs.getString("orderCreatedAt");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Double> getTotalPriceOfEachDayIn7CurrentDay() {
        List<Double> list = new ArrayList<>();
        String sql = "SELECT  ISNULL(SUM(orderTotalPrice), 0) AS TotalOrderPrice\n"
                + "FROM (\n"
                + "    SELECT CAST(DATEADD(DAY, -7, GETDATE()) AS DATE) AS OrderDate\n"
                + "    UNION ALL\n"
                + "    SELECT CAST(DATEADD(DAY, -6, GETDATE()) AS DATE) AS OrderDate\n"
                + "    UNION ALL\n"
                + "    SELECT CAST(DATEADD(DAY, -5, GETDATE()) AS DATE) AS OrderDate\n"
                + "    UNION ALL\n"
                + "    SELECT CAST(DATEADD(DAY, -4, GETDATE()) AS DATE) AS OrderDate\n"
                + "    UNION ALL\n"
                + "    SELECT CAST(DATEADD(DAY, -3, GETDATE()) AS DATE) AS OrderDate\n"
                + "    UNION ALL\n"
                + "    SELECT CAST(DATEADD(DAY, -2, GETDATE()) AS DATE) AS OrderDate\n"
                + "    UNION ALL\n"
                + "    SELECT CAST(DATEADD(DAY, -1, GETDATE()) AS DATE) AS OrderDate\n"
                + ") AS dateTable\n"
                + "LEFT JOIN [11twellStudio].[dbo].[Order] ON CAST(orderCreatedAt AS DATE) = dateTable.OrderDate\n"
                + "GROUP BY dateTable.OrderDate\n"
                + "ORDER BY dateTable.OrderDate ASC;";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                list.add(rs.getDouble("TotalOrderPrice"));
            }

            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public int countTotalOrderDetail() {
        String sql = "SELECT COUNT(*)\n"
                + "      \n"
                + "  FROM [11twellStudio].[dbo].[OrderDetail] ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {

        }

        return 0;
    }

    public double getTotalOrderRevenue() {
        String sql = "SELECT SUM(orderTotalPrice) FROM [11twellStudio].[dbo].[Order] ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {

        }

        return 0;
    }

    public boolean insertPromoID(int promoID) {
        String sql = "INSERT INTO [dbo].[Order]\n"
                + "           ([promoID])\n"
                + "     VALUES ( ? )";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, promoID);

            st.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePriceAfterAddPromo(OrderDTO order) {
        String sql = "UPDATE [dbo].[Order]\n"
                + "   SET \n"
                + "      [orderTotalPrice] = ?\n"
                + "      \n"
                + " WHERE orderID = ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setDouble(1, order.getOrderTotalPrice());
            st.setInt(2, order.getOrderID());

            st.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        OrderDAO dao = new OrderDAO();
        System.out.println(dao.getAllOrder(null, null, 1, 1, 7).get(0).getOrderCreatedAt());
    }
}
