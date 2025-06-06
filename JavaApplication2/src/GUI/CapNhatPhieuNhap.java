/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BLL.NChiTietPhieuNhapBLL;
import BLL.NNhaCungCapBLL;
import BLL.NPhieuNhapBLL;
import BLL.NSanPhamBLL;
import DAL.ChiTietPhieuNhap;
import DAL.NhaCungCap;
import DAL.PhieuNhap;
import DAL.SanPham;
import com.formdev.flatlaf.FlatLightLaf;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ngoc
 */
public class CapNhatPhieuNhap extends javax.swing.JFrame {

    private DefaultTableModel tblMode2;
    private DefaultTableModel tblModel;
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    private ArrayList<SanPham> allProduct;
    private PhieuNhap phieunhap;
    private ArrayList<ChiTietPhieuNhap> CTPhieu;
    private ArrayList<ChiTietPhieuNhap> CTPhieuOld;
    private PhieuNhapGUI parent;
    private static final ArrayList<NhaCungCap> arrNcc = NNhaCungCapBLL.getInstance().selectAll();
    
   public CapNhatPhieuNhap(PhieuNhapGUI parent, boolean modal) throws UnsupportedLookAndFeelException {
    UIManager.setLookAndFeel(new FlatLightLaf());
    initComponents();
    setLocationRelativeTo(null);
    this.parent = parent; // lưu lại biến cha
    this.phieunhap = parent.getPhieuNhapSelect();
    allProduct = NSanPhamBLL.getInstance().selectAllExist();
    CTPhieu = NChiTietPhieuNhapBLL.getInstance().selectAll(phieunhap.getMaPhieu());
    CTPhieuOld = NChiTietPhieuNhapBLL.getInstance().selectAll(phieunhap.getMaPhieu());

    initTable();
    loadDataToTableProduct(allProduct);
    loadDataToTableNhapHang();
    displayInfo();
}
    private void displayInfo() {
        txtMaPhieu.setText(phieunhap.getMaPhieu());
        textTongTien.setText(formatter.format(phieunhap.getTongTien()) + "đ");
        int vitri = loadNccToComboBox();
        cboNhaCungCap.setSelectedIndex(vitri);
    }

    private int loadNccToComboBox() {
        int vitri = -1;
        for (int i = 0; i < arrNcc.size(); i++) {
            cboNhaCungCap.addItem(arrNcc.get(i).getTenNcc());
            if (arrNcc.get(i).getMaNcc().equals(phieunhap.getNhaCungCap())) {
                vitri = i;
            }
        }
        return vitri;
    }
    
    public final void initTable() {
        tblModel = new DefaultTableModel();
        String[] headerTbl = new String[]{"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá"};
        tblModel.setColumnIdentifiers(headerTbl);
        tblMode2 = new DefaultTableModel();
        String[] headerTb2 = new String[]{"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá"};
        tblMode2.setColumnIdentifiers(headerTb2);
        tblSanPham.setModel(tblModel);
        tblNhapHang.setModel(tblMode2);
        tblSanPham.getColumnModel().getColumn(0).setPreferredWidth(90);
        tblSanPham.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblSanPham.getColumnModel().getColumn(2).setPreferredWidth(80);
        tblNhapHang.getColumnModel().getColumn(0).setPreferredWidth(90);
        tblNhapHang.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblNhapHang.getColumnModel().getColumn(2).setPreferredWidth(80);
        tblSanPham.setDefaultEditor(Object.class, null);
    }

    private void loadDataToTableProduct(ArrayList<SanPham> arrProd) {
        try {
            tblModel.setRowCount(0);
            for (var i : arrProd) {
                tblModel.addRow(new Object[]{
                    i.getMaSanPham(), i.getTenSanPham(), i.getSoLuongTon(), formatter.format(i.getGia()) + "đ"
                });
            }
        } catch (Exception e) {
        }
    }

    public double tinhTongTien() {
        double tt = 0;
        for (var i : CTPhieu) {
            tt += i.getDonGia() * i.getSoLuong();
        }
        return tt;
    }

