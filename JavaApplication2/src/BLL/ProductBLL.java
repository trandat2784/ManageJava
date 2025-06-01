/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;
import DAL.ProductDAL;
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
     public List<ProductDAL> getAllProduct() throws SQLException {
        ConnectDB connection= new ConnectDB();
        Connection conn = connection.getConnection();
        if (conn == null) {
          System.out.println("❌ Kết nối thất bại, conn = null tại HomeDAO");
         return new ArrayList<>(); // tránh lỗi tiếp theo
}
        String sql = "SELECT * FROM Product";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<ProductDAL> products = new ArrayList<>();

        while (rs.next()) {
            ProductDAL product = new ProductDAL(
                rs.getInt("ProductID"),
                rs.getString("ProductName"), 
                rs.getInt("CategoryID"),
                rs.getInt("SupplierID"),
                rs.getInt("Price"),
                rs.getInt("StockQuantity"),
                rs.getString("ImagePath")
            );
            products.add(product);
        }
        System.out.println("book size"+products.size());
        rs.close();
        stmt.close();
        conn.close();
        return products;
    }
    
    public boolean insertProduct(ProductDAL p) throws SQLException {
        ConnectDB connection= new ConnectDB();
        Connection conn = connection.getConnection();
        String sql = "INSERT INTO Product  (ProductName,CategoryID,SupplierID,Price,StockQuantity,ImagePath) VALUES ( ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, p.getProductName());
        ps.setInt(2, p.getCategoryID());
        ps.setInt(3, p.getSupplierID());
        ps.setInt(4, p.getPrice());
        ps.setInt(5, p.getStockQuantity());
        ps.setString(6, p.getImagePath());
        int rows = ps.executeUpdate();
        ps.close();
        return rows > 0;
        
    }
    public boolean updateProduct(ProductDAL p) throws SQLException {
        ConnectDB connection= new ConnectDB();
        Connection conn = connection.getConnection();
        String sql = "Update Product Set ProductName=?,CategoryID=?,SupplierID=?,Price=?,StockQuantity=?,ImagePath=? where ProductID=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(7, p.getProductID());
        ps.setString(1, p.getProductName());
        ps.setInt(2, p.getCategoryID());
        ps.setInt(3, p.getSupplierID());
        ps.setInt(4, p.getPrice());
        ps.setInt(5, p.getStockQuantity());
        ps.setString(6, p.getImagePath());
        int rows = ps.executeUpdate();
        ps.close();
        return rows > 0;
    }
     public boolean deleteProduct(int ProductID) throws SQLException {
        ConnectDB connection= new ConnectDB();
        Connection conn = connection.getConnection();
         String sql = "DELETE FROM Product WHERE ProductID=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, ProductID);
        int rows = ps.executeUpdate();
        ps.close();
        conn.close();
        return rows > 0;
     }
}
