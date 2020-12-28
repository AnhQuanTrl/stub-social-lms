package com.cq.sociallms.repository

import com.cq.sociallms.model.CourseUserId
import com.cq.sociallms.model.Mentor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MentorRepository : JpaRepository<Mentor, CourseUserId>