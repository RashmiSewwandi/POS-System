package dao.custom;

import dto.CustomerDTO;
import dao.CrudDAO;
import entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer,String> {

  public  List<CustomerDTO> SearchName(String id) throws SQLException, ClassNotFoundException;
  public CustomerDTO getCustomer(String id) throws SQLException, ClassNotFoundException;




    //public List<String> getCustomerIds() throws SQLException, ClassNotFoundException;

}
