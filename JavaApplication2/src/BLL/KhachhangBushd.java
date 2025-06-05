package BLL;

import DAL.KhachHang;
import Config.ConnectDB;
import DAL.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ADMIN
 */
public class KhachhangBushd extends ConnectDB {
    
    private Connection con;
    
    public KhachhangBushd() {
        try {
            con = getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Lấy danh sách tất cả tên khách hàng (chỉ tên)
    public List<KhachHang> getAllKhachHang() {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT maKh, tenKh FROM KhachHang ORDER BY tenKh";
        
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKh(rs.getString("maKh"));
                kh.setTenKh(rs.getString("tenKh"));
                list.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // Lấy đối tượng khách hàng theo tên (nếu cần)
    public KhachHang getKhachHangByTen(String tenKh) {
        String sql = "SELECT * FROM KhachHang WHERE tenKh = ?";
        KhachHang kh = null;
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenKh);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    kh = new KhachHang();
                    kh.setMaKh(rs.getString("maKh"));
                    kh.setTenKh(rs.getString("tenKh"));
                    kh.setEmail(rs.getString("email"));
                    kh.setSdt(rs.getString("sdt"));
                    kh.setDiaChi(rs.getString("diaChi"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kh;
    }
    // Trong DAL/KhachhangDAL.java
public KhachHang getKhachHangByMa(String maKH) {
    String sql = "SELECT * FROM KhachHang WHERE maKH = ?";
    KhachHang kh = null;
    
    try (
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setString(1, maKH);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                kh = new KhachHang();
                kh.setMaKh(rs.getString("maKH"));
                kh.setTenKh(rs.getString("tenKH"));
                // set các thuộc tính khác nếu cần
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return kh;
}
// Thêm phương thức mới để lấy danh sách tên KH
public List<String> getAllTenKhachHang() {
    List<String> list = new ArrayList<>();
    String sql = "SELECT tenKh FROM KhachHang ORDER BY tenKh";
    
    try (PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        
        while (rs.next()) {
            list.add(rs.getString("tenKh"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}

// Giữ nguyên phương thức cũ (nếu cần)




}