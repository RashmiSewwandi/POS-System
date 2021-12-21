package dto;

import java.io.Serializable;

public class CustomDTO implements Serializable {
    private String orderid;
    private String id;
    private String orderdate;
    private String ordertime;
    private double cost;
    private String itemCode;
    private int qtyForSell;
    private double unitPrice;

    public CustomDTO() {
    }

    public CustomDTO(String orderid, String id, String orderdate, String ordertime, double cost, String itemCode, int qtyForSell, double unitPrice) {
        this.setOrderid(orderid);
        this.setId(id);
        this.setOrderdate(orderdate);
        this.setOrdertime(ordertime);
        this.setCost(cost);
        this.setItemCode(itemCode);
        this.setQtyForSell(qtyForSell);
        this.setUnitPrice(unitPrice);
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQtyForSell() {
        return qtyForSell;
    }

    public void setQtyForSell(int qtyForSell) {
        this.qtyForSell = qtyForSell;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "CustomDTO{" +
                "orderid='" + orderid + '\'' +
                ", id='" + id + '\'' +
                ", orderdate='" + orderdate + '\'' +
                ", ordertime='" + ordertime + '\'' +
                ", cost=" + cost +
                ", itemCode='" + itemCode + '\'' +
                ", qtyForSell=" + qtyForSell +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
