/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie;

import java.sql.Connection;
import java.sql.Date;
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
public class MovieDAO extends DBUtils {

    public List<MovieDTO> movieList() {
        List<MovieDTO> list = new ArrayList<>();
        String sql = " SELECT * FROM Movie m join Genre g ON m.genreID = g.genreID where movieStatus = 1 or movieStatus = 2\n";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                MovieDTO movie = new MovieDTO();

                movie.setMovieID(rs.getInt("movieID"));
                movie.setGenre(rs.getString("genreName"));
                movie.setMovieTitle(rs.getString("movieTitle"));
                movie.setMovieImage(rs.getString("movieImage"));
                movie.setMoviePremiere(rs.getDate("moviePremiere"));
                movie.setMovieDirector(rs.getString("movieDirector"));
                movie.setMovieTime(rs.getInt("movieTime"));
                movie.setMovieImageSlide(rs.getString("movieImageSlide"));
                movie.setMovieDescription(rs.getString("movieDescription"));
                movie.setMovieTrailerLink(rs.getString("movieTrailerLink"));
                movie.setMovieStatus(rs.getInt("movieStatus"));
                movie.setMovieSliderStatus(rs.getInt("movieSliderStatus"));

                list.add(movie);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
//        } finally {
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//                if (statement != null) {
//                    statement.close();
//                }
//                
//            } catch (SQLException e) {
//                e.printStackTrace();
//                throw new RuntimeException(e);
//            }
        }
        return null;
    }
    
     

    public List<MovieDTO> movieListWithSliderStatus() {
        List<MovieDTO> list = new ArrayList<>();
        String sql = " SELECT * FROM Movie m join Genre g ON m.genreID = g.genreID where movieSliderStatus = 1\n";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                MovieDTO movie = new MovieDTO();

                movie.setMovieID(rs.getInt("movieID"));
                movie.setGenre(rs.getString("genreName"));
                movie.setMovieTitle(rs.getString("movieTitle"));
                movie.setMovieImage(rs.getString("movieImage"));
                movie.setMoviePremiere(rs.getDate("moviePremiere"));
                movie.setMovieDirector(rs.getString("movieDirector"));
                movie.setMovieTime(rs.getInt("movieTime"));
                movie.setMovieImageSlide(rs.getString("movieImageSlide"));
                movie.setMovieDescription(rs.getString("movieDescription"));
                movie.setMovieTrailerLink(rs.getString("movieTrailerLink"));
                movie.setMovieStatus(rs.getInt("movieStatus"));
                movie.setMovieSliderStatus(rs.getInt("movieSliderStatus"));

                list.add(movie);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
//        } finally {
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//                if (statement != null) {
//                    statement.close();
//                }
//                
//            } catch (SQLException e) {
//                e.printStackTrace();
//                throw new RuntimeException(e);
//            }
        }
        return null;
    }

    public List<MovieDTO> movieList(int status, int index, int pageSize) {
        List<MovieDTO> list = new ArrayList<>();
        String sql = " SELECT * FROM Movie m join Genre g ON m.genreID = g.genreID where 1=1 ";
        if (status == 0) {
            sql += " AND movieSliderStatus = 0 ";
        } else if (status != 3) {
            sql += " AND movieSliderStatus = " + status;
        }

        if (pageSize != -1) {
            sql += " ORDER BY movieID DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";
        }

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            if (pageSize != -1) {
                statement.setInt(1, (index - 1) * pageSize);
                statement.setInt(2, pageSize);
            }
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                MovieDTO movie = new MovieDTO();

                movie.setMovieID(rs.getInt("movieID"));
                movie.setGenre(rs.getString("genreName"));
                movie.setMovieTitle(rs.getString("movieTitle"));
                movie.setMovieImage(rs.getString("movieImage"));
                movie.setMoviePremiere(rs.getDate("moviePremiere"));
                movie.setMovieDirector(rs.getString("movieDirector"));
                movie.setMovieTime(rs.getInt("movieTime"));
                movie.setMovieImageSlide(rs.getString("movieImageSlide"));
                movie.setMovieDescription(rs.getString("movieDescription"));
                movie.setMovieTrailerLink(rs.getString("movieTrailerLink"));
                movie.setMovieStatus(rs.getInt("movieStatus"));
                movie.setMovieSliderStatus(rs.getInt("movieSliderStatus"));

                list.add(movie);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MovieDTO> movieListForManagement() {
        List<MovieDTO> list = new ArrayList<>();
        String sql = " SELECT * FROM Movie m join Genre g ON m.genreID = g.genreID ORDER BY movieID DESC \n";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                MovieDTO movie = new MovieDTO();

                movie.setMovieID(rs.getInt("movieID"));
                movie.setGenre(rs.getString("genreName"));
                movie.setMovieTitle(rs.getString("movieTitle"));
                movie.setMovieImage(rs.getString("movieImage"));
                movie.setMoviePremiere(rs.getDate("moviePremiere"));
                movie.setMovieDirector(rs.getString("movieDirector"));
                movie.setMovieTime(rs.getInt("movieTime"));
                movie.setMovieImageSlide(rs.getString("movieImageSlide"));
                movie.setMovieDescription(rs.getString("movieDescription"));
                movie.setMovieTrailerLink(rs.getString("movieTrailerLink"));
                movie.setMovieStatus(rs.getInt("movieStatus"));

                list.add(movie);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
//        } finally {
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//                if (statement != null) {
//                    statement.close();
//                }
//                
//            } catch (SQLException e) {
//                e.printStackTrace();
//                throw new RuntimeException(e);
//            }
        }
        return null;
    }

