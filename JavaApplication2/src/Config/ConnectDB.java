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


    

    public  Connection getConnection() {
        Connection c = null;
        try {
            // Đăng ký SQL Server Driver với DriverManager
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());

            // Các thông số kết nối
            String url = "jdbc:sqlserver://localhost:1433;databaseName=csdlqlbhnt3;encrypt=true;trustServerCertificate=true";

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
// public static void main(String[] args) {
//        Connection conn = getConnection();
//        if (conn != null) {
//            System.out.println("✅ Kết nối thành công!");
//            printInfo(conn);
//            closeConnection(conn);
//        } else {
//            System.out.println("❌ Kết nối thất bại.");
//        }
//        }
}
