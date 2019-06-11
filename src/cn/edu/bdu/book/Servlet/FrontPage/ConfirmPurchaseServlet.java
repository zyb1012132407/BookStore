package cn.edu.bdu.book.Servlet.FrontPage;

import cn.edu.bdu.book.Bean.Order;
import cn.edu.bdu.book.Dao.BookDao;
import cn.edu.bdu.book.Dao.OrderDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

//练习：
//对应页码：
@WebServlet(name = "ConfirmPurchaseServlet", urlPatterns = {"/ConfirmPurchaseServlet"})
public class ConfirmPurchaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置Response的格式，解决乱码问题
        response.setContentType("text/html;charset=utf-8");

        //声明一个用于输出的对象
        PrintWriter printWriter = response.getWriter();

        String type = request.getParameter("type");

        Enumeration enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            System.out.println(paraName+": "+request.getParameter(paraName));
        }
        if(type.equals("submit")){
            Float cost = Float.valueOf(request.getParameter("cost"));
            String DI_name = request.getParameter("submitInformation[DI_name]");
            String DI_position = request.getParameter("submitInformation[DI_position]");
            String DI_phonenumber = request.getParameter("submitInformation[DI_phonenumber]");
            String username = String.valueOf(request.getSession().getAttribute("username"));
            String books_ISBN = "";
            Map cart = (Map)request.getSession().getAttribute("cart");
            BookDao bookDao = new BookDao();
            for(Object ISBN:cart.keySet()){
                books_ISBN = books_ISBN +
                        bookDao.findByISBN((String)ISBN).getbName() +
                        " " + (int)(cart.get(ISBN)) + "本; ";
            }
            int num = 0;
            int isPaid = 0;
            Date date = new Date();
            OrderDao orderDao = new OrderDao();
            orderDao.insertOrder(new Order(new SimpleDateFormat("yyyyMMddhhmmss").format(date).toString(),username,
                    DI_name,DI_position,DI_phonenumber,cost,isPaid,books_ISBN,num,date.toString()));
            printWriter.println("success");

        }

        //输出结果为：
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
