package com.cq.sociallms.repository

import com.cq.sociallms.dto.QuestionCountDto
import com.cq.sociallms.model.Question
import com.cq.sociallms.model.Quiz
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface QuestionRepository : JpaRepository<Question, Long> {
    @Query(
        """
            select new com.cq.sociallms.dto.QuestionCountDto(quiz.id, count(question.id))
            from Question question
            left join question.quiz quiz
            where quiz in :quizzes
            group by quiz.id
        """
    )
    fun countQuestionByQuizGroupByQuiz(@Param("quizzes") quizzes: Collection<Quiz>): List<QuestionCountDto>
}