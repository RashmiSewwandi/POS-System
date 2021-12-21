package entity;

public class Order {

    private String orderid;
    private String id;
    private String orderdate;
    private String ordertime;
    private double cost;

    public Order() {
    }

    public Order(String orderid, String id, String orderdate, String ordertime, double cost) {
        this.setOrderid(orderid);
        this.setId(id);
        this.setOrderdate(orderdate);
        this.setOrdertime(ordertime);
        this.setCost(cost);
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
}
