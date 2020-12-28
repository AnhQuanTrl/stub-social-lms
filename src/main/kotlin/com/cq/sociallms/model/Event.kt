package com.cq.sociallms.model

import java.time.LocalDate
import javax.persistence.*

@Entity
class Event(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val startDate: LocalDate? = null,

    val endDate: LocalDate? = null,

    @OneToOne(optional = false)
    @JoinColumn(name = "course_item_id")
    val courseItem: CourseItem,

    ) : AuditableEntity<Long>()