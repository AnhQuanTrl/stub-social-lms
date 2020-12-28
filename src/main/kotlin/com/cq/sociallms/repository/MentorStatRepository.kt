package com.cq.sociallms.repository

import com.cq.sociallms.model.MentorStat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MentorStatRepository : JpaRepository<MentorStat, Long>