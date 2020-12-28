package com.cq.sociallms.response

import java.time.LocalDate

data class CourseOverviewResponse(
    val id: Long = 0,

    val name: String,

    val image: String?,

    val description: String?,

    val subtitle: String?,

    val mentors: Set<UserBadgeResponse>,

    val startDate: LocalDate?,

    val endDate: LocalDate?,

    val outlineNumber: Int,

    val groupNumber: Int,

    val reviewNumber: Int,

    val menteeCount: Int,
)