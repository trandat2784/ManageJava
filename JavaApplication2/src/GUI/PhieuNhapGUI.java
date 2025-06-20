/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BLL.NChiTietPhieuNhapBLL;
import BLL.NDinhDangPDF;
import BLL.NNhaCungCapBLL;
import BLL.NPhieuNhapBLL;
import DAL.ChiTietPhieuNhap;
import DAL.PhieuNhap;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Iterator;
import javax.swing.plaf.basic.BasicInternalFrameUI;


public class PhieuNhapGUI extends javax.swing.JFrame   {

   private DefaultTableModel tblModel;
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY HH:mm");
    
     public DecimalFormat getFormatter() {
        return formatter;
    }

    public SimpleDateFormat getFormatDate() {
        return formatDate;
    }
    
    public PhieuNhapGUI()throws SQLException {
        initComponents();
        tblPhieuNhap.setDefaultEditor(Object.class, null);
        initTable();
        initComboBoxData();
        loadDataToTable();
        changeTextFind();
        jDateChooserFrom.setDateFormatString("dd/MM/yyyy");
        jDateChooserTo.setDateFormatString("dd/MM/yyyy");
        
    }
   // Thêm vào constructor hoặc phương thức khởi tạo
    private void initComboBoxData() {
        jComboBoxLuaChon.removeAllItems(); // Xóa hết item cũ nếu có
        jComboBoxLuaChon.addItem("Tất cả");
        jComboBoxLuaChon.addItem("Mã phiếu");
        jComboBoxLuaChon.addItem("Nhà cung cấp");
        jComboBoxLuaChon.setSelectedIndex(0); // Chọn mục đầu tiên
    }
    public final void initTable() {
        tblModel = new DefaultTableModel();
        String[] headerTbl = new String[]{"STT", "Mã phiếu nhập", "Nhà cung cấp", "Thời gian tạo", "Tổng tiền"};
        tblModel.setColumnIdentifiers(headerTbl);
        tblPhieuNhap.setModel(tblModel);
        tblPhieuNhap.getColumnModel().getColumn(0).setPreferredWidth(1);
        tblPhieuNhap.getColumnModel().getColumn(1).setPreferredWidth(2);
        tblPhieuNhap.getColumnModel().getColumn(2).setPreferredWidth(300);
        tblPhieuNhap.getColumnModel().getColumn(3).setPreferredWidth(100);
    }

    public void loadDataToTable() {
        try {
            ArrayList<PhieuNhap> allPhieuNhap = NPhieuNhapBLL.getInstance().selectAll();
            tblModel.setRowCount(0);
            for (int i = 0; i < allPhieuNhap.size(); i++) {
                tblModel.addRow(new Object[]{
                    i + 1, allPhieuNhap.get(i).getMaPhieu(), NNhaCungCapBLL.getInstance().selectById(allPhieuNhap.get(i).getNhaCungCap()).getTenNcc(), formatDate.format(allPhieuNhap.get(i).getThoiGianTao()), formatter.format(allPhieuNhap.get(i).getTongTien()) + "đ"
                });
            }
        } catch (Exception e) {
        }
    }

    private void loadDataToTableSearch(ArrayList<PhieuNhap> result) {
        try {

            tblModel.setRowCount(0);
            for (int i = 0; i < result.size(); i++) {
                tblModel.addRow(new Object[]{
                    i + 1, result.get(i).getMaPhieu(), NNhaCungCapBLL.getInstance().selectById(result.get(i).getNhaCungCap()).getTenNcc(), formatDate.format(result.get(i).getThoiGianTao()), formatter.format(result.get(i).getTongTien()) + "đ"
                });
            }
        } catch (Exception e) {
        }
    }
    
    public ArrayList<PhieuNhap> searchTatCa(String text) {
        ArrayList<PhieuNhap> result = new ArrayList<>();
        ArrayList<PhieuNhap> armt = NPhieuNhapBLL.getInstance().selectAll();
        for (var phieu : armt) {
            if (phieu.getMaPhieu().toLowerCase().contains(text.toLowerCase())
                    || phieu.getNhaCungCap().toLowerCase().contains(text.toLowerCase())
                    ) {
                result.add(phieu);
            }

        }
        return result;
    }

    public ArrayList<PhieuNhap> searchMaPhieuNhap(String text) {
        ArrayList<PhieuNhap> result = new ArrayList<>();
        ArrayList<PhieuNhap> armt = NPhieuNhapBLL.getInstance().selectAll();
        for (var phieu : armt) {
            if (phieu.getMaPhieu().toLowerCase().contains(text.toLowerCase())) {
                result.add(phieu);
            }

        }
        return result;
    }

