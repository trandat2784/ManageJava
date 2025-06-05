package BLL;

import Config.ConnectDB;
import static Config.ConnectDB.getConnection;
import DAL.ChiTietHoaDon;  // Nếu bạn có DTO lớp ChiTietHoaDon, nếu không thì khai báo kiểu phù hợp
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

//public class ChiTietHoaDonBLL {
//    protected Connection con;
//
//    // Constructor lấy connection từ ConnectDB
//    public ChiTietHoaDonBLL() {
//       
//           
//        this.con = getConnection();
//    }
    
   
    
//   
//    public ChiTietHoaDon getByMaCTHD(String maCTHD) {
//        String sql = "SELECT * FROM ChiTietHoaDon WHERE MaCTHD = ?";
//        ChiTietHoaDon ct = null;
//        
//        try (PreparedStatement pst = connection.prepareStatement(sql)) {
//            pst.setString(1, maCTHD);
//            
//            try (ResultSet rs = pst.executeQuery()) {
//                if (rs.next()) {
//                    ct = new ChiTietHoaDon();
//                    ct.setMaChiTietHD(rs.getString("MaCTHD"));
//                    ct.setMaHD(rs.getString("MaHD"));
//                    ct.setMaSanPham(rs.getString("MaSP"));
//                    ct.setSoLuong(rs.getInt("SoLuong"));
//                    ct.setDonGia(rs.getFloat("DonGia"));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return ct;
//    }
//    
//   
//    public boolean update(ChiTietHoaDon ct) {
//        String sql = "UPDATE ChiTietHoaDon SET SoLuong = ?, DonGia = ? WHERE MaCTHD = ?";
//        
//        try (PreparedStatement pst = connection.prepareStatement(sql)) {
//            pst.setInt(1, ct.getSoLuong());
//            pst.setDouble(2, ct.getDonGia());
//            pst.setString(3, ct.getMaChiTietHD());
//            
//            int rowsAffected = pst.executeUpdate();
//            return rowsAffected > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//}
    // Lấy danh sách chi tiết hóa đơn theo mã hóa đơn
//    public ArrayList<ChiTietHoaDon> getChiTietHoaDonByMaHoaDon(String maHD) {
//        ArrayList<ChiTietHoaDon> list = new ArrayList<>();
//        String sql = "SELECT machitiethd, masanpham, soluong, dongia FROM chitiethoadon WHERE mahd = ?";
//        try (PreparedStatement pst = con.prepareStatement(sql)) {
//            pst.setString(1, maHD);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                ChiTietHoaDon ct = new ChiTietHoaDon();
//               ct.setMaChiTietHD(rs.getString("machitiethd"));
//               ct.setMaSanPham(rs.getString("masanpham"));
//               ct.setSoLuong(rs.getInt("soluong"));
//                ct.setDonGia(rs.getFloat("dongia"));
//                list.add(ct);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//    
//    
//
//   public boolean themChiTietHoaDon(ChiTietHoaDon cthd) {
//    String sql = "INSERT INTO chitiethoadon(machitiethd, mahd, masanpham, soluong, dongia) VALUES (?, ?, ?, ?, ?)";
//    try (PreparedStatement ps = con.prepareStatement(sql)) {
//        ps.setString(1, cthd.getMaChiTietHD());
//        ps.setString(2, cthd.getMaHD()); // ✅ Thêm dòng này
//        ps.setString(3, cthd.getMaSanPham());
//        ps.setInt(4, cthd.getSoLuong());
//        ps.setFloat(5, cthd.getDonGia());
//        int rowsAffected = ps.executeUpdate();
//        return rowsAffected > 0;
//    } catch (SQLException ex) {
//        ex.printStackTrace();
//        return false;
//    }
//}
//
//     public List<ChiTietHoaDon> getAllChiTietHoaDon() {
//    List<ChiTietHoaDon> list = new ArrayList<>();
//    try {
//        String sql = "SELECT * FROM chitiethoadon"; // hoặc tên bảng thực tế
//        PreparedStatement ps = con.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//            ChiTietHoaDon cthd = new ChiTietHoaDon();
//            cthd.setMaChiTietHD(rs.getString("machitiethd"));
//            cthd.setMaSanPham(rs.getString("masanpham"));
//            cthd.setSoLuong(rs.getInt("soluong"));
//            cthd.setDonGia(rs.getFloat("dongia"));
//            list.add(cthd);
//        }
//    } catch (SQLException ex) {
//        ex.printStackTrace();
//    }
//    return list;
//}
//

public class ChiTietHoaDonBLL {
    private Connection connection;
    
