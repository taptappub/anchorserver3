package io.tappatappa.repository

import io.tappatappa.repository.model.SentenceDto
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SentenceRepository : JpaRepository<SentenceDto, UUID> {
    fun findAllByUserId(userId: UUID): List<SentenceDto>
}
