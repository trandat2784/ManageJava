/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import Config.ConnectDB;
import DAL.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;

public class SanphamBusct {
    private Connection con;

    // Constructor: khởi tạo kết nối
    public SanphamBusct() {
        con = ConnectDB.getConnection();
    }

    // Lấy danh sách tất cả sản phẩm
    public ArrayList<SanPham> getAllSanPham() {
        ArrayList<SanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM sanpham";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SanPham sp = new SanPham(
                    rs.getString("maSanPham"),
                    rs.getString("tenSanPham"),
                    rs.getInt("maLoai"),
                    rs.getString("maNcc"),
                    rs.getFloat("gia"),
                    rs.getInt("soLuongTon"),
                    rs.getString("duongDanAnh")
                );
                list.add(sp);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<SanPham> getSanPhamByLoai(String tenLoai) {
    ArrayList<SanPham> list = new ArrayList<>();
    Connection con = ConnectDB.getConnection();
    String sql = "SELECT * FROM SanPham s JOIN LoaiSanPham l ON s.maLoai = l.maLoai WHERE l.tenLoai = ?";

    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, tenLoai);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            SanPham sp = new SanPham(
                rs.getString("maSanPham"),
                rs.getString("tenSanPham"),
                rs.getInt("maLoai"),
                rs.getString("maNcc"),
                rs.getFloat("gia"),
                rs.getInt("soLuongTon"),
                rs.getString("duongDanAnh")
            );
            list.add(sp);
        }

        rs.close();
        ps.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return list;
}

    public static String removeDiacritics(String s) {
    s = Normalizer.normalize(s, Normalizer.Form.NFD);
    return s.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
}

    public ArrayList<SanPham> timKiemSanPhamTheoTen(String tenNhap) {
    ArrayList<SanPham> ketQua = new ArrayList<>();
    ArrayList<SanPham> tatCaSanPham = getAllSanPham(); // lấy hết

    String tuKhoa = removeDiacritics(tenNhap).toLowerCase();

    for (SanPham sp : tatCaSanPham) {
        String tenSP = removeDiacritics(sp.getTenSanPham()).toLowerCase();

        if (tenSP.contains(tuKhoa)) {
            ketQua.add(sp);
        }
    }

    return ketQua;
}


    public SanPham getSanPhamById(String maSp) {
    SanPham sp = null;
    String sql = "SELECT * FROM SanPham WHERE maSanPham = ?";

    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maSp);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            sp = new SanPham(
                rs.getString("maSanPham"),
                rs.getString("tenSanPham"),
                rs.getInt("maLoai"),
                rs.getString("maNcc"),
                rs.getFloat("gia"),
                rs.getInt("soLuongTon"),
                rs.getString("duongDanAnh")
            );
        }

        rs.close();
        ps.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return sp;
}

public static int getSoLuongSanPham(String maSP) {
        int soLuong = 0;
        String query = "SELECT SoLuong FROM SanPham WHERE MaSP = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, maSP);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                soLuong = rs.getInt("SoLuong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soLuong;
    }

    // Hàm cập nhật số lượng sản phẩm
    public boolean updateSoLuong(String maSP, int soLuongMoi) {
        String query = "UPDATE SanPham SET SoLuong = ? WHERE MaSP = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, soLuongMoi);
            ps.setString(2, maSP);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

