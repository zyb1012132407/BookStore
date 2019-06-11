package cn.edu.bdu.book.Servlet.FrontPage;

import cn.edu.bdu.book.Dao.BookDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//练习：
//对应页码：
@WebServlet(name = "ShopElemGetServlet", urlPatterns = {"/ShopElemGetServlet"})
public class ShopElemGetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置Response的格式，解决乱码问题
        response.setContentType("text/html;charset=utf-8");

        //声明一个用于输出的对象
        PrintWriter printWriter = response.getWriter();

        String elem = request.getParameter("elem");
        if("findAllLength".equals(elem)){
            BookDao bookDao = new BookDao();
            printWriter.println(bookDao.findAll().size());
        }else if("findBookLength".equals(elem)){
            BookDao bookDao = new BookDao();
            String name = request.getParameter("bookName");
            printWriter.println(bookDao.findByName(name).size());
        }else if("findCategoryLength".equals(elem)){
            BookDao bookDao = new BookDao();
            String bookCategory = request.getParameter("bookCategory");
            printWriter.println(bookDao.findByCategory(bookCategory).size());
        }

        //输出结果为：
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
