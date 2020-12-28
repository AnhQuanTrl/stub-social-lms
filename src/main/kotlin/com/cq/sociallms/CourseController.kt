package com.cq.sociallms

import com.cq.sociallms.dto.CourseSearchDto
import com.cq.sociallms.request.CourseSearchRequest
import com.cq.sociallms.response.CourseOverviewResponse
import com.cq.sociallms.response.GroupResponse
import com.cq.sociallms.response.MentorResponse
import com.cq.sociallms.response.ModuleResponse
import com.cq.sociallms.service.CourseService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/courses")
class CourseController(val courseService: CourseService) {

    @GetMapping("")
    fun all(
        courseSearchRequest: CourseSearchRequest,
        pageable: Pageable
    ): Page<CourseSearchDto> = courseService.findAll(courseSearchRequest, pageable)

    @GetMapping("/{courseId}")
    fun one(
        @PathVariable courseId: Long
    ): ResponseEntity<CourseOverviewResponse>  {
        return ResponseEntity.of(courseService.findOne(courseId))
    }

    @GetMapping("/{courseId}/mentors")
    fun getMentorsByCourseId(
        @PathVariable courseId: Long
    ) : List<MentorResponse> {
        return courseService.getMentorsByCourseId(courseId)
    }

    @GetMapping("/{courseId}/groups")
    fun getAssociatedGroupByCourseId(
        @PathVariable courseId: Long,
        @RequestParam userId: Long,
    ) : List<GroupResponse> = courseService.getAssociatedGroupByCourseId(courseId, userId)

    @GetMapping("/{courseId}/outline")
    fun getOutline(
        @PathVariable courseId: Long,
        @RequestParam userId: Long
    ): List<ModuleResponse> = courseService.getOutline(courseId, userId)

}