//    public List<MovieDTO> movieListNew(String keyword) {
//        List<MovieDTO> list = new ArrayList<>();
//        String sql = " SELECT * FROM Movie m join Genre g ON m.genreID = g.genreID ";
//        
//        String where = "";
//        String whereJoinWord = " where ";
//        
//        if (keyword != null && !keyword.trim().isEmpty()){
//            where += whereJoinWord;
//            where += " (m.movieTitle LIKE ?)";
////            whereJoinWord = " and ";  
//        }
//        
//        try {
//            Connection conn = DBUtils.getConnection();
//            PreparedStatement ps = conn.prepareStatement(sql);
//            
//            int index = 1;
//            if (keyword != null && !keyword.trim().isEmpty()){
//                ps.setString(index, "%" + keyword + "%");
//                index ++;
//            }   
//            
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()){
//                    MovieDTO movie = new MovieDTO();
//
//                movie.setMovieID(rs.getInt("movieID"));
//                movie.setGenre(rs.getString("genreName"));
//                movie.setMovieTitle(rs.getString("movieTitle"));
//                movie.setMovieImage(rs.getString("movieImage"));
//                movie.setMoviePremiere(rs.getDate("moviePremiere"));
//                movie.setMovieDirector(rs.getString("movieDirector"));
//                movie.setMovieTime(rs.getInt("movieTime"));
//                movie.setMovieImageSlide(rs.getString("movieImageSlide"));
//                movie.setMovieDescription(rs.getString("movieDescription"));
//                movie.setMovieTrailerLink(rs.getString("movieTrailerLink"));
//                movie.setMovieStatus(rs.getInt("movieStatus"));
//
//                list.add(movie);
//            }
//            
//            return list;
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return null;
//    }
//    public List<MovieDTO> search(String name, int statusID, int genreID, Date premiere) {
//        List<MovieDTO> list = new ArrayList<>();
//        String sql = " SELECT * FROM Movie m join Genre g ON m.genreID = g.genreID where 1 = 1";
//        
//        if(name != null && !name.equals("")) {
//            sql += " and m.movieTitle like '%" + name + "%'";
//        }
//        
//        if(statusID == 0 || statusID == 1 || statusID == 2) {
//            sql += " and m.movieStatus = "  + statusID ;
//        }
//        
//        if(genreID != 0) {
//            sql += " and m.genreID = "  + genreID ;
//        }
//        
//        if(premiere != null) {
//            sql += " and m.moviePremiere = "  + premiere ;
//        }
//        
//        try {
//            Connection conn = DBUtils.getConnection();
//            PreparedStatement statement = conn.prepareStatement(sql);
//            ResultSet rs = statement.executeQuery();
//
//            while (rs.next()) {
//                MovieDTO movie = new MovieDTO();
//
//                movie.setMovieID(rs.getInt("movieID"));
//                movie.setGenre(rs.getString("genreName"));
//                movie.setMovieTitle(rs.getString("movieTitle"));
//                movie.setMovieImage(rs.getString("movieImage"));
//                movie.setMoviePremiere(rs.getDate("moviePremiere"));
//                movie.setMovieDirector(rs.getString("movieDirector"));
//                movie.setMovieTime(rs.getInt("movieTime"));
//                movie.setMovieImageSlide(rs.getString("movieImageSlide"));
//                movie.setMovieDescription(rs.getString("movieDescription"));
//                movie.setMovieTrailerLink(rs.getString("movieTrailerLink"));
//                movie.setMovieStatus(rs.getInt("movieStatus"));
//
//                list.add(movie);
//                return list;
//            }
//        } catch (Exception e) {
//        }
//        return null;
//    }
    public MovieDTO getMovieByID(int movieID) {
        String sql = " SELECT * FROM Movie m JOIN Genre g ON m.genreID = g.genreID WHERE movieID = ? ";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, movieID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                MovieDTO movie = new MovieDTO();

                movie.setMovieID(rs.getInt("movieID"));
                movie.setGenre(rs.getString("genreName"));
                movie.setMovieTitle(rs.getString("movieTitle"));
                movie.setMovieImage(rs.getString("movieImage"));
                movie.setMoviePremiere(rs.getDate("moviePremiere"));
                movie.setMovieDirector(rs.getString("movieDirector"));
                movie.setMovieTime(rs.getInt("movieTime"));
                movie.setMovieImageSlide(rs.getString("movieImageSlide"));
                movie.setMovieDescription(rs.getString("movieDescription"));
                movie.setMovieTrailerLink(rs.getString("movieTrailerLink"));
                movie.setMovieNumberOfLike(rs.getInt("movieNumberOfLike"));

                movie.setMovieStatus(rs.getInt("movieStatus"));

                return movie;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MovieDTO> searchMovieForManagement(String name, int statusID, int genreID, Date premiere, int index, int pageSize) {
        List<MovieDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Movie m join Genre g ON m.genreID = g.genreID where 1 = 1";

        if (name != null && !name.isEmpty()) {
            sql += " AND m.movieTitle LIKE '%" + name + "%'";
        }

        if (statusID != 3) {
            sql += " AND m.movieStatus = " + statusID;
        }

        if (genreID != 0) {
            sql += " AND m.genreID = " + genreID;
        }

        if (premiere != null) {
            sql += " AND m.moviePremiere =  '" + premiere + "'";
        }
        if (pageSize != -1) // pageSize = -1 : means there's no need to paging
        {
            sql += " ORDER BY m.movieID DESC " + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";
        }

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            if (pageSize != -1) {
                statement.setInt(1, (index - 1) * pageSize);
                statement.setInt(2, pageSize);
            }

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                MovieDTO movie = new MovieDTO();

                movie.setMovieID(rs.getInt("movieID"));
                movie.setGenre(rs.getString("genreName"));
                movie.setMovieTitle(rs.getString("movieTitle"));
                movie.setMovieImage(rs.getString("movieImage"));
                movie.setMoviePremiere(rs.getDate("moviePremiere"));
                movie.setMovieDirector(rs.getString("movieDirector"));
                movie.setMovieTime(rs.getInt("movieTime"));
                movie.setMovieImageSlide(rs.getString("movieImageSlide"));
                movie.setMovieDescription(rs.getString("movieDescription"));
                movie.setMovieTrailerLink(rs.getString("movieTrailerLink"));

                movie.setMovieStatus(rs.getInt("movieStatus"));

                list.add(movie);

            }
            return list;
        } catch (Exception e) {
        }

        return list;
    }

    public List<MovieDTO> searchMovieList(String search, int index, int pageSize, String sortBy, int movieStatus) {
        List<MovieDTO> list = new ArrayList<>();

        String sql = " SELECT * FROM Movie m join Genre g ON m.genreID = g.genreID \n"
                + " WHERE m.movieTitle LIKE ?  AND m.movieStatus = ? \n"
                + " ORDER BY m.moviePremiere " + sortBy + " \n"
                + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, "%" + search + "%");
            statement.setInt(2, movieStatus);
            statement.setInt(3, (index - 1) * pageSize);
            statement.setInt(4, pageSize);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                MovieDTO movie = new MovieDTO();

                movie.setMovieID(rs.getInt("movieID"));
                movie.setGenre(rs.getString("genreName"));
                movie.setMovieTitle(rs.getString("movieTitle"));
                movie.setMoviePremiere(rs.getDate("moviePremiere"));
                movie.setMovieDirector(rs.getString("movieDirector"));
                movie.setMovieTime(rs.getInt("movieTime"));
                movie.setMovieStatus(rs.getInt("movieStatus"));
                movie.setMovieImage(rs.getString("movieImage"));
                movie.setMovieNumberOfLike(rs.getInt("movieNumberOfLike"));

                list.add(movie);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
