package com.cq.sociallms.response

data class MentorResponse(
    val id: Long,
    val name: String,
    val avatar: String?,
    val title: String?,
    val description: String?,
    val courseNumber: Long?,
    val studentNumber: Long?,
    val studentRating: Double?,
)
