package cn.edu.bdu.book.Dao;

import cn.edu.bdu.book.Bean.User;
import cn.edu.bdu.book.Utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDao {
    private QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

    public List<User> findAll(){
        try {
            String sql = "select * from user";
            return queryRunner.query(sql,new BeanListHandler<User>(User.class));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public User findById(String username){
        try{
            String sql = "select * from user where username='"+username+"'";
            return queryRunner.query(sql,new BeanHandler<>(User.class));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void insertUser(User user){
        try{
            String sql = "INSERT INTO user (username,password,email,sex,age,level) VALUES('"+ user.getUsername() +"','" +
                    user.getPassword() + "','" + user.getEmail() +
                    "','"+user.getSex()+"'," + user.getAge()+","+ user.getLevel()+")";
            Connection connection = JDBCUtils.getConnection();
            int i = queryRunner.update(connection,sql);
            if(!(i>0)){
                System.out.println("插入用户出错");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteUser(String username){
        try{
            String sql = "DELETE from user where username='"+ username+"'";
            Connection connection = JDBCUtils.getConnection();
            int i = queryRunner.update(connection,sql);
            if(!(i>0)){
                System.out.println("删除用户出错");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateUser(User user,String toUpdateUsername){
        try{
            String sql = "UPDATE user SET username='"+user.getUsername()+
                    "',password='"+user.getPassword()+
                    "',email='"+user.getEmail()+
                    "',sex='"+user.getSex()+"'" +
                    ",age="+user.getAge()+
                    ",level="+user.getLevel()+
                    " where username='"+toUpdateUsername+"'";
            Connection connection = JDBCUtils.getConnection();
            int i = queryRunner.update(connection,sql);
            if(!(i>0)){
                System.out.println("修改用户出错");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
