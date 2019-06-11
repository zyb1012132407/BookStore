package cn.edu.bdu.book.Servlet.DashBoard.Manager;

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

//练习：
//对应页码：
@WebServlet(name = "UserManagerServlet", urlPatterns = {"/UserManagerServlet"})
public class UserManagerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置Response的格式，解决乱码问题
        response.setContentType("text/html;charset=utf-8");

        //声明一个用于输出的对象
        PrintWriter printWriter = response.getWriter();
        UserDao userDao = new UserDao();
        String type = request.getParameter("type");
        if("showAll".equals(type)){
            showAll(userDao,printWriter);
        }else if("findUserByUsername".equals(type)){
            String username = request.getParameter("username");
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            User user = userDao.findById(username);
            jsonObject.put("username",user.getUsername());
            jsonObject.put("password",user.getPassword());
            jsonObject.put("sex",user.getSex());
            jsonObject.put("email",user.getEmail());
            jsonObject.put("age",user.getAge());
            jsonObject.put("level",user.getLevel());
            jsonArray.add(jsonObject);
            printWriter.println(jsonArray);
            jsonArray.clear();
        }else if("deleteUser".equals(type)){
            String username = request.getParameter("deleteUsername");
            userDao.deleteUser(username);
            showAll(userDao,printWriter);
        }else if("addUser".equals(type)){
            String username = request.getParameter("addUser[username]");
            String password = request.getParameter("addUser[password]");
            String email = request.getParameter("addUser[email]");
            int age = Integer.parseInt(request.getParameter("addUser[age]"));
            String sex = request.getParameter("addUser[sex]");
            int level = Integer.parseInt(request.getParameter("addUser[level]"));
            userDao.insertUser(new User(username,password,sex,age,email,level));
            showAll(userDao,printWriter);
        }else if("updateUser".equals(type)){
            String username = request.getParameter("addUser[username]");
            String password = request.getParameter("addUser[password]");
            String email = request.getParameter("addUser[email]");
            int age = Integer.parseInt(request.getParameter("addUser[age]"));
            String sex = request.getParameter("addUser[sex]");
            int level = Integer.parseInt(request.getParameter("addUser[level]"));
            String toUpdateUserName = request.getParameter("toUpdateUser");
            userDao.updateUser(new User(username,password,sex,age,email,level),toUpdateUserName);
            showAll(userDao,printWriter);
        }else if("logout".equals(type)){
            request.getSession().removeAttribute("isLogin");
            request.getSession().removeAttribute("username");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void showAll(UserDao userDao,PrintWriter printWriter){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        for(User user: userDao.findAll()){
            jsonObject.put("username",user.getUsername());
            jsonObject.put("password",user.getPassword());
            jsonObject.put("sex",user.getSex());
            jsonObject.put("email",user.getEmail());
            jsonObject.put("age",user.getAge());
            jsonObject.put("level",user.getLevel());
            jsonArray.add(jsonObject);
        }
        printWriter.println(jsonArray);
        jsonArray.clear();
    }
}
