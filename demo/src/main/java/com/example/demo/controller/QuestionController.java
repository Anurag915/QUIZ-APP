package com.example.demo.controller;

import com.example.demo.model.Question;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @RequestMapping("allquestion")
    public ResponseEntity<List<Question>> getAllQuestion(){
        return  questionService.getAllQuestion();
    }
    @RequestMapping("category/{category}")
    public ResponseEntity<List<Question>> getAllQuestionByCategory(@PathVariable String category){
        return  questionService.getAllQuestionByCategory(category);
    }
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
         return questionService.addQuestion(question);
//         return "success";
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id){
        return questionService.deleteQuestion(id);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<String> updateQuestionById(@PathVariable  int id,@RequestBody Question question){
        return questionService.updateQuestionById(id,question);
    }
}
