package com.cq.sociallms.model

import javax.persistence.*

@Entity
@Table(name = "groups")
class Group(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val name: String,
    
    val description: String,

    @Column(nullable = false)
    val isPublic: Boolean,

    val avatar: String? = null,

    @ManyToOne
    @JoinColumn(name = "course_id")
    val associatedCourse: Course,

    @OneToMany(mappedBy = "group")
    val groupMembers: Set<GroupMember> = setOf(),
)