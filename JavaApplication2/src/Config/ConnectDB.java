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
}
