package io.tappatappa.repository

import io.tappatappa.repository.model.GroupDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import javax.transaction.Transactional

interface GroupRepository : JpaRepository<GroupDto?, Long?> {

    @Query(
        value = "SELECT id FROM group_table " +
                "WHERE uuid = :uuid AND userId = :userId AND isDeleted = FALSE",
        nativeQuery = true
    )
    fun findUserIdByUUID(
        @Param("uuid") uuid: String,
        @Param("userId") userId: String
    ): Long?

    @Query(
        value = "SELECT * FROM group_table " +
                "WHERE userId = :userId AND isDeleted = FALSE",
        nativeQuery = true
    )
    fun findAllByUserId(@Param("userId") userId: Long): List<GroupDto>

    @Query(
        value = "SELECT * FROM group_table " +
                "WHERE uuid = :uuid AND userId = :userId AND isDeleted = FALSE",
        nativeQuery = true
    )
    fun findByUUID(
        @Param("uuid") groupUUID: String,
        @Param("userId") userId: Long
    ): GroupDto?

    @Transactional
    @Modifying
    @Query(
        value = "DELETE FROM GroupDto WHERE uuid = :uuid AND userId = :userId"
    )
    fun deleteByUuidAndUserId(
        @Param("uuid") groupUUID: String,
        @Param("userId") userId: Long
    )
}
