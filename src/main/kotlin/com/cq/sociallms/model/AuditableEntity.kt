package com.cq.sociallms.model

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AuditableEntity<T> {
    @CreatedBy
    @Column(updatable = false)
    val createdBy: T? = null

    @LastModifiedBy
    val lastModifiedBy: T? = null

    @CreatedDate
    @Column(updatable = false, nullable = false)
    lateinit var createdDate: LocalDateTime

    @LastModifiedDate
    @Column(nullable = false)
    lateinit var lastModifiedDate: LocalDateTime
}