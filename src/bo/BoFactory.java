package bo;

import bo.impl.CustomerBOImpl;
import bo.impl.ItemBOImpl;
import bo.impl.PurchaseOrderBOImpl;

public class BoFactory {

    private static BoFactory boFactory;

    private BoFactory() {
    }

    public static BoFactory getBOFactory() {
        if (boFactory == null) {
            boFactory = new BoFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BoTypes types) {
        switch (types) {
            case ITEM:
                return new ItemBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case PURCHASE_ORDER:
                return new PurchaseOrderBOImpl();
            default:
                return null;
        }
    }

    //me thunen ona ekk illuwoth SuperBo type eken one eka return karanwa
    public enum BoTypes {
        CUSTOMER, ITEM, PURCHASE_ORDER
    }

}
