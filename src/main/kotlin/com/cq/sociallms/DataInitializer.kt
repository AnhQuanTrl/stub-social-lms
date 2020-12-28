package com.cq.sociallms

import com.cq.sociallms.model.Course
import com.cq.sociallms.model.Mentee
import com.cq.sociallms.model.Mentor
import com.cq.sociallms.model.User
import com.cq.sociallms.repository.CourseRepository
import com.cq.sociallms.repository.MenteeRepository
import com.cq.sociallms.repository.MentorRepository
import com.cq.sociallms.repository.UserRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class DataInitializer(
    val courseRepository: CourseRepository,
    val userRepository: UserRepository,
    val menteeRepository: MenteeRepository,
    val mentorRepository: MentorRepository,

) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        val mainStudent = userRepository.save(
            User(
                userName = "QuanTLA",
                fullName = "Quan Tran",
                passWord = "12345",
                institution = "HCM University of Technology",
            )
        )
        val second = userRepository.save(
            User(
                userName = "CuongVC",
                fullName = "Vuong Chi Cuong",
                passWord = "12345",
                institution = "HCM University of Technology",
            )
        )
        val teacher = userRepository.save(
            User(
                userName = "Jacob",
                fullName = "Jacob Jone",
                passWord = "12345",
                institution = "HCM University of Technology",
            )
        )
        val course = courseRepository.save(
            Course(
                name = "Principle of Programming Language",
            )
        )

        menteeRepository.saveAll(
            setOf(
                Mentee(
                    mainStudent,
                    course,
                    rating = 4
                ),
                Mentee(
                    second,
                    course,
                    rating = 3
                )
            )
        )

        mentorRepository.saveAll(
            setOf(
                Mentor(
                    teacher,
                    course,
                )
            )
        )

    }
}