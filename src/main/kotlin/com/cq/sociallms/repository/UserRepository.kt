package com.cq.sociallms.repository

import com.cq.sociallms.model.Course
import com.cq.sociallms.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByIdIn(userIds: Collection<Long>): Set<Long>
}