/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.sql.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import DAL.NhanVien;
//public class TSXnhanvienDAL {
//     
//     
//    public TSXnhanvienDAL() {
//        // Khởi tạo kết nối CSDL ở đây
//    
////      try {
////        // Thay đổi phù hợp với cấu hình SQL Server của bạn
////        String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=csdlqlbhnt";
////        String user = "sa";
////        String password = "123456";
////        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
////        conn = DriverManager.getConnection(url, user, password);
////        System.out.println("Kết nối CSDL thành công: " + conn);
////        } catch (Exception e) {
////            System.out.println("Lỗi kết nối CSDL: " + e.getMessage());
////       e.printStackTrace();
////           
////        }
//    }
//
//     public List<NhanVien> getAllNhanVien() {
//        List<NhanVien> list = new ArrayList<>();
//        String sql = "SELECT * FROM NhanVien";
//
//        try (Connection conn = ConnectDB.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//
//            while (rs.next()) {
//                NhanVien nv = new NhanVien(
//                    rs.getString("MaNV"),
//                    rs.getString("TenNV"),
//                    rs.getString("Email"),
//                    rs.getString("SDT"),
//                    rs.getString("DiaChi")
//                );
//                list.add(nv);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return list;
//    }
//    
//}




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TSXnhanvienDAL {

    // Constructor không cần khởi tạo kết nối riêng nữa
    public TSXnhanvienDAL() {
    }

    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien";

        try (Connection conn = Config.ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                NhanVien nv = new NhanVien(
                    rs.getString("MaNV"),
                    rs.getString("TenNV"),
                    rs.getString("Email"),
                    rs.getString("SDT"),
                    rs.getString("DiaChi")
                );
                list.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}