package com.cq.sociallms.model

import javax.persistence.Column
import javax.persistence.Entity

@Entity
class DownloadableContent(
    @Column(nullable = false)
    val url: String,
    name: String,
    module: Module
) : CourseItem(name = name, module = module)
