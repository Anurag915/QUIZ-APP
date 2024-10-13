package com.example.demo.service;

import com.example.demo.model.Question;
import com.example.demo.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class QuestionServiceImpl implements QuestionService{
    @Autowired
     QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestion(){
        try{

        return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Question>> getAllQuestionByCategory(String category) {
        try {

        return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<String> addQuestion(Question question) {
        try {

        questionDao.save(question);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("failure",HttpStatus.CONFLICT);
    }

    @Override
    public ResponseEntity<String> deleteQuestion(int id) {
        try {
        questionDao.deleteById(id);
        return new ResponseEntity<>("success",HttpStatus.GONE);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failure",HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> updateQuestionById(int id,Question question) {
        Optional<Question> emp=questionDao.findById(id);
//        System.out.println(emp.get());
        if (emp.isPresent()){
            Question question1=emp.get();
            question1.setId(question.getId());
            question1.setCategory(question.getCategory());
            question1.setDifficultyLevel(question.getDifficultyLevel());
            question1.setOption1(question.getOption1());
            question1.setOption2(question.getOption2());
            question1.setOption3(question.getOption3());
            question1.setOption4(question.getOption4());
            question1.setQuestionTitle(question.getQuestionTitle());
            question1.setCorrectAnswer(question.getCorrectAnswer());
            questionDao.save(question1);
            return new ResponseEntity<>("success",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("failure",HttpStatus.NOT_FOUND);
        }
    }
}
