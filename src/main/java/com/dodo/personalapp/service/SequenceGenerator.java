package com.dodo.personalapp.service;

import com.dodo.personalapp.entity.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class SequenceGenerator {
    @Autowired
    private MongoOperations op;

    public int generateNextThoughtsId()
    {
        /*Sequence count=op.findAndModify(
                Query.query(where("_id").is("sequence")),
                new Update().inc("sequence",1),
                options.returnNew(true).upsert(true),
                Sequence.class
        );
        */

        //Criteria criteria = new Criteria("_id").is("sequence");
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is("sequence"));

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        options.upsert(true);

        Sequence count=op.findAndModify(
                //new Query(criteria),
                query,
                new Update().inc("sequence",1),
                options,
                Sequence.class
        );

        return count.getSequence();
    }
}