    public ChiTietHoaDonBLL() {
        this.connection = getConnection();
    }
     public ArrayList<ChiTietHoaDon> getChiTietHoaDonByMaHoaDon(String maHD) {
        ArrayList<ChiTietHoaDon> list = new ArrayList<>();
        String sql = "SELECT machitiethd, masanpham, soluong, dongia FROM chitiethoadon WHERE mahd = ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, maHD);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ChiTietHoaDon ct = new ChiTietHoaDon();
               ct.setMaChiTietHD(rs.getString("machitiethd"));
               ct.setMaSanPham(rs.getString("masanpham"));
               ct.setSoLuong(rs.getInt("soluong"));
                ct.setDonGia(rs.getFloat("dongia"));
                list.add(ct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    // Lấy chi tiết hóa đơn theo mã
    public ChiTietHoaDon getByMaCTHD(String maCTHD) {
        validateMaCTHD(maCTHD);
        
        String sql = "SELECT * FROM ChiTietHoaDon WHERE MaCTHD = ?";
        ChiTietHoaDon ct = null;
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, maCTHD);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    ct = new ChiTietHoaDon();
                    ct.setMaChiTietHD(rs.getString("machitiethd"));
                    ct.setMaHD(rs.getString("mahd"));
                    ct.setMaSanPham(rs.getString("masanpham"));
                    ct.setSoLuong(rs.getInt("soluong"));
                    ct.setDonGia(rs.getFloat("dongia"));
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return ct;
    }
    // them
    public boolean themChiTietHoaDon(ChiTietHoaDon cthd) {
    String sql = "INSERT INTO chitiethoadon(machitiethd, mahd, masanpham, soluong, dongia) VALUES (?, ?, ?, ?, ?)";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, cthd.getMaChiTietHD());
        ps.setString(2, cthd.getMaHD()); // ✅ Thêm dòng này
        ps.setString(3, cthd.getMaSanPham());
        ps.setInt(4, cthd.getSoLuong());
        ps.setFloat(5, cthd.getDonGia());
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;
    }
}
    
    // Cập nhật chi tiết hóa đơn
    public boolean updateChiTietHoaDon(ChiTietHoaDon ct) {
        validateChiTietHoaDon(ct);
        
        String sql = "UPDATE ChiTietHoaDon SET SoLuong = ?, DonGia = ? WHERE MaCTHD = ?";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, ct.getSoLuong());
            pst.setDouble(2, ct.getDonGia());
            pst.setString(3, ct.getMaChiTietHD());
            
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }
    
    // Xóa chi tiết hóa đơn
    public boolean deleteChiTietHoaDon(String maCTHD) {
        validateMaCTHD(maCTHD);
        
        String sql = "DELETE FROM ChiTietHoaDon WHERE MaCTHD = ?";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, maCTHD);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }
    
//    // Lấy tất cả chi tiết hóa đơn
//    public List<ChiTietHoaDon> getAll() {
//        List<ChiTietHoaDon> list = new ArrayList<>();
//        String sql = "SELECT * FROM ChiTietHoaDon";
//        
//        try (Statement st = connection.createStatement();
//             ResultSet rs = st.executeQuery(sql)) {
//            
//            while (rs.next()) {
//                ChiTietHoaDon ct = new ChiTietHoaDon();
//                ct.setMaChiTietHD(rs.getString("MaCTHD"));
//                ct.setMaHD(rs.getString("MaHD"));
//                ct.setMaSanPham(rs.getString("MaSP"));
//                ct.setSoLuong(rs.getInt("SoLuong"));
//                ct.setDonGia(rs.getFloat("dongia"));
//                list.add(ct);
//            }
//        } catch (SQLException e) {
//            handleSQLException(e);
//        }
//        return list;
//    }
    
    // ===== Các phương thức validate =====
    private void validateMaCTHD(String maCTHD) {
        if (maCTHD == null || maCTHD.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã chi tiết hóa đơn không hợp lệ");
        }
    }
    
    private void validateChiTietHoaDon(ChiTietHoaDon ct) {
        if (ct == null) {
            throw new IllegalArgumentException("Đối tượng không được null");
        }
        validateMaCTHD(ct.getMaChiTietHD());
        
        if (ct.getSoLuong() <= 0) {
            throw new IllegalArgumentException("Số lượng phải lớn hơn 0");
        }
        
        if (ct.getDonGia() < 0) {
            throw new IllegalArgumentException("Đơn giá không được âm");
        }
    }
    
    private void handleSQLException(SQLException e) {
        // Có thể log lỗi ra file hoặc hệ thống quản lý lỗi
        System.err.println("Lỗi SQL: " + e.getMessage());
        e.printStackTrace();
    }
}