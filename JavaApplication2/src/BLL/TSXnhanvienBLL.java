package BLL;

import Config.ConnectDB;
import DAL.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class TSXnhanvienBLL {

    public List<NhanVien> getAllNhanVien() throws SQLException {
        ConnectDB connection = new ConnectDB();
        Connection conn = connection.getConnection();
        if (conn == null) {
            System.out.println("❌ Kết nối thất bại, conn = null tại getAllNhanVien");
            return new ArrayList<>();
        }

        String sql = "SELECT * FROM nhanvien";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<NhanVien> nhanvienList = new ArrayList<>();

        while (rs.next()) {
            NhanVien nv = new NhanVien(
                rs.getString("manv"),
                rs.getString("tennv"),
                rs.getString("email"),
                rs.getString("sdt"),
                rs.getString("diachi")
            );
            nhanvienList.add(nv);
        }

        System.out.println("📘 Danh sách nhân viên size: " + nhanvienList.size());

        rs.close();
        stmt.close();
        conn.close();

        return nhanvienList;
    }

    public boolean insertNhanVien(NhanVien nv) throws SQLException {
        ConnectDB connection = new ConnectDB();
        Connection conn = connection.getConnection();
        if (conn == null) return false;

        String sql = "INSERT INTO nhanvien (manv, tennv, email, sdt, diachi) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nv.getMaNv());
        ps.setString(2, nv.getTenNv());
        ps.setString(3, nv.getEmail());
        ps.setString(4, nv.getSdt());
        ps.setString(5, nv.getDiaChi());

        int rows = ps.executeUpdate();
        ps.close();
        conn.close();

        return rows > 0;
    }

    public boolean updateNhanVien(NhanVien nv) throws SQLException {
        ConnectDB connection = new ConnectDB();
        Connection conn = connection.getConnection();
        if (conn == null) return false;

        String sql = "UPDATE nhanvien SET tennv=?, email=?, sdt=?, diachi=? WHERE manv=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nv.getTenNv());
        ps.setString(2, nv.getEmail());
        ps.setString(3, nv.getSdt());
        ps.setString(4, nv.getDiaChi());
        ps.setString(5, nv.getMaNv());

        int rows = ps.executeUpdate();
        ps.close();
        conn.close();

        return rows > 0;
    }

    public boolean deleteNhanVien(String manv) throws SQLException {
        ConnectDB connection = new ConnectDB();
        Connection conn = connection.getConnection();
        if (conn == null) return false;

        String sql = "DELETE FROM nhanvien WHERE manv=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, manv);

        int rows = ps.executeUpdate();
        ps.close();
        conn.close();

        return rows > 0;
    }
    
    public List<NhanVien> searchNhanVien(String keyword) throws SQLException {
    ConnectDB connection = new ConnectDB();
    Connection conn = connection.getConnection();
    List<NhanVien> result = new ArrayList<>();
    if (conn == null) return result;

    String sql = "SELECT * FROM nhanvien WHERE manv LIKE ? OR tennv LIKE ?";
    PreparedStatement ps = conn.prepareStatement(sql);
    String pattern = "%" + keyword + "%";
    ps.setString(1, pattern);
    ps.setString(2, pattern);

    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
        NhanVien nv = new NhanVien(
            rs.getString("manv"),
            rs.getString("tennv"),
            rs.getString("email"),
            rs.getString("sdt"),
            rs.getString("diachi")
        );
        result.add(nv);
    }
    rs.close();
    ps.close();
    conn.close();

    return result;
}


}

