/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import BLL.NSanPhamBLL;
import java.util.ArrayList;

/**
 *
 * @author Ngoc
 */
public class SanPham {
    private String maSanPham;
    private String tenSanPham;
    private int maLoai;
    private String maNcc;
    private float gia;
    private int soLuongTon;
    private String duongDanAnh;

    public SanPham(String maSanPham, String tenSanPham, int maLoai, String maNcc, float gia, int soLuongTon, String duongDanAnh) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.maLoai = maLoai;
        this.maNcc = maNcc;
        this.gia = gia;
        this.soLuongTon = soLuongTon;
        this.duongDanAnh = duongDanAnh;
    }

    public SanPham() {
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getMaNcc() {
        return maNcc;
    }

    public void setMaNcc(String maNcc) {
        this.maNcc = maNcc;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public String getDuongDanAnh() {
        return duongDanAnh;
    }

    public void setDuongDanAnh(String duongDanAnh) {
        this.duongDanAnh = duongDanAnh;
    }

    @Override
    public String toString() {
        return "SanPham{" + "maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", maLoai=" + maLoai + ", maNcc=" + maNcc + ", gia=" + gia + ", soLuongTon=" + soLuongTon + ", duongDanAnh=" + duongDanAnh + '}';
    }

    public ArrayList<NSanPhamBLL> docdanhsachsanphamtheodm(String tenLoai) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
