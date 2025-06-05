/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Ngoc
 */
public class ConnectDB {
<<<<<<< HEAD
    public static Connection con = null;

    // Lấy connection duy nhất (Singleton pattern đơn giản)
    public static Connection getConnection() {
        if (con == null) {
            try {
                String url = "jdbc:sqlserver://localhost:57524;databaseName=csdlqlbh;encrypt=true;trustServerCertificate=true";
                String user = "sa";
                String pass = "ngocngoc";
                con = DriverManager.getConnection(url, user, pass);
                System.out.println("Kết nối thành công!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Kết nối thất bại do lỗi: " + e.getMessage());
            }
        }
        return con;
    }

    // Đóng kết nối khi không cần nữa
    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
                con = null;
                System.out.println("Đóng kết nối thành công");
            } catch (SQLException e) {
                System.out.println("Lỗi đóng kết nối: " + e.getMessage());
            }
        }
    }

    // Test thử kết nối
    public static void main(String[] args) {
        Connection c = ConnectDB.getConnection(); // Gọi đúng
    }
=======
    
    public static Connection getConnection() {
        Connection c = null;
        try {
            // Đăng ký SQL Server Driver với DriverManager
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());

            // Các thông số kết nối
            String url = "jdbc:sqlserver://localhost:1433;databaseName=csdlqlbhnt3;encrypt=false;trustServerCertificate=true";
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

>>>>>>> 790d662ec0dd5215e6006ee15ec37378f9619939
}
