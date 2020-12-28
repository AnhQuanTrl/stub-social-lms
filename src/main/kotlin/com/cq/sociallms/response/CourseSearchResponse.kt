package com.cq.sociallms.response

data class CourseSearchResponse(
    val id: Long,
    val name: String,
    val mentorNames: List<String>,
    val image: String?,
    val menteeAvatars: List<String>,
    val progress: Int
)
