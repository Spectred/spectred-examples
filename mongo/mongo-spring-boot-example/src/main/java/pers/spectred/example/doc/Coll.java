package pers.spectred.example.doc;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "coll")
public class Coll {

    private String id;

    private String name;

    private String age;

    private Date birth;

    public Coll() {
    }

    public Coll(String id, String name, String age, Date birth) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birth = birth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Coll{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", birth=" + birth +
                '}';
    }
}
