package com.cq.sociallms.model

import javax.persistence.*

@Entity
class Topic(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false, updatable = false, unique = true)
    val name: String,
)
