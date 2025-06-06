/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;
import DAL.SanPham;
import Config.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Windows
 */
public class ProductBLL {
     public List<SanPham> getAllProduct() throws SQLException {
        ConnectDB connection= new ConnectDB();
        Connection conn = connection.getConnection();
        if (conn == null) {
          System.out.println("❌ Kết nối thất bại, conn = null tại HomeDAO");
         return new ArrayList<>(); // tránh lỗi tiếp theo
        }
        String sql = "SELECT * FROM sanpham";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<SanPham> products = new ArrayList<>();

        while (rs.next()) {
            SanPham product = new SanPham(
                rs.getString("masanpham"),
                rs.getString("tensanpham"), 
                rs.getInt("maloai"),
                rs.getString("mancc"),
                rs.getInt("gia"),
                rs.getInt("soluongton"),
                rs.getString("duongdananh")
            );
            products.add(product);
        }
        System.out.println("book size"+products.size());
        rs.close();
        stmt.close();
        conn.close();
        return products;
    }
    
    public boolean insertProduct(SanPham p) throws SQLException {
        ConnectDB connection= new ConnectDB();
        Connection conn = connection.getConnection();
        String sql = "INSERT INTO sanpham (masanpham,tensanpham,maloai,mancc,gia,soluongton,duongdananh) VALUES ( ?, ?, ?, ?, ?, ?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, p.getMaSanPham());
        ps.setString(2, p.getTenSanPham());
        ps.setInt(3, p.getMaLoai());
        ps.setString(4, p.getMaNcc());
        ps.setFloat(5, p.getGia());
        ps.setInt(6, p.getSoLuongTon());
        ps.setString(7, p.getDuongDanAnh());
        int rows = ps.executeUpdate();
        ps.close();
        return rows > 0;
        
    }
    public boolean updateProduct(SanPham p) throws SQLException {
        ConnectDB connection= new ConnectDB();
        Connection conn = connection.getConnection();
        String sql = "Update sanpham Set tensanpham=?,maloai=?,mancc=?,gia=?,soluongton=?,duongdananh=? where masanpham=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(7, p.getMaSanPham());
        ps.setString(1, p.getTenSanPham());
        ps.setInt(2, p.getMaLoai());
        ps.setString(3, p.getMaNcc());
        ps.setFloat(4, p.getGia());
        ps.setInt(5, p.getSoLuongTon());
        ps.setString(6, p.getDuongDanAnh());
        int rows = ps.executeUpdate();
        ps.close();
        return rows > 0;
    }
    
     public boolean deleteProduct(String ProductID) throws SQLException {
        ConnectDB connection= new ConnectDB();
        Connection conn = connection.getConnection();
         String sql = "DELETE FROM sanpham WHERE masanpham=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, ProductID);
        int rows = ps.executeUpdate();
        ps.close();
        conn.close();
        return rows > 0;
     }
     public List<SanPham> searchProductByName(String keyword) throws SQLException {
    ConnectDB connection = new ConnectDB();
    Connection conn = connection.getConnection();
    List<SanPham> products = new ArrayList<>();

    if (conn == null) {
        System.out.println("❌ Kết nối thất bại, conn = null tại searchProductByName");
        return products;
    }

    String sql = "SELECT * FROM sanpham WHERE tensanpham LIKE ?";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, "%" + keyword + "%"); // tìm chứa từ khóa
    ResultSet rs = ps.executeQuery();

    while (rs.next()) {
        SanPham product = new SanPham(
            rs.getString("masanpham"),
            rs.getString("tensanpham"), 
            rs.getInt("maloai"),
            rs.getString("mancc"),
            rs.getInt("gia"),
            rs.getInt("soluongton"),
            rs.getString("duongdananh")
        );
        products.add(product);
    }

    rs.close();
    ps.close();
    conn.close();
    return products;
    }
public String getImagePathById(String productId) throws SQLException {
    ConnectDB connection= new ConnectDB();
        Connection conn = connection.getConnection();
    String sql = "SELECT duongdananh  FROM SanPham WHERE masanpham = ?";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, productId);
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
        return rs.getString("duongdananh");
    }
    return null;
}
}
