package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;

    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException ;

    public boolean addCustomer(CustomerDTO id) throws SQLException, ClassNotFoundException ;

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException ;

}
