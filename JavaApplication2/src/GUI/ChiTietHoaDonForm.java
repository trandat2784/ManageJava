/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BLL.ChiTietHoaDonBLL;
import BLL.HoaDonBLL;
import BLL.KhachhangBushd;
import BLL.LoaiSpbusct;
import BLL.NhanvienBushd;
import BLL.SanphamBusct;
//import Config.ConnectDB;
import DAL.ChiTietHoaDon;
import DAL.LoaiSanPham;
import DAL.SanPham;
import com.sun.jdi.connect.spi.Connection;
import java.awt.Component;
import java.util.List;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
//import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


/**
 *
 * @author ADMIN
 */
public class ChiTietHoaDonForm extends javax.swing.JFrame {
 // Khai báo ở đầu class
private List<LoaiSanPham> danhSachLoaiSanPham = new ArrayList<>();
     private String maHD;
     Connection con;
     private ChiTietHoaDonBLL bll = new ChiTietHoaDonBLL();
     //private SanphamBusct sanPhamBusct;
     SanphamBusct sanPhamBusct = new SanphamBusct();
    
    /**
     * Creates new form ChiTietHoaDon
     */

   public ChiTietHoaDonForm(String maHD) {
    initComponents();
    this.maHD = maHD;
    lblMaHoaDon.setText(maHD);
    loadLoaiSanPhamToCombobox();  
    addEventFilter();      
    addEventTimKiem();
    loadTableChiTietHoaDon(maHD);
    loadSanPhamToTable();
    
   }
   public void loadTableChiTietHoaDon(String maHD) {
   ChiTietHoaDonBLL bll = new ChiTietHoaDonBLL();
    List<ChiTietHoaDon> ds = bll.getChiTietHoaDonByMaHoaDon(maHD); // CHỈ lấy chi tiết của hóa đơn được chọn!
    DefaultTableModel model = (DefaultTableModel) tblChitiethoadon.getModel();
    model.setRowCount(0); // Xóa dữ liệu cũ
    for (ChiTietHoaDon cthd : ds) {
        model.addRow(new Object[]{
            cthd.getMaChiTietHD(),
            cthd.getMaSanPham(),
            cthd.getSoLuong(),
            cthd.getDonGia()
        });
    }
}
private void loadLoaiSanPhamToCombobox() {
    LoaiSpbusct loaiDAO = new LoaiSpbusct();
    ArrayList<String> listLoai = loaiDAO.getAllTenLoai();
    //hienthi toan 
    listLoai.add(0, "Tất cả");
    
    // Gán dữ liệu vào combobox
    cbloaispcthd.setModel(new DefaultComboBoxModel<>(listLoai.toArray(new String[0])));
}
private void addEventFilter() {
    cbloaispcthd.addActionListener(e -> {
        String selectedLoai = cbloaispcthd.getSelectedItem().toString();
        
        if (selectedLoai.equals("Tất cả")) {
            loadSanPhamToTable();
        } else {
            filterSanPhamByLoai(selectedLoai);
        }
    });
}

private void filterSanPhamByLoai(String tenLoai) {
    ArrayList<SanPham> filteredList = new SanphamBusct().getSanPhamByLoai(tenLoai);
    DefaultTableModel model = (DefaultTableModel) tblSpcthd.getModel();
    model.setRowCount(0);

    for (SanPham sp : filteredList) {
        model.addRow(new Object[]{
            sp.getMaSanPham(),
            sp.getTenSanPham(),
            sp.getGia(),
            sp.getSoLuongTon(),        
        });
    }
}
// private void displaySelectedRowData() {
//        int row = tblChitiethoadon.getSelectedRow();
//        if (row >= 0) {
//            // Lấy dữ liệu từ bảng
//            String maCTHD = (String) tblChitiethoadon.getValueAt(row, 0);
//            String maSP = (String) tblChitiethoadon.getValueAt(row, 1);
//            String tenSP = (String) tblChitiethoadon.getValueAt(row, 2);
//            int soLuong = (int) tblChitiethoadon.getValueAt(row, 3);
//            
//            // Hiển thị lên các control
//            txtMaChiTietHoaDon.setText(maCTHD);
//            lblTenSanPham.setText(tenSP);
//            spinnerSoLuong.setValue(soLuong);
//            
//            // Enable các nút chức năng
//            btnSuaCTHD.setEnabled(true);
//            btnXoaCTHD.setEnabled(true);
//        }
//    }

private void displaySelectedRowData() {
    
    }
public void loadSanPhamToTable() {
    ArrayList<SanPham> listSanPham = sanPhamBusct.getAllSanPham(); // Lấy danh sách từ CSDL
    
    // Tạo model với chỉ 4 cột cần hiển thị
    DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Không cho phép chỉnh sửa trực tiếp trên table
        }
    };
 
    // Thêm các cột cần hiển thị
    model.addColumn("Mã SP");
    model.addColumn("Tên SP");
    model.addColumn("Giá");
    model.addColumn("Số lượng");
    
    // Đổ dữ liệu vào model
    for (SanPham sp : listSanPham) {
        model.addRow(new Object[]{
            sp.getMaSanPham(),
            sp.getTenSanPham(),
            sp.getGia(),
            sp.getSoLuongTon()
        });
    }
     tblSpcthd.setModel(model);

    // Đặt độ rộng cột
    tblSpcthd.getColumnModel().getColumn(0).setPreferredWidth(50);   // Cột "Mã SP" rộng 80px
    tblSpcthd.getColumnModel().getColumn(1).setPreferredWidth(200);  // Cột "Tên SP" rộng 250px (hiển thị đủ tên dài)
    tblSpcthd.getColumnModel().getColumn(2).setPreferredWidth(100);  // Cột "Giá" rộng 100px
    tblSpcthd.getColumnModel().getColumn(3).setPreferredWidth(50);   // Cột "Số lượng" rộng 80px

    // Gán model vào JTable
    tblSpcthd.setModel(model);
    
    //  Định dạng cột giá tiền
    tblSpcthd.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
        DecimalFormat format = new DecimalFormat("#,##0.00");
        
        @Override
        public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column
        ) {
            if (value instanceof Number) {
                value = format.format(value) ;
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
            });
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaChiTietHoaDon = new javax.swing.JTextField();
        lblMaHoaDon = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblTenSanPham = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        spinnerSoLuong = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtTimspcthd = new javax.swing.JTextField();
        cbloaispcthd = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSpcthd = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChitiethoadon = new javax.swing.JTable();
        btnSuaCTHD = new javax.swing.JButton();
        btnXoaCTHD = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenuItem1.setText("jMenuItem1");

        jMenu5.setText("jMenu5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(1183, 564));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết hóa đơn"));

        jLabel1.setText("Mã chi tiết hóa đơn : ");

        jLabel2.setText("Mã hóa đơn : ");

        txtMaChiTietHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaChiTietHoaDonActionPerformed(evt);
            }
        });

        lblMaHoaDon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setText("Tên sản phẩm ");

        lblTenSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText("Số lượng :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(lblMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaChiTietHoaDon)))
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spinnerSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9)
                    .addComponent(lblTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaChiTietHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(spinnerSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin sản phẩm "));

        jLabel7.setText("Tìm sản phẩm :");

        txtTimspcthd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimspcthdActionPerformed(evt);
            }
        });

        cbloaispcthd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Chọn loại sản phẩm :");

        tblSpcthd.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSpcthd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSpcthdMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSpcthd);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTimspcthd)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbloaispcthd, 0, 308, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTimspcthd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(cbloaispcthd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Sự kiện của hóa đơn"));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 777, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        tblChitiethoadon.setModel(new javax.swing.table.DefaultTableModel(
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
        tblChitiethoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChitiethoadonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblChitiethoadon);

        btnSuaCTHD.setText("Sửa");
        btnSuaCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCTHDActionPerformed(evt);
            }
        });

        btnXoaCTHD.setText("Xóa");
        btnXoaCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCTHDActionPerformed(evt);
            }
        });

        jButton3.setText("Hủy");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 777, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnSuaCTHD, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnXoaCTHD, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(btnSuaCTHD)
                                .addGap(26, 26, 26)
                                .addComponent(btnXoaCTHD)
                                .addGap(35, 35, 35)
                                .addComponent(jButton3)))
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    private void txtMaChiTietHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaChiTietHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaChiTietHoaDonActionPerformed

    private void tblChitiethoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChitiethoadonMouseClicked
        // TODO add your handling code here:

        displaySelectedRowData();
        
    }//GEN-LAST:event_tblChitiethoadonMouseClicked

    private void addEventTimKiem() {
    txtTimspcthd.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            timKiem();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            timKiem();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            timKiem();
        }

        private void timKiem() {
            String tuKhoa = txtTimspcthd.getText().trim();
            SanphamBusct spBUS = new SanphamBusct(); // hoặc sử dụng sẵn nếu đã khai báo ngoài
            ArrayList<SanPham> ds = spBUS.timKiemSanPhamTheoTen(tuKhoa);

            DefaultTableModel model = (DefaultTableModel) tblSpcthd.getModel();
            model.setRowCount(0); // xóa dữ liệu cũ

            for (SanPham sp : ds) {
                model.addRow(new Object[]{
                    sp.getMaSanPham(),
                    sp.getTenSanPham(),
                    sp.getSoLuongTon(),
                    sp.getGia()
                });
            }
        }
    });
}

    private void txtTimspcthdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimspcthdActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtTimspcthdActionPerformed

    private void tblSpcthdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSpcthdMouseClicked
        // TODO add your handling code here:
          int row = tblSpcthd.getSelectedRow();
        if (row != -1) {
            String maSP = tblSpcthd.getValueAt(row, 0).toString();
            String tenSP = tblSpcthd.getValueAt(row, 1).toString();
            float gia = Float.parseFloat(tblSpcthd.getValueAt(row, 2).toString());

            
            String maHD = lblMaHoaDon.getText();  // hoặc lấy từ dòng được chọn trên bảng
            ThemChiTietHoaDon formThem = new ThemChiTietHoaDon(maSP, tenSP, gia, maHD, this);
            formThem.setVisible(true);
            formThem.setSanPham(maSP, tenSP, gia);
            System.out.println("lblMaHoaDon = " + lblMaHoaDon.getText());
        }
    }//GEN-LAST:event_tblSpcthdMouseClicked

    private void btnSuaCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCTHDActionPerformed
        // TODO add your handling code here:
          int row = tblChitiethoadon.getSelectedRow();
        if (row >= 0) {
            try {
                String maCTHD = txtMaChiTietHoaDon.getText();
                int soLuongMoi = (int) spinnerSoLuong.getValue();
                
                // Cập nhật vào database
                ChiTietHoaDon ct = bll.getByMaCTHD(maCTHD);
                ct.setSoLuong(soLuongMoi);
                
                if (bll.updateChiTietHoaDon(ct)) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                    // Cập nhật lại bảng
                    tblChitiethoadon.setValueAt(soLuongMoi, row, 3);
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnSuaCTHDActionPerformed

    private void btnXoaCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCTHDActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnXoaCTHDActionPerformed

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
            java.util.logging.Logger.getLogger(ChiTietHoaDonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiTietHoaDonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiTietHoaDonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiTietHoaDonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new ChiTietHoaDonForm("HD02").setVisible(true); // truyền mã hóa đơn vào
        }
    });
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSuaCTHD;
    private javax.swing.JButton btnXoaCTHD;
    private javax.swing.JComboBox<String> cbloaispcthd;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblMaHoaDon;
    private javax.swing.JLabel lblTenSanPham;
    private javax.swing.JSpinner spinnerSoLuong;
    private javax.swing.JTable tblChitiethoadon;
    private javax.swing.JTable tblSpcthd;
    private javax.swing.JTextField txtMaChiTietHoaDon;
    private javax.swing.JTextField txtTimspcthd;
    // End of variables declaration//GEN-END:variables
    }
