/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhanVien {
    private String maNv;
    private String tenNv;
    private String email;
    private String sdt;
    private String diaChi;

    public NhanVien(String maNv, String tenNv, String email, String sdt, String diaChi) {
        this.maNv = maNv;
        this.tenNv = tenNv;
        this.email = email;
        this.sdt = sdt;
        this.diaChi = diaChi;
    }

    public NhanVien() {
    }

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public String getTenNv() {
        return tenNv;
    }

    public void setTenNv(String tenNv) {
        this.tenNv = tenNv;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "maNv=" + maNv + ", tenNv=" + tenNv + ", email=" + email + ", sdt=" + sdt + ", diaChi=" + diaChi + '}';
    }

    public String gettennv() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
