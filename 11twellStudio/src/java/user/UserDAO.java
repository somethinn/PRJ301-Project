/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import order.OrderDAO;
import order.OrderDTO;
import seat.SeatDTO;
import utils.DBUtils;
import utils.SendMail;

/**
 *
 * @author ASUS
 */
public class UserDAO {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int DEFAULT_LENGTH = 10;

    public static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    public UserDTO getUser(int userID) {
        String sql = "SELECT [userID]\n"
                + "      ,[userName] \n"
                + "      ,[userEmail] \n"
                + "      ,[userGender] \n"
                + "      ,[userPhone] \n"
                + "      ,[userRegion] \n"
                + "      ,[userDOB] \n"
                + "      ,[userRole] \n"
                + "  FROM [dbo].[User]  WHERE userID = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UserDTO userDTO = new UserDTO(rs.getInt("userID"),
                        rs.getString("userName"),
                        rs.getString("userEmail"),
                        rs.getString("userGender"),
                        rs.getString("userPhone"),
                        rs.getString("userRegion"),
                        rs.getDate("userDOB"),
                        rs.getInt("userRole")
                );
                return userDTO;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserDTO getUserByMail(String email) {
        String sql = "SELECT [userID]\n"
                + "      ,[userName] \n"
                + "      ,[userEmail] \n"
                + "      ,[userGender] \n"
                + "      ,[userPhone] \n"
                + "      ,[userRegion] \n"
                + "      ,[userDOB] \n"
                + "      ,[userRole] \n"
                + "  FROM [dbo].[User]  WHERE [userEmail] = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UserDTO userDTO = new UserDTO(rs.getInt("userID"),
                        rs.getString("userName"),
                        rs.getString("userEmail"),
                        rs.getString("userGender"),
                        rs.getString("userPhone"),
                        rs.getString("userRegion"),
                        rs.getDate("userDOB"),
                        rs.getInt("userRole")
                );
                return userDTO;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//       public List<UserDTO> list(int userID, ){
//        
//        ArrayList<UserDTO> list;  
//        list = new ArrayList<UserDTO>();
//        
//        String sql = " SELECT * FROM User ";
//
//        String where = "";
//        String whereJoinWord = " WHERE ";     
//        
//        if (seatID != 0){
//            where += whereJoinWord;
//            where += " seatID = ? ";
//           
//        }
//   
//        
//        try {
//            
//            Connection conn = DBUtils.getConnection();
//            PreparedStatement ps = conn.prepareStatement(sql);
//            
//            int index = 1;
//            if (seatID != 0){
//                ps.setInt(index, seatID);
//                index ++;
//              
//            }
//            
//            
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()){
//                    list.add(new SeatDTO(rs.getInt("seatID"), 
//                                         rs.getString("seatName")
//                            )
//                    );
//                                   
//            }
//            return list;
//
//	}
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;	
//		
//    }
    public boolean checkEmailExist(String userEmail) {
        String sql = "SELECT * from [User] where userEmail = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, userEmail);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {

                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean checkUserSetDate(String userEmail) {
        String sql = "SELECT * from [User] where userEmail = ? AND userIsSetDate = 1 ";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, userEmail);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {

                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean checkUserSetChangePassWord(String userEmail) {
        String sql = "SELECT * from [User] where userEmail = ? AND userGoogleIsChangePassword = 1 ";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, userEmail);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {

                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public UserDTO getUserGoogleRaw(String userEmail, String password) {
        String sql = "SELECT [userID]\n"
                + "      ,[userName]\n"
                + "      ,[userEmail]\n"
                + "      ,[userPassword]\n"
                + "      ,[userGender]\n"
                + "      ,[userPhone]\n"
                + "      ,[userRegion]\n"
                + "      ,[userDOB]\n"
                + "      ,[userRole]\n"
                + "      ,[userIsActive]\n"
                + "  FROM [dbo].[User]\n"
                + "  WHERE userEmail = ? \n"
                + "  AND userPassword = ? \n"
                + "  AND userIsActive = 0 ";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, userEmail);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                userEmail = rs.getString("userEmail");
                String userName = rs.getString("userName");
                String userGender = rs.getString("userGender");
                String userPhone = rs.getString("userPhone");
                String userRegion = rs.getString("userRegion");
                Date userDOB = rs.getDate("userDOB");
                int userRole = rs.getInt("userRole");
                int userID = rs.getInt("userID");
                UserDTO u = new UserDTO(userID, userName, userEmail, userGender, userPhone, userRegion, userDOB, userRole);
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public UserDTO check(String userEmail, String password) {
        String sql = "SELECT [userID]\n"
                + "      ,[userName]\n"
                + "      ,[userEmail]\n"
                + "      ,[userPassword]\n"
                + "      ,[userGender]\n"
                + "      ,[userPhone]\n"
                + "      ,[userRegion]\n"
                + "      ,[userDOB]\n"
                + "      ,[userRole]\n"
                + "      ,[userIsActive]\n"
                + "  FROM [dbo].[User]\n"
                + "  WHERE userEmail = ? \n"
                + "  AND userPassword = ? \n"
                + "  AND userIsActive = 1 ";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, userEmail);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                userEmail = rs.getString("userEmail");
                String userName = rs.getString("userName");
                String userGender = rs.getString("userGender");
                String userPhone = rs.getString("userPhone");
                String userRegion = rs.getString("userRegion");
                Date userDOB = rs.getDate("userDOB");
                int userRole = rs.getInt("userRole");
                int userID = rs.getInt("userID");
                UserDTO u = new UserDTO(userID, userName, userEmail, userGender, userPhone, userRegion, userDOB, userRole);
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public String register(UserDTO u, String password, int role) {
        String key = generateRandomString(10);

        String sql = "INSERT INTO [dbo].[User] \n"
                + "           ([userName] \n"
                + "           ,[userEmail] \n"
                + "           ,[userPassword] \n"
                + "           ,[userGender] \n"
                + "           ,[userPhone] \n"
                + "           ,[userRegion] \n"
                + "           ,[userDOB] \n"
                + "           ,[userRole] \n"
                + "           ,[userIsActive] \n"
                + "           ,[userIsSetDate] \n"
                + "           ,[userGoogleIsChangePassword])\n"
                + "     VALUES          \n"
                + "          (?, ?, ?, ?, ?, ?, ?, ?, 0, 0, 0) ";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, u.getUserName());
            st.setString(2, u.getUserEmail());
            st.setString(3, password);
            st.setString(4, u.getUserGender());
            st.setString(5, u.getUserPhone());
            st.setString(6, u.getUserRegion());
            st.setDate(7, u.getUserDOB());
            st.setInt(8, role);

            st.executeUpdate();
            SendMail.send(u.getUserEmail(), "Verify new user!", "<div style=\"width: 100%; height: 70px; background-color: black; text-align: center;\"><h2 style=\"color: white; line-height: 70px;\">11-12 STUDIO</h2></div>" + "<h1 style=\"text-align: center;\">Welcome to 11twellCine</h1> " + "<div>Your password now is : " + password + "</div>"
                    + "<div>Please change the password to verify your account.</div>" + " <a href=\"http://localhost:8080/11twell/change-password?email=" + u.getUserEmail() + "&key=" + key
                    + "\" >Click here to change your password! </a> " + "<div style=\"width: 100%; height: 70px; background-color: black; margin-top: 30px;\"></div>");
            return key;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public String forgetPassword(String mail) {
        String key = generateRandomString(10);

        SendMail.send(mail, "Change password!", "<div style=\"width: 100%; height: 70px; background-color: black; text-align: center;\"><h2 style=\"color: white; line-height: 70px;\">11-12 STUDIO</h2></div>" + "<h1 style=\"text-align: center;\">Welcome to 11twellCine</h1> "
                + "<div>Reset password.</div>" + " <a href=\"http://localhost:8080/11twell/change-password-forgot?email=" + mail + "&key=" + key
                + "\" >Click here to reset your password! </a> " + "<div style=\"width: 100%; height: 70px; background-color: black; margin-top: 30px;\"></div>");
        return key;
    }

    public String checkRegisterGmail(UserDTO u, String password, int role) {
        String key = generateRandomString(10);

        SendMail.send(u.getUserEmail(), "Verify new user!", "<div style=\"width: 100%; height: 70px; background-color: black; text-align: center;\"><h2 style=\"color: white; line-height: 70px;\">11-12 STUDIO</h2></div>" + "<h1 style=\"text-align: center;\">Welcome to 11twellCine</h1> " + "<div>Your password now is : " + password + "</div>"
                + "<div>Please change the password to verify your account.</div>" + " <a href=\"http://localhost:8080/11twell/change-password?email=" + u.getUserEmail() + "&key=" + key
                + "\" >Click here to change your password! </a> " + "<div style=\"width: 100%; height: 70px; background-color: black; margin-top: 30px;\"></div>");
        return key;

    }

    public String register(String name, String mail, String password, String gender, String phone, String region, String DOB, int role) {
        try {
            String key = generateRandomString(10);
            String sql = "INSERT INTO [dbo].[User]\n"
                    + "           ([userName]\n"
                    + "           ,[userEmail]\n"
                    + "           ,[userPassword]\n"
                    + "           ,[userGender]\n"
                    + "           ,[userPhone]\n"
                    + "           ,[userRegion]\n"
                    + "           ,[userDOB]\n"
                    + "           ,[userRole]\n"
                    + "           ,[userIsActive]\n"
                    + "           ,[userIsSetDate]\n"
                    + "           ,[userGoogleIsChangePassword])\n"
                    + "     VALUES (?,?,?,?,?,?,?,?,0,1,1) ";

            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, mail);
            st.setString(3, password);
            st.setString(4, gender);
            st.setString(5, phone);
            st.setString(6, region);
            st.setString(7, DOB);
            st.setInt(8, role);

            st.executeUpdate();
            
            SendMail.send(mail, "Verify new user!", "<div style=\"width: 100%; height: 70px; background-color: black; text-align: center;\"><h2 style=\"color: white; line-height: 70px;\">11-12 STUDIO</h2></div>" + "<h1 style=\"text-align: center;\">Welcome to 11twellCine</h1> " 
                    +  " <a href=\"http://localhost:8080/11twell/update-account-status?actionUpdate=updateAccountStatus&mailUpdate=" + mail  + "\">Click here to verify - You must login again !!</a> " + "<div style=\"width: 100%; height: 70px; background-color: black; margin-top: 30px;\"></div>");
            return key;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean activeAccount(String email) {
        try {
            String sql = "  UPDATE [User] SET [userIsActive] = 1 WHERE [userEmail] = ?";
            Connection conn = DBUtils.getConnection();
            PreparedStatement pm = conn.prepareStatement(sql);

            pm.setString(1, email);
            pm.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean activeUserGoogleIsChane(String email) {
        try {
            String sql = "  UPDATE [User] SET [userGoogleIsChangePassword] = 1 WHERE [userEmail] = ?";
            Connection conn = DBUtils.getConnection();
            PreparedStatement pm = conn.prepareStatement(sql);

            pm.setString(1, email);
            pm.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUserProfile(UserDTO u, int userID) {
        String sql = "UPDATE [dbo].[User] \n"
                + "   SET [userName] = ? \n"
                + "      ,[userGender] = ? \n"
                + "      ,[userPhone] = ? \n"
                + "      ,[userRegion] = ? \n"
                + "      ,[userDOB] = ? \n"
                + " WHERE userID = ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, u.getUserName());

            st.setString(2, u.getUserGender());
            st.setString(3, u.getUserPhone());
            st.setString(4, u.getUserRegion());
            st.setDate(5, u.getUserDOB());
            st.setInt(6, userID);
            st.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean updatePassword(String mail, String newPassWord) {
        String sql = "UPDATE [dbo].[User] \n"
                + "   SET [userPassword] = ? \n"
                + " WHERE userEmail= ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, newPassWord);
            st.setString(2, mail);

            st.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean isActive(String mail) {
        String sql = "SELECT * FROM  [User] WHERE userEmail = ? AND userIsActive = 1";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, mail);

            st.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean updateUserProfileAndDate(UserDTO u, int userID) {
        String sql = "UPDATE [dbo].[User] \n"
                + "   SET [userName] = ? \n"
                + "      ,[userGender] = ? \n"
                + "      ,[userPhone] = ? \n"
                + "      ,[userRegion] = ? \n"
                + "      ,[userDOB] = ? \n"
                + "      ,[userIsSetDate] = 1 \n"
                + " WHERE userID = ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, u.getUserName());
            st.setString(2, u.getUserGender());
            st.setString(3, u.getUserPhone());
            st.setString(4, u.getUserRegion());
            st.setDate(5, u.getUserDOB());
            st.setInt(6, userID);
            st.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        System.out.println(dao.getUser(1));
    }

    public boolean checkManagerAccount(String account, String password) {
        String sql = "SELECT [userID]\n"
                + "      ,[userName]\n"
                + "      ,[userEmail]\n"
                + "      ,[userPassword]\n"
                + "      ,[userGender]\n"
                + "      ,[userPhone]\n"
                + "      ,[userRegion]\n"
                + "      ,[userDOB]\n"
                + "      ,[userRole]\n"
                + "      ,[userIsActive]\n"
                + "      ,[userIsSetDate]\n"
                + "      ,[userGoogleIsChangePassword]\n"
                + "  FROM [11twellStudio].[dbo].[User]\n"
                + "WHERE [userEmail] = ? AND [userPassword] = ? AND [userRole]= 0";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, account);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    public String getPasswordByMail(String mail) {
        String sql = " SELECT userPassword FROM [User] WHERE userEmail = ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, mail);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;

    }

    public void sendMailAfterPay(UserDTO u, OrderDTO order) {
        OrderDAO orderDAO = new OrderDAO();
        List<SeatDTO> list = orderDAO.getListOfSeat(order.getOrderID());
        StringBuilder seatNames = new StringBuilder();
        for (SeatDTO seatDTO : list) {
            seatNames.append(seatDTO.getSeatName()).append(" ");
        }
        SendMail.send(u.getUserEmail(), "[11-12 Cinema_ Ticket Information]-Your online ticket purchase has been successfully", " <table  style=\"border: none;\">\n"
                + "            <tbody>\n"
                + "                <tr>\n"
                + "                    <td><div style=\"width: 100%; height: 70px; background-color: black; text-align: center;\"><h1 style=\"color: white; line-height: 70px;\">11-12 STUDIO</h1></div></td>\n"
                + "                </tr>\n"
                + "                <tr>\n"
                + "                    <td><h1 style=\"text-align: center\">Hi " + u.getUserName() + "</h1></td>\n"
                + "                </tr>\n"
                + "                <tr>\n"
                + "                    <td>\n"
                + "                        <table style=\"border: none;\">\n"
                + "\n"
                + "                            <tbody>\n"
                + "                                <tr>\n"
                + "                                    <td><strong>Reservation code:</strong></td>\n"
                + "                                    <td style=\"padding-left: 100px\">" + order.getOrderID() + "</td>\n"
                + "                                </tr>\n"
                + "                                <tr>\n"
                + "                                    <td><strong>Movie:</strong></td>\n"
                + "                                    <td style=\"padding-left: 100px\">" + order.getShow().getMovie().getMovieTitle() + "</td>\n"
                + "                                </tr>\n"
                + "                                <tr>\n"
                + "                                    <td><strong>Theater:</strong></td>\n"
                + "                                    <td style=\"padding-left: 100px\">11-12 Cinema FPT</td>\n"
                + "                                </tr>\n"
                + "                                <tr>\n"
                + "                                    <td><strong>Hall:</strong></td>\n"
                + "                                    <td style=\"padding-left: 100px\">" + order.getShow().getRoom().getRoomName() + "</td>\n"
                + "                                </tr>\n"
                + "                                <tr>\n"
                + "                                    <td><strong>Session:</strong></td>\n"
                + "                                    <td style=\"padding-left: 100px\">" + order.getShow().getShowDate() + " " + order.getShow().getSlot().getSlotTime() + "</td>\n"
                + "                                </tr>\n"
                + "                                <tr>\n"
                + "                                    <td><strong>Seat:</strong></td>\n"
                + "                                    <td style=\"padding-left: 100px\">" + seatNames + "</td>\n"
                + "                                </tr>\n"
                + "                                <tr>\n"
                + "                                    <td><strong>Payment method:</strong></td>\n"
                + "                                    <td style=\"padding-left: 100px\">Payment method example</td>\n"
                + "                                </tr>\n"
                + "                                <tr>\n"
                + "                                    <td><strong>Total:</strong></td>\n"
                + "                                    <td style=\"padding-left: 100px\">" + order.getOrderTotalPrice() + " VNĐ" + "</td>\n"
                + "                                </tr>\n"
                + "                            </tbody>\n"
                + "                        </table>\n"
                + "\n"
                + "                    </td>\n"
                + "                </tr>\n"
                + "\n"
                + "                <tr>\n"
                + "                    <td>\n"
                + "                        <h4>Note:</h4>\n"
                + "                        <p style=\"max-width: 750px\">11-12 Cinema does not offer refunds or exchanges for successful tickets purchased on our Culterplex website. If you have any questions or problems with this order, you can Contact Us of see our F.A.Q for more information. Thank you for choosing 11-12 Cinema and Enjoy the movie!</p>\n"
                + "                        \n"
                + "                    </td>\n"
                + "                </tr>\n"
                + "            </tbody>\n"
                + "        </table>");
        

    }
}
