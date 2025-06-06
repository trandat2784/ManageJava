/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import Config.ConnectDB;
import DAL.ChiTietPhieuNhap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NChiTietPhieuNhapBLL extends ConnectDB {
    
    public static NChiTietPhieuNhapBLL getInstance() {
        return new NChiTietPhieuNhapBLL();
    }

    public int insert(ChiTietPhieuNhap t) {
        ConnectDB connection= new ConnectDB();
        Connection con = connection.getConnection();
        int ketQua = 0;
        try {
            String sql = "INSERT INTO ChiTietPhieuNhap (maPhieu, maSanPham, soLuong, donGia) VALUES (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaPhieu());
            pst.setString(2, t.getMaSanPham());
            pst.setInt(3, t.getSoLuong());
            pst.setDouble(4, t.getDonGia());
            ketQua = pst.executeUpdate();
            ConnectDB.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }
    
    public ArrayList<ChiTietPhieuNhap> selectAll(String t) {
         ConnectDB connection= new ConnectDB();
         Connection con = connection.getConnection();
        ArrayList<ChiTietPhieuNhap> ketQua = new ArrayList<ChiTietPhieuNhap>();
        try {
            String sql = "SELECT * FROM ChiTietPhieuNhap WHERE maPhieu=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maPhieu = rs.getString("maPhieu");
                String maSanPham = rs.getString("maSanPham");
                int soLuong = rs.getInt("soLuong");
                double donGia = rs.getDouble("donGia");
                ChiTietPhieuNhap ctp = new ChiTietPhieuNhap(maPhieu, maSanPham, soLuong, donGia);
                ketQua.add(ctp);
            }
            ConnectDB.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<ChiTietPhieuNhap> selectAll() {
        ConnectDB connection= new ConnectDB();
        Connection con = connection.getConnection();
        ArrayList<ChiTietPhieuNhap> ketQua = new ArrayList<ChiTietPhieuNhap>();
        try {
            String sql = "SELECT * FROM ChiTietPhieuNhap";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maPhieu = rs.getString("maPhieu");
                String maSanPham = rs.getString("maSanPham");
                int soLuong = rs.getInt("soLuong");
                double donGia = rs.getDouble("donGia");
                ChiTietPhieuNhap ctp = new ChiTietPhieuNhap(maPhieu, maSanPham, soLuong, donGia);
                ketQua.add(ctp);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }
    
    public int update(ChiTietPhieuNhap t) {
        ConnectDB connection= new ConnectDB();
        Connection con = connection.getConnection();
        int ketQua = 0;
        try {
            String sql = "UPDATE ChiTietPhieuNhap SET maPhieu=?, maSanPham=?, soLuong=?, donGia = ?  WHERE maPhieu=? AND maSanPham=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaPhieu());
            pst.setString(2, t.getMaSanPham());
            pst.setInt(3, t.getSoLuong());
            pst.setDouble(4, t.getDonGia());
            ketQua = pst.executeUpdate();
            ConnectDB.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    public int delete(ChiTietPhieuNhap t) {
        ConnectDB connection= new ConnectDB();
        Connection con = connection.getConnection();
        int ketQua = 0;
        try {
            String sql = "DELETE FROM ChiTietPhieuNhap WHERE maPhieu=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaPhieu());
            ketQua = pst.executeUpdate();
            ConnectDB.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }
    
    public ChiTietPhieuNhap selectById(String t) {
        ConnectDB connection= new ConnectDB();
        Connection con = connection.getConnection();
        ChiTietPhieuNhap ketQua = null;
        try {
            String sql = "SELECT * FROM ChiTietPhieuNhap WHERE maPhieu=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maPhieu = rs.getString("maPhieu");
                String maSanPham = rs.getString("maSanPham");
                int soLuong = rs.getInt("soLuong");
                double donGia = rs.getDouble("donGia");
                ketQua = new ChiTietPhieuNhap(maPhieu, maSanPham, soLuong, donGia);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }
}
