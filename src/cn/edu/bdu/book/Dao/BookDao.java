package cn.edu.bdu.book.Dao;

import cn.edu.bdu.book.Bean.Book;
import cn.edu.bdu.book.Utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookDao {
    private QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

    public List<Book> findAll(){
        try {
            String sql = "select * from book";
            return queryRunner.query(sql,new BeanListHandler<Book>(Book.class));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public List<Book> findAll(int start,int num){
        try {
            String sql = "select * from book limit " + start + "," + num;
            return queryRunner.query(sql,new BeanListHandler<Book>(Book.class));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Book> findAllByOrder(String elem,Boolean isDesc){
        try{
            String sql = "select * from book order by " +elem+ ((isDesc)?" desc":"");
            return queryRunner.query(sql,new BeanListHandler<Book>(Book.class));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Book> findAllByOrder(String elem,Boolean isDesc,int start,int num){
        try{
            String sql = "select * from book order by " +elem+ ((isDesc)?" desc":"") + " limit "+ start + "," + num;
            return queryRunner.query(sql,new BeanListHandler<Book>(Book.class));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Book> findByName(String bName){
        try{
            String sql = "select * from book WHERE bName like '%"+bName+"%'";
            return queryRunner.query(sql,new BeanListHandler<Book>(Book.class));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Book> findByName(String bName,int start,int num){
        try{
            String sql = "select * from book WHERE bName like '%"+bName+"%'"+ " limit "+ start + "," + num;
            return queryRunner.query(sql,new BeanListHandler<Book>(Book.class));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Book> findByCategory(String category){
        try {
            String sql = "select * from book where category='"+category+"'";
            return queryRunner.query(sql,new BeanListHandler<Book>(Book.class));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Book> findByCategory(String category,int start,int num){
        try {
            String sql = "select * from book where category='"+category+"'"+ " limit "+ start + "," + num;
            return queryRunner.query(sql,new BeanListHandler<Book>(Book.class));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Book findByISBN(String ISBN){
        try{
            String sql = "select * from book where isbn='"+ISBN+"'";
            return queryRunner.query(sql,new BeanHandler<Book>(Book.class));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void insertBook(Book book){
        try{
            String sql = "insert into book VALUES('"+
                    book.getbName()+"','"+
                    book.getISBN()+"','"+
                    book.getAuthor()+"',"+
                    book.getPrice()+",'"+
                    book.getCategory()+"',"+
                    book.getNumber()+",'"+
                    book.getDescription()+"','"+
                    book.getImgUrl()+"')";
            Connection connection = JDBCUtils.getConnection();
            int i = queryRunner.update(connection,sql);
            if(!(i>0)){
                System.out.println("插入用户出错");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteBook(String ISBN){
        try{
            String sql = "delete from book where ISBN='"+ISBN+"'";
            Connection connection = JDBCUtils.getConnection();
            int i = queryRunner.update(connection,sql);
            if(!(i>0)){
                System.out.println("删除用户出错");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateBook(Book book,String toUpdateISBN){
        try{
            String sql = "update book set bName='" + book.getbName()+
                    "',ISBN='" +book.getISBN()+
                    "',author='"+book.getAuthor()+
                    "',price="+book.getPrice()+
                    ",category='"+book.getCategory()+
                    "',number="+book.getNumber()+
                    ",description='"+book.getDescription()+
                    "',imgUrl='"+book.getImgUrl()+
                    "' where ISBN='"+toUpdateISBN+
                    "'";
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
