package cn.edu.bdu.book.Servlet.Check;

import cn.edu.bdu.book.Bean.User;
import cn.edu.bdu.book.Dao.UserDao;

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

@WebServlet(name = "CheckAccountRegistryServlet", urlPatterns = {"/CheckAccountRegistryServlet"})
public class CheckAccountRegistryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置Response的格式，解决乱码问题
        response.setContentType("text/html;charset=utf-8");

        //声明一个用于输出的对象
        PrintWriter printWriter = response.getWriter();
//        printWriter.println("Test  <br/>");

//        Enumeration<String> requestParameterNames = request.getParameterNames();
//        while (requestParameterNames.hasMoreElements()){
//            System.out.println(requestParameterNames.nextElement());
//        }

        //获取请求参数
        String username = request.getParameter("newUser[username]");
        String password = request.getParameter("newUser[password]");
        String email = request.getParameter("newUser[email]");
        String checkCode = request.getParameter("check_code");
        String savedCode = (String)request.getSession().getAttribute("check-mode");

        //输出结果为：
        if(!savedCode.equals(checkCode)){
            printWriter.println("checkCodeWrong");
        }else if(checkSameUsername(username)){
            printWriter.println("usernameFalse");
        }else if(checkSameEmail(email)){
            printWriter.println("emailFalse");
        }else{
            User user = new User();
            UserDao userDao = new UserDao();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setLevel(0);
            userDao.insertUser(user);
            printWriter.println("true");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public boolean checkSameUsername(String username){
        UserDao userDao = new UserDao();
        for(User user:userDao.findAll()){
            if(user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public boolean checkSameEmail(String email){
        UserDao userDao = new UserDao();
        for(User user:userDao.findAll()){
            if(user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }


}
