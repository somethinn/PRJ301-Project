/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import slot.SlotDTO;
import utils.DBUtils;

/**
 *
 * @author ASUS
 */
public class RoomDAO {

    public RoomDTO getRoomByID(int roomID) {
        String sql = "SELECT [roomID]\n"
                + "      ,[roomName]\n"
                + "  FROM [11twellStudio].[dbo].[Room]\n"
                + "  WHERE roomID = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, roomID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                RoomDTO roomDTO = new RoomDTO(rs.getInt("roomID"), rs.getString("roomName"));
                return roomDTO;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
     public List<RoomDTO> getListRoom() {
        List<RoomDTO> list = new ArrayList<>();
        String sql = "SELECT [roomID]\n"
                + "      ,[roomName]\n"
                + "  FROM [11twellStudio].[dbo].[Room]\n";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RoomDTO roomDTO = new RoomDTO(rs.getInt("roomID"), rs.getString("roomName"));
                list.add(roomDTO);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        RoomDAO dao = new RoomDAO();
        RoomDTO slot = dao.getRoomByID(1);
        System.out.println(slot);
    }
}
