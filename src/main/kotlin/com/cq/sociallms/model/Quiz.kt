package com.cq.sociallms.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Quiz(
    val duration: Int? = null,
    val passedThreshold: Int? = null,
    @OneToMany(mappedBy = "quiz")
    val questions: Set<Question> = setOf(),
    name: String,
    module: Module,
) : CourseItem(name = name, module = module)