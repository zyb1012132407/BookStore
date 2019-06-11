package cn.edu.bdu.book.Servlet.DashBoard;

import cn.edu.bdu.book.Bean.Book;
import cn.edu.bdu.book.Dao.BookDao;
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
@WebServlet(name = "ShopShowServlet", urlPatterns = {"/ShopShowServlet"})
public class ShopShowServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置Response的格式，解决乱码问题
        response.setContentType("text/html;charset=utf-8");

        //声明一个用于输出的对象
        PrintWriter printWriter = response.getWriter();
        BookDao bookDao = new BookDao();
        String type = request.getParameter("type");
        if("showAll".equals(type)){
            int start = Integer.parseInt(request.getParameter("start"));
            int num = Integer.parseInt(request.getParameter("num"));
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            for(Book book: bookDao.findAll(start,num)){
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
        }else if("findBookByName".equals(type)){
            String bName = request.getParameter("bookName");
            int start = Integer.parseInt(request.getParameter("start"));
            int num = Integer.parseInt(request.getParameter("num"));
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            for(Book book: bookDao.findByName(bName,start,num)){
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
        }else if("findBookByCategory".equals(type)){
            String category = request.getParameter("category");
            int start = Integer.parseInt(request.getParameter("start"));
            int num = Integer.parseInt(request.getParameter("num"));
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            for(Book book: bookDao.findByCategory(category,start,num)){
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
        }else if("showAllWithDesc".equals(type)){
            String elem = request.getParameter("elem");
            Boolean isDesc = request.getParameter("type").equals("true");
            int start = Integer.parseInt(request.getParameter("start"));
            int num = Integer.parseInt(request.getParameter("num"));
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            for(Book book: bookDao.findAllByOrder(elem,isDesc,start,num)){
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
        }else if("logout".equals(type)){
            request.getSession().removeAttribute("isLogin");
            request.getSession().removeAttribute("username");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
