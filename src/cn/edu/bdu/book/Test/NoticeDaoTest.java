package cn.edu.bdu.book.Test;

import cn.edu.bdu.book.Bean.Notice;
import cn.edu.bdu.book.Dao.NoticeDao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NoticeDaoTest {
    public static void main(String[] args) {
        Date date = new Date();
        new SimpleDateFormat("yyyyMMddhhmmss").format(date).toString();
        System.out.println(new SimpleDateFormat("yyyyMMddhhmmss").format(date).toString());
//        notice.setTitle("title");
//        notice.setContent("content");
//        notice.setDate("2019-12-01 12:01");

//        noticeDao.addNotice(notice);
//        noticeDao.updateNotice(notice,1);
//        noticeDao.deleteNotice(2);
    }
}