    public SanPham findMayTinh(String maMay) {
        for (var i : allProduct) {
            if (maMay.equals(i.getMaSanPham())) {
                return i;
            }
        }
        return null;
    }

    public ChiTietPhieuNhap findCTPhieu(String maMay) {
        for (var i : CTPhieu) {
            if (maMay.equals(i.getMaSanPham())) {
                return i;
            }
        }
        return null;
    }

    private void loadDataToTableNhapHang() {
        try {
            DefaultTableModel tblNhapHangmd = (DefaultTableModel) tblNhapHang.getModel();
            tblNhapHangmd.setRowCount(0);

            for (int i = 0; i < CTPhieu.size(); i++) {
                tblNhapHangmd.addRow(new Object[]{
                    i + 1, findMayTinh(CTPhieu.get(i).getMaSanPham()).getTenSanPham(), CTPhieu.get(i).getSoLuong(), formatter.format(CTPhieu.get(i).getDonGia()) + "đ"
                });
            }
        } catch (Exception e) {
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSearch = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtMaPhieu = new javax.swing.JTextField();
        btnReset = new javax.swing.JButton();
        addProduct = new javax.swing.JButton();
        deleteProduct = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnNhapHang = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNhapHang = new javax.swing.JTable();
        cboNhaCungCap = new javax.swing.JComboBox<>();
        textTongTien = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtSearch.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
        getContentPane().add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 160, 30));

        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtSoLuong.setText("1");
        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });
        getContentPane().add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 560, 50, -1));

        txtMaPhieu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        getContentPane().add(txtMaPhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 410, 280, -1));

        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnReset.setText("X");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        getContentPane().add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 40, 30));

        addProduct.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addProduct.setText("Thêm");
        addProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductActionPerformed(evt);
            }
        });
        getContentPane().add(addProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 560, -1, -1));

        deleteProduct.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        deleteProduct.setText("Xóa sản phẩm");
        deleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteProductActionPerformed(evt);
            }
        });
        getContentPane().add(deleteProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 560, -1, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("Cập nhật số lượng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 560, -1, -1));

        btnNhapHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnNhapHang.setText("Lưu");
        btnNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapHangActionPerformed(evt);
            }
        });
        getContentPane().add(btnNhapHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 560, -1, -1));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSanPham.setRowHeight(25);
        jScrollPane1.setViewportView(tblSanPham);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 380, 385));

        tblNhapHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblNhapHang.setRowHeight(25);
        jScrollPane2.setViewportView(tblNhapHang);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, 430, 293));

        cboNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        getContentPane().add(cboNhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 460, 280, -1));

        textTongTien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        textTongTien.setForeground(new java.awt.Color(255, 51, 51));
        textTongTien.setText("0 đ");
        getContentPane().add(textTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 510, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tìm sản phẩm");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mã phiếu nhập");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 410, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nhà cung cấp");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 460, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 51));
        jLabel4.setText("Tổng tiền :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 510, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nhập số lượng");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 560, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 27)); // NOI18N
        jLabel6.setText("CHỈNH SỬA PHIẾU NHẬP");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(306, 306, 306)
                .addComponent(jLabel6)
                .addContainerGap(319, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel6)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 950, 70));

        jPanel2.setBackground(new java.awt.Color(33, 47, 61));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 950, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 950, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapHangActionPerformed
        // TODO add your handling code here:
        if (CTPhieu.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm để nhập hàng !","Cảnh báo", JOptionPane.WARNING_MESSAGE);
        } else {
            for (var ct : CTPhieuOld) {
                NSanPhamBLL.getInstance().updateSoLuong(ct.getMaSanPham(), NSanPhamBLL.getInstance().selectById(ct.getMaSanPham()).getSoLuongTon() - ct.getSoLuong());
                System.out.println(ct.getSoLuong());
            }
            for (var ct : CTPhieu) {
                NSanPhamBLL.getInstance().updateSoLuong(ct.getMaSanPham(), NSanPhamBLL.getInstance().selectById(ct.getMaSanPham()).getSoLuongTon() + ct.getSoLuong());
                System.out.println(ct.getSoLuong());
            }
            // Lay thoi gian hien tai
            long now = System.currentTimeMillis();
            Timestamp sqlTimestamp = new Timestamp(now);
            // Tao doi tuong phieu nhap
            PhieuNhap pn = new PhieuNhap(arrNcc.get(cboNhaCungCap.getSelectedIndex()).getMaNcc(), phieunhap.getMaPhieu(), sqlTimestamp, CTPhieu, tinhTongTien());
            try {
                NPhieuNhapBLL.getInstance().update(pn);
                NChiTietPhieuNhapBLL.getInstance().delete(CTPhieuOld.get(CTPhieuOld.size() - 1));
                for (var i : CTPhieu) {
                    NChiTietPhieuNhapBLL.getInstance().insert(i);
                }
                JOptionPane.showMessageDialog(this, "Cập nhật thành công !");
                this.parent.loadDataToTable();
                this.dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại !","Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnNhapHangActionPerformed

    private void deleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteProductActionPerformed
        // TODO add your handling code here:
        int i_row = tblNhapHang.getSelectedRow();
        if (i_row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xoá khỏi bảng nhập hàng !");
        } else {
            CTPhieu.remove(i_row);
            textTongTien.setText(formatter.format(tinhTongTien()) + "đ");
            loadDataToTableNhapHang();
        }
    }//GEN-LAST:event_deleteProductActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int i_row = tblNhapHang.getSelectedRow();
        if (i_row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xoá sửa số lượng !");
        } else {
            String newSL = JOptionPane.showInputDialog(this, "Nhập số lượng cần thay đổi", "Thay đổi số lượng", QUESTION_MESSAGE);
            if (newSL != null) {
                int soLuong;
                try {
                    soLuong = Integer.parseInt(newSL);
                    CTPhieu.get(i_row).setSoLuong(soLuong);
                    loadDataToTableNhapHang();
                    textTongTien.setText(formatter.format(tinhTongTien()) + "đ");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng ở dạng số nguyên!");
                }
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void addProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductActionPerformed
        // TODO add your handling code here:
        int i_row = tblSanPham.getSelectedRow();
        if (i_row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để nhập hàng !");
        } else {
            int soluong = Integer.parseInt(txtSoLuong.getText().trim());
            ChiTietPhieuNhap mtl = findCTPhieu((String) tblSanPham.getValueAt(i_row, 0));
            if (mtl != null) {
                mtl.setSoLuong(mtl.getSoLuong() + soluong);
            } else {
                SanPham mt = NSanPhamBLL.getInstance().TimKiemId((String) tblSanPham.getValueAt(i_row, 0));
                ChiTietPhieuNhap ctp = new ChiTietPhieuNhap(phieunhap.getMaPhieu(), mt.getMaSanPham(), soluong, mt.getGia());
                CTPhieu.add(ctp);
            }
            loadDataToTableNhapHang();
            textTongTien.setText(formatter.format(tinhTongTien()) + "đ");
        }
    }//GEN-LAST:event_addProductActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        // TODO add your handling code here:
        DefaultTableModel tblsp = (DefaultTableModel) tblSanPham.getModel();
        String textSearch = txtSearch.getText().toLowerCase();
        ArrayList<SanPham> Mtkq = new ArrayList<>();
        for (SanPham i : allProduct) {
            if (i.getMaSanPham().concat(i.getTenSanPham()).toLowerCase().contains(textSearch)) {
                Mtkq.add(i);
            }
        }
        loadDataToTableProduct(Mtkq);
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        txtSearch.setText("");
        loadDataToTableProduct(allProduct);
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatLightLaf());
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CapNhatPhieuNhap dialog = null;
                try {
                    try {
                        dialog = new CapNhatPhieuNhap(new PhieuNhapGUI(), true);
                    } catch (SQLException ex) {
                        Logger.getLogger(CapNhatPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(CapNhatPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
                }
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProduct;
    private javax.swing.JButton btnNhapHang;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox<String> cboNhaCungCap;
    private javax.swing.JButton deleteProduct;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblNhapHang;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JLabel textTongTien;
    private javax.swing.JTextField txtMaPhieu;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
