/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ngoc
 */
public class ConnectDB {
       protected Connection con;
    
    public ConnectDB() {
    try {
        String url = "jdbc:sqlserver://localhost:1433;"
                  + "databaseName=csdlqlbhnt2;"
                  + "user=sa;"
                  + "password=ngocngoc;"
                  + "encrypt=true;trustServerCertificate=true";
        this.con = DriverManager.getConnection(url);
        
        // Kiểm tra và in thông báo kết nối thành công/thất bại
        System.out.println(this.con != null ? "Kết nối thành công!" : "Kết nối thất bại!");
        
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Kết nối thất bại do lỗi: " + e.getMessage());
    }
}
    
    
   // Thêm getter để các lớp con truy cập connection
    public Connection getConnection() {
        return this.con;
    }
    public static void main(String[] args) {
    new ConnectDB(); // Tự động kiểm tra kết nối khi khởi tạo
}
}
