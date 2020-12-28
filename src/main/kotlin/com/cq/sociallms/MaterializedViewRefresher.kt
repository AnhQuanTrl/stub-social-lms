package com.cq.sociallms

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Component
class MaterializedViewRefresher(val entityManager: EntityManager) {

    @Transactional
    @Scheduled(fixedRate = 5000L)
    fun refresh() {
//        entityManager.createNativeQuery("call refresh_mat_view();").executeUpdate();
    }
}