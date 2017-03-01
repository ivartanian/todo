package com.mbrunarskiy.todo.service;

import org.apache.spark.api.java.JavaRDD;
import org.springframework.stereotype.Service;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

/**
 * @author maks
 */
@Service
public class SparkService {

    public List<String> topX(JavaRDD<String> lines, int top) {
        return lines.map(String::toLowerCase)
                .flatMap(s -> Arrays.asList(s.split(" ")).iterator())
                .mapToPair(s -> new Tuple2<>(s, 1))
                .reduceByKey(Integer::sum)
                .mapToPair(Tuple2::swap)
                .sortByKey(false)
                .map(Tuple2::_2)
                .take(top);
    }

}
