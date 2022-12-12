package pers.spectred.example.dao;

import pers.spectred.example.doc.Coll;

import java.util.List;

public interface CollDao {

    void insertColl(Coll coll);

    Coll findByName(String name);

    List<Coll> findList(String name,double salary);
}
