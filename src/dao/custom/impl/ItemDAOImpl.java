package dao.custom.impl;

import dto.ItemDTO;
import dao.CrudUtil;
import dao.custom.ItemDAO;
import entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean add(Item dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Item VALUES (?,?,?,?,?)", dto.getItemcode(), dto.getDescription(),
                dto.getPacksize(), dto.getUnitprice(), dto.getQtyOnHand());
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Item WHERE itemCode='" + code + "'");
    }

    @Override
    public boolean update(Item dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Item SET description=?,packSize=?, unitPrice=?,qtyOnHand=? WHERE itemCode=?", dto.getDescription(),
                dto.getPacksize(), dto.getUnitprice(), dto.getQtyOnHand(), dto.getItemcode());
    }

    @Override
    public Item search(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public List<ItemDTO> SearchItem(String description) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item WHERE description LIKE ?", "%" + description + "%");
        List<ItemDTO> items = new ArrayList<>();
        if (rst.next()) {
            items.add(new ItemDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5)
            ));
        }
        return items;

    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item");
        ArrayList<Item> allItems = new ArrayList();
        while (rst.next()) {
            allItems.add(new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5)

            ));
        }
        return allItems;
    }


    @Override
    public ItemDTO getItem(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item WHERE itemCode=?", code);

        if (rst.next()) {
            return new ItemDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5)
            );
        } else {
            return null;
        }
    }

    @Override
    public boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE ITEM SET qtyOnHand=(qtyOnHand-" + qty + ") WHERE itemCode='" + itemCode + "'");
    }


}






