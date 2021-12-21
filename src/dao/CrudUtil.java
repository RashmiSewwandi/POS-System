package dao;

import db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {
    //sql aniwaren wann,ita passe args ekata kamathi pramanayak
    //ewanath puluwan,ewan nathuwa innath puluwan
    private static PreparedStatement getPreparedStatement(String sql, Object... args) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        //valus wala length ekata for ek danne
        for (int i = 0; i < args.length; i++) {
            //setObject danne onema data type ekk ganna puluwan wenna
            pstm.setObject(i + 1, args[i]);

        }
        return pstm;
    }
    //static kare object hadanna one nathi nisa,ethakota class name ekenma wadde karagena yanna puluwan
    public static boolean  executeUpdate(String sql, Object... args) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(sql, args).executeUpdate() > 0;
    }

    public static ResultSet executeQuery(String sql, Object... args) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(sql, args).executeQuery();
    }



}
