/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package show;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import movie.MovieDAO;
import slot.SlotDAO;
import utils.DBUtils;
import room.RoomDAO;

/**
 *
 * @author ASUS
 */
public class ShowDAO {

    public ShowDTO getShow(int showID) {
        String sql = "SELECT [showID] \n"
                + "      ,[showDate] \n"
                + "      ,[slotID] \n"
                + "      ,[movieID] \n"
                + "      ,[roomID] \n"
                + "      ,[showPrice] \n"
                + "      ,[showStatus] \n"
                + "  FROM [11twellStudio].[dbo].[Show] WHERE showID = ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, showID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ShowDTO showDTO = new ShowDTO(rs.getInt("showID"),
                        rs.getDate("showDate"),
                        new SlotDAO().getSlotByID(rs.getInt("slotID")),
                        (new MovieDAO()).getMovieByID(rs.getInt("movieID")),
                        new RoomDAO().getRoomByID(rs.getInt("roomID")),
                        rs.getDouble("showPrice"),
                        rs.getInt("showStatus"));
                return showDTO;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ShowDTO> getShowByMovieID(int movieID) {

        String sql = "SELECT [showID] \n"
                + "      ,[showDate] \n"
                + "      ,[slotID] \n"
                + "      ,[movieID] \n"
                + "      ,[roomID] \n"
                + "      ,[showPrice] \n"
                + "      ,[showStatus] \n"
                + "  FROM [11twellStudio].[dbo].[Show] WHERE movieID = ? ";
        List<ShowDTO> list = new ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, movieID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ShowDTO showDTO = new ShowDTO();
                showDTO.setShowID(rs.getInt("showID"));
                showDTO.setMovie(new MovieDAO().getMovieByID(movieID));
                showDTO.setShowDate(rs.getDate("showDate"));
                showDTO.setShowPrice(rs.getInt("showPrice"));
                showDTO.setShowStatus(rs.getInt("showStatus"));

                list.add(showDTO);

            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ShowDTO> showListFortManagement(String name, int status, Date date, int index, int pageSize) {

        String sql = " SELECT * FROM [Show] s JOIN [Movie] m ON s.movieID = m.movieID WHERE 1=1 ";
        
        if (name != null && !name.isEmpty()){
            sql += " AND movieTitle LIKE '%" + name + "%' ";
        }
        
        if(status != 3){
            sql += " AND showStatus = " + status;
        }
        
        if(date !=null){
            sql +=" AND showDate = '" + date + "' ";
        }
        
        if(pageSize != -1){
            sql += " ORDER BY showDate DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";
        }
        
        List<ShowDTO> list = new ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            if (pageSize != -1) {
                ps.setInt(1, (index - 1) * pageSize);
                ps.setInt(2, pageSize);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ShowDTO showDTO = new ShowDTO();
                showDTO.setShowID(rs.getInt("showID"));
                showDTO.setMovie(new MovieDAO().getMovieByID(rs.getInt("movieID")));
                showDTO.setRoom(new RoomDAO().getRoomByID(rs.getInt("roomID")));
                showDTO.setSlot(new SlotDAO().getSlotByID(rs.getInt("slotID")));
                showDTO.setShowDate(rs.getDate("showDate"));
                showDTO.setShowPrice(rs.getInt("showPrice"));
                showDTO.setShowStatus(rs.getInt("showStatus"));

                list.add(showDTO);

            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ShowDTO> getShowByMovieIDPublic(int movieID, String showDate) {

        String sql = "SELECT \n"
                + "    showID,\n"
                + "    showDate,\n"
                + "    s.slotID,\n"
                + "    movieID,\n"
                + "    roomID,\n"
                + "    showPrice,\n"
                + "    showStatus,\n"
                + "    sl.slotTime\n"
                + "FROM Show s \n"
                + "JOIN Slot sl ON s.slotID = sl.slotID \n"
                + "WHERE movieID = ? AND showDate = ? AND showStatus = 1 \n"
                + "ORDER BY\n"
                + "    CAST(SUBSTRING(sl.slotTime, 1, CHARINDEX(':', sl.slotTime) - 1) AS INT),\n"
                + "    sl.slotTime ASC";
        List<ShowDTO> list = new ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, movieID);
            ps.setString(2, showDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ShowDTO showDTO = new ShowDTO();
                showDTO.setShowID(rs.getInt("showID"));
                showDTO.setMovie(new MovieDAO().getMovieByID(movieID));
                showDTO.setShowDate(rs.getDate("showDate"));
                showDTO.setSlot(new SlotDAO().getSlotByID(rs.getInt("slotID")));
                showDTO.setShowPrice(rs.getInt("showPrice"));
                showDTO.setShowStatus(rs.getInt("showStatus"));

                list.add(showDTO);

            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteShow(int showID) {
        String sql = "DELETE FROM [dbo].[Show]\n"
                + "      WHERE showID = ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, showID);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkShowExist(Date showDate, int slotID, int roomID) {
        String sql = "SELECT * FROM [dbo].[Show]\n"
                + "      WHERE showDate = ? AND slotID = ? AND roomID = ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, showDate);
            ps.setInt(2, slotID);
            ps.setInt(3, roomID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkShowExistEdit(Date showDate, int slotID, int roomID, int showID) {
        String sql = "SELECT * FROM [dbo].[Show]\n"
                + "      WHERE showDate = ? AND slotID = ? AND roomID = ? AND showID <> ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, showDate);
            ps.setInt(2, slotID);
            ps.setInt(3, roomID);
            ps.setInt(4, showID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertShow(ShowDTO show) {
        String sql = "INSERT INTO [dbo].[Show]\n"
                + "           ([showDate]\n"
                + "           ,[slotID]\n"
                + "           ,[movieID]\n"
                + "           ,[roomID]\n"
                + "           ,[showPrice]\n"
                + "           ,[showStatus])\n"
                + "     VALUES \n"
                + "           (?,?,?,?,?,?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, show.getShowDate());
            ps.setInt(2, show.getSlot().getSlotID());
            ps.setInt(3, show.getMovie().getMovieID());
            ps.setInt(4, show.getRoom().getRoomID());
            ps.setDouble(5, show.getShowPrice());
            ps.setInt(6, show.getShowStatus());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateShow(ShowDTO s) {
        String sql = "UPDATE [dbo].[Show]\n"
                + "   SET [showDate] = ?\n"
                + "      ,[slotID] = ?\n"
                + "      ,[roomID] = ?\n"
                + "      ,[showPrice] = ?\n"
                + "      ,[showStatus] = ?\n"
                + " WHERE showID = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setDate(1, s.getShowDate());
            st.setInt(2, s.getSlot().getSlotID());
            st.setInt(3, s.getRoom().getRoomID());
            st.setDouble(4, s.getShowPrice());
            st.setInt(5, s.getShowStatus());
            st.setInt(6, s.getShowID());
            st.executeUpdate();
            
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public void updateShowStatus(ShowDTO s) {
        String sql = "UPDATE [dbo].[Show]\n"
                + "   SET [showStatus] = ?\n"
                + " WHERE showID = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, s.getShowStatus());
            st.setInt(2, s.getShowID());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /*
    SELECT * FROM Show s 
        INNER JOIN Movie m ON s.movieID  = m.movieID 
        INNER JOIN Slot sl ON s.slotID = sl.slotID 
        INNER JOIN Room r ON s.roomID = r.roomID
        WHERE sl.slotID = 1 AND r.roomID = 1
     */
    public static void main(String[] args) {
        ShowDAO dao = new ShowDAO();
        System.out.println(dao.showListFortManagement(null, 3, Date.valueOf("2023-06-16"), 1, -1).size());
    }
}
