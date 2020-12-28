package com.cq.sociallms.model

import javax.persistence.Column
import javax.persistence.Entity

@Entity
class Video(
    @Column(nullable = false)
    val url: String,

    @Column(nullable = false)
    val duration: Int,

    name: String,

    module: Module,
) : CourseItem(name = name, module = module)
