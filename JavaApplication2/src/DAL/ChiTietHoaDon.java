/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author Ngoc
 */
public class ChiTietHoaDon {
    private String maChiTietHD;
    private String maHD;
    private String maSanPham;
    private int soLuong;
    private float donGia;
    private float tongTien;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(String maChiTietHD, String maHD, String maSanPham, int soLuong, float donGia, float tongTien) {
        this.maChiTietHD = maChiTietHD;
        this.maHD = maHD;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tongTien = tongTien;
    }

    public String getMaChiTietHD() {
        return maChiTietHD;
    }

    public void setMaChiTietHD(String maChiTietHD) {
        this.maChiTietHD = maChiTietHD;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon{" + "maChiTietHD=" + maChiTietHD + ", maHD=" + maHD + ", maSanPham=" + maSanPham + ", soLuong=" + soLuong + ", donGia=" + donGia + ", tongTien=" + tongTien + '}';
    }
    
    
    
}
