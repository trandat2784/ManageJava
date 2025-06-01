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
        private String maKh;
        private String maNv;
        private LocalDateTime ngayLap;

    public HoaDon() {
    }

    public HoaDon(String maHd, String maKh, String maNv, LocalDateTime ngayLap) {
        this.maHd = maHd;
        this.maKh = maKh;
        this.maNv = maNv;
        this.ngayLap = ngayLap;
    }

    public String getMaHd() {
        return maHd;
    }

    public void setMaHd(String maHd) {
        this.maHd = maHd;
    }

    public String getMaKh() {
        return maKh;
    }

    public void setMaKh(String maKh) {
        this.maKh = maKh;
    }

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public LocalDateTime getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDateTime ngayLap) {
        this.ngayLap = ngayLap;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "maHd=" + maHd + ", maKh=" + maKh + ", maNv=" + maNv + ", ngayLap=" + ngayLap + '}';
    }

        
}
