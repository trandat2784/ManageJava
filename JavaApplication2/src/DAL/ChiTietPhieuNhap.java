/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.util.Objects;

/**
 *
 * @author Ngoc
 */
public class ChiTietPhieuNhap {
    
    private String maPhieu;
    private String maSanPham;
    private int  soLuong;
    private double donGia;

    public ChiTietPhieuNhap() {
    }

    public ChiTietPhieuNhap(String maPhieu, String maSanPham, int soLuong, double donGia) {
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        return "ChiTietPhieuNhap{" + "maPhieu=" + maPhieu + ", maSanPham=" + maSanPham + ", soLuong=" + soLuong + ", donGia=" + donGia + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChiTietPhieuNhap other = (ChiTietPhieuNhap) obj;
        if (this.soLuong != other.soLuong) {
            return false;
        }
        if (Double.doubleToLongBits(this.donGia) != Double.doubleToLongBits(other.donGia)) {
            return false;
        }
        if (!Objects.equals(this.maPhieu, other.maPhieu)) {
            return false;
        }
        return Objects.equals(this.maSanPham, other.maSanPham);
    }
}
