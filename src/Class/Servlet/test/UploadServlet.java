package Class.Servlet.test;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

//练习：
//对应页码：
@WebServlet(name = "UploadServlet", urlPatterns = {"/UploadServlet"})
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置Response的格式，解决乱码问题
        response.setContentType("text/html;charset=utf-8");
//        Enumeration<String> parameterNames = request.getParameterNames();
//        while (parameterNames.hasMoreElements()){
//            System.out.println(parameterNames.nextElement());
//        }
        //声明一个用于输出的对象

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
            PrintWriter printWriter = response.getWriter();
            String value = "";

            for(FileItem fileItem:fileItems){
                if(fileItem.isFormField()){
                    String name = fileItem.getFieldName();
                    value = fileItem.getString("utf-8");
                    printWriter.println("上传者" + value + "<br>");
                }else{
                    String filename = fileItem.getName();
                    printWriter.print("文件来源:" + filename + "<br>");
                    System.out.println(filename);
//                    filename = filename.substring(filename.lastIndexOf("\\" + 1));
//                    System.out.println(filename);
                    printWriter.println("成功上传的文件:" + filename + "<br>");
                    String file2 = UUID.randomUUID().toString() + "_" + value + filename;
                    System.out.println(file2);
                    String webPath = "/upload/";
                    String filepath = getServletContext().getRealPath(webPath + filename);
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


        //输出结果为：
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
