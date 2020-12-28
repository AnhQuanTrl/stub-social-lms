package com.cq.sociallms.model

import javax.persistence.*

@Entity
@IdClass(GroupUserId::class)
class GroupMember(
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @Id
    @ManyToOne
    @JoinColumn(name = "group_id")
    val group: Group,

    @Column(nullable = false)
    val isModerator: Boolean = false,

    @Column(nullable = false)
    val isCreator: Boolean = false,

    @Column(nullable = false)
    val isPinned: Boolean = false,
) : AuditableEntity<Long>()