package com.example.demo.service;

import com.example.demo.model.QuestionWrapper;
import com.example.demo.model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface QuizService {
    public ResponseEntity<String> createQuiz(String category,int numQs, String title);

    public ResponseEntity<List<QuestionWrapper>> getQuiz(int id);

    public ResponseEntity<Integer> submitQuiz(int id, List<Response> response);
}
