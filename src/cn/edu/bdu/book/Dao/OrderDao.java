package cn.edu.bdu.book.Dao;

import cn.edu.bdu.book.Bean.Order;
import cn.edu.bdu.book.Utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDao {
    private QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

    public List<Order> findAll(){
        try {
            String sql = "select * from orders";
            return queryRunner.query(sql,new BeanListHandler<Order>(Order.class));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Order> findByID(String ID){
        try {
            String sql = "SELECT * from orders where id='"+ID+"'";
            return queryRunner.query(sql,new BeanListHandler<Order>(Order.class));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Order> findByUsername(String username){
        try {
            String sql = "select * from orders where username='"+username+"'";
            return queryRunner.query(sql,new BeanListHandler<Order>(Order.class));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void insertOrder(Order order){
        try{
            String sql = "insert into orders VALUES('"+order.getID()+
                    "','"+order.getUsername()+
                    "','"+order.getDI_name()+
                    "','"+order.getDI_position()+
                    "','"+order.getDI_phonenumber()+
                    "',"+order.getPrice()+
                    ","+order.getIsPaid()+
                    ",'"+order.getBooks_ISBN()+
                    "',"+order.getNum()+
                    ",'"+order.getDate()+
                    "');";
            Connection connection = JDBCUtils.getConnection();
            int i = queryRunner.update(connection,sql);
            if(!(i>0)){
                System.out.println("添加订单出错");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void deleteOrder(String ID){
        try{
            String sql = "delete from orders where id='"+ID+"'";
            Connection connection = JDBCUtils.getConnection();
            int i = queryRunner.update(connection,sql);
            if(!(i>0)){
                System.out.println("删除订单出错");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void updateOrder(Order order,String toUpdateOrder){
        try{
            String sql = "update orders set ID='"+order.getID()+
                    "',username='"+order.getUsername()+
                    "',DI_name='"+order.getDI_name()+
                    "',DI_position='"+order.getDI_position()+
                    "',DI_phonenumber='"+order.getDI_phonenumber()+
                    "',price="+order.getPrice()+
                    ",isPaid="+order.getIsPaid()+
                    ",books_ISBN='"+order.getBooks_ISBN()+
                    "',num="+order.getNum()+
                    ",date='"+order.getDate()+
                    "' WHERE id='"+toUpdateOrder+
                    "';";
            Connection connection = JDBCUtils.getConnection();
            int i = queryRunner.update(connection,sql);
            if(!(i>0)){
                System.out.println("修改订单出错");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
