package bo.impl;

import bo.custom.PurchaseOrderBO;
import dao.DAOFactory;
import dao.custom.*;
import db.DbConnection;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.ItemDetailsDTO;
import dto.OrderDTO;
import entity.Customer;
import entity.Item;
import entity.Order;
import entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

//OrderController
public class PurchaseOrderBOImpl implements PurchaseOrderBO {
   //onema table ekakata data danna ganna ehema puluwan

   private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
   private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
   private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
   private final ItemDetailDAO itemDetailDAO = (ItemDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEMDETAILS);



   @Override
   public boolean placeOrder(OrderDTO order) throws SQLException, ClassNotFoundException {
      //saveOrder
      Connection con=null;
         con = DbConnection.getInstance().getConnection();
         con.setAutoCommit(false);

         boolean add = orderDAO.add(new Order(order.getOrderid(),order.getId(),order.getOrderdate(),order.getOrdertime(),order.getCost()));

         if(add){
            if(saveOrderDetail(order.getItems())){
               con.commit();
               con.setAutoCommit(true);
               return true;

            }else{
               con.rollback();
               con.setAutoCommit(true);
               return false;

            }

         }else {
            con.rollback();
            con.setAutoCommit(true);
            return false;
         }

   }



   @Override
   public boolean saveOrderDetail( ArrayList<ItemDetailsDTO> items) throws SQLException, ClassNotFoundException {
      for (ItemDetailsDTO temp : items
      ) {

         //tiugth cuple> looscople
       //  ItemDetailsDTO itemDetailsDTO = new ItemDetailsDTO(temp.getItemCode(),orderid,temp.getQtyForSell(),temp.getUnitPrice());
         boolean add =itemDetailDAO.add(new OrderDetail(temp.getItemCode(),temp.getOrderid(),temp.getQtyForSell(),temp.getUnitPrice()));

         if (add) {
            if (itemDAO.updateQty(temp.getItemCode(), temp.getQtyForSell())){
            }else {
               return false;
            }


         } else {
            return false;
         }
      }
      return true;
   }

   @Override
   public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
      ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
      ArrayList<Customer> all = customerDAO.getAll();
      for (Customer c : all) {
         allCustomers.add(new CustomerDTO(c.getId(),c.getTitle(),c.getName(),c.getAddress(),c.getCity(),c.getProvince(),c.getPostalcode()));
      }
      return allCustomers;
   }

   @Override
   public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
      ArrayList<ItemDTO> allItems = new ArrayList<>();
      ArrayList<Item> all = itemDAO.getAll();
      for (Item item : all) {
         allItems.add(new ItemDTO(item.getItemcode(), item.getDescription(), item.getPacksize(), item.getUnitprice(),item.getQtyOnHand()));
      }
      return allItems;
   }
}
