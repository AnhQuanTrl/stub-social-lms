package com.cq.sociallms.repository

import com.cq.sociallms.model.CourseItem
import com.cq.sociallms.model.Module
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CourseItemRepository : JpaRepository<CourseItem, Long> {

    @Query(
        """
            select ci
            from CourseItem ci
            inner join ci.module md
            inner join md.course c
            left join fetch ci.courseItemProgresses progress
            where c.id = :courseId and progress.user.id = :userId
        """
    )
    fun findCourseItemByCourseId(@Param("courseId") courseId: Long, @Param("userId") userId: Long): List<CourseItem>


    @Query("""
        select ci
        from CourseItem ci
        left join fetch ci.courseItemParticipants cip
        left join fetch cip.user
        where ci in :courseItems
    """)
    fun findCourseItemParticipantByCourseItem(@Param("courseItems") courseItems: Collection<CourseItem>): List<CourseItem>
}