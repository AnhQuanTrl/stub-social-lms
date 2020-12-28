package com.cq.sociallms.model

import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract class CourseItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    val description: String? = null,

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    val module: Module,

    @Column(nullable = false)
    val isRequired: Boolean = false,

    @OneToMany(mappedBy = "courseItem")
    val courseItemParticipants: Set<CourseItemParticipant> = setOf(),

    @OneToMany(mappedBy = "courseItem")
    val courseItemProgresses: Set<CourseItemProgress> = setOf(),

    @OneToOne(mappedBy = "courseItem")
    val event: Event? = null,
) : AuditableEntity<Long>()