package com.cq.sociallms.model

import javax.persistence.*

@Entity
@IdClass(CourseTopicId::class)
class CourseTopic(
    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    val course: Course,

    @Id
    @ManyToOne
    @JoinColumn(name = "topic_id")
    val topic: Topic,
) : AuditableEntity<Long>()