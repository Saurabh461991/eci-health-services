package org.cdac.gist.web.repositories;

import com.mongodb.MapReduceCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sgalande on 2/6/15.
 */

@Repository
public class FormsCountRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<MapReduceCommand.OutputType> getFormsCount(String formName) {
        AggregationResults<MapReduceCommand.OutputType> results = mongoTemplate.aggregate(agg, "INPUT_COLLECTION_NAME", MapReduceCommand.OutputType.class);
        List<MapReduceCommand.OutputType> mappedResult = results.getMappedResults();
        return mappedResult;
    }

}
