package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ItemDetailDAO;
import entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDetailsDAOImpl implements ItemDetailDAO {


    @Override
    public boolean add(OrderDetail dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO `Order Detail` VALUES(?,?,?,?)",dto.getItemCode(),dto.getOrderid(),
                dto.getQty(),dto.getPrice());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(OrderDetail itemDetailsDTO) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public OrderDetail search(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }




    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

}
