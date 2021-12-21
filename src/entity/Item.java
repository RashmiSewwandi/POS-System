package entity;

public class Item {

    private String itemcode;
    private String description;
    private String packsize;
    private Double unitprice;
    private int qtyOnHand;

    public Item() {
    }

    public Item(String itemcode, String description, String packsize, Double unitprice, int qtyOnHand) {
        this.setItemcode(itemcode);
        this.setDescription(description);
        this.setPacksize(packsize);
        this.setUnitprice(unitprice);
        this.setQtyOnHand(qtyOnHand);
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPacksize() {
        return packsize;
    }

    public void setPacksize(String packsize) {
        this.packsize = packsize;
    }

    public Double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Double unitprice) {
        this.unitprice = unitprice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }
}
