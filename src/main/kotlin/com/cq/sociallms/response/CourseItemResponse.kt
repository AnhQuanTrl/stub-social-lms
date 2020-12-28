package com.cq.sociallms.response

import java.time.Duration

data class CourseItemResponse(
    val id: Long,
    val name: String,
    val type: String,
    val description: String? = null,
    val participants: List<UserBadgeResponse>,
    val lastModifiedDate: String,
    var passedThreshold: Int? = 0,
    var duration: Int? = null,
    var url: String? = null,
    var questionNumber: Long = 0,
    val lastAttempt: Int?,
    val lastAttemptDate: String
)