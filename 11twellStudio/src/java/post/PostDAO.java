/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author somethinn
 */
public class PostDAO {

    public List<PostDTO> getPostList() {
        String sql = "SELECT * FROM [Post] order by postID desc ";

        //
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<PostDTO> list = new ArrayList<>();
            while (rs.next()) {
                PostDTO post = new PostDTO(rs.getInt("postID"),
                        rs.getString("postTitle"),
                        rs.getString("postImage"),
                        rs.getString("postContent"),
                        rs.getInt("postStatus"));
                list.add(post);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public PostDTO getPostByID(int postID) {
        String sql = " SELECT * FROM Post where postID = ? ";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, postID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                postID = rs.getInt("postID");
                String postTitle = rs.getString("postTitle");
                String postImage = rs.getString("postImage");
                String postContent = rs.getString("postContent");
                int postStatus = rs.getInt("postStatus");

                PostDTO post = new PostDTO(postID, postTitle, postImage, postContent, postStatus);
                return post;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void insertPost(PostDTO p) {
        String sql = "INSERT INTO [dbo].[Post]\n"
                + "           ([postTitle]\n"
                + "           ,[postImage]\n"
                + "           ,[postContent]\n"
                + "           ,[postStatus])\n"
                + "     VALUES (?, ?, ?, ?)";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, p.getPostTitle());
            ps.setString(2, p.getPostImage());
            ps.setString(3, p.getPostContent());
            ps.setInt(4, p.getPostStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deletePost(int postID) {
        String sql = " DELETE FROM [dbo].[Post]\n"
                + "      WHERE postID = ? ";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, postID);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updatePost(PostDTO post) {
        String sql = " UPDATE [dbo].[Post]\n"
                + "   SET [postTitle] = ?\n"
                + "      ,[postImage] = ?\n"
                + "      ,[postContent] = ?\n"
                + "      ,[postStatus] = ?\n"
                + " WHERE [postID] = ? ";
        
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, post.getPostTitle());
            ps.setString(2, post.getPostImage());
            ps.setString(3, post.getPostContent());
            ps.setInt(4, post.getPostStatus());
            ps.setInt(5, post.getPostID());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateStatus(PostDTO post) {
        String sql = " UPDATE [dbo].[Post]\n"
                + "   SET [postStatus] = ?\n"
                + " WHERE [postID] = ? ";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, post.getPostStatus());
            ps.setInt(2, post.getPostID());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    /*
    private int postID;
    private String postTitle;
    private String postImage;
    private String postContent;
    private int postStatus;

    */
    public List<PostDTO> searchPostForManagement (String title, int status, int index , int pageSize){
        List<PostDTO> list= new ArrayList<>();
        String sql = "SELECT * FROM [post] WHERE 1=1 ";
        
        if(title != null && !title.isEmpty()){
            sql = sql + " AND [postTitle] LIKE  '%"+ title + "%' ";
        }
        
        if(status !=3){
            sql += " AND [postStatus] = " + status;
        }
        if(pageSize != -1){
            sql += " ORDER BY postID DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";
        }
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            if(pageSize != -1){
                ps.setInt(1, (index - 1) * pageSize);
                ps.setInt(2, pageSize);
            }
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                PostDTO post = new PostDTO(rs.getInt("postID"),
                        rs.getString("postTitle"),
                        rs.getString("postImage"),
                        rs.getString("postContent"),
                        rs.getInt("postStatus"));
                list.add(post);
            }
            return list;
            
        } catch (SQLException e) {
            e.getMessage();
        }

        return list;
    }

    /*
    public static void main(String[] args) {
        PostDAO dao = new PostDAO();
        List<PostDTO> list= new ArrayList<>();
        list = dao.getPostList();
        for(PostDTO post : list){
            System.out.println(post);
        }
        
        
    }
     */
}
