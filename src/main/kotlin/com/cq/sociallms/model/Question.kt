package com.cq.sociallms.model

import javax.persistence.*

@Entity
class Question(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val description: String,

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    val quiz: Quiz

) : AuditableEntity<Long>()