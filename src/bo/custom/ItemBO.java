package bo.custom;

import bo.SuperBO;
import dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {

    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException ;

    public boolean addItems(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException ;

    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException;

}
