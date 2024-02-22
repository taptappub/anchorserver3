package io.tappatappa.repository

import io.tappatappa.repository.model.WordDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*


interface WordRepository : JpaRepository<WordDto, UUID> {
    fun findAllByGroupId(groupId: UUID): List<WordDto>

    @Query("SELECT w FROM WordDto w WHERE w.groupId = ?1 AND w.id = ?2")
    fun findAllByGroupIdAndWordId(groupId: UUID, id: UUID): Optional<WordDto>
//    fun findAllByGroupIdAndWordId(groupId: UUID, id: UUID): WordDto?
}
