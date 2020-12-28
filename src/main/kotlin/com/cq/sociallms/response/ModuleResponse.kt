package com.cq.sociallms.response

data class ModuleResponse(
    val id: Long,
    val name: String,
    val courseItems: List<CourseItemResponse>
)