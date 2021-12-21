package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDAO;
import entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean add(Order dto) throws SQLException, ClassNotFoundException {
       return CrudUtil.executeUpdate("INSERT INTO `Order` VALUES(?,?,?,?,?)",dto.getOrderid(),dto.getId(),dto.getOrderdate(),
          dto.getOrdertime(),dto.getCost());

    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(Order orderDTO) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public Order search(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }


    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }




    @Override
    public String getOrderid() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT orderid FROM `Order` ORDER BY orderid DESC LIMIT 1");
        if (rst.next()) {
            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId = tempId +1;
            if (tempId < 9) {
                return "O-00" + tempId;
            } else if (tempId < 99) {
                return "O-0" + tempId;
            } else {
                return "O-" + tempId;
            }

        } else {
            return "O-001";
        }
    }

}
