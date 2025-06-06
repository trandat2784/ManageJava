/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import Config.ConnectDB;
import DAL.LoaiSanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Windows
 */
public class loaisanphamBLL extends ConnectDB{
    public List<LoaiSanPham> getAllCategory() throws SQLException {
        ConnectDB connection= new ConnectDB();
        Connection conn = connection.getConnection();
        if (conn == null) {
          System.out.println("❌ Kết nối thất bại, conn = null tại HomeDAO");
         return new ArrayList<>(); // tránh lỗi tiếp theo
}
        String sql = "SELECT * FROM loaisanpham";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<LoaiSanPham> categorys = new ArrayList<>();

        while (rs.next()) {
            LoaiSanPham category = new LoaiSanPham(
                rs.getInt("maloai"),
                rs.getString("tenloai")
                
            );
            categorys.add(category);
        }
        System.out.println("categorys size"+categorys.size());
        rs.close();
        stmt.close();
        conn.close();
        return categorys;
    }
    
    public boolean insertCategory(LoaiSanPham p) throws SQLException {
        ConnectDB connection= new ConnectDB();
        Connection conn = connection.getConnection();
        String sql = "INSERT INTO loaisanpham  (tenloai) VALUES (  ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, p.getTenLoai());
        int rows = ps.executeUpdate();
        ps.close();
        return rows > 0;
        
    }
    public boolean updateCategory(LoaiSanPham p) throws SQLException {
        ConnectDB connection= new ConnectDB();
        Connection conn = connection.getConnection();
        String sql = "Update loaisanpham Set tenloai=? where maloai=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(2, p.getMaLoai());
        ps.setString(1, p.getTenLoai());
        int rows = ps.executeUpdate();
        ps.close();
        return rows > 0;
    }
     public boolean deleteCategory(int maloai) throws SQLException {
        ConnectDB connection= new ConnectDB();
        Connection conn = connection.getConnection();
         String sql = "DELETE FROM loaisanpham WHERE maloai=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, maloai);
        int rows = ps.executeUpdate();
        ps.close();
        conn.close();
        return rows > 0;
     }
     

    
    
    
}
