package com.example.demo.service;

import com.example.demo.model.Question;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {
//    @Autowired
//    static QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestion();

    public ResponseEntity<List<Question>> getAllQuestionByCategory(String category);

    public ResponseEntity<String> addQuestion(Question question);

    public ResponseEntity<String> deleteQuestion(int id);

    public ResponseEntity<String> updateQuestionById(int id,Question question);

//        return  questionDao.getAllQuestion();
//    }
}
