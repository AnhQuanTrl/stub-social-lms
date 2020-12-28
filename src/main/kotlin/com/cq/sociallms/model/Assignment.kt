package com.cq.sociallms.model

import javax.persistence.Entity

@Entity
class Assignment(
    val passedThreshold: Int? = null,
    name: String,
    module: Module
) : CourseItem(name = name, module = module)