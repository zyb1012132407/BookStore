package cn.edu.bdu.book.Servlet.DashBoard.Manager;

import cn.edu.bdu.book.Bean.Book;
import cn.edu.bdu.book.Dao.BookDao;
import cn.edu.bdu.book.Dao.UserDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//练习：
//对应页码：
@WebServlet(name = "BookManagerServlet", urlPatterns = {"/BookManagerServlet"})
public class BookManagerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置Response的格式，解决乱码问题
        response.setContentType("text/html;charset=utf-8");

        //声明一个用于输出的对象
        PrintWriter printWriter = response.getWriter();
        BookDao bookDao = new BookDao();
        String type = request.getParameter("type");
        if("showAll".equals(type)){
            showAll(bookDao,printWriter);
        }else if("findBookByISBN".equals(type)){
            String ISBN = request.getParameter("ISBN");
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            Book book = bookDao.findByISBN(ISBN);
//            System.out.println(book);
            jsonObject.put("bName",book.getbName());
            jsonObject.put("ISBN",book.getISBN());
            jsonObject.put("author",book.getAuthor());
            jsonObject.put("price",book.getPrice());
            jsonObject.put("category",book.getCategory());
            jsonObject.put("number",book.getNumber());
            jsonObject.put("description",book.getDescription());
            jsonObject.put("imgUrl",book.getImgUrl());
            jsonArray.add(jsonObject);
            printWriter.println(jsonArray);
            jsonArray.clear();
        }else if("showAllWithDesc".equals(type)){
            String elem = request.getParameter("elem");
            Boolean isDesc = request.getParameter("type").equals("true");
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            for(Book book: bookDao.findAllByOrder(elem,isDesc)){
                jsonObject.put("bName",book.getbName());
                jsonObject.put("ISBN",book.getISBN());
                jsonObject.put("author",book.getAuthor());
                jsonObject.put("price",book.getPrice());
                jsonObject.put("category",book.getCategory());
                jsonObject.put("number",book.getNumber());
                jsonObject.put("description",book.getDescription());
                jsonObject.put("imgUrl",book.getImgUrl());
                jsonArray.add(jsonObject);
            }
            printWriter.println(jsonArray);
            jsonArray.clear();
        }else if("deleteBook".equals(type)){
            String ISBN = request.getParameter("deleteISBN");
            bookDao.deleteBook(ISBN);
            showAll(bookDao,printWriter);
        }else if("addBook".equals(type)){
            String bName = request.getParameter("addBook[bName]");
            String ISBN = request.getParameter("addBook[ISBN]");
            String author = request.getParameter("addBook[author]");
            float price = Float.parseFloat(request.getParameter("addBook[price]"));
            String category = request.getParameter("addBook[category]");
            int number = Integer.parseInt(request.getParameter("addBook[number]"));
            String description = request.getParameter("addBook[description]");
//            String imgUrl = request.getParameter("addBook[email]");
            bookDao.insertBook(new Book(bName,ISBN,author,price,category,number,description,"imgUrl"));
            showAll(bookDao,printWriter);
        }else if("updateBook".equals(type)){
            String bName = request.getParameter("addBook[bName]");
            String ISBN = request.getParameter("addBook[ISBN]");
            String author = request.getParameter("addBook[author]");
            float price = Float.parseFloat(request.getParameter("addBook[price]"));
            String category = request.getParameter("addBook[category]");
            int number = Integer.parseInt(request.getParameter("addBook[number]"));
            String description = request.getParameter("addBook[description]");
//            String imgUrl = request.getParameter("addBook[email]");
            String toUpdateISBN = request.getParameter("toUpdateISBN");
            bookDao.updateBook(new Book(bName,ISBN,author,price,category,number,description,"imgUrl"),toUpdateISBN);
            showAll(bookDao,printWriter);
        }else if("logout".equals(type)){
            request.getSession().removeAttribute("isLogin");
            request.getSession().removeAttribute("username");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void showAll(BookDao bookDao,PrintWriter printWriter){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        for(Book book: bookDao.findAll()){
            jsonObject.put("bName",book.getbName());
            jsonObject.put("ISBN",book.getISBN());
            jsonObject.put("author",book.getAuthor());
            jsonObject.put("price",book.getPrice());
            jsonObject.put("category",book.getCategory());
            jsonObject.put("number",book.getNumber());
            jsonObject.put("description",book.getDescription());
            jsonObject.put("imgUrl",book.getImgUrl());
            jsonArray.add(jsonObject);
        }
        printWriter.println(jsonArray);
        jsonArray.clear();
    }
}
