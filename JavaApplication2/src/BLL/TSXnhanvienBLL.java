/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import Config.ConnectDB;
import DAL.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import GUI.QuanLyNhanVien;
import java.util.List;
import DAL.TSXnhanvienDAL;

public class TSXnhanvienBLL {
     private TSXnhanvienDAL dal;
     public TSXnhanvienBLL() {
        dal = new TSXnhanvienDAL();
    }

    // Lấy danh sách tất cả nhân viên
    public List<NhanVien> getAllNhanVien() {
        return dal.getAllNhanVien();
    }
    
    

     // Thêm nhân viên
    public boolean addNhanVien(NhanVien nv) {
        String sql = "INSERT INTO NhanVien (MaNV, TenNV, Email, SDT, DiaChi) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nv.getMaNv());
            stmt.setString(2, nv.getTenNv());
            stmt.setString(3, nv.getEmail());
            stmt.setString(4, nv.getSdt());
            stmt.setString(5, nv.getDiaChi());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Sửa thông tin nhân viên
    public boolean updateNhanVien(NhanVien nv) {
        String sql = "UPDATE NhanVien SET TenNV = ?, Email = ?, SDT = ?, DiaChi = ? WHERE MaNV = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nv.getTenNv());
            stmt.setString(2, nv.getEmail());
            stmt.setString(3, nv.getSdt());
            stmt.setString(4, nv.getDiaChi());
            stmt.setString(5, nv.getMaNv());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa nhân viên
    public boolean deleteNhanVien(String maNv) {
        String sql = "DELETE FROM NhanVien WHERE MaNV = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maNv);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
   
}
    

