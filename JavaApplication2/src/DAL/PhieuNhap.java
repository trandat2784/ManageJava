/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 * @author Ngoc
 */
public class PhieuNhap {
    
    private String nhaCungCap;
    private String maPhieu;
    private String maNcc;
    private Timestamp thoiGianTao;
    private ArrayList<ChiTietPhieuNhap> CTPhieu;
    private double tongTien;

    public String getMaNcc() {
        return maNcc;
    }

    public void setMaNcc(String maNcc) {
        this.maNcc = maNcc;
    }

    
    public PhieuNhap(String nhaCungCap, String maPhieu, Timestamp thoiGianTao, ArrayList<ChiTietPhieuNhap> CTPhieu, double tongTien) {
        this.nhaCungCap = nhaCungCap;
        this.maPhieu = maPhieu;
        this.thoiGianTao = thoiGianTao;
        this.CTPhieu = CTPhieu;
        this.tongTien = tongTien;
    }

    public PhieuNhap() {
    }

    public PhieuNhap(String maPhieu, Timestamp thoiGianTao, ArrayList<ChiTietPhieuNhap> CTPhieu, double tongTien) {
        this.maPhieu = maPhieu;
        this.thoiGianTao = thoiGianTao;
        this.CTPhieu = CTPhieu;
        this.tongTien = tongTien;
    }

    

    public PhieuNhap(String maPhieu, Timestamp thoiGianTao, double tongTien) {
        this.maPhieu = maPhieu;
        this.thoiGianTao = thoiGianTao;
        this.tongTien = tongTien;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public Timestamp getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(Timestamp thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }


    public ArrayList<ChiTietPhieuNhap> getCTPhieu() {
        return CTPhieu;
    }

    public void setCTPhieu(ArrayList<ChiTietPhieuNhap> CTPhieu) {
        this.CTPhieu = CTPhieu;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final PhieuNhap other = (PhieuNhap) obj;
        if (Double.doubleToLongBits(this.tongTien) != Double.doubleToLongBits(other.tongTien)) {
            return false;
        }
        if (!Objects.equals(this.maPhieu, other.maPhieu)) {
            return false;
        }
        if (!Objects.equals(this.thoiGianTao, other.thoiGianTao)) {
            return false;
        }
        return Objects.equals(this.CTPhieu, other.CTPhieu);
    }

    @Override
    public String toString() {
        return "Phieu{" + "maPhieu=" + maPhieu + ", thoiGianTao=" + thoiGianTao + ",  CTPhieu=" + CTPhieu + ", tongTien=" + tongTien + '}';
    }


    

    

    public PhieuNhap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

 
  


  
    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    

    

   
    
    
}
