/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import Config.ConnectDB;
import DAL.PhieuNhap;
import DAL.PhieuTao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Ngoc
 */
public class NPhieuNhapBLL implements GDBLL<PhieuNhap> {
    
    public static NPhieuNhapBLL getInstance() {
        return new NPhieuNhapBLL();
    }

    public int insert(PhieuNhap t) {
        int ketQua = 0;
        try {
           ConnectDB connection= new ConnectDB();
           Connection con = connection.getConnection();
            String sql = "INSERT INTO PhieuNhap (maPhieu, thoiGianTao,maNcc, tongTien) VALUES (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaPhieu());
            pst.setTimestamp(2, t.getThoiGianTao());
            pst.setString(3, t.getNhaCungCap());
            pst.setDouble(4, t.getTongTien());
            ketQua = pst.executeUpdate();
            ConnectDB.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }
    
    public PhieuNhap selectById(String t) {
        PhieuNhap ketQua = null;
        try {
           ConnectDB connection= new ConnectDB();
           Connection con = connection.getConnection();
            String sql = "SELECT * FROM PhieuNhap WHERE maPhieu=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maPhieu = rs.getString("maPhieu");
                Timestamp thoiGianTao = rs.getTimestamp("thoiGianTao");
                String maNcc = rs.getString("maNcc");
                double tongTien = rs.getDouble("tongTien");
                ketQua = new PhieuNhap(maNcc, maPhieu, thoiGianTao, NChiTietPhieuNhapBLL.getInstance().selectAll(maPhieu), tongTien);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<PhieuNhap> selectAll() {
        ArrayList<PhieuNhap> ketQua = new ArrayList<PhieuNhap>();
        try {
            ConnectDB connection= new ConnectDB();
            Connection con = connection.getConnection();
            String sql = "SELECT * FROM PhieuNhap ORDER BY thoiGianTao DESC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maPhieu = rs.getString("maPhieu");
                Timestamp thoiGianTao = rs.getTimestamp("thoiGianTao");
                String maNcc = rs.getString("maNcc");
                double tongTien = rs.getDouble("tongTien");
                PhieuNhap p = new PhieuNhap(maNcc, maPhieu, thoiGianTao, NChiTietPhieuNhapBLL.getInstance().selectAll(maPhieu), tongTien);
                ketQua.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }
    
    public int update(PhieuNhap t) {
        int ketQua = 0;
        try {
            ConnectDB connection= new ConnectDB();
            Connection con = connection.getConnection();
            String sql = "UPDATE PhieuNhap SET maPhieu=?, thoiGianTao=?, maNcc=?, tongTien = ? WHERE maPhieu=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaPhieu());
            pst.setTimestamp(2, t.getThoiGianTao());
            pst.setString(3, t.getNhaCungCap());
            pst.setDouble(4, t.getTongTien());
            ketQua = pst.executeUpdate();
            ConnectDB.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int delete(PhieuNhap t) {
        int ketQua = 0;
        try {
           ConnectDB connection= new ConnectDB();
           Connection con = connection.getConnection();
            String sql = "DELETE FROM PhieuNhap WHERE maPhieu=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaPhieu());
            ketQua = pst.executeUpdate();
            ConnectDB.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

     public ArrayList<PhieuTao> selectAllAccount(String acc) {
        ArrayList<PhieuTao> ketQua = new ArrayList<PhieuTao>();
        try {
           ConnectDB connection= new ConnectDB();
           Connection con = connection.getConnection();
            String sql = "SELECT maPhieu,thoiGianTao,tongTien FROM PhieuNhap  WHERE nguoiTao = ? ORDER BY thoiGianTao DESC";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, acc);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maPhieu = rs.getString("maPhieu");
                Timestamp thoiGianTao = rs.getTimestamp("thoiGianTao");
                double tongTien = rs.getDouble("tongTien");
                PhieuTao p = new PhieuTao(maPhieu, thoiGianTao, NChiTietPhieuNhapBLL.getInstance().selectAll(maPhieu), tongTien);
                ketQua.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<PhieuTao> selectAllP() {
        ArrayList<PhieuTao> ketQua = new ArrayList<PhieuTao>();
        try {
            ConnectDB connection= new ConnectDB();
            Connection con = connection.getConnection();
            String sql = "SELECT maPhieu,thoiGianTao,tongTien FROM PhieuNhap  ORDER BY thoiGianTao DESC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maPhieu = rs.getString("maPhieu");
                Timestamp thoiGianTao = rs.getTimestamp("thoiGianTao");
                double tongTien = rs.getDouble("tongTien");
                PhieuTao p = new PhieuTao(maPhieu, thoiGianTao, NChiTietPhieuNhapBLL.getInstance().selectAll(maPhieu), tongTien);
                ketQua.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }
    
}
