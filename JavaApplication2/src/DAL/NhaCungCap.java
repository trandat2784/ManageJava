/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author Ngoc
 */
public class NhaCungCap {
    private String maNcc;
    private String tenNcc;
    private String email;
    private String sdt;
    private String diaChi;

    public NhaCungCap(String maNcc, String tenNcc, String email, String sdt, String diaChi) {
        this.maNcc = maNcc;
        this.tenNcc = tenNcc;
        this.email = email;
        this.sdt = sdt;
        this.diaChi = diaChi;
    }

    public NhaCungCap() {
    }

    public String getMaNcc() {
        return maNcc;
    }

    public void setMaNcc(String maNcc) {
        this.maNcc = maNcc;
    }

    public String getTenNcc() {
        return tenNcc;
    }

    public void setTenNcc(String tenNcc) {
        this.tenNcc = tenNcc;
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
        return "NhaCungCap{" + "maNcc=" + maNcc + ", tenNcc=" + tenNcc + ", email=" + email + ", sdt=" + sdt + ", diaChi=" + diaChi + '}';
    }
    
    
}
