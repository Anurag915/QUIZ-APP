package com.example.demo.service;

import com.example.demo.dao.QuestionDao;
import com.example.demo.dao.QuizDao;
import com.example.demo.model.Question;
import com.example.demo.model.QuestionWrapper;
import com.example.demo.model.Quiz;
import com.example.demo.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class QuizServiceImpl implements QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    @Override
    public ResponseEntity<String> createQuiz(String category,int numQs, String title) {
        List<Question> questions=questionDao.findRandomQuestionByCategory(category,numQs);
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
        Optional<Quiz> quiz=quizDao.findById(id);
        List<Question> questionFromDb=quiz.get().getQuestions();
        List<QuestionWrapper> questionforuser=new ArrayList<>();
        for(Question q:questionFromDb){
            QuestionWrapper questionWrapper=new QuestionWrapper();
            questionWrapper.setId(q.getId());
            questionWrapper.setQuestionTitle(q.getQuestionTitle());
            questionWrapper.setOption1(q.getOption1());
            questionWrapper.setOption2(q.getOption2());
            questionWrapper.setOption3(q.getOption3());
            questionWrapper.setOption4(q.getOption4());
            questionforuser.add(questionWrapper);
        }
        return new ResponseEntity<>(questionforuser,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> submitQuiz(int id, List<Response> responses) {
        Quiz quiz=quizDao.findById(id).get();
        List<Question> questions=quiz.getQuestions();
        int right=0;
        int i=0;
        for (Response response:responses){
            if (response.getResponse().equals(questions.get(i).getCorrectAnswer())){
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
