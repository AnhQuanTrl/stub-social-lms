package com.cq.sociallms.model

import javax.persistence.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@Entity
@IdClass(CourseUserId::class)
class Mentee(
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: User,

    @Id
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    val course: Course,

    @Column(nullable = false)
    val isCompleted: Boolean = false,

    val rating: Int? = null,

    val review: String? = null,
) : AuditableEntity<Long>()