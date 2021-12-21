package dao.custom.impl;

import dto.CustomerDTO;
import dao.CrudUtil;
import dao.custom.CustomerDAO;
import entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean add(Customer dto) throws SQLException, ClassNotFoundException {
        System.out.println(dto.getName());
        return CrudUtil.executeUpdate("INSERT INTO customer VALUES(?,?,?,?,?,?,?)", dto.getId(), dto.getTitle(),
                dto.getName(), dto.getAddress(), dto.getCity(), dto.getProvince(), dto.getPostalcode());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM customer WHERE id='" + id + "'");
    }

    @Override
    public boolean update(Customer dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE customer SET title=?,name=?, address=?,city=?,province=?,postalCode=? WHERE id=?", dto.getTitle(),
                dto.getName(), dto.getAddress(), dto.getCity(), dto.getProvince(), dto.getPostalcode(), dto.getId());
    }

    @Override
    public Customer search(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }


    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = new ArrayList();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM customer");
        while (rst.next()) {
            allCustomers.add(new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            ));
        }
        return allCustomers;
    }

    @Override
    public List<CustomerDTO> SearchName(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM customer WHERE id LIKE ?", "%" + id + "%");
        List<CustomerDTO> customers = new ArrayList<>();
        while (rst.next()) {
            customers.add(new CustomerDTO(
                    rst.getString("id"),
                    rst.getString("title"),
                    rst.getString("name"),
                    rst.getString("address"),
                    rst.getString("city"),
                    rst.getString("province"),
                    rst.getString("postalCode")
            ));
        }
        return customers;
    }

    @Override
    public CustomerDTO getCustomer(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer WHERE id=?", id);
        if (rst.next()) {
            return new CustomerDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)

            );

        } else {
            return null;
        }
    }

}