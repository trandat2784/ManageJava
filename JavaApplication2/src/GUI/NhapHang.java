/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BLL.NChiTietPhieuNhapBLL;
import BLL.NDinhDangPDF;
import BLL.NLoaiSanPhamBLL;
import BLL.loaisanphamBLL;
import BLL.NNhaCungCapBLL;
import BLL.NPhieuNhapBLL;
import BLL.NSanPhamBLL;
import DAL.ChiTietPhieuNhap;
import DAL.LoaiSanPham;
import DAL.NhaCungCap;
import DAL.PhieuNhap;
import DAL.SanPham;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import javax.swing.table.DefaultTableModel;


public class NhapHang extends javax.swing.JFrame {
    
    ArrayList<SanPham> dssp;
    private DefaultTableModel tblModel;
    private DefaultTableModel tblMode2;
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    private ArrayList<SanPham> allProduct;
    private String MaPhieu;
    private ArrayList<ChiTietPhieuNhap> CTPhieu;
    private static final ArrayList<NhaCungCap> arrNcc = NNhaCungCapBLL.getInstance().selectAll();
    
    public NhapHang() throws SQLException {
        initComponents();
        allProduct = NSanPhamBLL.getInstance().selectAllExist();
        initTable();
        loadDataToTableProduct(allProduct);
        loadNccToComboBox();
        loadToComboBox();
        tblSanPham.setDefaultEditor(Object.class, null);
        tblNhapHang.setDefaultEditor(Object.class, null);
        MaPhieu = createId(NPhieuNhapBLL.getInstance().selectAll());
        txtMaPhieu.setText(MaPhieu);
        CTPhieu = new ArrayList<ChiTietPhieuNhap>();
    }

    
    private void loadNccToComboBox() {
        for (NhaCungCap i : arrNcc) {
            cboNhaCungCap.addItem(i.getTenNcc());
        }
    }
    
    private void loadToComboBox() {
        NLoaiSanPhamBLL dmDao = new NLoaiSanPhamBLL();
        var danhMucList = dmDao.docdanhmuc(); 
        DefaultComboBoxModel<String> cboModel = new DefaultComboBoxModel<>();
        for (LoaiSanPham dm : danhMucList) 
        {
            cboModel.addElement(dm.getTenLoai());
        }
       cboLoaiSanPham.setModel(cboModel); 
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
        tblSanPham.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblSanPham.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblNhapHang.getColumnModel().getColumn(0).setPreferredWidth(90);
        tblNhapHang.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblNhapHang.getColumnModel().getColumn(2).setPreferredWidth(50);
    }

