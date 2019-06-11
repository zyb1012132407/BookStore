package cn.edu.bdu.book.Servlet.DashBoard.Manager;

import cn.edu.bdu.book.Bean.Notice;
import cn.edu.bdu.book.Dao.NoticeDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.omg.CORBA.INTERNAL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//练习：
//对应页码：
    @WebServlet(name = "NoticeManagerServlet", urlPatterns = {"/NoticeManagerServlet"})
public class NoticeManagerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置Response的格式，解决乱码问题
        response.setContentType("text/html;charset=utf-8");

        //声明一个用于输出的对象
        PrintWriter printWriter = response.getWriter();
        NoticeDao noticeDao = new NoticeDao();
        String type = request.getParameter("type");
        noticeDao.refreshID();
        if("showAll".equals(type)){
            showAll(noticeDao,printWriter);

        }else if("findByID".equals(type)){
            int ID = Integer.parseInt(request.getParameter("id"));
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            for(Notice notice: noticeDao.findByID(ID)){
                jsonObject.put("id",notice.getId());
                jsonObject.put("title",notice.getTitle());
                jsonObject.put("content",notice.getContent());
                jsonObject.put("date",notice.getDate());
                jsonArray.add(jsonObject);
            }
            printWriter.println(jsonArray);
            jsonArray.clear();
        }else if("deleteNotice".equals(type)){
            int ID = Integer.parseInt(request.getParameter("id"));
            noticeDao.deleteNotice(ID);
            showAll(noticeDao,printWriter);
        }else if("addNotice".equals(type)){
            String title = request.getParameter("addNotice[title]");
            String content = request.getParameter("addNotice[content]");
            String date = request.getParameter("addNotice[date]");
            noticeDao.addNotice(new Notice(title,content,date));
            showAll(noticeDao,printWriter);
        }else if("updateNotice".equals(type)){
            String title = request.getParameter("addNotice[title]");
            String content = request.getParameter("addNotice[content]");
            String date = request.getParameter("addNotice[date]");
            int ID = Integer.parseInt(request.getParameter("id"));
            noticeDao.updateNotice(new Notice(title,content,date),ID);
            showAll(noticeDao,printWriter);
        }

        //输出结果为：
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void showAll(NoticeDao noticeDao,PrintWriter printWriter){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        for(Notice notice: noticeDao.findAll()){
            jsonObject.put("id",notice.getId());
            jsonObject.put("title",notice.getTitle());
            jsonObject.put("content",notice.getContent());
            jsonObject.put("date",notice.getDate());
            jsonArray.add(jsonObject);
        }
        printWriter.println(jsonArray);
        jsonArray.clear();
    }
}
