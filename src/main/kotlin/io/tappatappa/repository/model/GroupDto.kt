package io.tappatappa.repository.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "group_table")
data class GroupDto(
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID,
    val name: String,
    val userId: UUID,
    val externalId: String? = null,
    val repetitionProgress: Int = 0,
    val isDeleted: Boolean = false

//    val words: List<WordDto> = emptyList()
) {
    constructor() : this(
        UUID.randomUUID(),
        "",
        UUID.randomUUID(),
        null,
        0,
        false
    )
}
