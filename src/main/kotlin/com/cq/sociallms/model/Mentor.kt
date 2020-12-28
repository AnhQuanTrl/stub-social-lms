package com.cq.sociallms.model

import javax.persistence.*

@Entity
@IdClass(CourseUserId::class)
class Mentor(
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: User,

    @Id
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    val course: Course,

    @Column(nullable = false)
    val isPrimary: Boolean = true,

    val description: String = "",
) : AuditableEntity<Long>()