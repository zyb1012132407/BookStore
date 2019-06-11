package cn.edu.bdu.book.Test;

import cn.edu.bdu.book.Bean.User;
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
import java.util.List;

//练习：
//对应页码：
@WebServlet(name = "TestJasonServlet", urlPatterns = {"/TestJasonServlet"})
public class TestJasonServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置Response的格式，解决乱码问题
        response.setContentType("text/html;charset=utf-8");

        //声明一个用于输出的对象
        PrintWriter printWriter = response.getWriter();

        UserDao userDao = new UserDao();
        List<User> users  = userDao.findAll();
        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArray1 = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();


        for (User user:users){
            jsonObject.put("username",user.getUsername());
            jsonObject.put("password",user.getPassword());
            jsonObject.put("email",user.getEmail());
            jsonObject.put("sex",user.getSex());
            jsonObject.put("age",user.getAge());
            jsonObject.put("level",user.getLevel());
            jsonArray.add(jsonObject);
        }
        jsonObject1.put("userInformation",jsonArray);
        jsonObject1.put("userLogin",true);
        jsonArray1.add(jsonObject1);
        jsonArray1.add(jsonObject1);
//        jsonArray1.add(jsonArray);

        //输出结果为：
//        int[] t = {1,2,3,4,5,6,7,8};
//        printWriter.println(t);
        System.out.println(jsonArray1);
        printWriter.println(jsonArray1);
        //输出结果为：
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
