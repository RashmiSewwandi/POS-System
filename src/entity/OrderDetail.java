package entity;

public class OrderDetail {
    private String itemCode;
    private String orderid;
    private int qty ;
    private double price;

    public OrderDetail() {
    }

    public OrderDetail(String itemCode, String orderid, int qty, double price) {
        this.setItemCode(itemCode);
        this.setOrderid(orderid);
        this.setQty(qty);
        this.setPrice(price);
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
