package com.cq.sociallms.service

import com.cq.sociallms.dto.CourseSearchDto
import com.cq.sociallms.model.*
import com.cq.sociallms.repository.*
import com.cq.sociallms.request.CourseSearchRequest
import com.cq.sociallms.response.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class CourseService(
    val courseRepository: CourseRepository,
    val menteeRepository: MenteeRepository,
    val courseItemRepository: CourseItemRepository,
    val userRepository: UserRepository,
    val mentorStatRepository: MentorStatRepository,
    val groupMemberRepository: GroupMemberRepository,
    val questionRepository: QuestionRepository,
    val menteeStatRepository: MenteeStatRepository
) {

    fun findAll(
        courseSearchRequest: CourseSearchRequest,
        pageable: Pageable
    ) : Page<CourseSearchDto> {
        return courseRepository
                .findCourseSearchDtoByMenteeUserId(courseSearchRequest.menteeId, pageable)
    }

    fun findOne(
        courseId: Long,
    ) : Optional<CourseOverviewResponse> {
        val course = courseRepository.findCourseWithMentorAndMenteeById(courseId)
        return course.map { mapCourseToCourseOverviewResponse(it) }
    }

    fun getMentorsByCourseId(
        courseId: Long
    ) : List<MentorResponse> {
        val course = courseRepository.findCourseWithMentorAndMenteeById(courseId)
        val mentors = course.map { course ->
            course.mentors.toList()
        }.orElse(emptyList())

        val mentorStats = mentorStatRepository.findAllById(mentors.map { it.user.id })
        return mentors.map {
            val user = it.user
            val mentorStat = mentorStats.firstOrNull { mentorStat -> mentorStat.id == user.id }
            MentorResponse(
                id = user.id,
                name = user.fullName,
                avatar = user.avatar,
                title = user.title,
                description = it.description,
                courseNumber = mentorStat?.courseNumber,
                studentNumber = mentorStat?.studentNumber,
                studentRating = mentorStat?.studentRating
            )
        }
    }

    fun getAssociatedGroupByCourseId(courseId: Long, userId: Long): List<GroupResponse> {
        val course = courseRepository.findCourseWithGroupById(courseId)
        val groups = course.map {
            it.associatedGroups
        }.orElse(emptySet())
        val groupUserMemberDetail = groupMemberRepository.findAllById(groups.map { GroupUserId(userId, it.id) })
        return groups.map {
                val isPinned = groupUserMemberDetail.firstOrNull { groupMember -> groupMember.group.id == it.id }?.isPinned
                GroupResponse(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    isPublic = it.isPublic,
                    avatar = it.avatar,
                    isPinned = isPinned == true,
                    groupMemberCount = it.groupMembers.size,
                    groupMembers = it.groupMembers.map { groupMember -> groupMember.user }.map { user -> UserBadgeResponse(user.id, user.fullName, user.avatar) }
                )
            }
    }

    fun getOutline(courseId: Long, userId: Long): List<ModuleResponse> {
        val courseItems = courseItemRepository.findCourseItemByCourseId(courseId, userId)
        val courseItemParticipants = courseItemRepository.findCourseItemParticipantByCourseItem(courseItems)
        val quiz = courseItems.filterIsInstance<Quiz>()
        val questionCounts = questionRepository.countQuestionByQuizGroupByQuiz(quiz)
        val courseItemGroupByModule = courseItems.groupBy { it.module.id }
        return courseItemGroupByModule.entries.map {
            val value = it.value
            ModuleResponse(
                id = value.first().module.id,
                name = value.first().module.name,
                courseItems = value.map {
                    courseItem ->
                    val courseItemResponse = CourseItemResponse(
                        id = courseItem.id,
                        name = courseItem.name,
                        type = courseItem.javaClass.kotlin.simpleName.toString(),
                        description = courseItem.description,
                        lastModifiedDate = courseItem.lastModifiedDate.toString(),
                        lastAttempt = courseItem.courseItemProgresses.firstOrNull()?.finalScore?.toInt(),
                        lastAttemptDate = courseItem.courseItemProgresses.firstOrNull()?.lastModifiedDate.toString(),
                        participants =
                            courseItemParticipants
                                .first { ci -> ci.id == courseItem.id }
                                .courseItemParticipants
                                .map { courseItemParticipant -> courseItemParticipant.user }
                                .map { user -> UserBadgeResponse(user.id, user.fullName, user.avatar) }
                    )
                    when (courseItem) {
                        is Video -> courseItemResponse.also { courseItemResponse ->
                            courseItemResponse.url = courseItem.url
                        }
                        is DownloadableContent -> courseItemResponse.also { courseItemResponse ->
                            courseItemResponse.url = courseItem.url
                        }
                        is Quiz -> courseItemResponse.also { res ->
                            res.duration = courseItem.duration
                            res.questionNumber = questionCounts.first { dto -> dto.id == courseItem.id }.count
                            res.passedThreshold = courseItem.passedThreshold
                        }
                        is Assignment -> courseItemResponse.also { courseItemResponse ->
                            courseItemResponse.passedThreshold = courseItem.passedThreshold
                        }
                        else -> courseItemResponse
                    }
                }
            )

        }
    }

    private fun mapCourseToCourseOverviewResponse(course: Course): CourseOverviewResponse {
        return CourseOverviewResponse(
            id = course.id,
            name = course.name,
            image = course.image,
            description = course.description,
            subtitle = course.subtitle,
            mentors = course.mentors.map {
                UserBadgeResponse(it.user.id, it.user.fullName, it.user.avatar) }.toSet(),
            startDate = course.startDate,
            endDate = course.endDate,
            groupNumber = courseRepository.countAssociatedGroupById(course.id),
            reviewNumber = courseRepository.countReviewById(course.id),
            outlineNumber = courseRepository.countCourseItemById(course.id),
            menteeCount = course.mentees.size
        )
    }
}