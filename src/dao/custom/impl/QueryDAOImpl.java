package dao.custom.impl;

import dto.CustomDTO;
import dao.CrudUtil;
import dao.custom.QueryDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public ArrayList <CustomDTO> getOrderDetailsFromOrderID(String orderid) throws SQLException, ClassNotFoundException {
        //custom type eke data witharai danna puluwan
        ArrayList <CustomDTO>allData= new ArrayList();
        ResultSet rst = CrudUtil.executeQuery("select o.orderid,o.id,o.orderdate,o.ordertime,o.cost,od.itemcode,od.orderid,od.qty,od.price from `order` o inner join `Order Detail` od on o.orderid=od.orderid where o.orderid=? ;",orderid);
        while (rst.next()) {
            allData.add( new CustomDTO(rst.getString("orderid"),
                    rst.getString("id"),
                    rst.getString("orderdate"),
                    rst.getString("ordertime"),
                    rst.getDouble("cost"),
                    rst.getString("itemCode"),
                    rst.getInt("qtyForSell"),
                    rst.getDouble("unitPrice")));

        }
        return allData;

    }
}
