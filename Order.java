package model;

public class Order {
    private int orderId;
    private int productId;
    private String productName;
    private int quantity;
    private double totalAmount;
    private String buyerName;
    private String buyerEmail;
    private String buyerPhone;

    public Order(int orderId, int productId, String productName, int quantity, double totalAmount,  String buyerName, String buyerEmail, String buyerPhone) {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.buyerName = buyerName;
        this.buyerEmail = buyerEmail;
        this.buyerPhone = buyerPhone;
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getBuyerName() {
        return buyerName;
    }
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
    public String getBuyerEmail() {
        return buyerEmail;
    }
    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }
    public String getBuyerPhone() {
        return buyerPhone;
    }
    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    @Override
    public String toString() {
        return "Buyer Name: "+buyerName+"\nBuyer Email: "+buyerEmail+"\nBuyer Phone: "+buyerPhone+"\nOrderID: "+orderId+"\nProduct: "+productName+"\nQty: "+quantity+"\nTotal Amount: "+totalAmount;
    }
}