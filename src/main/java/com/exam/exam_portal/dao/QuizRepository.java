package com.exam.exam_portal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.exam.exam_portal.entity.quiz.Quiz;

import jakarta.transaction.Transactional;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM quiz WHERE quizID = ?1", nativeQuery = true)
    public void deleteById(Long quizID);
}
