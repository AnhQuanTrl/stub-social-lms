package com.cq.sociallms.model

import java.io.Serializable

data class CourseTopicId(
    val course: Long,
    val topic: Long,
) : Serializable