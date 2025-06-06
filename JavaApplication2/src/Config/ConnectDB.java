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
    public static Connection getConnection() {
=======


    

    public  Connection getConnection() {
>>>>>>> 23844c0a77fbe22d344d431826a308c164c0a266
        Connection c = null;
        try {
            // Đăng ký SQL Server Driver với DriverManager
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());

            // Các thông số kết nối
<<<<<<< HEAD
            String url = "jdbc:sqlserver://localhost:1433;databaseName=csdlqlbhnt;encrypt=false;trustServerCertificate=true";
=======
            String url = "jdbc:sqlserver://localhost:1433;databaseName=nhom6csdl;encrypt=true;trustServerCertificate=true";

>>>>>>> 23844c0a77fbe22d344d431826a308c164c0a266
            String userName = "sa";
            String password = "12345678";


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
<<<<<<< HEAD
    }
//public static void main(String[] args) {
//        Connection conn = getConnection();
=======
       
    }}
// public static void main(String[] args) {
//        //Connection conn = getConnection();
//        ConnectDB connection= new ConnectDB();
//           Connection conn = connection.getConnection();
>>>>>>> 23844c0a77fbe22d344d431826a308c164c0a266
//        if (conn != null) {
//            System.out.println("✅ Kết nối thành công!");
//            printInfo(conn);
//            closeConnection(conn);
//        } else {
//            System.out.println("❌ Kết nối thất bại.");
//        }
<<<<<<< HEAD
//    }
}
=======
//        }
//}
>>>>>>> 23844c0a77fbe22d344d431826a308c164c0a266
