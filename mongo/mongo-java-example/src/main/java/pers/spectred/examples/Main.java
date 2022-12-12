package pers.spectred.examples;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class Main {

    public static void main(String[] args) {
        // 插入一条
//        insert();

        // 查询
//        find();
        // 文档查询过滤
        findFilter();
    }


    private static void insert() {
        MongoClient mongoClient = new MongoClient("tx", 27017);
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("coll");
        Document doc = Document.parse("{name:'Java',birth: new ISODate('1995-01-01'),title:'Java语言'}");
        collection.insertOne(doc);
        mongoClient.close();
    }

    private static void find() {
        MongoClient mongoClient = new MongoClient("tx", 27017);
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("coll");

        Document doc = new Document();
        doc.append("name", 1);

        FindIterable<Document> iterable = collection.find().sort(doc);
        for (Document document : iterable) {
            System.out.println(document);
        }
        mongoClient.close();
    }

    private static void findFilter() {
        MongoClient mongoClient = new MongoClient("tx", 27017);
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("coll");

        Document doc = new Document();
        doc.append("name", 1);

        FindIterable<Document> iterable = collection.find(Filters.eq("name","Java")).sort(doc);
        for (Document document : iterable) {
            System.out.println(document);
        }
        mongoClient.close();
    }
}
