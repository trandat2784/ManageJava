/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.LoaiSanPham;
import Config.ConnectDB;
import DAL.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultListModel;

/**
 *
 * @author Ngoc
 */
public class LoaiSanPhamBLL extends ConnectDB {
    
   public Vector<LoaiSanPham> docdanhmuc() {
        Vector<LoaiSanPham> vec = new Vector<LoaiSanPham>();
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "select * from loaisanpham";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                LoaiSanPham dm = new LoaiSanPham();
                dm.setMaLoai(rs.getInt(1));
                dm.setTenLoai(rs.getString(2));
                vec.add(dm);
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return vec;
    }
   
   public String timMaDanhMucTheoTen(String tenloai) {
       Connection con = ConnectDB.getConnection();
    String ma = null;
    try {
        String sql = "SELECT maloai FROM loaisanpham WHERE tenloai = ?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, tenloai);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            ma = rs.getString("maloai");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return ma;
}
}
