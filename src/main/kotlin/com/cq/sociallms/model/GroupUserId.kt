package com.cq.sociallms.model

import java.io.Serializable

data class GroupUserId(
    val user: Long = 0,
    val group: Long = 0,
) : Serializable