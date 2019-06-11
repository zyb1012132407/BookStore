package cn.edu.bdu.book.Servlet.FrontPage;

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


@WebServlet(name = "SessionGetServlet", urlPatterns = {"/SessionGetServlet"})
public class SessionGetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置Response的格式，解决乱码问题
        response.setContentType("text/html;charset=utf-8");

        //声明一个用于输出的对象
        PrintWriter printWriter = response.getWriter();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        UserDao userDao = new UserDao();
        String isLogin = (String) request.getSession().getAttribute("isLogin");
        String username = (String) request.getSession().getAttribute("username");



        jsonObject.put("isLogin",isLogin);
        jsonArray.add(jsonObject);
        jsonObject.clear();
        if("true".equals(isLogin)){
            User user = userDao.findById(username);
            jsonObject.put("username",user.getUsername());
            jsonObject.put("password",user.getPassword());
            jsonObject.put("email",user.getEmail());
            jsonObject.put("sex",user.getSex());
            jsonObject.put("age",user.getAge());
            jsonObject.put("level",user.getLevel());
            jsonArray.add(jsonObject);
        }


        printWriter.println(jsonArray);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
