/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;
import BLL.ProductBLL;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import BLL.TSXnhanvienBLL;
import BLL.loaisanphamBLL;
import DAL.LoaiSanPham;
import DAL.NhanVien;
import DAL.SanPham;
import java.sql.SQLException;
import java.util.List;
import javax.swing.event.DocumentListener;


public class QuanLyNhanVien extends javax.swing.JFrame {
//TSXnhanvienBLL nhanVienBLL = new TSXnhanvienBLL();
   
    public QuanLyNhanVien() {
        initComponents();
        loadDanhSachNhanVien();
}
    
  private void loadDanhSachNhanVien() {

    try {
        DefaultTableModel model = (DefaultTableModel) tblNV.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ

        TSXnhanvienBLL nhanVienBLL = new TSXnhanvienBLL();
        List<NhanVien> list = nhanVienBLL.getAllNhanVien();

        for (NhanVien nv : list) {
            Object[] rowData = {
                nv.getMaNv(),
                nv.getTenNv(),
                nv.getEmail(),
                nv.getSdt(),
                nv.getDiaChi()
            };
            model.addRow(rowData);
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách nhân viên!");
    }

}
  private void themNhanVien() {
    try {
        String maNv = txtMaNV.getText().trim();
        String tenNv = txtTenNV.getText().trim();
        String email = txtEmailNV.getText().trim();
        String sdt = txtSdtNV.getText().trim();
        String diaChi = txtDiaChiNV.getText().trim();

        NhanVien nv = new NhanVien(maNv, tenNv, email, sdt, diaChi);

        TSXnhanvienBLL bll = new TSXnhanvienBLL();
        bll.insertNhanVien(nv); // gọi đến tầng BLL để thêm

        JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
        loadDanhSachNhanVien(); // cập nhật lại bảng
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi thêm nhân viên!");
    }
}
  private void clearForm() {
    txtMaNV.setText("");
    txtTenNV.setText("");
    txtEmailNV.setText("");
    txtSdtNV.setText("");
    txtDiaChiNV.setText("");
}

  private void suaNhanVien() {
    try {
        // Lấy dữ liệu từ các ô nhập
        String maNv = txtMaNV.getText().trim();
        String tenNv = txtTenNV.getText().trim();
        String email = txtEmailNV.getText().trim();
        String sdt = txtSdtNV.getText().trim();
        String diaChi = txtDiaChiNV.getText().trim();

        if (maNv.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên để sửa (Mã nhân viên không được trống)");
            return;
        }

        // Tạo đối tượng
        NhanVien nv = new NhanVien(maNv, tenNv, email, sdt, diaChi);

        // Gọi BLL để cập nhật
        TSXnhanvienBLL bll = new TSXnhanvienBLL();
        bll.updateNhanVien(nv);

        JOptionPane.showMessageDialog(this, "Cập nhật thành công!");

        loadDanhSachNhanVien(); // load lại bảng
        clearForm(); // xóa form sau khi sửa
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật nhân viên!");
    }
  }
  private void xoaNhanVien() {
    int selectedRow = tblNV.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa.");
        return;
    }

    String maNV = tblNV.getValueAt(selectedRow, 0).toString();

    int confirm = JOptionPane.showConfirmDialog(this,
        "Bạn có chắc muốn xóa nhân viên có mã: " + maNV + "?",
        "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        try {
            TSXnhanvienBLL bll = new TSXnhanvienBLL();
            boolean success = bll.deleteNhanVien(maNV);
            if (success) {
                JOptionPane.showMessageDialog(this, "✅ Xóa thành công.");
                loadDanhSachNhanVien(); // cập nhật lại bảng
                clearForm(); // làm trống form
            } else {
                JOptionPane.showMessageDialog(this, "❌ Xóa thất bại.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "❌ Lỗi khi xóa nhân viên.");
        }
    }
}

  



              //  loadDataToTable();}

//          this.addWindowListener(new java.awt.event.WindowAdapter() {
//    @Override
//    public void windowOpened(java.awt.event.WindowEvent evt) {
//        DefaultTableModel model = (DefaultTableModel) NVtable.getModel();
//        
//        NVtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        NVtable.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
//        NVtable.getColumnModel().getColumn(1).setPreferredWidth(170); // Tên
//        NVtable.getColumnModel().getColumn(2).setPreferredWidth(170); // Email
//        NVtable.getColumnModel().getColumn(3).setPreferredWidth(150);  // SĐT
//        NVtable.getColumnModel().getColumn(4).setPreferredWidth(250); // Địa chỉ
//        
//        loadData();
//  }

//}
          
//          private void loadDataToTable() {
//    try {
//         tblNV.setModel(new javax.swing.table.DefaultTableModel(
//            new Object [][] {},
//            new String [] {
//                "manv", "tennv", "email", "sdt", "diachi"
//            }
//        ));
//        ProductBLL dao = new ProductBLL();
//        List<SanPham> list = dao.getAllProduct();
//          System.out.println(list.size());
//        DefaultTableModel model = (DefaultTableModel) TableProduct.getModel();
//        model.setRowCount(0); 
//        for (SanPham b : list) {
//        System.out.println(b); // In ra Book nhờ toString()
//        model.addRow(new Object[]{
//        b.getMaSanPham(), b.getTenSanPham(),b.getMaLoai(),b.getMaNcc(), b.getGia(), b.getSoLuongTon(), b.getDuongDanAnh()
//        });
//    }
//    } catch  (SQLException e) {
//        e.printStackTrace();
//        JOptionPane.showMessageDialog(this, "Lỗi khi load dữ liệu!");
//    }
//}
//private void loadCategorytoComboBox (){
//
//    loaisanphamBLL loaisanpham = new loaisanphamBLL();
//    try {
//        List<LoaiSanPham> categories = loaisanpham.getAllCategory();
//        CategoryCbx.removeAllItems();
//        for (LoaiSanPham category : categories) {
//            String display = category.getMaLoai() + " - " + category.getTenLoai();
//        CategoryCbx.addItem(display);  // Thêm đối tượng trực tiếp
//        }
//    } catch (SQLException ex) {
//        ex.printStackTrace();
//    }
//}
//     
//    }
//    private void loadData() {
//    TSXnhanvienBLL bll = new TSXnhanvienBLL();
//    List<NhanVien> list = bll.getAllNhanVien();
//
//    DefaultTableModel model = (DefaultTableModel) NVtable.getModel();
//    model.setRowCount(0); // xóa dữ liệu cũ
//
//    for (NhanVien nv : list) {
//        model.addRow(new Object[]{nv.getMaNv(), nv.getTenNv(), nv.getEmail(), nv.getSdt(), nv.getDiaChi()});
//    }
//}

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtnSua = new javax.swing.JButton();
        BtnXoa = new javax.swing.JButton();
        BtnBack = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNV = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        txtSdtNV = new javax.swing.JTextField();
        txtDiaChiNV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEmailNV = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        BtnThem = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtTimkiem = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnSua.setBackground(new java.awt.Color(250, 250, 250));
        BtnSua.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        BtnSua.setText("Sửa");
        BtnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSuaActionPerformed(evt);
            }
        });
        getContentPane().add(BtnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 365, 100, 35));

        BtnXoa.setBackground(new java.awt.Color(250, 250, 250));
        BtnXoa.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        BtnXoa.setText("Xóa");
        BtnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnXoaActionPerformed(evt);
            }
        });
        getContentPane().add(BtnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 418, 100, 35));

        BtnBack.setBackground(new java.awt.Color(51, 153, 255));
        BtnBack.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        BtnBack.setForeground(new java.awt.Color(255, 255, 255));
        BtnBack.setText("Trở về");
        BtnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBackActionPerformed(evt);
            }
        });
        getContentPane().add(BtnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(847, 554, 130, 40));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Quản lý nhân viên");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(332, 332, 332))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 983, -1));

        tblNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Họ tên ", "Email", "Số điện thoại", "Địa chỉ"
            }
        ));
        tblNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNV);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 312, 792, 282));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Họ tên");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 33, 54, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Địa chỉ");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(577, 77, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Số điện thoại");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 76, 84, 30));
        jPanel2.add(txtTenNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 33, 260, 25));
        jPanel2.add(txtSdtNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 80, 260, 25));

        txtDiaChiNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiNVActionPerformed(evt);
            }
        });
        jPanel2.add(txtDiaChiNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 76, 260, 25));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Email");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(577, 34, 37, -1));

        txtEmailNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailNVActionPerformed(evt);
            }
        });
        jPanel2.add(txtEmailNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 33, 260, 25));
        jPanel2.add(txtMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 128, 260, 25));

        jLabel6.setText("ID");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 132, 37, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 72, 961, 160));

        BtnThem.setBackground(new java.awt.Color(250, 250, 250));
        BtnThem.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        BtnThem.setText("Thêm");
        BtnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnThemActionPerformed(evt);
            }
        });
        getContentPane().add(BtnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 312, 100, 35));

        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(873, 471, -1, -1));
        getContentPane().add(txtTimkiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 250, 218, -1));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tim theo ma nv va ten nv :");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 253, 156, -1));

        jPanel3.setBackground(new java.awt.Color(33, 47, 61));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 980, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 980, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void BtnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnXoaActionPerformed
