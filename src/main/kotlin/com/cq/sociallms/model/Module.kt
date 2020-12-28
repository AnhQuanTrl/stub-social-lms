package com.cq.sociallms.model

import javax.persistence.*

@Entity
class Module(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    val course: Course,

    @OneToMany(mappedBy = "module")
    val courseItems: Set<CourseItem> = setOf(),
) : AuditableEntity<Long>()
