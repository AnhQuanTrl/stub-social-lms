package com.cq.sociallms.repository

import com.cq.sociallms.model.GroupMember
import com.cq.sociallms.model.GroupUserId
import org.springframework.data.jpa.repository.JpaRepository

interface GroupMemberRepository : JpaRepository<GroupMember, GroupUserId>