xoaNhanVien();
    }//GEN-LAST:event_BtnXoaActionPerformed

    private void BtnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBackActionPerformed
        // TODO add your handling code here:
        Trangchutest Trangchutest = new Trangchutest();
        Trangchutest.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnBackActionPerformed

    private void txtDiaChiNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiNVActionPerformed

    }//GEN-LAST:event_txtDiaChiNVActionPerformed

    private void txtEmailNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailNVActionPerformed

    }//GEN-LAST:event_txtEmailNVActionPerformed

    private void BtnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnThemActionPerformed
        // TODO add your handling code here:
         themNhanVien();
        
        
        
       
        
//    String maNV = txtMaNV.getText().trim();
//    String tenNV = txtTenNV.getText().trim();
//    String emailNV = txtEmailNV.getText().trim();
//    String sdtNV = txtSdtNV.getText().trim();
//    String diaChiNV = txtDiaChiNV.getText().trim();
//
//    // Kiểm tra nhập thiếu
//    if (maNV.isEmpty() || tenNV.isEmpty() || emailNV.isEmpty() || sdtNV.isEmpty() || diaChiNV.isEmpty()) {
//        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
//        return;
//    }
//    if (!emailNV.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
//    JOptionPane.showMessageDialog(this, "Email không hợp lệ!");
//    return;
//}
//    if (!sdtNV.matches("\\d{9,11}")) {
//    JOptionPane.showMessageDialog(this, "Số điện thoại phải có từ 9 đến 11 chữ số!");
//    return;
//}
//
//    // Tạo đối tượng nhân viên
//    NhanVien nv = new NhanVien(maNV, tenNV, emailNV, sdtNV, diaChiNV);
//
//    // Gọi BLL để thêm
//    TSXnhanvienBLL bll = new TSXnhanvienBLL();
//    boolean result = bll.addNhanVien(nv);
//
//    if (result) {
//        JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
//
//        // Thêm vào JTable
//        DefaultTableModel model = (DefaultTableModel) NVtable.getModel();
//        model.addRow(new Object[]{maNV, tenNV, emailNV, sdtNV, diaChiNV});
//
//        // Xóa trắng các ô nhập
//        txtMaNV.setText("");
//        txtTenNV.setText("");
//        txtEmailNV.setText("");
//        txtSdtNV.setText("");
//        txtDiaChiNV.setText("");
//
//    } else {
//        JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại!");
//    }



    }//GEN-LAST:event_BtnThemActionPerformed

    private void BtnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSuaActionPerformed
