package io.tappatappa.repository.model

import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "user_table")
data class UserDto(
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID,
    val login: String,
    val email: String,
    val externalId: String,
    val firstname: String?,
    val lastname: String?,
    val avatar: String? = null,
    val isDeleted: Boolean = false
) {
    // Default no-argument constructor required by Hibernate
    constructor() : this(
        UUID.randomUUID(),
        "",
        "",
        "",
        null,
        null,
        null,
        false
    )
}
