package com.cq.sociallms.repository

import com.cq.sociallms.model.CourseUserId
import com.cq.sociallms.model.Mentee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenteeRepository : JpaRepository<Mentee, CourseUserId>