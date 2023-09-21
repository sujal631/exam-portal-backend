package com.exam.exam_portal.entity.questions;

import com.exam.exam_portal.entity.quiz.Quiz;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionID;
    @Column(length = 5000)
    private String questionContent;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    @Transient
    private String selectedAnswer;
    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;

    public Question() {
    }

    public Question(String questionContent, String option1, String option2, String option3, String option4,
            String answer, Quiz quiz) {
        this.questionContent = questionContent;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.quiz = quiz;
    }

    public Long getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Long questionID) {
        this.questionID = questionID;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

}
