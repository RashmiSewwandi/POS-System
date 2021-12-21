package dao.custom;

import dto.ItemDTO;
import dao.CrudDAO;
import entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item,String> {

    public List<ItemDTO> SearchItem(String description) throws SQLException, ClassNotFoundException;

    public ItemDTO getItem(String code) throws SQLException, ClassNotFoundException;

    public boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException;

}
