/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Ngoc
 */
public class ConnectDB {

//    public static void closeConnection(Connection con) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//       protected Connection con;
//    
//    public ConnectDB() {
//    try {
//        String url = "jdbc:sqlserver://localhost:1433;"
//                  + "databaseName=csdlqlbhnt2;"
//                  + "user=sa;"
//                  + "password=ngocngoc;"
//                  + "encrypt=true;trustServerCertificate=true";
//        this.con = DriverManager.getConnection(url);
//        
//        // Kiểm tra và in thông báo kết nối thành công/thất bại
//        System.out.println(this.con != null ? "Kết nối thành công!" : "Kết nối thất bại!");
//        
//    } catch (SQLException e) {
//        e.printStackTrace();
//        System.out.println("Kết nối thất bại do lỗi: " + e.getMessage());
//    }
//}
//    
//    
//   // Thêm getter để các lớp con truy cập connection
//    public Connection getConnection() {
//        return this.con;
//    }
//    public static void main(String[] args) {
//    new ConnectDB(); // Tự động kiểm tra kết nối khi khởi tạo
//}
    
    public static Connection getConnection() {
        Connection c = null;
        try {
            // Đăng ký SQL Server Driver với DriverManager
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());

            // Các thông số kết nối
            String url = "jdbc:sqlserver://localhost:1433;databaseName=csdlqlbhnt2;encrypt=false;trustServerCertificate=true";
            String userName = "sa";
            String password = "ngocngoc";


            // Tạo kết nối
            c = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            // Xử lý ngoại lệ
            JOptionPane.showMessageDialog(null, "Không thể kết nối đến cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return c;
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printInfo(Connection c) {
        try {
            if (c != null) {
                DatabaseMetaData mtdt = c.getMetaData();
                System.out.println(mtdt.getDatabaseProductName());
                System.out.println(mtdt.getDatabaseProductVersion());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
