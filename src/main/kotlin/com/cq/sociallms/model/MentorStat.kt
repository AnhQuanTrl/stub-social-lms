package com.cq.sociallms.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class MentorStat(
    @Id
    val id: Long,

    val courseNumber: Long,

    val studentNumber: Long,

    val studentRating: Double,
)