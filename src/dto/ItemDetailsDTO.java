package dto;

import java.io.Serializable;

public class ItemDetailsDTO implements Serializable  {
    private String itemCode;
    private String orderid;
    private int qtyForSell;
    private double unitPrice;

    public ItemDetailsDTO() {
    }

    public ItemDetailsDTO(String itemCode, String orderid, int qtyForSell, double unitPrice) {
        this.setItemCode(itemCode);
        this.setOrderid(orderid);
        this.setQtyForSell(qtyForSell);
        this.setUnitPrice(unitPrice);
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
        return "ItemDetailsDTO{" +
                "itemCode='" + itemCode + '\'' +
                ", orderid='" + orderid + '\'' +
                ", qtyForSell=" + qtyForSell +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
