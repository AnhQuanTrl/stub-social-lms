package com.cq.sociallms.repository

import com.cq.sociallms.dto.CourseSearchDto
import com.cq.sociallms.model.Course
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CourseRepository : JpaRepository<Course, Long> {

    @Query("""
        select course
        from Course course
        left join fetch course.mentors mentor
        left join fetch mentor.user
        left join fetch course.mentees mentee
        left join fetch mentee.user
        where course.id = :id
    """)
    fun findCourseWithMentorAndMenteeById(@Param("id") id: Long): Optional<Course>;

    @Query("""
        select course
        from Course course
        left join fetch course.associatedGroups associatedGroup
        left join fetch associatedGroup.groupMembers as groupMember
        left join fetch groupMember.user
        where course.id = :id
    """)
    fun findCourseWithGroupById(@Param("id") id: Long): Optional<Course>;

    @Query("""
        select count(associatedGroup)
        from Course course
        left join course.associatedGroups associatedGroup
        where course.id = :id
    """)
    fun countAssociatedGroupById(@Param("id") id: Long): Int;

    @Query("""
        select count(mentee)
        from Course course
        left join course.mentees mentee
            on coalesce(mentee.review, '') <> ''
        where course.id = :id
    """)
    fun countReviewById(@Param("id") id: Long): Int;

    @Query("""
        select count(courseItem)
        from Course course
        left join course.modules module
        left join module.courseItems courseItem
        where course.id = :id
    """)
    fun countCourseItemById(@Param("id") id: Long): Int

    @Query(
        """
            WITH course_progress as (
                SELECT 
                    c.id, 
                    c.name, 
                    100 * COUNT(CASE WHEN cip.is_completed THEN 1 END) / greatest(COUNT(ci.id), 1) as progress
                FROM mentee m 
                INNER JOIN course c
                    ON c.id = m.course_id
                LEFT JOIN module md
                    ON md.course_id = c.id
                LEFT JOIN course_item ci
                    ON ci.module_id = md.id
                LEFT JOIN course_item_progress cip
                    ON cip.course_item_id = ci.id
                    AND cip.user_id = 1
                WHERE 
                    m.user_id = 1
                GROUP BY
                    c.id
            )
            SELECT cp.id, cp.name, cp.progress, u2.full_name as mentorNames, u1.avatar as menteeAvatars
            FROM course c
            INNER JOIN course_progress cp
                ON cp.id = c.id
            LEFT JOIN mentee tee
                ON tee.course_id = c.id
            LEFT JOIN users u1
                ON u1.id = tee.user_id
            LEFT JOIN mentor tor
                ON tor.course_id = c.id
            LEFT JOIN users u2
                ON u2.id = tor.user_id
        """,
        countQuery =
        """
            SELECT COUNT(*)
            FROM mentee m 
            INNER JOIN course c
                ON c.id = m.course_id
            WHERE 
                m.user_id = 1
        """,
        nativeQuery = true
    )
    fun findCourseSearchDtoByMenteeUserId(
        @Param("userId") userId: Long,
        pageable: Pageable
    ): Page<CourseSearchDto>
}