//        // TODO add your handling code here:
suaNhanVien();
//      int selectedRow = tblNV.getSelectedRow();
//    if (selectedRow == -1) {
//        JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên để sửa!");
//        return;
//    }
//
//    String maNV = txtMaNV.getText().trim();
//    String tenNV = txtTenNV.getText().trim();
//    String emailNV = txtEmailNV.getText().trim();
//    String sdtNV = txtSdtNV.getText().trim();
//    String diaChiNV = txtDiaChiNV.getText().trim();
//
//    if (maNV.isEmpty() || tenNV.isEmpty() || emailNV.isEmpty() || sdtNV.isEmpty() || diaChiNV.isEmpty()) {
//        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
//        return;
//    }
//
//    if (!emailNV.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
//        JOptionPane.showMessageDialog(this, "Email không hợp lệ!");
//        return;
//    }
//
//    if (!sdtNV.matches("\\d{9,11}")) {
//        JOptionPane.showMessageDialog(this, "Số điện thoại phải có từ 9 đến 11 chữ số!");
//        return;
//    }
//
//    // Tạo đối tượng nhân viên mới với dữ liệu cập nhật
//    NhanVien nv = new NhanVien(maNV, tenNV, emailNV, sdtNV, diaChiNV);
//
//    TSXnhanvienBLL bll = new TSXnhanvienBLL();
//    boolean result = bll.updateNhanVien(nv);
//
//    if (result) {
//        JOptionPane.showMessageDialog(this, "Cập nhật thông tin nhân viên thành công!");
//
//        // Cập nhật lại bảng
//        loadData();
//
//        // Xóa trắng các ô nhập
//        txtMaNV.setText("");
//        txtTenNV.setText("");
//        txtEmailNV.setText("");
//        txtSdtNV.setText("");
//        txtDiaChiNV.setText("");
//    } else {
//        JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
//    }
    }//GEN-LAST:event_BtnSuaActionPerformed

    private void tblNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNVMouseClicked
        // TODO add your handling code here:
          int selectedRow = tblNV.getSelectedRow();
        if (selectedRow >= 0) {
            txtMaNV.setText(tblNV.getValueAt(selectedRow, 0).toString());
            txtTenNV.setText(tblNV.getValueAt(selectedRow, 1).toString());
            txtEmailNV.setText(tblNV.getValueAt(selectedRow, 2).toString());
            txtSdtNV.setText(tblNV.getValueAt(selectedRow, 3).toString());
            txtDiaChiNV.setText(tblNV.getValueAt(selectedRow, 4).toString());
        }
