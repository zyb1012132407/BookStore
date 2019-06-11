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
import java.util.List;

//练习：
//对应页码：
@WebServlet(name = "BookImageServlet", urlPatterns = {"/BookImageServlet"})
public class BookImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置Response的格式，解决乱码问题
        response.setContentType("text/html;charset=utf-8");
        int i = 1;
        InputStream inputStream = null;
        OutputStream outputStream = null;
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
                    String webPath = "/file/image/book/" + name + "/";
                    String filepath = getServletContext().getRealPath(webPath + i + ".png");
                    i++;
                    if(i >5){
                        break;
                    }
                    File file = new File(filepath);
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                    inputStream = fileItem.getInputStream();
                    outputStream = new FileOutputStream(file);

                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buffer)) > 0)
                        outputStream.write(buffer,0,len);
//                    fileItem.delete();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            inputStream.close();
            outputStream.close();

        }

        response.sendRedirect("/Servlet/FontPage/pages/dashboard-bookManage-Admin.html");
        //输出结果为：
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
