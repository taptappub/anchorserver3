package io.tappatappa.repository

import io.tappatappa.repository.model.UserDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserRepository : JpaRepository<UserDto, Long> {

    @Query(
        value = "SELECT id FROM UserDto " +
                "WHERE uuid = :uuid AND isDeleted = FALSE",
        nativeQuery = true
    )
    fun findUserIdByUUID(@Param("uuid") uuid: String): Long?
}
