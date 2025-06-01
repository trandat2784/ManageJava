/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDateTime;

/**
 *
 * @author Ngoc
 */
public class PhieuNhap {
    private String maPhieu;
    private LocalDateTime thoiGianTao;
    private String maNcc;
    private String phuongThucThanhToan;
    private float tongTien;

    public PhieuNhap() {
    }

    public PhieuNhap(String maPhieu, LocalDateTime thoiGianTao, String maNcc, String phuongThucThanhToan, float tongTien) {
        this.maPhieu = maPhieu;
        this.thoiGianTao = thoiGianTao;
        this.maNcc = maNcc;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.tongTien = tongTien;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public LocalDateTime getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(LocalDateTime thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public String getMaNcc() {
        return maNcc;
    }

    public void setMaNcc(String maNcc) {
        this.maNcc = maNcc;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "PhieuNhap{" + "maPhieu=" + maPhieu + ", thoiGianTao=" + thoiGianTao + ", maNcc=" + maNcc + ", phuongThucThanhToan=" + phuongThucThanhToan + ", tongTien=" + tongTien + '}';
    }
    
    
}
