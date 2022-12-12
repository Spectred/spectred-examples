package pers.spectred.example.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import pers.spectred.example.dao.CollDao;
import pers.spectred.example.doc.Coll;

@Repository("collDao")
public class CollDaoImpl implements CollDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insertColl(Coll coll) {
        // 如果不指定collectionName,模式是实体对象类的类名称
        mongoTemplate.insert(coll,"coll");
    }
}
