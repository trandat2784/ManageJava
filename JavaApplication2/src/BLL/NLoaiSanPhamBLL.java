/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import Config.ConnectDB;
import DAL.LoaiSanPham;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.sql.Connection; // ✅ đúng package của JDBC
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;
import java.sql.PreparedStatement;
/**
 *
 * @author Ngoc
 */
public class NLoaiSanPhamBLL extends ConnectDB {
    public Vector<LoaiSanPham> docdanhmuc() {
        Vector<LoaiSanPham> vec = new Vector<LoaiSanPham>();
        try {
            ConnectDB connection= new ConnectDB();
            Connection con = connection.getConnection();
            String sql = "select * from loaisanpham";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                LoaiSanPham dm = new LoaiSanPham();
                dm.setMaLoai(rs.getInt(1));
                dm.setTenLoai(rs.getString(2));
                vec.add(dm);
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return vec;
    }
   
   
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
