/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

/**
 *
 * @author ADMIN
 */

import DAL.HoaDon;
import Config.ConnectDB;
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;

public class HoaDonBLL extends ConnectDB {
 private Connection con;

    public HoaDonBLL() {
        ConnectDB connection= new ConnectDB();
        Connection con = connection.getConnection();
    }

    public ArrayList<HoaDon> getAllHoaDon() {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM hoadon";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDon hd = new HoaDon(
                    rs.getString("maHD"),
                    rs.getString("maKH"),
                    rs.getString("maNV"),
                    rs.getTimestamp("ngayLap").toLocalDateTime()
                );
                list.add(hd);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    
    }
     // Thêm hóa đơn
    public boolean insertHoaDon(HoaDon hd) {
        String sql = "INSERT INTO hoadon (maHD, maKH, maNV, ngayLap) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, hd.getMaHd());
            ps.setString(2, hd.getMaKh());
            ps.setString(3, hd.getMaNv());
            ps.setTimestamp(4, Timestamp.valueOf(hd.getNgayLap()));

            int rows = ps.executeUpdate();
            ps.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xoá hóa đơn
    public boolean deleteHoaDon(String maHD) {
        String sql = "DELETE FROM hoadon WHERE maHD = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maHD);

            int rows = ps.executeUpdate();
            ps.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Sửa hóa đơnp
    public boolean updateHoaDon(HoaDon hd) {
        String sql = "UPDATE hoadon SET maKH = ?, maNV = ?, ngayLap = ? WHERE maHD = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, hd.getMaKh());
            ps.setString(2, hd.getMaNv());
            ps.setTimestamp(3, Timestamp.valueOf(hd.getNgayLap()));
            ps.setString(4, hd.getMaHd());

            int rows = ps.executeUpdate();
            ps.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // Kiểm tra mã khách hàng có tồn tại
public boolean isMaKhExist(String maKH) {
    String sql = "SELECT 1 FROM khachhang WHERE maKH = ?";
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maKH);
        ResultSet rs = ps.executeQuery();
        boolean exists = rs.next();
        rs.close();
        ps.close();
        return exists;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

// Kiểm tra mã nhân viên có tồn tại
public boolean isMaNvExist(String maNV) {
    String sql = "SELECT 1 FROM nhanvien WHERE maNV = ?";
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maNV);
        ResultSet rs = ps.executeQuery();
        boolean exists = rs.next();
        rs.close();
        ps.close();
        return exists;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
}


   
