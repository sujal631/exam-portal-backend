package com.exam.exam_portal.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.exam_portal.entity.questions.Question;
import com.exam.exam_portal.entity.quiz.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Set<Question> findByQuiz(Quiz quiz);

}
