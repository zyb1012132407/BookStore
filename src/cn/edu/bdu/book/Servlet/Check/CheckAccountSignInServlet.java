package cn.edu.bdu.book.Servlet.Check;

import cn.edu.bdu.book.Bean.User;
import cn.edu.bdu.book.Dao.UserDao;
import com.mysql.cj.Session;

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

@WebServlet(name = "CheckAccountSignInServlet", urlPatterns = {"/CheckAccountSignInServlet"})
public class CheckAccountSignInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置Response的格式，解决乱码问题
        response.setContentType("text/html;charset=utf-8");

        //声明一个用于输出的对象
        PrintWriter printWriter = response.getWriter();


        //获取请求参数
        String username = request.getParameter("newUser[username]");
        String password = request.getParameter("newUser[password]");
        String checkCode = request.getParameter("check_code");
        String savedCode = (String)request.getSession().getAttribute("check-mode");

        //输出结果为：
        if(!savedCode.equals(checkCode)){
            printWriter.println("checkCodeWrong");
        }else if(!checkSameUsername(username)){
            printWriter.println("usernameIsNotExist");
        }else if(!checkPassword(username,password)){
            printWriter.println("passwordWrong");
        }else{
            //vue识别Boolean类型
            request.getSession().setAttribute("isLogin","true");
            request.getSession().setAttribute("username",username);
            printWriter.println("true");
        }

//        printWriter.println("用户名：" + username + "密码：" + password);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public boolean checkPassword(String username,String password){
        UserDao userDao = new UserDao();
        for(User user:userDao.findAll()){
            if(user.getUsername().equals(username)){
                if (user.getPassword().equals(password)){
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
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
