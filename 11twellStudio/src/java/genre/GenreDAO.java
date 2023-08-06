/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author Nguyen Thanh
 */
public class GenreDAO {
    public List<GenreDTO> genreList() {
        String sql = "select * from Genre";
        List<GenreDTO> list = new ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()) {
                int genreID = rs.getInt("genreID");
                String genreName = rs.getString("genreName");
                GenreDTO genre = new GenreDTO(genreID, genreName);
                list.add(genre);
            }
            
            return list;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
