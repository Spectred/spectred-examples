package pers.spectred.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.spectred.example.dao.CollDao;
import pers.spectred.example.doc.Coll;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Coll coll = new Coll();
        coll.setName("Mary");
        coll.setAge("10");
        coll.setBirth(new Date());

        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        CollDao dao = context.getBean("collDao", CollDao.class);
//        dao.insertColl(coll);
        List<Coll> list = dao.findList("test-0",0.98);
        System.out.println(list);
    }
}