package com.cq.sociallms.model

import java.io.Serializable

data class CourseItemUserId(
    val user: Long,
    val courseItem: Long,
) : Serializable
