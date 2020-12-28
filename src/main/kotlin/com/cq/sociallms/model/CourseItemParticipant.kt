package com.cq.sociallms.model

import javax.persistence.*

@Entity
@IdClass(CourseItemUserId::class)
class CourseItemParticipant(
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @Id
    @ManyToOne
    @JoinColumn(name = "course_item_id")
    val courseItem: CourseItem,
) : AuditableEntity<Long>()
