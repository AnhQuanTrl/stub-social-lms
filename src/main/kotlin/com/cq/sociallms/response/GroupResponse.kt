package com.cq.sociallms.response

data class GroupResponse(
    val id: Long,

    val name: String,

    val description: String,

    val isPublic: Boolean,

    val avatar: String?,

    val isPinned: Boolean,

    val groupMemberCount: Int,

    val groupMembers: List<UserBadgeResponse>
)