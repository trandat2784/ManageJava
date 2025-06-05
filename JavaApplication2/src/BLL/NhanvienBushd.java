package BLL;

import DAL.NhanVien;
import Config.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanvienBushd extends ConnectDB {
    
    private Connection con;
    
    public NhanvienBushd() {
        try {
            con = getConnection(); // Sửa lại từ ConnectDB.getConnection() sang getConnection()
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT maNV, tenNV FROM nhanvien ORDER BY tenNV";
        
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNv(rs.getString("maNV")); // Sửa setMaNv thành setMaNV
                nv.setTenNv(rs.getString("tenNV")); // Sửa setTenNv thành setTenNV
                list.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public NhanVien getNhanVienByMa(String maNV) {
        String sql = "SELECT * FROM nhanvien WHERE maNV = ?";
        NhanVien nv = null;
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maNV);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nv = new NhanVien();
                    nv.setMaNv(rs.getString("maNV")); // Sửa setMaNv thành setMaNV
                    nv.setTenNv(rs.getString("tenNV")); // Sửa setTenNv thành setTenNV
                    // Các thông tin khác nếu cần
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nv;
    }
    
    

}