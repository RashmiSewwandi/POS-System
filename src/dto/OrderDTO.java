package dto;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderDTO implements Serializable {
    private String orderid;
    private String id;
    private String orderdate;
    private String ordertime;
    private double cost;
    private ArrayList<ItemDetailsDTO> items;



    public OrderDTO(String orderid, String id, String orderdate, String ordertime, double cost) {
        this.orderid = orderid;
        this.id = id;
        this.orderdate = orderdate;
        this.ordertime = ordertime;
        this.cost = cost;
    }

    public OrderDTO(String orderid, String id, String orderdate, String ordertime, double cost, ArrayList<ItemDetailsDTO> items) {
        this.setOrderid(orderid);
        this.setId(id);
        this.setOrderdate(orderdate);
        this.setOrdertime(ordertime);
        this.setCost(cost);
        this.setItems(items);
    }

    public OrderDTO(String itemCode, int qty) {
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

    public ArrayList<ItemDetailsDTO> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemDetailsDTO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderid='" + orderid + '\'' +
                ", id='" + id + '\'' +
                ", orderdate='" + orderdate + '\'' +
                ", ordertime='" + ordertime + '\'' +
                ", cost=" + cost +
                ", items=" + items +
                '}';
    }
}
