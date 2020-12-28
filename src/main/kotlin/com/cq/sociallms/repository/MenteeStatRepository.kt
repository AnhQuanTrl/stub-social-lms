package com.cq.sociallms.repository

import com.cq.sociallms.model.MenteeStat
import com.cq.sociallms.model.MenteeStatId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenteeStatRepository : JpaRepository<MenteeStat, MenteeStatId>