//int selectedRow = tblNV.getSelectedRow();
//        if (selectedRow >= 0) {
//             DefaultTableModel model = (DefaultTableModel) tblNV.getModel();
//                String productId = model.getValueAt(selectedRow, 0).toString(); 
//                String productName = model.getValueAt(selectedRow, 1).toString(); 
//                String price = model.getValueAt(selectedRow, 4).toString();
//                String stock = model.getValueAt(selectedRow, 5).toString();
//                          String stock1 = model.getValueAt(selectedRow, 6).toString();
//
//
//                 txtTenNV.setText(productId);
//                 txtSdtNV.setText(productName);
//                 txtEmailNV.setText(price);
//                 txtDiaChiNV.setText(stock); 
//                 txtMaNV.setText(stock1);
    }//GEN-LAST:event_tblNVMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_jButton1ActionPerformed


//private void timKiemNhanVien() {
//    String keyword = txtTimkiem.getText().trim();
//    try {
//        TSXnhanvienBLL bll = new TSXnhanvienBLL();
//        List<NhanVien> ds;
//        if (keyword.isEmpty()) {
//            ds = bll.getAllNhanVien();
//        } else {
//            ds = bll.searchNhanVien(keyword);
//        }
//        loadDanhSachNhanVien(ds);
//    } catch (SQLException ex) {
//        ex.printStackTrace();
//        JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm nhân viên.");
//    }
//}

//    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
         try {
            FlatLightLaf.setup(); 
            
        } catch (Exception ex) {
           
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBack;
    private javax.swing.JButton BtnSua;
    private javax.swing.JButton BtnThem;
    private javax.swing.JButton BtnXoa;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblNV;
    private javax.swing.JTextField txtDiaChiNV;
    private javax.swing.JTextField txtEmailNV;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtSdtNV;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTimkiem;
    // End of variables declaration//GEN-END:variables
}