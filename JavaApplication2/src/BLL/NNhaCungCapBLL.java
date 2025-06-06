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
import javax.swing.JOptionPane;
import java.sql.SQLException;
/**
 *
 * @author Ngoc
 */
public class NNhaCungCapBLL  {
    
    public static NNhaCungCapBLL getInstance() {
        return new NNhaCungCapBLL();
    }
    public ArrayList<NhaCungCap> selectAll() {
        ArrayList<NhaCungCap> ketQua = new ArrayList<NhaCungCap>();
        try {
               ConnectDB connection= new ConnectDB();
            Connection con = connection.getConnection();
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
                 ConnectDB connection= new ConnectDB();
            Connection con = connection.getConnection();
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
    public boolean insertSupplier(NhaCungCap p) throws SQLException {
        
         ConnectDB connection= new ConnectDB();
        Connection conn = connection.getConnection();
        String sql = "INSERT INTO nhacungcap  (mancc,tenncc,email,sdt,diachi) VALUES ( ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, p.getMaNcc());
        ps.setString(2, p.getTenNcc());
        ps.setString(3, p.getEmail());
        ps.setString(4, p.getSdt());
        ps.setString(5, p.getDiaChi());
        int rows = ps.executeUpdate();
        ps.close();
        return rows > 0;
    }
        
        public boolean updateSupplier(NhaCungCap p) throws SQLException  {
        ConnectDB connection = new ConnectDB();
        Connection conn = connection.getConnection();
        String sql = "Update nhacungcap Set tenncc=?,email=?,sdt=?,diachi=? where mancc=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, p.getTenNcc());
        ps.setString(2, p.getEmail());
        ps.setString(3, p.getSdt());
        ps.setString(4, p.getDiaChi());
        ps.setString(5, p.getMaNcc());
        int rows = ps.executeUpdate();
        ps.close();
        conn.close();  // nhớ đóng kết nối sau khi xong
        return rows > 0;
         
        }
        public boolean deleteSupplier(String maloai) throws SQLException {
        ConnectDB connection= new ConnectDB();
        Connection conn = connection.getConnection();
         String sql = "DELETE FROM nhacungcap WHERE mancc=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, maloai);
        int rows = ps.executeUpdate();
        ps.close();
        conn.close();
        return rows > 0;
        }
      
//    @Override
//    public int insert(NhaCungCap t) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public int update(NhaCungCap t) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public int delete(NhaCungCap t) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
}
