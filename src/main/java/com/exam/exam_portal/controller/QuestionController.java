package com.exam.exam_portal.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.exam_portal.entity.questions.Question;
import com.exam.exam_portal.entity.quiz.Quiz;
import com.exam.exam_portal.service.QuestionService;
import com.exam.exam_portal.service.QuizService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    // add a question
    @PostMapping("/")
    public ResponseEntity<?> addQuestion(@RequestBody Question question) {
        Question question2 = this.questionService.addQuestion(question);
        return ResponseEntity.ok(question2);
    }

    // get all questions
    @GetMapping("/")
    public ResponseEntity<?> getQuestions() {
        return ResponseEntity.ok(this.questionService.getQuestions());
    }

    // get a question
    @GetMapping("/{questionID}")
    public ResponseEntity<?> getQuestion(@PathVariable("questionID") Long questionID) {
        return ResponseEntity.ok(this.questionService.getQuestion(questionID));
    }

    // get all questions from a quiz for user
    @GetMapping("/quiz/{quizID}")
    public ResponseEntity<?> getQuestionsFromQuiz(@PathVariable("quizID") Long quizID) {
        Quiz quiz = this.quizService.getQuiz(quizID);
        Set<Question> questions = quiz.getQuestions();
        List<Question> list = new ArrayList<>(questions);
        if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
            list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions()));
        }
        list.forEach((ques) -> {
            ques.setAnswer("");
        });
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    // get all questions from a quiz for Admin
    @GetMapping("/quiz/all/{quizID}")
    public ResponseEntity<?> getQuestionsFromQuizForAdmin(@PathVariable("quizID") Long quizID) {
        Quiz quiz = this.quizService.getQuiz(quizID);
        Set<Question> questions = quiz.getQuestions();
        List<Question> list = new ArrayList<>(questions);

        return ResponseEntity.ok(list);
    }

    // update question
    @PutMapping("/")
    public ResponseEntity<?> updateQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }

    // delete a question
    @DeleteMapping("/{questionID}")
    public void deleteQuestion(@PathVariable("questionID") Long questionID) {
        this.questionService.deleteQuestion(questionID);
    }

}
