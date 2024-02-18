package io.tappatappa.repository.model

import javax.persistence.*

@Entity
@Table(name = "user_table")
data class UserDto(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    val uuid: String? = null,
    val login: String,
    val email: String,
    val externalId: String,
    val firstname: String?,
    val lastname: String?,
    val avatar: String? = null,
    val isDeleted: Boolean = false
)
