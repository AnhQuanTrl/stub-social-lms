package com.cq.sociallms.model

import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
class MenteeStatId(
    var userId: Long,
    var courseId: Long
) : Serializable