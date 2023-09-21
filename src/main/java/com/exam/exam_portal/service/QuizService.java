package com.exam.exam_portal.service;

import java.util.Set;

import com.exam.exam_portal.entity.quiz.Quiz;

public interface QuizService {

    public Quiz addQuiz(Quiz quiz);

    public Quiz updateQuiz(Quiz quiz);

    public Set<Quiz> getQuizzes();

    public Quiz getQuiz(Long quizID);

    public void deleteQuiz(Long quizID);
}
