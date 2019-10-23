package com.mxh.springbootstart.demo.springbootstartdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringbootStartDemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    JdbcTemplate db1JdbcTemplate;

    @Autowired
    JdbcTemplate db2JdbcTemplate;

//    @Test
//    public  void addData(){
//        String sql = "insert into user_info(name,age) values('tom',30)";
//        jdbcTemplate.execute(sql);
//    }

    @Test
    public  void addData1(){
        String sql = "insert into user_info(name,age) values('tom2',30)";
        db1JdbcTemplate.execute(sql);
    }

    @Test
    public  void addData2(){
        String sql = "insert into user_info(name,age) values('jack',28)";
        db2JdbcTemplate.execute(sql);
    }


}
