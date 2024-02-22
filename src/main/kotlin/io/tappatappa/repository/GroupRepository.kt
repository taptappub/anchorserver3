package io.tappatappa.repository

import io.tappatappa.repository.model.GroupDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*
import javax.transaction.Transactional

interface GroupRepository : JpaRepository<GroupDto, UUID> {
    fun findAllByUserId(userId: UUID): List<GroupDto>
}
