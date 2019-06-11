package cn.edu.bdu.book.Test;

import cn.edu.bdu.book.Bean.User;
import cn.edu.bdu.book.Dao.UserDao;


public class UserDaoTest {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
//        User user = new User("yonghuming","mima","boy",1,"2",2);
        User user = new User();
//        userDao.updateUser(user,"username");
        user = userDao.findById("16894321");
        System.out.println(user);
    }
}
