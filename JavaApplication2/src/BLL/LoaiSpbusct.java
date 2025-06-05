package BLL;

import Config.ConnectDB;
import DAL.LoaiSanPham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoaiSpbusct {
    private Connection con;

    public LoaiSpbusct() {
        try {
            con = ConnectDB.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


 public ArrayList<String> getAllTenLoai() {
    ArrayList<String> list = new ArrayList<>();
    String sql = "SELECT tenLoai FROM LoaiSanPham";
    
    try (PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            list.add(rs.getString("tenLoai"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}

}
