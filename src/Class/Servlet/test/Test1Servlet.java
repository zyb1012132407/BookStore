package Class.Servlet.test;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

//练习：
//对应页码：
@WebServlet(name = "Test1Servlet", urlPatterns = {"/Test1Servlet"})
public class Test1Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置Response的格式，解决乱码问题
        response.setContentType("text/html;charset=utf-8");

        //声明一个用于输出的对象
        PrintWriter printWriter = response.getWriter();
        System.out.println(request.getParameter("file"));


        try{
            DiskFileItemFactory factory = new DiskFileItemFactory();
            File f = new File("d:\\Target");
            if(!f.exists()){
                f.mkdirs();
            }
            factory.setRepository(f);
            ServletFileUpload fileUpload = new ServletFileUpload(factory);
            fileUpload.setHeaderEncoding("utf-8");
        }catch (Exception e){

        }
        //输出结果为：
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
