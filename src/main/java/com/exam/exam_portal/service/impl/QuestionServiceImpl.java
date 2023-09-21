package com.exam.exam_portal.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.exam_portal.dao.QuestionRepository;
import com.exam.exam_portal.entity.questions.Question;
import com.exam.exam_portal.entity.quiz.Quiz;
import com.exam.exam_portal.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question addQuestion(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        return this.questionRepository.save(question);

    }

    @Override
    public Set<Question> getQuestions() {
        return new HashSet<>(this.questionRepository.findAll());
    }

    @Override
    public Question getQuestion(Long questionID) {
        return this.questionRepository.findById(questionID).get();
    }

    @Override
    public Set<Question> getQuestionsFromQuiz(Quiz quiz) {
        return this.questionRepository.findByQuiz(quiz);
    }

    @Override
    public void deleteQuestion(Long questionID) {
        Question question = new Question();
        question.setQuestionID(questionID);

        this.questionRepository.delete(question);
    }

}
