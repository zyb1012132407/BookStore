package cn.edu.bdu.book.Servlet.FrontPage;

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
import java.util.HashMap;
import java.util.Map;

//练习：
//对应页码：
@WebServlet(name = "CartManagerServlet", urlPatterns = {"/CartManagerServlet"})
public class CartManagerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置Response的格式，解决乱码问题
        response.setContentType("text/html;charset=utf-8");

        //声明一个用于输出的对象
        PrintWriter printWriter = response.getWriter();

        String type = request.getParameter("type");
        Map cart = null;
        if(type.equals("addElem")){
            cart = (Map)request.getSession().getAttribute("cart");
            if(cart == null){
                cart = new HashMap();
                request.getSession().setAttribute("cart",cart);
            }
            String ISBN = request.getParameter("ISBN");
            if(cart.containsKey(ISBN)){
                cart.put(ISBN,(int)cart.get(ISBN)+1);
            }else{
                cart.put(ISBN,1);
            }
            request.getSession().setAttribute("cart",cart);
            System.out.println("添加元素到购物车,书籍ISBN为" + ISBN);
        }else if(type.equals("deleteElem")){
            cart = (Map)request.getSession().getAttribute("cart");
            if(cart == null){
                printWriter.println("error");
            }else{
                String ISBN = request.getParameter("ISBN");
                if(!cart.containsKey(ISBN)){
                    printWriter.println("error");
                }else{
                    cart.remove(ISBN);
                    request.getSession().setAttribute("cart",cart);
                }
            }
        }else if(type.equals("updateElem")){
            cart = (Map)request.getSession().getAttribute("cart");
            if(cart == null){
                printWriter.println("error");
            }else{
                String ISBN = request.getParameter("ISBN");
                if(!cart.containsKey(ISBN)){
                    printWriter.println("error");
                }else{
                    int newValue = Integer.parseInt(request.getParameter("newValue"));
                    cart.put(ISBN,newValue);
                    request.getSession().setAttribute("cart",cart);
                    System.out.println("数据被改变" + "ISBN:" + ISBN + "新的值为:" + newValue);
                }
            }
        }else if(type.equals("showAll")){
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            BookDao bookDao = new BookDao();
            cart = (Map)request.getSession().getAttribute("cart");
            if(cart == null){
            }else{
                for(Object ISBN:cart.keySet()){
                    jsonObject.put("ISBN",(String)ISBN);
                    jsonObject.put("num",(int)(cart.get(ISBN)));
                    jsonObject.put("name",bookDao.findByISBN((String)ISBN).getbName());
                    jsonObject.put("price",bookDao.findByISBN((String)ISBN).getPrice());
                    jsonArray.add(jsonObject);
                }
                printWriter.println(jsonArray);
                jsonArray.clear();
            }
        }else if(type.equals("confirmPurchase")){
            cart = (Map)request.getSession().getAttribute("cart");
            if(cart != null){
                request.getSession().setAttribute("confirmPurchase",true);
            }
        }

        //输出结果为：
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
