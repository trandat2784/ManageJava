/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import Config.ConnectDB;
import DAL.LoaiSanPham;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.sql.Connection; // ✅ đúng package của JDBC


/**
 *
 * @author Ngoc
 */
public class NLoaiSanPhamBLL extends ConnectDB {
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
   
   
}
