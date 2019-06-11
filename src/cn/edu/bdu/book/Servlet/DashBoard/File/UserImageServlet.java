package cn.edu.bdu.book.Servlet.DashBoard.File;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

//练习：
//对应页码：
@WebServlet(name = "UserImageServlet", urlPatterns = {"/UserImageServlet"})
public class UserImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置Response的格式，解决乱码问题
        response.setContentType("text/html;charset=utf-8");


        try{
            DiskFileItemFactory factory = new DiskFileItemFactory();
            File f = new File("d:\\Target");
            if(!f.exists()){
                f.mkdirs();
            }
            factory.setRepository(f);
            ServletFileUpload fileUpload = new ServletFileUpload(factory);
            fileUpload.setHeaderEncoding("utf-8");

            List<FileItem> fileItems =fileUpload.parseRequest(request);
            String value = "";
            String name  ="";
            for(FileItem fileItem:fileItems){
                if(fileItem.isFormField()){
                    name = fileItem.getFieldName();
                    value = fileItem.getString("utf-8");
                }else{
                    String filename = fileItem.getName();
                    System.out.println("用户名:" + name + ",文件名:" + filename);
                    String webPath = "/file/image/user/";
                    String filepath = getServletContext().getRealPath(webPath + name + ".png");
                    File file = new File(filepath);
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                    InputStream inputStream = fileItem.getInputStream();
                    OutputStream outputStream = new FileOutputStream(file);

                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buffer)) > 0)
                        outputStream.write(buffer,0,len);
                    inputStream.close();
                    outputStream.close();
                    fileItem.delete();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        response.sendRedirect("/Servlet/FontPage/pages/dashboard-userInformationManage.html");
//        request.getRequestDispatcher("/FontPage/pages/dashboard-userInformationManage_1.html").forward(request,response);
        //输出结果为：
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
