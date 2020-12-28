package com.cq.sociallms.model

import javax.persistence.*

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true, nullable = false, updatable = false)
    val userName: String,

    @Column(nullable = false)
    val passWord: String,

    @Column(nullable = false)
    val fullName: String,

    @OneToMany(mappedBy = "user")
    val mentors: Set<Mentor> = setOf(),

    @OneToMany(mappedBy = "user")
    val mentees: Set<Mentor> = setOf(),

    @OneToMany(mappedBy = "user")
    val groupMembers: Set<GroupMember> = setOf(),

    @OneToMany(mappedBy = "user")
    val courseItemParticipants: Set<CourseItemParticipant> = setOf(),

    @OneToMany(mappedBy = "user")
    val courseItemProgresses: Set<CourseItemProgress> = setOf(),

    val title: String? = null,

    val institution: String? = null,

    val avatar: String? = null,
) : AuditableEntity<Long>()