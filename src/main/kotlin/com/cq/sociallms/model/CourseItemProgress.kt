package com.cq.sociallms.model

import javax.persistence.*

@Entity
@IdClass(CourseItemUserId::class)
class CourseItemProgress(
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @Id
    @ManyToOne
    @JoinColumn(name = "course_item_id")
    val courseItem: CourseItem,

    @Column(nullable = false)
    val isCompleted: Boolean = false,

    @Column(precision = 4, scale = 2)
    val finalScore: Float? = null
) : AuditableEntity<Long>()