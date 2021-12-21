package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.ItemDetailsDTO;
import dto.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PurchaseOrderBO extends SuperBO {

     ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

     ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;

     public boolean placeOrder(OrderDTO order) throws SQLException, ClassNotFoundException;

     public boolean saveOrderDetail( ArrayList<ItemDetailsDTO> items) throws SQLException, ClassNotFoundException;
}
