package pers.spectred.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pers.spectred.example.doc.Coll;

import java.util.List;

@Repository("collRepository")
public interface CollRepository extends MongoRepository<Coll,String> {


    @Query(value = "{name: {$gt: ?0}}")
    List<Coll> findCollCus(double salary);
}
