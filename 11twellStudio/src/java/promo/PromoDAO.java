/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package promo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import room.RoomDTO;
import utils.DBUtils;

/**
 *
 * @author ASUS
 */
public class PromoDAO {

    public List<PromoDTO> getAllPromo() {
        List<PromoDTO> list = new ArrayList<>();
        String sql = "SELECT [promoID]\n"
                + "      ,[promoCode]\n"
                + "      ,[promoDiscount]\n"
                + "      ,[promoContent]\n"
                + "      ,[promoStartDate]\n"
                + "      ,[promoEndDate]\n"
                + "      ,[promoStatus]\n"
                + "  FROM [dbo].[Promo] ORDER BY [promoID] DESC";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PromoDTO promoDTO = PromoDTO.builder()
                        .promoID(rs.getInt("promoID"))
                        .promoCode(rs.getString("promoCode"))
                        .promoDiscount(rs.getFloat("promoDiscount"))
                        .promoContent(rs.getString("promoContent"))
                        .promoStartDate(rs.getDate("promoStartDate"))
                        .promoEndDate(rs.getDate("promoEndDate"))
                        .promoStatus(rs.getInt("promoStatus"))
                        .build();
                list.add(promoDTO);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PromoDTO getPromoByID(int promoID) {
        String sql = "SELECT [promoID]\n"
                + "      ,[promoCode]\n"
                + "      ,[promoDiscount]\n"
                + "      ,[promoContent]\n"
                + "      ,[promoStartDate]\n"
                + "      ,[promoEndDate]\n"
                + "      ,[promoStatus]\n"
                + "  FROM [dbo].[Promo]"
                + "  WHERE promoID = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, promoID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                PromoDTO promoDTO = PromoDTO.builder()
                        .promoID(promoID)
                        .promoCode(rs.getString("promoCode"))
                        .promoDiscount(rs.getFloat("promoDiscount"))
                        .promoContent(rs.getString("promoContent"))
                        .promoStartDate(rs.getDate("promoStartDate"))
                        .promoEndDate(rs.getDate("promoEndDate"))
                        .promoStatus(rs.getInt("promoStatus"))
                        .build();
                return promoDTO;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PromoDTO> getListPromo() {
        List<PromoDTO> list = new ArrayList<>();
        String sql = "SELECT [promoID]\n"
                + "      ,[promoCode]\n"
                + "      ,[promoDiscount]\n"
                + "      ,[promoContent]\n"
                + "      ,[promoStartDate]\n"
                + "      ,[promoEndDate]\n"
                + "      ,[promoStatus]\n"
                + "  FROM [dbo].[Promo]";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PromoDTO promoDTO = PromoDTO.builder()
                        .promoID(rs.getInt("promoID"))
                        .promoCode(rs.getString("promoCode"))
                        .promoDiscount(rs.getFloat("promoDiscount"))
                        .promoContent(rs.getString("promoContent"))
                        .promoStartDate(rs.getDate("promoStartDate"))
                        .promoEndDate(rs.getDate("promoEndDate"))
                        .promoStatus(rs.getInt("promoStatus"))
                        .build();

                list.add(promoDTO);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PromoDTO getPromoByCode(String promoCode) {
        String sql = "SELECT [promoID]\n"
                + "      ,[promoCode]\n"
                + "      ,[promoDiscount]\n"
                + "      ,[promoContent]\n"
                + "      ,[promoStartDate]\n"
                + "      ,[promoEndDate]\n"
                + "      ,[promoStatus]\n"
                + "  FROM [dbo].[Promo]"
                + "  WHERE promoCode = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, promoCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                PromoDTO promoDTO = PromoDTO.builder()
                        .promoID(rs.getInt("promoID"))
                        .promoCode(rs.getString("promoCode"))
                        .promoDiscount(rs.getFloat("promoDiscount"))
                        .promoContent(rs.getString("promoContent"))
                        .promoStartDate(rs.getDate("promoStartDate"))
                        .promoEndDate(rs.getDate("promoEndDate"))
                        .promoStatus(rs.getInt("promoStatus"))
                        .build();
                return promoDTO;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertPromo(PromoDTO promo) {
        String sql = "INSERT INTO [dbo].[Promo]\n"
                + "           ([promoCode]\n"
                + "           ,[promoDiscount]\n"
                + "           ,[promoContent]\n"
                + "           ,[promoStartDate]\n"
                + "           ,[promoEndDate]\n"
                + "           ,[promoStatus])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, promo.getPromoCode());
            ps.setFloat(2, promo.getPromoDiscount());
            ps.setString(3, promo.getPromoContent());
            ps.setDate(4, promo.getPromoStartDate());
            ps.setDate(5, promo.getPromoEndDate());
            ps.setInt(6, promo.getPromoStatus());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePromo(PromoDTO promo) {
        String sql = "UPDATE [dbo].[Promo]\n"
                + "   SET [promoDiscount] = ?\n"
                + "      ,[promoContent] = ?\n"
                + "      ,[promoStartDate] = ?\n"
                + "      ,[promoEndDate] = ?\n"
                + "      ,[promoStatus] = ?\n"
                + " WHERE promoID = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
   
            ps.setFloat(1, promo.getPromoDiscount());
            ps.setString(2, promo.getPromoContent());
            ps.setDate(3, promo.getPromoStartDate());
            ps.setDate(4, promo.getPromoEndDate());
            ps.setInt(5, promo.getPromoStatus());
            ps.setInt(6, promo.getPromoID());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateStatus(PromoDTO promo) {
        String sql = "UPDATE [dbo].[Promo]\n"
                + "   SET \n"
                + "      [promoStatus] = ?\n"
                + " WHERE promoID = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, promo.getPromoStatus());
            ps.setInt(2, promo.getPromoID());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletePromotion(int promoID) {
        String sql = " DELETE FROM [dbo].[Promo]\n"
                + "      WHERE promoID = ? ";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, promoID);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<PromoDTO> getPromoListForManagement(String code, int status, Date startDate, Date endDate, int index, int pageSize){
        
        List<PromoDTO> list= new ArrayList<>();
        String sql = "SELECT * FROM [promo] WHERE 1=1 ";
        
        if(code !=null && !code.isEmpty()){
            sql += " AND promoCode like '%" + code + "%'";
        }
        
        if(status != 3){
            sql +=" AND promoStatus =" + status;
        }
        
        if(startDate != null){
            sql +=" AND ('" + startDate + "' <= promoStartDate) ";
        }
        if(endDate != null){
            sql +=" AND ('" + endDate + "' >= promoEndDate) ";
        }
        if(pageSize != -1){
            sql += " ORDER BY promoStartDate DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";
        }
        
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
             if(pageSize != -1){
                ps.setInt(1, (index - 1) * pageSize);
                ps.setInt(2, pageSize);
            }
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PromoDTO promoDTO = PromoDTO.builder()
                        .promoID(rs.getInt("promoID"))
                        .promoCode(rs.getString("promoCode"))
                        .promoDiscount(rs.getFloat("promoDiscount"))
                        .promoContent(rs.getString("promoContent"))
                        .promoStartDate(rs.getDate("promoStartDate"))
                        .promoEndDate(rs.getDate("promoEndDate"))
                        .promoStatus(rs.getInt("promoStatus"))
                        .build();

                list.add(promoDTO);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
