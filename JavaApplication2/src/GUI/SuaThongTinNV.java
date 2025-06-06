/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;
import DAL.NhanVien;
public class SuaThongTinNV extends javax.swing.JFrame {

    /**
     * Creates new form SuaThongTin
     */
    public SuaThongTinNV() {
        initComponents();
    }

    public void setNhanVienData(NhanVien nv) {
        SuaTen.setText(nv.getTenNv());
        SuaEmail.setText(nv.getEmail());
        SuaSdt.setText(nv.getSdt());
        SuaDiaChi.setText(nv.getDiaChi());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        SuaTen = new javax.swing.JTextField();
        SuaSdt = new javax.swing.JTextField();
        SuaDiaChi = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        SuaEmail = new javax.swing.JTextField();
        BtnLuu = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Họ tên");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 11, 54, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Địa chỉ");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 146, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Số điện thoại");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 54, 100, 30));
        getContentPane().add(SuaTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 11, 260, 25));
        getContentPane().add(SuaSdt, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 58, 260, 25));

        SuaDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuaDiaChiActionPerformed(evt);
            }
        });
        getContentPane().add(SuaDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 145, 260, 25));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Email");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 103, 37, -1));

        SuaEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuaEmailActionPerformed(evt);
            }
        });
        getContentPane().add(SuaEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 102, 260, 25));

        BtnLuu.setBackground(new java.awt.Color(51, 153, 255));
        BtnLuu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnLuu.setForeground(new java.awt.Color(255, 255, 255));
        BtnLuu.setText("Lưu");
        getContentPane().add(BtnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 200, 130, 40));

        jPanel1.setBackground(new java.awt.Color(33, 47, 61));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 290));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SuaDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuaDiaChiActionPerformed

    }//GEN-LAST:event_SuaDiaChiActionPerformed

    private void SuaEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuaEmailActionPerformed

    }//GEN-LAST:event_SuaEmailActionPerformed

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
            java.util.logging.Logger.getLogger(SuaThongTinNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SuaThongTinNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SuaThongTinNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SuaThongTinNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SuaThongTinNV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnLuu;
    private javax.swing.JTextField SuaDiaChi;
    private javax.swing.JTextField SuaEmail;
    private javax.swing.JTextField SuaSdt;
    private javax.swing.JTextField SuaTen;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
