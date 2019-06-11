package cn.edu.bdu.book.Dao;

import cn.edu.bdu.book.Bean.Notice;
import cn.edu.bdu.book.Utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class NoticeDao {
    private QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

    public List<Notice> findAll(){
        try {
            String sql = "select * from notice order by id DESC";
            return queryRunner.query(sql,new BeanListHandler<Notice>(Notice.class));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Notice> findByID(int ID){
        try {
            String sql = "select * from notice where id=" + ID;
            return queryRunner.query(sql,new BeanListHandler<Notice>(Notice.class));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void deleteNotice(int ID){
        try{
            String sql = "delete from notice where id="+ID+";";
            Connection connection = JDBCUtils.getConnection();
            int i = queryRunner.update(connection,sql);
            if(!(i>0)){
                System.out.println("删除用户出错");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        refreshID();
    }

    public void addNotice(Notice notice){
        try{
            String sql = "insert notice (title,content,date) VALUES('"+notice.getTitle()+
                    "','"+notice.getContent()+
                    "','"+notice.getDate()+"')";
            Connection connection = JDBCUtils.getConnection();
            int i = queryRunner.update(connection,sql);
            if(!(i>0)){
                System.out.println("添加用户出错");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        refreshID();
    }

    public void updateNotice(Notice notice,int toUpdateID){
        try{
            String sql = "update notice set title = '"+notice.getTitle()+
                    "',content='"+notice.getContent()+
                    "',date='"+notice.getDate()+
                    "' where id = "+toUpdateID;
            Connection connection = JDBCUtils.getConnection();
            int i = queryRunner.update(connection,sql);
            if(!(i>0)){
                System.out.println("更新用户出错");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void refreshID(){
        try{
            String sql = "call refreshNotice();";
            Connection connection = JDBCUtils.getConnection();
            int i = queryRunner.update(connection,sql);
            if(!(i>0)){
                System.out.println("刷新ID出错");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



}
