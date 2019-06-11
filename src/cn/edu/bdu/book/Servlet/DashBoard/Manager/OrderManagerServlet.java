package cn.edu.bdu.book.Servlet.DashBoard.Manager;

import cn.edu.bdu.book.Bean.Order;
import cn.edu.bdu.book.Dao.OrderDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

//练习：
//对应页码：
@WebServlet(name = "OrderManagerServlet", urlPatterns = {"/OrderManagerServlet"})
public class OrderManagerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置Response的格式，解决乱码问题
        response.setContentType("text/html;charset=utf-8");

        //声明一个用于输出的对象
        PrintWriter printWriter = response.getWriter();

        String type = request.getParameter("type");

        if(type.equals("showAll")){
            showAll(printWriter);
        }else if(type.equals("findByUsername")){
            OrderDao orderDao = new OrderDao();
            String username = request.getParameter("username");
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            for(Order order: orderDao.findByUsername(username)){
                jsonObject.put("id",order.getID());
                jsonObject.put("username",order.getUsername());
                jsonObject.put("DI_name",order.getDI_name());
                jsonObject.put("DI_position",order.getDI_position());
                jsonObject.put("DI_phonenumber",order.getDI_phonenumber());
                jsonObject.put("price",order.getPrice());
                jsonObject.put("isPaid",order.getIsPaid());
                jsonObject.put("books_ISBN",order.getBooks_ISBN());
                jsonObject.put("num",order.getNum());
                jsonObject.put("date",order.getDate());
                jsonArray.add(jsonObject);
            }
            printWriter.println(jsonArray);
            jsonArray.clear();
        }else if(type.equals("findByID")){
            OrderDao orderDao = new OrderDao();
            String ID = request.getParameter("ID");
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            for(Order order: orderDao.findByID(ID)){
                jsonObject.put("id",order.getID());
                jsonObject.put("username",order.getUsername());
                jsonObject.put("DI_name",order.getDI_name());
                jsonObject.put("DI_position",order.getDI_position());
                jsonObject.put("DI_phonenumber",order.getDI_phonenumber());
                jsonObject.put("price",order.getPrice());
                jsonObject.put("isPaid",order.getIsPaid());
                jsonObject.put("books_ISBN",order.getBooks_ISBN());
                jsonObject.put("num",order.getNum());
                jsonObject.put("date",order.getDate());
                jsonArray.add(jsonObject);
            }
            printWriter.println(jsonArray);
            jsonArray.clear();
        }else if(type.equals("deleteOrder")){
            OrderDao orderDao = new OrderDao();
            String ID = request.getParameter("ID");
            orderDao.deleteOrder(ID);
            showAll(printWriter);
        }else if(type.equals("insertOrder")){
            String ID = request.getParameter("addOrder[id]");
            String username = request.getParameter("addOrder[username]");
            String DI_name = request.getParameter("addOrder[DI_name]");
            String DI_position = request.getParameter("addOrder[DI_position]");
            String DI_phonenumber = request.getParameter("addOrder[DI_phonenumber]");
            float price = Float.parseFloat(request.getParameter("addOrder[price]"));
            int isPaid = Integer.parseInt(request.getParameter("addOrder[isPaid]"));
            String books_ISBN = request.getParameter("addOrder[books_ISBN]");
            int num = Integer.parseInt(request.getParameter("addOrder[num]"));
            String date = request.getParameter("addOrder[date]");
            OrderDao orderDao = new OrderDao();
            Order order = new Order(ID,username,DI_name,DI_position,DI_phonenumber,price,isPaid,books_ISBN,num,date);
            orderDao.insertOrder(order);
            showAll(printWriter);
        }else if(type.equals("updateOrder")){
            String ID = request.getParameter("addOrder[id]");
            String username = request.getParameter("addOrder[username]");
            String DI_name = request.getParameter("addOrder[DI_name]");
            String DI_position = request.getParameter("addOrder[DI_position]");
            String DI_phonenumber = request.getParameter("addOrder[DI_phonenumber]");
            float price = Float.parseFloat(request.getParameter("addOrder[price]"));
            int isPaid = Integer.parseInt(request.getParameter("addOrder[isPaid]"));
            String books_ISBN = request.getParameter("addOrder[books_ISBN]");
            int num = Integer.parseInt(request.getParameter("addOrder[num]"));
            String date = request.getParameter("addOrder[date]");

            String toUpdateOrder = request.getParameter("id");

//            System.out.println(request.getParameter("[addOrder]price"));
            OrderDao orderDao = new OrderDao();
            Order order = new Order(ID,username,DI_name,DI_position,DI_phonenumber,price,isPaid,books_ISBN,num,date);
            orderDao.updateOrder(order,toUpdateOrder);
            showAll(printWriter);
        }


        //输出结果为：
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void showAll(PrintWriter printWriter){
        OrderDao orderDao = new OrderDao();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        for(Order order: orderDao.findAll()){
            jsonObject.put("id",order.getID());
            jsonObject.put("username",order.getUsername());
            jsonObject.put("DI_name",order.getDI_name());
            jsonObject.put("DI_position",order.getDI_position());
            jsonObject.put("DI_phonenumber",order.getDI_phonenumber());
            jsonObject.put("price",order.getPrice());
            jsonObject.put("isPaid",order.getIsPaid());
            jsonObject.put("books_ISBN",order.getBooks_ISBN());
            jsonObject.put("num",order.getNum());
            jsonObject.put("date",order.getDate());
            jsonArray.add(jsonObject);
        }
        printWriter.println(jsonArray);
        jsonArray.clear();
    }
}
