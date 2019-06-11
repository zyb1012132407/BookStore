package cn.edu.bdu.book.Utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtils {
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
    private static DataSource dataSource = new ComboPooledDataSource();
    public static DataSource getDataSource(){
        return dataSource;
    }

    public static Connection getConnection() throws SQLException{
        Connection connection = threadLocal.get();
        if(connection == null){
            connection = dataSource.getConnection();
            threadLocal.set(connection);
        }
        return connection;
    }

    public static void startTransaction(){
        try{
            Connection connection = getConnection();
            connection.setAutoCommit(false);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void commit(){
        try{
            Connection connection = getConnection();
            connection.commit();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void rollback(){
        try{
            Connection connection = getConnection();
            connection.rollback();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void close(){
        Connection connection;
        try{
            connection = getConnection();
            connection.close();
        }catch(SQLException e){
            threadLocal.remove();
            connection = null;
        }
    }


}
