package pers.spectred.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import pers.spectred.example.doc.Coll;
import pers.spectred.example.repository.CollRepository;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class MongoSpringBootExampleApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(MongoSpringBootExampleApplication.class, args);

        Coll coll = new Coll();
        coll.setName("TTTT");
        coll.setAge("23");
        coll.setBirth(new Date());

        CollRepository collRepository = ctx.getBean("collRepository", CollRepository.class);
        collRepository.insert(coll);

        List<Coll> all = collRepository.findAll();
        System.out.println(all.size());

        List<Coll> collCus = collRepository.findCollCus(0.5);
        System.out.println(collCus.size());
    }

}
