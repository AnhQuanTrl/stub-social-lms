package com.cq.sociallms.model

import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass

@Entity
class MenteeStat(
    @EmbeddedId
    val menteeStatId: MenteeStatId,

    val studentNumber: Long,

    val studentRating: Double,
)