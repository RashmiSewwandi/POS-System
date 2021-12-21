package bo.impl;

import bo.custom.CustomerBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import entity.Customer;
import dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
    // return customerDAO.getAll();
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        ArrayList<Customer> all = customerDAO.getAll();
        for (Customer customer : all) {
            allCustomers.add(new CustomerDTO(customer.getId(), customer.getTitle(), customer.getName(),customer.getAddress(),customer.getCity(),customer.getProvince(),customer.getPostalcode()));
        }
        return allCustomers;
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public boolean addCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        System.out.println(dto.getName());
        return customerDAO.add(new Customer(dto.getId(),dto.getTitle(),dto.getName(),dto.getAddress(),dto.getCity(),dto.getProvince(),dto.getPostalCode()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getId(),dto.getTitle(),dto.getName(),dto.getAddress(),dto.getCity(),dto.getProvince(),dto.getPostalCode()));
    }
}
