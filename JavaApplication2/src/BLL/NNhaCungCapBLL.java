/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import Config.ConnectDB;
import DAL.NhaCungCap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Ngoc
 */
public class NNhaCungCapBLL implements GDBLL<NhaCungCap> {
    
    public static NNhaCungCapBLL getInstance() {
        return new NNhaCungCapBLL();
    }
    public ArrayList<NhaCungCap> selectAll() {
        ArrayList<NhaCungCap> ketQua = new ArrayList<NhaCungCap>();
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM NhaCungCap";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maNcc = rs.getString("maNcc");
                String tenNcc = rs.getString("tenNcc");
                String email = rs.getString("email");
                String sdt = rs.getString("Sdt");
                String diaChi = rs.getString("diaChi");
                NhaCungCap ncc = new NhaCungCap(maNcc, tenNcc, email, sdt, diaChi);
                ketQua.add(ncc);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }
    
    public NhaCungCap selectById(String t) {
        NhaCungCap ketQua = null;
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM NhaCungCap WHERE maNcc=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maNcc = rs.getString("maNcc");
                String tenNcc = rs.getString("tenNcc");
                String email = rs.getString("email");
                String sdt = rs.getString("Sdt");
                String diaChi = rs.getString("diaChi");
                ketQua = new NhaCungCap(maNcc, tenNcc, email, sdt, diaChi);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int insert(NhaCungCap t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(NhaCungCap t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(NhaCungCap t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
