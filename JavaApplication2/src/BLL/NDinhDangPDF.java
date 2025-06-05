/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.ChiTietPhieuNhap;
import DAL.PhieuNhap;
import DAL.SanPham;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.awt.Desktop;
import java.awt.FileDialog;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ngoc
 */
public class NDinhDangPDF {
    private final DecimalFormat formatter = new DecimalFormat("###,###,###");
    private final SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private Document document;
    private FileOutputStream file;
    private final JFrame jf = new JFrame();
    private final FileDialog fd = new FileDialog(jf, "Xuất PDF", FileDialog.SAVE);
    
    // Fonts
    private Font fontTitle;
    private Font fontHeader;
    private Font fontData;
    private Font fontFooter;
    
    // Màu sắc
    private static final BaseColor RED_COLOR = new BaseColor(200, 16, 46); // Màu đỏ đậm #C8102E
    private static final BaseColor GRAY_COLOR = new BaseColor(245, 245, 245); // Màu xám nhạt #F5F5F5
    private static final BaseColor WHITE_COLOR = BaseColor.WHITE;

    public NDinhDangPDF() {
        try {
            // Khởi tạo các font chữ
            BaseFont baseFont;
            BaseFont baseFontBold;
            try {
                baseFont = BaseFont.createFont("font/Roboto-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                baseFontBold = BaseFont.createFont("font/Roboto-Bold.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            } catch (IOException e) {
                // Nếu không tìm thấy font, sử dụng font mặc định
                System.err.println("Không tìm thấy font Roboto. Sử dụng font mặc định.");
                baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                baseFontBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            }
            fontTitle = new Font(baseFontBold, 25, Font.NORMAL, RED_COLOR);
            fontHeader = new Font(baseFontBold, 11, Font.NORMAL, BaseColor.WHITE);
            fontData = new Font(baseFont, 11, Font.NORMAL, BaseColor.BLACK);
            fontFooter = new Font(baseFont, 9, Font.NORMAL, BaseColor.GRAY);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void chooseURL(String url) {
        try {
            if (document != null) {
                document.close();
            }
            document = new Document();
            file = new FileOutputStream(url);
            PdfWriter writer = PdfWriter.getInstance(document, file);
            // Thêm sự kiện để thêm số trang
            writer.setPageEvent(new PdfPageEventHelper() {
                @Override
                public void onEndPage(PdfWriter writer, Document document) {
                    PdfContentByte cb = writer.getDirectContent();
                    Phrase footer = new Phrase(String.format("Trang %d", writer.getPageNumber()), fontFooter);
                    ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, footer,
                            document.right(), document.bottom() - 10, 0);
                }
            });
            document.open();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy đường dẫn file " + url);
            ex.printStackTrace();
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Không thể mở document!");
            ex.printStackTrace();
        }
    }

    private void setHeader(String title) throws DocumentException {
        // Thêm logo
        try {
            java.net.URL logoUrl = getClass().getResource("icon/logo.png");
            if (logoUrl != null) {
                Image logoImage = Image.getInstance(logoUrl);
                logoImage.scaleToFit(100, 100); // Điều chỉnh kích thước logo
                logoImage.setAlignment(Element.ALIGN_CENTER);
                logoImage.setSpacingAfter(10);
                document.add(logoImage);
            } else {
                System.err.println("Không tìm thấy logo tại /icon/logo.png");
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi tải logo: " + e.getMessage());
        }

        // Luôn hiển thị dòng chữ "CỬA HÀNG MÁY TÍNH NGUYÊN KHANH"
        Paragraph logoFallback = new Paragraph("CỬA HÀNG NỘI THẤT THÔNG MINH", new Font(fontTitle.getBaseFont(), 30, Font.BOLD, RED_COLOR));
        logoFallback.setAlignment(Element.ALIGN_CENTER);
        logoFallback.setSpacingAfter(10);
        document.add(logoFallback);

        // Tiêu đề phiếu
        Paragraph pdfTitle = new Paragraph(title, fontTitle);
        pdfTitle.setAlignment(Element.ALIGN_CENTER);
        pdfTitle.setSpacingAfter(10);
        document.add(pdfTitle);

        // Đường kẻ ngang màu đỏ
        LineSeparator line = new LineSeparator();
        line.setLineColor(RED_COLOR);
        line.setLineWidth(1);
        document.add(new Chunk(line));
        document.add(Chunk.NEWLINE);
    }

    private String getFile(String name) {
        fd.pack();
        fd.setSize(800, 600);
        fd.validate();
        Rectangle rect = jf.getContentPane().getBounds();
        double width = fd.getBounds().getWidth();
        double height = fd.getBounds().getHeight();
        double x = rect.getCenterX() - (width / 2);
        double y = rect.getCenterY() - (height / 2);
        Point leftCorner = new Point();
        leftCorner.setLocation(x, y);
        fd.setLocation(leftCorner);
        fd.setFile(name + ".pdf");
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("nullnull")) {
            return null;
        }
        return url;
    }

    public void writePhieuNhap(String mapn) {
        String url = "";
        try {
            fd.setTitle("In Phiếu Nhập");
            fd.setLocationRelativeTo(null);
            url = getFile("PhieuNhap_" + mapn);
            if (url == null) {
                return;
            }
            chooseURL(url);

            // Thiết lập tiêu đề
            setHeader("PHIẾU NHẬP HÀNG");

            // Lấy thông tin phiếu nhập
            PhieuNhap pn = NPhieuNhapBLL.getInstance().selectById(mapn);

            // Phần thông tin phiếu (căn chỉnh 2 cột)
            PdfPTable infoTable = new PdfPTable(2);
            infoTable.setWidthPercentage(100);
            infoTable.setWidths(new float[]{50f, 50f});
            
            PdfPCell cellLeft = new PdfPCell();
            cellLeft.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
            Paragraph paraLeft = new Paragraph();
            paraLeft.setFont(fontData);
            paraLeft.add("Mã phiếu: " + pn.getMaPhieu() + "\n");
            paraLeft.add("Thời gian tạo: " + formatDate.format(pn.getThoiGianTao()));
            cellLeft.addElement(paraLeft);

            PdfPCell cellRight = new PdfPCell();
            cellRight.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
            Paragraph paraRight = new Paragraph();
            paraRight.setFont(fontData);
            paraRight.setAlignment(Element.ALIGN_RIGHT);
            paraRight.add("Nhà cung cấp: " + NNhaCungCapBLL.getInstance().selectById(pn.getNhaCungCap()).getTenNcc());
            cellRight.addElement(paraRight);

            infoTable.addCell(cellLeft);
            infoTable.addCell(cellRight);
            document.add(infoTable);
            document.add(Chunk.NEWLINE);

            // Bảng chi tiết sản phẩm
            PdfPTable pdfTable = new PdfPTable(5);
            pdfTable.setWidthPercentage(100);
            pdfTable.setWidths(new float[]{10f, 30f, 15f, 10f, 15f});
            
            // Tiêu đề bảng
            String[] headers = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Đơn giá", "Số lượng", "Tổng tiền"};
            for (String header : headers) {
                PdfPCell headerCell = new PdfPCell(new Phrase(header, fontHeader));
                headerCell.setBackgroundColor(RED_COLOR);
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                headerCell.setPadding(8);
                headerCell.setBorderColor(RED_COLOR);
                pdfTable.addCell(headerCell);
            }

            // Dữ liệu chi tiết
            for (ChiTietPhieuNhap ctpn : NChiTietPhieuNhapBLL.getInstance().selectAll(mapn)) {
                SanPham mt = NSanPhamBLL.getInstance().selectById(ctpn.getMaSanPham());
                PdfPCell cell;
                
                cell = new PdfPCell(new Phrase(ctpn.getMaSanPham(), fontData));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPadding(5);
                cell.setBackgroundColor(GRAY_COLOR);
                pdfTable.addCell(cell);

                cell = new PdfPCell(new Phrase(mt.getTenSanPham(), fontData));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setPadding(5);
                cell.setBackgroundColor(GRAY_COLOR);
                pdfTable.addCell(cell);

                cell = new PdfPCell(new Phrase(formatter.format(mt.getGia()) + " VNĐ", fontData));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPadding(5);
                cell.setBackgroundColor(GRAY_COLOR);
                pdfTable.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(ctpn.getSoLuong()), fontData));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPadding(5);
                cell.setBackgroundColor(GRAY_COLOR);
                pdfTable.addCell(cell);

                cell = new PdfPCell(new Phrase(formatter.format(ctpn.getSoLuong() * mt.getGia()) + " VNĐ", fontData));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPadding(5);
                cell.setBackgroundColor(GRAY_COLOR);
                pdfTable.addCell(cell);
            }

            document.add(pdfTable);
            document.add(Chunk.NEWLINE);

            // Tổng thanh toán
            Paragraph paraTongThanhToan = new Paragraph("Tổng thanh toán: " + formatter.format(pn.getTongTien()) + " VNĐ", 
                    new Font(fontHeader.getBaseFont(), 12, Font.BOLD, RED_COLOR));
            paraTongThanhToan.setAlignment(Element.ALIGN_RIGHT);
            paraTongThanhToan.setSpacingBefore(10);
            document.add(paraTongThanhToan);

            // Chân trang
            document.add(Chunk.NEWLINE);
            LineSeparator footerLine = new LineSeparator();
            footerLine.setLineColor(RED_COLOR);
            footerLine.setLineWidth(1);
            document.add(new Chunk(footerLine));
            
            Paragraph footer = new Paragraph();
            footer.setFont(fontFooter);
            footer.setAlignment(Element.ALIGN_CENTER);
            footer.add("Chương trình quản lý bán hàng - cửa hàng nội thất thông minh\n");
            footer.add("Địa chỉ: 123C Nguyễn Ái Quốc, Trảng Dài, Biên Hòa, Đồng Nai\n");
            footer.add("Email: cuahangnoithatthongminh@gmail.com | Hotline: 0971 234 500\n");
            footer.add("Ngày in: " + formatDate.format(new Date()));
            document.add(footer);

            document.close();
            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + url);
            openFile(url);

        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi ghi file (DocumentException): " + url);
            ex.printStackTrace();
        }
    }
    
    private void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
