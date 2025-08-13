package org.jpahibernante.jpahibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcRepository {


    @Autowired
     private JdbcTemplate jdbcTemplate ;

     private static String INSERT =
             "insert into course values (2,'randira','test') " ;
     public void insertData() {
        jdbcTemplate.update( INSERT);

     }



}
