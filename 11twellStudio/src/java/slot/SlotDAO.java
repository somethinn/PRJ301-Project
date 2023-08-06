/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import movie.MovieDAO;
import room.RoomDTO;
import show.ShowDTO;
import utils.DBUtils;

/**
 *
 * @author ASUS
 */
public class SlotDAO {

    public SlotDTO getSlotByID(int slotID) {
        String sql = "SELECT [slotID]\n"
                + "      ,[slotTime]\n"
                + "  FROM [11twellStudio].[dbo].[Slot]\n"
                + "  WHERE slotID = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, slotID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                SlotDTO slotDTO = new SlotDTO(rs.getInt("slotID"), rs.getString("slotTime"));
                return slotDTO;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<SlotDTO> getListSlot() {
        List<SlotDTO> list = new ArrayList<>();
        String sql = "SELECT [slotID]\n"
                + "      ,[slotTime]\n"
                + "  FROM [11twellStudio].[dbo].[Slot]\n";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SlotDTO slotDTO = new SlotDTO(rs.getInt("slotID"), rs.getString("slotTime"));
                list.add(slotDTO);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        SlotDAO dao = new SlotDAO();
        SlotDTO slot = dao.getSlotByID(1);
        System.out.println(slot);
    }
}
