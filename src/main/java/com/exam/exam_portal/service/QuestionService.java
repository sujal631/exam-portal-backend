package com.exam.exam_portal.service;

import java.util.Set;

import com.exam.exam_portal.entity.questions.Question;
import com.exam.exam_portal.entity.quiz.Quiz;

public interface QuestionService {

    public Question addQuestion(Question question);

    public Question updateQuestion(Question question);

    public Set<Question> getQuestions();

    public Question getQuestion(Long questionID);

    public Set<Question> getQuestionsFromQuiz(Quiz quiz);

    public void deleteQuestion(Long questionID);

}