//          finally {
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//                if (statement != null) {
//                    statement.close();
//                }
//                
//            } catch (SQLException e) {
//                e.printStackTrace();
//                throw new RuntimeException(e);
//            }
//        }
        return null;

    }

    public List<MovieDTO> sortMovieBystatus(int status) {
        List<MovieDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Movie where movieStatus = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, status);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                MovieDTO movie = new MovieDTO();

                movie.setMovieID(rs.getInt("movieID"));
                movie.setGenre(rs.getString("genreName"));
                movie.setMovieTitle(rs.getString("movieTitle"));
                movie.setMoviePremiere(rs.getDate("moviePremiere"));
                movie.setMovieDirector(rs.getString("movieDirector"));
                movie.setMovieTime(rs.getInt("movieTime"));
                movie.setMovieStatus(rs.getInt("movieStatus"));

                list.add(movie);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public int getTotalRowsCondition(String search, int status) {
        String sql = " SELECT COUNT(*) FROM Movie m join Genre g ON m.genreID = g.genreID \n"
                + " WHERE m.movieTitle LIKE ?  AND m.movieStatus = ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, "%" + search + "%");
            st.setInt(2, status);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    public int countTotalMovie() {
        String sql = "select COUNT(*) from [Movie] m ";
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

    public void insertMovie(MovieDTO m, int genre) {
        String sql = "INSERT INTO [dbo].[Movie]\n"
                + "           ([genreID]\n"
                + "           ,[movieTitle]\n"
                + "           ,[movieImage]\n"
                + "           ,[moviePremiere]\n"
                + "           ,[movieDirector]\n"
                + "           ,[movieTime]\n"
                + "           ,[movieImageSlide]\n"
                + "           ,[movieDescription]\n"
                + "           ,[movieTrailerLink]\n"
                + "           ,[movieStatus])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, genre);
            stm.setString(2, m.getMovieTitle());
            stm.setString(3, m.getMovieImage());
            stm.setDate(4, m.getMoviePremiere());
            stm.setString(5, m.getMovieDirector());
            stm.setInt(6, m.getMovieTime());
            stm.setString(7, m.getMovieImageSlide());
            stm.setString(8, m.getMovieDescription());
            stm.setString(9, m.getMovieTrailerLink());
            stm.setInt(10, m.getMovieStatus());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    
    public boolean deleteMovie(int movieID) {

        String sql = " DELETE FROM [dbo].[Movie]\n"
                + "      WHERE movieID = ? ";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, movieID);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public void updateMovie(MovieDTO m, int genre) {
//        String sql = "UPDATE [dbo].[Movie]\n"
//                + "   SET [genreID] = ?, [movieTitle] = ?, [movieImage] = ?, [moviePremiere] = ?, [movieDirector] = ? ,[movieTime] = ?, [movieImageSlide] = ?, [movieDescription] = ?, [movieTrailerLink] = ?, [movieStatus] = ? WHERE movieID = ? ";
        String sql = " UPDATE [dbo].[Movie]\n"
                + "   SET [genreID] = ?\n"
                + "      ,[movieTitle] = ?\n"
                + "      ,[movieImage] = ?\n"
                + "      ,[moviePremiere] = ?\n"
                + "      ,[movieDirector] = ?\n"
                + "      ,[movieTime] = ?\n"
                + "      ,[movieImageSlide] = ?\n"
                + "      ,[movieDescription] = ?\n"
                + "      ,[movieTrailerLink] = ?\n"
                + "      ,[movieStatus] = ?\n"
                + " WHERE movieID = ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, genre);
            st.setString(2, m.getMovieTitle());
            st.setString(3, m.getMovieImage());
            st.setDate(4, m.getMoviePremiere());
            st.setString(5, m.getMovieDirector());
            st.setInt(6, m.getMovieTime());
            st.setString(7, m.getMovieImageSlide());
            st.setString(8, m.getMovieDescription());
            st.setString(9, m.getMovieTrailerLink());
            st.setInt(10, m.getMovieStatus());
            st.setInt(11, m.getMovieID());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateSliderImage(MovieDTO movie) {
        String sql = " UPDATE [dbo].[Movie]\n"
                + "   SET [movieImageSlide] = ?\n"
                + " WHERE [movieID] = ? ";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, movie.getMovieImageSlide());
            st.setInt(2, movie.getMovieID());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateStatus(MovieDTO m) {
        String sql = "UPDATE [dbo].[Movie]\n"
                + "   SET [movieStatus] = ?\n"
                + " WHERE movieID = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, m.getMovieStatus());
            st.setInt(2, m.getMovieID());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateSliderStatus(MovieDTO m) {
        String sql = "UPDATE [dbo].[Movie]\n"
                + "   SET [movieSliderStatus] = ?\n"
                + " WHERE movieID = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, m.getMovieSliderStatus());
            st.setInt(2, m.getMovieID());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insertMovieImage(String movieImage, String movieID) {
        try {

            String sql = " UPDATE Movie\n"
                    + " SET movieImage = ? \n"
                    + " WHERE movieID = ? ";
            Connection conn = DBUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, movieImage);
            stm.setString(2, movieID);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Integer> getListOfTop3OrderMovie() {
        String sql = "SELECT TOP 3 s.movieID ,COUNT(s.movieID) AS [NumberOfOrder] FROM [Order] o JOIN Show s ON s.showID = o.showID GROUP BY s.movieID ORDER BY NumberOfOrder DESC";
        List<Integer> list = new ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("movieID"));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public int getNumberOfOrderByMovieID(int movieID) {

        String sql = "SELECT COUNT(s.movieID) AS [NumberOfOrder] FROM [Order] o JOIN Show s ON s.showID = o.showID GROUP BY s.movieID HAVING s.movieID = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, movieID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public void updateMovieNumberOfLike(MovieDTO movie) {
        String sql = "UPDATE [dbo].[Movie]\n"
                + "SET [movieNumberOfLike] = (SELECT COUNT(*) FROM NumberOfLikeDetail WHERE movieID = ?)\n"
                + "WHERE movieID = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, movie.getMovieID());
            st.setInt(2, movie.getMovieID());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean insertMovieNumberOfLikeDetail(int movieID, int userID) {
        String sql = "INSERT INTO [dbo].[NumberOfLikeDetail]\n"
                + "           ([userID]\n"
                + "           ,[movieID])\n"
                + "     VALUES\n"
                + "           (?, ?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userID);
            st.setInt(2, movieID);
            st.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkMovieNumberOfLikeDetailExist(int movieID, int userID) {
        String sql = "SELECT [userID]\n"
                + "      ,[movieID]\n"
                + "  FROM [dbo].[NumberOfLikeDetail] WHERE [userID] = ? AND [movieID] = ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userID);
            st.setInt(2, movieID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteMovieNumberOfLikeDetail(int movieID, int userID) {
        String sql = "DELETE FROM [dbo].[NumberOfLikeDetail]\n"
                + "      WHERE userID = ? AND movieID = ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userID);
            st.setInt(2, movieID);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        MovieDAO dao = new MovieDAO();

        List<MovieDTO> list = dao.movieList();
        List<MovieDTO> movieList = dao.searchMovieForManagement("", 3, 0, null, 1, -1);
        System.out.println(movieList.size());
//        dao.updateMovie(movie, 0);
//        List<MovieDTO> list = dao.searchMovieList("", 1, 12);
//        for (MovieDTO movieDTO : list) {
//            System.out.println(movieDTO);
//        }
//        System.out.println(dao.getTotalRowsCondition(""));

    }
}
