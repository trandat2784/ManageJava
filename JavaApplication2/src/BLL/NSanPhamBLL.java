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
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Ngoc
 */
public class NSanPhamBLL extends ConnectDB {
    
    public ArrayList<SanPham> docsanpham() {
        ConnectDB connection= new ConnectDB();
        Connection con = connection.getConnection();
        ArrayList<SanPham> arrsp = new ArrayList<>();
        try (Statement statement = con.createStatement();
             ResultSet result = statement.executeQuery("SELECT * FROM sanpham")) {

            while (result.next()) {
                SanPham sp = new SanPham();
                sp.setMaSanPham(result.getString("masanpham"));
                sp.setTenSanPham(result.getString("tensanpham"));
                sp.setMaLoai(result.getInt("maloai"));
                sp.setMaNcc(result.getString("mancc"));
                sp.setGia(result.getFloat("gia"));
                sp.setSoLuongTon(result.getInt("soluongton"));
                sp.setDuongDanAnh(result.getString("duongdananh"));
                arrsp.add(sp);
            }
        } catch (SQLException ex) {
            System.err.println("Lỗi khi đọc danh sách sản phẩm: " + ex.getMessage());
            ex.printStackTrace();
        }
        return arrsp;
    }
    
    public ArrayList<SanPham> docdanhsachsanphamtheodm(String tenloai) {
ConnectDB connection= new ConnectDB();
        Connection con = connection.getConnection();
        ArrayList<SanPham> dssp = new ArrayList<SanPham>();
        try {
            String sql = "SELECT sp.* FROM sanpham sp " +
                     "JOIN loaisanpham dm ON sp.maloai = dm.maloai " +
                     "WHERE dm.tenloai = ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, tenloai);
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSanPham(rs.getString(1));
                sp.setTenSanPham(rs.getString(2));
                sp.setMaLoai(rs.getInt(3));
                sp.setMaNcc(rs.getString(4));
                sp.setGia(rs.getFloat(5));
                sp.setSoLuongTon(rs.getInt(6));
                sp.setDuongDanAnh(rs.getString(7));
                dssp.add(sp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dssp;
    }
    
    public ArrayList<SanPham> timkiemtheoten(String tensanpham) {
        ConnectDB connection= new ConnectDB();
        Connection con = connection.getConnection();
        ArrayList<SanPham> dssp = new ArrayList<SanPham>();
    try {
        String sql = "SELECT * FROM sanpham where tensanpham like N'" + tensanpham + "%'";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        
        while(rs.next()) {
            SanPham sp = new SanPham();
            sp.setMaSanPham(rs.getString(1));
            sp.setTenSanPham(rs.getString(2));
            sp.setMaLoai(rs.getInt(3));
            sp.setMaNcc(rs.getString(4));
            sp.setGia(rs.getFloat(5));
            sp.setSoLuongTon(rs.getInt(6));
            sp.setDuongDanAnh(rs.getString(7));
            dssp.add(sp);
        }
    } catch(Exception ex) {
        ex.printStackTrace();
    }
    return dssp;
}
       
    public ArrayList<SanPham> selectAllExist() {
        ConnectDB connection= new ConnectDB();
        Connection con = connection.getConnection();
        ArrayList<SanPham> ketQua = new ArrayList<SanPham>();
        try {
            String sql = "SELECT masanpham, tensanpham, maloai, mancc, gia, soluongton, duongdananh FROM sanpham";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maSanPham = rs.getString("maSanPham");
                String tenSanPham = rs.getString("tenSanPham");
                int maLoai = rs.getInt("maLoai");
                String maNcc = rs.getString("maNcc");
                float gia  = rs.getFloat("gia");
                int soLuongTon = rs.getInt("soLuongTon");
                String duongDanAnh = rs.getString("duongDanAnh");
             
                SanPham mt = new SanPham(maSanPham, tenSanPham, maLoai, maNcc, gia, soLuongTon, duongDanAnh);
                ketQua.add(mt);
            }
            ConnectDB.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }    
    
    public static NSanPhamBLL getInstance() {
        return new NSanPhamBLL();
    }
    
    public SanPham TimKiemId(String text) {
        SanPham result = new SanPham();
        ArrayList<SanPham> armt = NSanPhamBLL.getInstance().selectAllExist();
        for (var mt : armt) {
            if (mt.getMaSanPham().toLowerCase().contains(text.toLowerCase())) {
                return mt;
            }
        }
        return null;
    }
    
    public SanPham selectById(String t) {
        ConnectDB connection= new ConnectDB();
        Connection con = connection.getConnection();
        SanPham ketQua = null;
        try {
            String sql = "SELECT masanpham, tensanpham, maloai, mancc, gia, soluongton, duongdananh FROM SanPham WHERE maSanPham = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maSanPham = rs.getString("maSanPham");
                String tenSanPham = rs.getString("tenSanPham");
                int maLoai = rs.getInt("maLoai");
                String maNcc = rs.getString("maNcc");
                float gia  = rs.getFloat("gia");
                int soLuongTon = rs.getInt("soLuongTon");
                String duongDanAnh = rs.getString("duongDanAnh");
                ketQua = new SanPham(maSanPham, tenSanPham, maLoai, maNcc, gia, soLuongTon, duongDanAnh);
            }
            ConnectDB.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }
    public int updateSoLuong(String maSanPham, int soLuongTon) {
        ConnectDB connection= new ConnectDB();
        Connection con = connection.getConnection();
        int ketQua = 0;
        try {
            String sql = "UPDATE SanPham SET soLuongTon=? WHERE maSanPham=? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, soLuongTon);
            pst.setString(2, maSanPham);
            ketQua = pst.executeUpdate();
            ConnectDB.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }
}
