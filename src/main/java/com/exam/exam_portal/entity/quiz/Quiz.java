package com.exam.exam_portal.entity.quiz;

import java.util.HashSet;
import java.util.Set;

import com.exam.exam_portal.entity.category.Category;
import com.exam.exam_portal.entity.questions.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long quizID;
    private String quizName;
    @Column(length = 5000)
    private String quizDescription;
    private String maxPoints;
    private String numberOfQuestions;
    private boolean active = false;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Question> questions = new HashSet<>();

    public Quiz() {
    }

    public Quiz(String quizName, String quizDescription, String maxPoints, String numberOfQuestions,
            boolean active) {
        this.quizName = quizName;
        this.quizDescription = quizDescription;
        this.maxPoints = maxPoints;
        this.numberOfQuestions = numberOfQuestions;
        this.active = active;
    }

    public Long getQuizID() {
        return quizID;
    }

    public void setQuizID(Long quizID) {
        this.quizID = quizID;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getQuizDescription() {
        return quizDescription;
    }

    public void setQuizDescription(String quizDescription) {
        this.quizDescription = quizDescription;
    }

    public String getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(String maxPoints) {
        this.maxPoints = maxPoints;
    }

    public String getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(String numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

}
