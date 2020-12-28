package com.cq.sociallms.response

class CourseUserStatResponse(
    val progress: Int,
    val score: Int,
    val ranking: Int,
    val videoTotalCount: Int,
    val quizTotalCount: Int,
    val assignmentTotalCount: Int,
    val videoCompletedCount: Int,
    val quizCompletedCount: Int,
    val assignmentCompletedCount: Int,
)