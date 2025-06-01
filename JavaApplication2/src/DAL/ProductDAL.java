/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author Windows
 */
public class ProductDAL {
     private int ProductID;
    private String ProductName;
    private int CategoryID;
    private int SupplierID;
    private int Price;
    private int StockQuantity;
 

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public void setSupplierID(int SupplierID) {
        this.SupplierID = SupplierID;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public void setStockQuantity(int StockQuantity) {
        this.StockQuantity = StockQuantity;
    }

    public void setImagePath(String ImagePath) {
        this.ImagePath = ImagePath;
    }

    public int getProductID() {
        return ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public int getSupplierID() {
        return SupplierID;
    }

    public int getPrice() {
        return Price;
    }

    public int getStockQuantity() {
        return StockQuantity;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public ProductDAL(int ProductID, String ProductName, int CategoryID, int SupplierID, int Price, int StockQuantity, String ImagePath) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.CategoryID = CategoryID;
        this.SupplierID = SupplierID;
        this.Price = Price;
        this.StockQuantity = StockQuantity;
        this.ImagePath = ImagePath;
    }
    private String ImagePath;
}
