package com.example.demo.dao;

import com.example.demo.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {


    List<Question> findByCategory(String category);

    @Query(value = "SELECT * \n" +
            "FROM question q \n" +
            "WHERE q.category = :category \n" +
            "ORDER BY RAND() \n" +
            "LIMIT :numQs",nativeQuery = true)
    List<Question> findRandomQuestionByCategory(String category,int numQs);
}
