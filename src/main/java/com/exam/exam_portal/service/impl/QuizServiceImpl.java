package com.exam.exam_portal.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.exam_portal.dao.QuizRepository;
import com.exam.exam_portal.entity.quiz.Quiz;
import com.exam.exam_portal.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Set<Quiz> getQuizzes() {
        return new HashSet<>(this.quizRepository.findAll());
    }

    @Override
    public Quiz getQuiz(Long quizID) {
        return this.quizRepository.findById(quizID).get();
    }

    @Override
    public void deleteQuiz(Long quizID) {
        this.quizRepository.deleteById(quizID);
    }

}
