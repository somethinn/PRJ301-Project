/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;


/**
 *
 * @author ASUS
 */
public class SeatDAO {
   public List<SeatDTO> list(int seatID){
        
        ArrayList<SeatDTO> list;  
        list = new ArrayList<SeatDTO>();
        
        String sql = " SELECT seatID, seatName FROM Seat ";

        String where = "";
        String whereJoinWord = " WHERE ";     
        
        if (seatID != 0){
            where += whereJoinWord;
            where += " seatID = ? ";
           
        }
   
        
        try {
            
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            int index = 1;
            if (seatID != 0){
                ps.setInt(index, seatID);
                index ++;
              
            }
            
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                    list.add(new SeatDTO(rs.getInt("seatID"), 
                                         rs.getString("seatName")
                            )
                    );
                                   
            }
            return list;

	}
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;	
		
    }
    public SeatDTO getSeatBySeatID(int seatID) {
       String sql = "SELECT seatID, seatName FROM Seat WHERE seatID = ? ";
       try {
           Connection conn = DBUtils.getConnection();
           PreparedStatement st = conn.prepareStatement(sql);
           st.setInt(1, seatID);
           ResultSet rs = st.executeQuery();
           if(rs.next()) {
               SeatDTO seat = new SeatDTO();
               seat.setSeatID(seatID);
               seat.setSeatName(rs.getString("seatName"));
               return seat;
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return null;
   }
   
   public String getSeatNameByID(int seatID) {
       String sql = "SELECT seatName FROM Seat WHERE seatID = ? ";
       try {
           Connection conn = DBUtils.getConnection();
           PreparedStatement st = conn.prepareStatement(sql);
           st.setInt(1, seatID);
           ResultSet rs = st.executeQuery();
           if(rs.next()) {
               return rs.getString("seatName");
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return null;
   }
    public static void main(String[] args) {
        SeatDAO dao = new  SeatDAO();
        List<SeatDTO> list = dao.list(0);
        for (SeatDTO seatDTO : list) {
            System.out.println(seatDTO.getSeatName());
        }
        System.out.println(list.size());
    }
}
