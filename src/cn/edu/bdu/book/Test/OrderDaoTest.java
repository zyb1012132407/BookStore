package cn.edu.bdu.book.Test;

import cn.edu.bdu.book.Bean.Order;
import cn.edu.bdu.book.Dao.OrderDao;

public class OrderDaoTest {


    public static void main(String[] args) {
        OrderDao orderDao = new OrderDao();
//        for(Order order:orderDao.findByUsername("username1")){
//            System.out.println(order);
//        }
        Order order = new Order("ID1","username1","DI_name1","DI_position1","DI_phonenumber1",(float)2.2,1,"books_ISBN1",2,"date");
//        orderDao.insertOrder(order);
//        orderDao.deleteOrder("1");
        orderDao.updateOrder(order,"2");
    }
}
