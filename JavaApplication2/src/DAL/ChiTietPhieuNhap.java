/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author Ngoc
 */
public class ChiTietPhieuNhap {
    
    private String maPhieu;
    private String maSanPham;
    private String soLuong;
    private String donGia;

    public ChiTietPhieuNhap() {
    }

    public ChiTietPhieuNhap(String maPhieu, String maSanPham, String soLuong, String donGia) {
        this.maPhieu = maPhieu;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getDonGia() {
        return donGia;
    }

    public void setDonGia(String donGia) {
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        return "ChiTietPhieuNhap{" + "maPhieu=" + maPhieu + ", maSanPham=" + maSanPham + ", soLuong=" + soLuong + ", donGia=" + donGia + '}';
    }
    
    
}
