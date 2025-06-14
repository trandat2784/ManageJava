/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BLL.ChiTietHoaDonBLL;
import DAL.ChiTietHoaDon;

import javax.swing.*;

/**
 *
 * @author ADMIN
 */
public class ThemChiTietHoaDon extends javax.swing.JFrame {
   

    private ChiTietHoaDonBLL chiTietHoaDonBUS = new ChiTietHoaDonBLL();
    private ChiTietHoaDonForm formCha; // Tham chiếu form cha để reload bảng (nếu có)
    private String maHD;
    /**
     * Creates new form ThemChiTietHoaDon
     */

    
    public ThemChiTietHoaDon(String maSP, String tenSP, float gia, String maHD, ChiTietHoaDonForm formCha) {
    initComponents();
    this.formCha = formCha;
    this.maHD = maHD;

    lblMaSP.setText(maSP);
    lblTenSP.setText(tenSP);
    lblGia.setText(String.valueOf(gia));

    // Không cho người dùng sửa
    lblMaSP.setEnabled(false);
    lblTenSP.setEnabled(false);
    lblGia.setEnabled(false);

    themcthd.addActionListener(e -> themChiTiet());
    jButton2.addActionListener(e -> this.dispose());
    jButton3.addActionListener(e -> resetForm());
}

        private void themChiTiet() {
        String maChiTiet = txtMaChiTietHoaDon.getText().trim();
        int soLuong = (int) spnSoLuong.getValue();

        if (maChiTiet.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã chi tiết hóa đơn");
            return;
        }

        // Tạo DTO
        ChiTietHoaDon cthd = new ChiTietHoaDon();
        cthd.setMaChiTietHD(maChiTiet);
        cthd.setMaSanPham(lblMaSP.getText());
        cthd.setSoLuong(soLuong);
        cthd.setMaHD(this.maHD);
       
        try {
            cthd.setDonGia(Float.parseFloat(lblGia.getText()));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Giá không hợp lệ!");
            return;
        }

        boolean success = chiTietHoaDonBUS.themChiTietHoaDon(cthd);

        if (success) {
            JOptionPane.showMessageDialog(this, "Thêm thành công!");
            if (formCha != null) {
                formCha.loadTableChiTietHoaDon(maHD);
                this.dispose();// reload bảng chi tiết hóa đơn ở form cha
            }
           // this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại!");
        
    }
        }
        public void setSanPham(String maSP, String tenSP, float gia) {
    lblMaSP.setText(maSP);
    lblTenSP.setText(tenSP);
    lblGia.setText(String.valueOf(gia));
}

    private void resetForm() {
        txtMaChiTietHoaDon.setText("");
        spnSoLuong.setValue(1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblMaSP = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaChiTietHoaDon = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lblTenSP = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        spnSoLuong = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        lblGia = new javax.swing.JLabel();
        themcthd = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin sản phẩm "));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mã Sản phẩm : ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 24, -1, -1));

        lblMaSP.setForeground(new java.awt.Color(255, 255, 255));
        lblMaSP.setText("jLabel2");
        jPanel1.add(lblMaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(146, 24, 172, -1));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mã chi tiết hóa đơn ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 61, -1, -1));

        txtMaChiTietHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaChiTietHoaDonActionPerformed(evt);
            }
        });
        jPanel1.add(txtMaChiTietHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(146, 58, 172, -1));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tên sản phẩm : ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 108, 106, -1));

        lblTenSP.setForeground(new java.awt.Color(255, 255, 255));
        lblTenSP.setText("jLabel5");
        jPanel1.add(lblTenSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(146, 108, 172, -1));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Số lượng : ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 153, -1, -1));
        jPanel1.add(spnSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(146, 150, 105, -1));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Đơn giá : ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 190, 57, -1));

        lblGia.setForeground(new java.awt.Color(255, 255, 255));
        lblGia.setText("jLabel8");
        jPanel1.add(lblGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(146, 190, 89, -1));

        themcthd.setText("Thêm ");
        themcthd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themcthdActionPerformed(evt);
            }
        });
        jPanel1.add(themcthd, new org.netbeans.lib.awtextra.AbsoluteConstraints(183, 239, -1, -1));

        jButton2.setText("Hủy ");
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 239, -1, -1));

        jButton3.setText("Reset");
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(273, 239, -1, -1));

        jPanel2.setBackground(new java.awt.Color(33, 47, 61));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 290));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void themcthdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themcthdActionPerformed
        
    }//GEN-LAST:event_themcthdActionPerformed

    private void txtMaChiTietHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaChiTietHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaChiTietHoaDonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThemChiTietHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemChiTietHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemChiTietHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemChiTietHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 String maHoaDon = "HD01";
            ThemChiTietHoaDon form = new ThemChiTietHoaDon("SP01", "Sản phẩm test", 10000f, "HD01", null);
            form.setVisible(true);
                //new ThemChiTietHoaDon(this, maHD).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblGia;
    private javax.swing.JLabel lblMaSP;
    private javax.swing.JLabel lblTenSP;
    private javax.swing.JSpinner spnSoLuong;
    private javax.swing.JButton themcthd;
    private javax.swing.JTextField txtMaChiTietHoaDon;
    // End of variables declaration//GEN-END:variables

  
}
