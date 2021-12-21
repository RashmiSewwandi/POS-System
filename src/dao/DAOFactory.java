package dao;

import dao.custom.impl.*;

public class DAOFactory {
    //object creation logic eka hide karanna.singleton hadagannawa(eka object ekk reuse karaganna)
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDAOFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    //factory method
    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ITEMDETAILS:
                return new ItemDetailsDAOImpl();
            case QUERYDAO:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }

    //danata produce kranna puluwan tika enum ekaka save krnwa.final statics values thiyenne
    public enum DAOTypes {
        CUSTOMER, ITEM, ORDER, ITEMDETAILS, QUERYDAO
    }
}
