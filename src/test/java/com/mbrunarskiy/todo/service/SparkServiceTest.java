package com.mbrunarskiy.todo.service;

import com.mbrunarskiy.todo.TodoApplication;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author maks
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SparkServiceTest {

    @Autowired
    private JavaSparkContext sc;
    @Autowired
    private SparkService sparkService;

    @Test
    public void testTopX() throws Exception {

        JavaRDD<String> rdd = sc.parallelize(Arrays.asList("java java java scala groovy python", "java java java scala groovy python"));
        List<String> top1 = sparkService.topX(rdd, 1);
        Assert.assertEquals("java", top1.get(0));

    }
}
