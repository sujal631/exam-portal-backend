package com.exam.exam_portal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map;

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

import com.exam.exam_portal.entity.category.Category;
import com.exam.exam_portal.entity.questions.Question;
import com.exam.exam_portal.entity.quiz.Quiz;
import com.exam.exam_portal.service.CategoryService;
import com.exam.exam_portal.service.QuestionService;
import com.exam.exam_portal.service.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CategoryService categoryService;

    // add a quiz
    @PostMapping("/")
    public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz) {
        Quiz quiz2 = this.quizService.addQuiz(quiz);
        return ResponseEntity.ok(quiz2);
    }

    // get all quizzes
    @GetMapping("/")
    public ResponseEntity<?> getQuizzes() {
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }

    // get all quizzes from a category
    @GetMapping("/category/{categoryID}")
    public ResponseEntity<?> getQuizzesFromCategory(@PathVariable("categoryID") Long categoryID) {
        Category category = this.categoryService.getCategory(categoryID);
        Set<Quiz> quizzes = category.getQuizzes();
        List<Quiz> list = new ArrayList<>(quizzes);

        return ResponseEntity.ok(list);
    }

    // get a quiz
    @GetMapping("/{quizID}")
    public ResponseEntity<?> getQuiz(@PathVariable("quizID") Long quizID) {
        return ResponseEntity.ok(this.quizService.getQuiz(quizID));
    }

    // update quiz
    @PutMapping("/")
    public ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }

    // delete a quiz
    @DeleteMapping("/{quizID}")
    public ResponseEntity<?> deleteQuiz(@PathVariable("quizID") Long quizID) {
        this.quizService.deleteQuiz(quizID);
        return ResponseEntity.ok("Success.");
    }

    // evaluating quiz
    @PostMapping("/evaluate-quiz")
    public ResponseEntity<?> evaluateQuiz(@RequestBody List<Question> questions) {
        System.out.println(questions);
        double pointsScored = 0;
        int correctAnswers = 0;
        int questionsAttempted = 0;
        for (Question ques : questions) {
            Question question = this.questionService.getQuestion(ques.getQuestionID());
            // calculating total correct answers and points scored
            if (question.getAnswer().trim().equals(ques.getSelectedAnswer().trim())) {
                correctAnswers++;
                double pointsForEachQuestion = Double.parseDouble(questions.get(0).getQuiz().getMaxPoints())
                        / questions.size();
                pointsScored += pointsForEachQuestion;
            }
            // calculating how many questions were attempted
            if (ques.getSelectedAnswer() != null && !ques.getSelectedAnswer().trim().equals("")) {
                questionsAttempted++;
            }

        }
        ;

        Map<String, Object> map = Map.of("pointsScored", pointsScored, "correctAnswers", correctAnswers,
                "questionsAttempted", questionsAttempted);
        return ResponseEntity.ok(map);
    }

}
