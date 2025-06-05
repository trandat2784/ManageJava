/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author Ngoc
 */
public class KhachHang {
    private String maKh;
    private String tenKh;
    private String email;
    private String sdt;
    private String diaChi;

    public KhachHang(String maKh, String tenKh, String email, String sdt, String diaChi) {
        this.maKh = maKh;
        this.tenKh = tenKh;
        this.email = email;
        this.sdt = sdt;
        this.diaChi = diaChi;
    }

    public KhachHang() {
    }

    public String getMaKh() {
        return maKh;
    }

    public void setMaKh(String maKh) {
        this.maKh = maKh;
    }

    public String getTenKh() {
        return tenKh;
    }

    public void setTenKh(String tenKh) {
        this.tenKh = tenKh;
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
        return "KhachHang{" + "maKh=" + maKh + ", tenKh=" + tenKh + ", email=" + email + ", sdt=" + sdt + ", diaChi=" + diaChi + '}';
    }
    
    
}
