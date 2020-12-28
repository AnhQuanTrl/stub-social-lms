package com.cq.sociallms.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "course")
class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    val image: String? = null,

    val description: String? = null,

    val subtitle: String? = null,

    @OneToMany(mappedBy = "course")
    val mentors: Set<Mentor> = setOf(),

    @OneToMany(mappedBy = "course")
    val mentees: Set<Mentee> = setOf(),

    val startDate: LocalDate? = null,

    val endDate: LocalDate? = null,

    @OneToMany(mappedBy = "associatedCourse")
    val associatedGroups: Set<Group> = setOf(),

    @OneToMany(mappedBy = "course")
    val modules: Set<Module> = setOf()
) : AuditableEntity<Long>()
