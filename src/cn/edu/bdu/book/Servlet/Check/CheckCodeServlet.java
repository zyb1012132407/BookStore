package cn.edu.bdu.book.Servlet.Check;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

//练习：
//对应页码：
@WebServlet(name = "CheckCodeServlet", urlPatterns = {"/CheckCodeServlet"})
public class CheckCodeServlet extends HttpServlet {

    //声明验证码的长度宽度
    private static int WIDTH = 93;
    private static int HEIGHT = 39;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //声明HttpSession对象
        HttpSession session = request.getSession();

        //设置输出类型为图片
        response.setContentType("image/jpeg");

        //声明一个输出对象
        ServletOutputStream servletOutputStream = response.getOutputStream();

        //设置浏览器不要缓存此照片
        response.setHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires",0);

        //创建内存图像并获得其图形上下文
        BufferedImage bufferedImage = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        //获得画笔
        Graphics g =  bufferedImage.getGraphics();
        //产生随机的认证码
        char[] rands = generateCheckCode();
        //产生图像
        drawBackground(g);
        drawRands(g,rands);
        //将图像输出到客户端
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage,"JPEG",byteArrayOutputStream);
        byte[] buf = byteArrayOutputStream.toByteArray();
        response.setContentLength(buf.length);
        //下面的语句也可以写成：byteArrayOutputStream.writeTo(sos);
        servletOutputStream.write(buf);
        byteArrayOutputStream.close();
        servletOutputStream.close();
        //将验证码存入check-mode中
//        System.out.println("checkPost:" + new String(rands));
        session.setAttribute("check-mode",new String(rands));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    //生成4位随机验证码
    private char[] generateCheckCode(){
        String chars="0123456789abcdefghijklmnopqrstuvwxyz";
        char[] rands = new char[4];
        for(int i = 0; i<4;i++){
            int rand = (int)(Math.random() * 36);
            rands[i] = chars.charAt(rand);
        }
        return rands;
    }

    //开始画背景
    private void drawBackground(Graphics graphics){
        graphics.setColor(new Color(0xDCDCDC));
        graphics.fillRect(0,0,WIDTH,HEIGHT);
        for(int i = 0; i<120;i++){
            graphics.setColor(new Color((int)Math.random()*255,
                                        (int)Math.random()*255,
                                        (int)Math.random()*255));
            graphics.drawOval((int)Math.random() * WIDTH,
                                (int)Math.random()*HEIGHT,
                                1,
                                0);
        }
    }

    //开始画验证数
    private void drawRands(Graphics graphics,char[] rands){
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font(null,Font.ITALIC|Font.BOLD,30));
        graphics.drawString("" + rands[0],8,27);
        graphics.drawString("" + rands[1],26,25);
        graphics.drawString("" + rands[2],46,28);
        graphics.drawString("" + rands[3],60,26);
    }

}