    public ArrayList<PhieuNhap> searchNhaCungCap(String text) {
        ArrayList<PhieuNhap> result = new ArrayList<>();
        ArrayList<PhieuNhap> armt = NPhieuNhapBLL.getInstance().selectAll();
        for (var phieu : armt) {
            if (phieu.getNhaCungCap().toLowerCase().contains(text.toLowerCase())) {
                result.add(phieu);
            }

        }
        return result;
    }
    
    
    public void changeTextFind() {
        jTextFieldSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                /* do nothing */
                if (jTextFieldSearch.getText().length() == 0) {
                    loadDataToTable();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }
    
    public String createId(ArrayList<PhieuNhap> arr) {
        int id = arr.size() + 1;
        String check = "";
        for (PhieuNhap phieuNhap : arr) {
            if (phieuNhap.getMaPhieu().equals("PN" + id)) {
                check = phieuNhap.getMaPhieu();
            }
        }
        while (check.length() != 0) {
            id++;
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i).getMaPhieu().equals("PN" + id)) {
                    check = arr.get(i).getMaPhieu();
                }
            }
        }
        return "PN" + id;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jComboBoxLuaChon = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jDateChooserFrom = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jDateChooserTo = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        giaTu = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        giaDen = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhieuNhap = new javax.swing.JTable();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDetail = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(33, 47, 61));

        jComboBoxLuaChon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tìm kiếm thông tin");

        jTextFieldSearch.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTextFieldSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchActionPerformed(evt);
            }
        });
        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyReleased(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton7.setText("X");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Từ ngày");

        jDateChooserFrom.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserFromPropertyChange(evt);
            }
        });
        jDateChooserFrom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDateChooserFromKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Đến ngày");

        jDateChooserTo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserToPropertyChange(evt);
            }
        });
        jDateChooserTo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDateChooserToKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooserFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jDateChooserTo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jDateChooserTo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooserFrom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Giá từ");

        giaTu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        giaTu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                giaTuKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Đến");

        giaDen.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        giaDen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                giaDenKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(giaTu, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(giaDen, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(giaTu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(giaDen, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblPhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPhieuNhap.setRowHeight(25);
        jScrollPane1.setViewportView(tblPhieuNhap);

        btnDelete.setBackground(new java.awt.Color(33, 47, 61));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Xóa phiếu");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(33, 47, 61));
        btnEdit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Sửa phiếu");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDetail.setBackground(new java.awt.Color(33, 47, 61));
        btnDetail.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDetail.setForeground(new java.awt.Color(255, 255, 255));
        btnDetail.setText("Xem chi tiết");
        btnDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(33, 47, 61));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Xuất PDF");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setBackground(new java.awt.Color(33, 47, 61));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 27)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(33, 47, 61));
        jLabel6.setText("QUẢN LÍ PHIẾU NHẬP");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(297, 297, 297)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBoxLuaChon, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(jLabel5)
                        .addGap(17, 17, 17)
                        .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(30, 43, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxLuaChon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel2.getAccessibleContext().setAccessibleName("Lọc theo ngày");
        jPanel2.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
         if (tblPhieuNhap.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu cần xoá");
        } else {
            deletePhieuNhap(getPhieuNhapSelect());
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    public void deletePhieuNhap(PhieuNhap pn) {
        int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá " + pn.getMaPhieu(), "Xác nhận xoá phiếu", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            ArrayList<ChiTietPhieuNhap> ctPhieuNhap = NChiTietPhieuNhapBLL.getInstance().selectAll(pn.getMaPhieu());
            for (ChiTietPhieuNhap i : ctPhieuNhap) {
                NChiTietPhieuNhapBLL.getInstance().delete(i);
            }
            NPhieuNhapBLL.getInstance().delete(pn);
            JOptionPane.showMessageDialog(this, "Đã xoá thành công phiếu " + pn.getMaPhieu());
            loadDataToTable();
        }
    }
    
    private void jDateChooserFromKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooserFromKeyReleased
        // TODO add your handling code here:
                searchAllRepect();

    }//GEN-LAST:event_jDateChooserFromKeyReleased

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
       if (tblPhieuNhap.getSelectedRow() == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu cần sửa");
    } else {
        try {
            CapNhatPhieuNhap a = new CapNhatPhieuNhap(this, true); // sửa chỗ này
            a.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(PhieuNhapGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }//GEN-LAST:event_btnEditActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        loadDataToTable();
        initComboBoxData();
        //jComboBoxLuaChon.setSelectedIndex();
        jTextFieldSearch.setText("");
        jDateChooserFrom.setCalendar(null);
        jDateChooserTo.setCalendar(null);
        giaDen.setText("");
        giaTu.setText("");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       
//    try {
//        JFileChooser jFileChooser = new JFileChooser();
//        jFileChooser.setDialogTitle("Chọn nơi lưu file Excel");
//        int userSelection = jFileChooser.showSaveDialog(this);
//        
//        if (userSelection == JFileChooser.APPROVE_OPTION) {
//            File saveFile = jFileChooser.getSelectedFile();
//            // Thêm đuôi .xlsx nếu chưa có
//            if (!saveFile.getName().toLowerCase().endsWith(".xlsx")) {
//                saveFile = new File(saveFile.getAbsolutePath() + ".xlsx");
//            }
//            
//            // Tạo workbook và sheet
//            Workbook wb = new XSSFWorkbook();
//            Sheet sheet = wb.createSheet("PhieuNhap");
//
//            // Tạo header row
//            Row headerRow = sheet.createRow(0);
//            for (int i = 0; i < tblPhieuNhap.getColumnCount(); i++) {
//                Cell cell = headerRow.createCell(i);
//                cell.setCellValue(tblPhieuNhap.getColumnName(i));
//            }
//
//            // Thêm dữ liệu từ table
//            for (int row = 0; row < tblPhieuNhap.getRowCount(); row++) {
//                Row sheetRow = sheet.createRow(row + 1);
//                for (int col = 0; col < tblPhieuNhap.getColumnCount(); col++) {
//                    Cell cell = sheetRow.createCell(col);
//                    Object value = tblPhieuNhap.getValueAt(row, col);
//                    if (value != null) {
//                        cell.setCellValue(value.toString());
//                    }
//                }
//            }
//
//            // Tự động điều chỉnh độ rộng cột
//            for (int i = 0; i < tblPhieuNhap.getColumnCount(); i++) {
//                sheet.autoSizeColumn(i);
//            }
//
//            // Lưu file
//            try (FileOutputStream out = new FileOutputStream(saveFile)) {
//                wb.write(out);
//                JOptionPane.showMessageDialog(this, "Xuất file Excel thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//            }
//            
//            wb.close();
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//        JOptionPane.showMessageDialog(this, "Lỗi khi xuất file: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//    }

         NDinhDangPDF writepdf = new NDinhDangPDF();
        writepdf.writePhieuNhap(this.getPhieuNhapSelect().getMaPhieu());

    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailActionPerformed
        // TODO add your handling code here:
         if (tblPhieuNhap.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu !");
        } else {
            CTPhieuNhap a = new CTPhieuNhap(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), rootPaneCheckingEnabled);
            a.setVisible(true);
        }
    }//GEN-LAST:event_btnDetailActionPerformed

    private void jTextFieldSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchActionPerformed

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyReleased
        // TODO add your handling code here:
         searchAllRepect();
    }//GEN-LAST:event_jTextFieldSearchKeyReleased

    private void giaTuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_giaTuKeyReleased
        // TODO add your handling code here:
         searchAllRepect();
    }//GEN-LAST:event_giaTuKeyReleased

    private void jDateChooserToKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooserToKeyReleased
        // TODO add your handling code here: searchAllRepect();
         searchAllRepect();
    }//GEN-LAST:event_jDateChooserToKeyReleased

    private void giaDenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_giaDenKeyReleased
        // TODO add your handling code here:
        searchAllRepect();
        System.out.println(giaDen.getText());
    }//GEN-LAST:event_giaDenKeyReleased

    private void jDateChooserToPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserToPropertyChange
        // TODO add your handling code here:
         searchAllRepect();
    }//GEN-LAST:event_jDateChooserToPropertyChange

    private void jDateChooserFromPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserFromPropertyChange
        // TODO add your handling code here:
         searchAllRepect();
    }//GEN-LAST:event_jDateChooserFromPropertyChange


     public PhieuNhap getPhieuNhapSelect() {
        int i_row = tblPhieuNhap.getSelectedRow();
        PhieuNhap pn = NPhieuNhapBLL.getInstance().selectById(tblModel.getValueAt(i_row, 1).toString());
        return pn;
    }

    public boolean checkDate(Date dateTest, Date star, Date end) {
        return dateTest.getTime() >= star.getTime() && dateTest.getTime() <= end.getTime();
    }

    public ArrayList<PhieuNhap> searchDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        ArrayList<PhieuNhap> result = new ArrayList<PhieuNhap>();
        Date from = jDateChooserFrom.getDate();
        Date to = jDateChooserTo.getDate();
        ArrayList<PhieuNhap> armt = NPhieuNhapBLL.getInstance().selectAll();
        for (var phieu : armt) {
            System.out.println("From:" + from + " " + from.getTime());
            System.out.println("To: " + to + " " + to.getTime());
            System.out.println("Current: " + phieu.getThoiGianTao() + " " + phieu.getThoiGianTao().getTime());
            System.out.println("Check: " + checkDate(phieu.getThoiGianTao(), from, to));
            if (checkDate(phieu.getThoiGianTao(), from, to)) {
                result.add(phieu);
            }

        }
        return result;
    }
    
    private void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void searchAllRepect() {
       String luaChon = (jComboBoxLuaChon.getSelectedItem() != null) ? jComboBoxLuaChon.getSelectedItem().toString() : "";

       // String luaChon = jComboBoxLuaChon.getSelectedItem().toString();
        String content = jTextFieldSearch.getText();
        ArrayList<PhieuNhap> result = null;
        if (content.length() > 0) {
            result = new ArrayList<>();
            switch (luaChon) {
                case "Tất cả":
                    result = searchTatCa(content);
                    break;
                case "Mã phiếu":
                    result = searchMaPhieuNhap(content);
                    break;
                case "Nhà cung cấp":
                    result = searchNhaCungCap(content);
                    break;
            }
        } else if (content.length() == 0) {
            result = NPhieuNhapBLL.getInstance().selectAll();
        }
        Iterator<PhieuNhap> itr = result.iterator();
        if (jDateChooserFrom.getDate() != null || jDateChooserTo.getDate() != null) {
            Date from;
            Date to;
            if (jDateChooserFrom.getDate() != null && jDateChooserTo.getDate() == null) {
                try {
                    from = ChangeFrom(jDateChooserFrom.getDate());
                    to = ChangeTo(new Date());
                    while (itr.hasNext()) {
                        PhieuNhap phieu = itr.next();
                        if (!checkDate(phieu.getThoiGianTao(), from, to)) {
                            itr.remove();
                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(PhieuNhapGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (jDateChooserTo.getDate() != null && jDateChooserFrom.getDate() == null) {
                try {
                    String sDate1 = "01/01/2002";
                    from = ChangeFrom(new SimpleDateFormat("dd/MM/yyyy").parse(sDate1));
                    to = ChangeTo(jDateChooserTo.getDate());
                    while (itr.hasNext()) {
                        PhieuNhap phieu = itr.next();
                        if (!checkDate(phieu.getThoiGianTao(), from, to)) {
                            itr.remove();
                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(PhieuNhapGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    from = ChangeFrom(jDateChooserFrom.getDate());
                    to = ChangeTo(jDateChooserTo.getDate());
                    if (from.getTime() > to.getTime()) {
                        JOptionPane.showMessageDialog(this, "Thời gian không hợp lệ !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                        jDateChooserFrom.setCalendar(null);
                        jDateChooserTo.setCalendar(null);
                    } else {
                        while (itr.hasNext()) {
                            PhieuNhap phieu = itr.next();
                            if (!checkDate(phieu.getThoiGianTao(), from, to)) {
                                itr.remove();
                            }
                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(PhieuNhapGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        ArrayList<PhieuNhap> result1 = new ArrayList<>();
        if (giaTu.getText().length() > 0 || giaDen.getText().length() > 0) {
            double a;
            double b;
            if (giaTu.getText().length() > 0 && giaDen.getText().length() == 0) {
                a = Double.parseDouble(giaTu.getText());
                for (int i = 0; i < result.size(); i++) {
                    if (result.get(i).getTongTien() >= a) {
                        result1.add(result.get(i));
                    }
                }
            } else if (giaTu.getText().length() == 0 && giaDen.getText().length() > 0) {;
                b = Double.parseDouble(giaDen.getText());
                for (int i = 0; i < result.size(); i++) {
                    if (result.get(i).getTongTien() <= b) {
                        result1.add(result.get(i));
                    }
                }
            } else if (giaTu.getText().length() > 0 && giaDen.getText().length() > 0) {
                a = Double.parseDouble(giaTu.getText());
                b = Double.parseDouble(giaDen.getText());
                for (int i = 0; i < result.size(); i++) {
                    if (result.get(i).getTongTien() >= a && result.get(i).getTongTien() <= b) {
                        result1.add(result.get(i));
                    }
                }
            }
        }
        if (giaTu.getText().length() > 0 || giaDen.getText().length() > 0) {
            loadDataToTableSearch(result1);
        } else {
            loadDataToTableSearch(result);
        }
    }

    public Date ChangeFrom(Date date) throws ParseException {
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy 00:00:00");
        String dateText = fm.format(date);
        SimpleDateFormat par = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        Date result = par.parse(dateText);
        return result;
    }

    public Date ChangeTo(Date date) throws ParseException {
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy 23:59:59");
        String dateText = fm.format(date);
        SimpleDateFormat par = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        Date result = par.parse(dateText);
        return result;
    }

    
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PhieuNhapGUI().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(PhieuNhapGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnEdit;
    private javax.swing.JTextField giaDen;
    private javax.swing.JTextField giaTu;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBoxLuaChon;
    private com.toedter.calendar.JDateChooser jDateChooserFrom;
    private com.toedter.calendar.JDateChooser jDateChooserTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JTable tblPhieuNhap;
    // End of variables declaration//GEN-END:variables
}