    private void loadDataToTableProduct(ArrayList<SanPham> arrProd) {
        try {
            tblModel.setRowCount(0);
            for (var i : arrProd) {
                tblModel.addRow(new Object[]{
                    i.getMaSanPham(), i.getTenSanPham(), i.getSoLuongTon(),formatter.format(i.getGia()), i.getMaLoai(),i.getMaNcc(),i.getDuongDanAnh() + "đ"
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

    public void loadDataToTableNhapHang() {
        double sum = 0;
        try {
            DefaultTableModel tblNhapHangmd = (DefaultTableModel) tblNhapHang.getModel();
            tblNhapHangmd.setRowCount(0);

            for (int i = 0; i < CTPhieu.size(); i++) {
                tblNhapHangmd.addRow(new Object[]{
                    i+1, findMayTinh(CTPhieu.get(i).getMaSanPham()).getTenSanPham(), CTPhieu.get(i).getSoLuong() , formatter.format(CTPhieu.get(i).getDonGia())+ "đ"
                });
                sum += CTPhieu.get(i).getDonGia() * CTPhieu.get(i).getSoLuong();
            }
        } catch (Exception e) {
        }
        textTongTien.setText(formatter.format(sum) + "đ");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cboLoaiSanPham = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNhapHang = new javax.swing.JTable();
        txtTim = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        addProduct = new javax.swing.JButton();
        textTongTien = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaPhieu = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboNhaCungCap = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        deleteProduct = new javax.swing.JButton();
        btnNhapHang = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        txtSearch = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cboLoaiSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboLoaiSanPhamMouseClicked(evt);
            }
        });
        cboLoaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiSanPhamActionPerformed(evt);
            }
        });
        getContentPane().add(cboLoaiSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 140, 30));

        tblNhapHang.setBackground(new java.awt.Color(204, 204, 204));
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

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, 500, 220));

        txtTim.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });
        getContentPane().add(txtTim, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 160, -1));

        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtSoLuong.setText("1");
        getContentPane().add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 480, 60, -1));

        addProduct.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addProduct.setText("Thêm");
        addProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductActionPerformed(evt);
            }
        });
        getContentPane().add(addProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 480, -1, -1));

        textTongTien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        textTongTien.setForeground(new java.awt.Color(255, 51, 51));
        textTongTien.setText("0 Đ");
        getContentPane().add(textTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 430, 137, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tìm kiếm");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setText("Tổng tiền :");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 430, -1, -1));

        txtMaPhieu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        getContentPane().add(txtMaPhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 320, 340, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mã phiếu nhập");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 330, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nhà cung cấp");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 380, -1, -1));

        cboNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        getContentPane().add(cboNhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 380, 340, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("Cập nhật số lượng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 480, 190, 42));

        deleteProduct.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        deleteProduct.setText("Xóa sản phẩm");
        deleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteProductActionPerformed(evt);
            }
        });
        getContentPane().add(deleteProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 480, -1, 42));

        btnNhapHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnNhapHang.setText("Nhập hàng");
        btnNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapHangActionPerformed(evt);
            }
        });
        getContentPane().add(btnNhapHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 480, -1, 42));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Chọn số lượng hàng");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, -1, -1));

        jPanel1.setBackground(new java.awt.Color(33, 47, 61));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        tblSanPham.setToolTipText("");
        tblSanPham.setRowHeight(25);
        jScrollPane1.setViewportView(tblSanPham);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 340));

        txtSearch.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtSearch.setText("xóa");
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 60, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1030, 500));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 27)); // NOI18N
        jLabel6.setText("QUẢN LÍ NHẬP HÀNG");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(372, 372, 372)
                .addComponent(jLabel6))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel6))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 80));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboLoaiSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLoaiSanPhamMouseClicked
    
    }//GEN-LAST:event_cboLoaiSanPhamMouseClicked

    private void cboLoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiSanPhamActionPerformed
      if (cboLoaiSanPham.getSelectedItem() != null) {
        String tenloai = cboLoaiSanPham.getSelectedItem().toString();
        filltotable(tenloai);}
    }//GEN-LAST:event_cboLoaiSanPhamActionPerformed
         public void filltotable(String tenLoai)
        {
            NSanPhamBLL spdao= new NSanPhamBLL();
            dssp = spdao.docdanhsachsanphamtheodm(tenLoai);
            DefaultTableModel dftbl =(DefaultTableModel) tblSanPham.getModel();
            dftbl.setRowCount(0);
            for(int i=0; i<dssp.size(); i++)
            {
                String masanpham=dssp.get(i).getMaSanPham();
                String tensanpham=dssp.get(i).getTenSanPham();
                int soluong = dssp.get(i).getSoLuongTon();
                String dongia=String.valueOf(dssp.get(i).getGia());

                Object[] row=new Object[] {masanpham, tensanpham, soluong, dongia};
                dftbl.addRow(row);
            }
        }
    private void txtTimKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyPressed
       
    }//GEN-LAST:event_txtTimKeyPressed

    private void addProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductActionPerformed
        int i_row = tblSanPham.getSelectedRow();
        if (i_row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để nhập hàng !");
        } else {
            int soluong;
            try {
                soluong = Integer.parseInt(txtSoLuong.getText().trim());
                if (soluong > 0) {
                    System.out.println("sinh");
                    ChiTietPhieuNhap mtl = findCTPhieu((String) tblSanPham.getValueAt(i_row, 0));
                    if (mtl != null) {
                        mtl.setSoLuong(mtl.getSoLuong() + soluong);
                    } else {
                        SanPham mt = NSanPhamBLL.getInstance().TimKiemId((String) tblSanPham.getValueAt(i_row, 0));
                        ChiTietPhieuNhap ctp = new ChiTietPhieuNhap(MaPhieu, mt.getMaSanPham(), soluong, mt.getGia());
                        CTPhieu.add(ctp);
                    }
                    loadDataToTableNhapHang();
                    textTongTien.setText(formatter.format(tinhTongTien()) + "đ");
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng lớn hơn 0");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng ở dạng số nguyên!");
            }
        }
    }//GEN-LAST:event_addProductActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int i_row = tblNhapHang.getSelectedRow();
        if (i_row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xoá sửa số lượng !");
        } else {
            String newSL = JOptionPane.showInputDialog(this, "Nhập số lượng cần thay đổi", "Thay đổi số lượng", QUESTION_MESSAGE);
            if (newSL != null) {
                int soLuong;
                try {
                    soLuong = Integer.parseInt(newSL);
                    if (soLuong > 0) {
                        CTPhieu.get(i_row).setSoLuong(soLuong);
                        loadDataToTableNhapHang();
                        textTongTien.setText(formatter.format(tinhTongTien()) + "đ");
                    } else {
                        JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng lớn hơn 0");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng ở dạng số nguyên!");
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void deleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteProductActionPerformed
        int i_row = tblNhapHang.getSelectedRow();
        if (i_row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xoá khỏi bảng nhập hàng !");
        } else {
            CTPhieu.remove(i_row);
            loadDataToTableNhapHang();
            textTongTien.setText(formatter.format(tinhTongTien()) + "đ");
        }
    }//GEN-LAST:event_deleteProductActionPerformed

    private void btnNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapHangActionPerformed
        if (CTPhieu.size() == 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm để nhập hàng !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        } else {
            int check = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn nhập hàng ?", "Xác nhận nhập hàng", JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.YES_OPTION) {
                long now = System.currentTimeMillis();
                Timestamp sqlTimestamp = new Timestamp(now);
                PhieuNhap pn = new PhieuNhap(arrNcc.get(cboNhaCungCap.getSelectedIndex()).getMaNcc(), MaPhieu, sqlTimestamp, CTPhieu, tinhTongTien());
                try {
                    NPhieuNhapBLL.getInstance().insert(pn);
                    NSanPhamBLL mtdao = NSanPhamBLL.getInstance();
                    for (var i : CTPhieu) {
                        NChiTietPhieuNhapBLL.getInstance().insert(i);
                        mtdao.updateSoLuong(i.getMaSanPham(), mtdao.selectById(i.getMaSanPham()).getSoLuongTon() + i.getSoLuong());
                    }
                    JOptionPane.showMessageDialog(this, "Nhập hàng thành công !");
                    int res = JOptionPane.showConfirmDialog(this, "Bạn có muốn xuất file pdf ?","",JOptionPane.YES_NO_OPTION);
                    if (res == JOptionPane.YES_OPTION) {
                        NDinhDangPDF writepdf = new NDinhDangPDF();
                        writepdf.writePhieuNhap(MaPhieu);
                    }
                    ArrayList<SanPham> productUp = NSanPhamBLL.getInstance().selectAllExist();
                    txtSoLuong.setText("1");
                    loadDataToTableProduct(productUp);
                    DefaultTableModel r = (DefaultTableModel) tblNhapHang.getModel();
                    r.setRowCount(0);
                    CTPhieu = new ArrayList<>();
                    textTongTien.setText("0");
                    this.MaPhieu = createId(NPhieuNhapBLL.getInstance().selectAll());
                    txtMaPhieu.setText(this.MaPhieu);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi !");
                }
            }
        }
    }//GEN-LAST:event_btnNhapHangActionPerformed

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
        DefaultTableModel tblsp = (DefaultTableModel) tblSanPham.getModel();
        String textSearch = txtTim.getText().toLowerCase();
        ArrayList<SanPham> Mtkq = new ArrayList<>();
        for (SanPham i : allProduct) {
            if (i.getMaSanPham().concat(i.getTenSanPham()).toLowerCase().contains(textSearch)) {
                Mtkq.add(i);
            }
        }
        loadDataToTableProduct(Mtkq);
    }//GEN-LAST:event_txtTimKeyReleased

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
        txtSearch.setText("");
        loadDataToTableProduct(allProduct);
        
    }//GEN-LAST:event_txtSearchActionPerformed

    public String createId(ArrayList<PhieuNhap> arr) {
        int id = arr.size() + 1;
        String check = "";
        for (PhieuNhap phieuNhap : arr) {
            if (phieuNhap.getMaPhieu().equals("PN" + id)) {
                check = phieuNhap.getMaPhieu();
            }
        }
        while (check.length() != 0) {
            String c = check;
            id++;
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i).getMaPhieu().equals("PN" + id)) {
                    check = arr.get(i).getMaPhieu();
                }
            }
            if (check.equals(c)) {
                check = "";
            }
        }
        return "PN" + id;
    }
     
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new NhapHang().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(NhapHang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProduct;
    private javax.swing.JButton btnNhapHang;
    private javax.swing.JComboBox<String> cboLoaiSanPham;
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
    private javax.swing.JButton txtSearch;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
