package com.cq.sociallms.dto

interface CourseSearchDto {
    val id: Long
    val name: String
    val image: String?
    val progress: Int
    val mentorNames: List<String>
    val menteeAvatars: List<String>
}