package pers.spectred.example.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import pers.spectred.example.dao.CollDao;
import pers.spectred.example.doc.Coll;

import java.util.List;

@Repository("collDao")
public class CollDaoImpl implements CollDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insertColl(Coll coll) {
        // 如果不指定collectionName,模式是实体对象类的类名称
        mongoTemplate.insert(coll, "coll");
    }

    @Override
    public Coll findByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        List<Coll> colls = mongoTemplate.find(query, Coll.class);
        return CollectionUtils.isEmpty(colls) ? null : colls.get(0);
    }

    @Override
    public List<Coll> findList(String name, double salary) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name)
                .andOperator(Criteria.where("salary").is(salary))
        );
        return mongoTemplate.find(query, Coll.class, "coll");
    }
}
