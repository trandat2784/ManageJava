/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.time.LocalDateTime;

/**
 *
 * @author Ngoc
 */
public class HoaDon {
        private String maHd;
        private String maNv;
        private String tenKh;
        private String sdtKh;
        private LocalDateTime ngayLap;

    public HoaDon() {
    }

    public HoaDon(String maHd, String maNv, String tenKh, String sdtKh, LocalDateTime ngayLap) {
        this.maHd = maHd;
        this.maNv = maNv;
        this.tenKh = tenKh;
        this.sdtKh = sdtKh;
        this.ngayLap = ngayLap;
    }

    public String getMaHd() {
        return maHd;
    }

    public void setMaHd(String maHd) {
        this.maHd = maHd;
    }

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public String getTenKh() {
        return tenKh;
    }

    public void setTenKh(String tenKh) {
        this.tenKh = tenKh;
    }

    public String getSdtKh() {
        return sdtKh;
    }

    public void setSdtKh(String sdtKh) {
        this.sdtKh = sdtKh;
    }

    public LocalDateTime getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDateTime ngayLap) {
        this.ngayLap = ngayLap;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "maHd=" + maHd + ", maNv=" + maNv + ", tenKh=" + tenKh + ", sdtKh=" + sdtKh + ", ngayLap=" + ngayLap + '}';
    }
        
        

    

        
}
