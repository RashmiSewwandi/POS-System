package bo.impl;

import bo.custom.ItemBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dto.ItemDTO;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
       //return itemDAO.getAll();
        ArrayList<ItemDTO> allItems = new ArrayList<>();
        ArrayList<Item> all = itemDAO.getAll();
        for (Item i : all) {
            allItems.add(new ItemDTO(i.getItemcode(), i.getDescription(), i.getPacksize(), i.getUnitprice(),i.getQtyOnHand()));
        }
        return allItems;

    }

    @Override
    public boolean addItems(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.add(new Item(dto.getCode(),dto.getDescription(),dto.getPackSize(),dto.getUnitPrize(),dto.getQtyOnHand()));
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(dto.getCode(),dto.getDescription(),dto.getPackSize(),dto.getUnitPrize(),dto.getQtyOnHand()));
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }

}
