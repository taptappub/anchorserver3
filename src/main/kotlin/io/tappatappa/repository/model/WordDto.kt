package io.tappatappa.repository.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "word_table")
data class WordDto(
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID,
    val groupId: UUID,
    val externalId: String?,
    val word: String,
    val description: String,
    val imageUrl: String?,
    val repetitionProgress: Int = 0,
    val points: Int = 0,
    val isDeleted: Boolean = false
) {
    constructor() : this(
        UUID.randomUUID(),
        UUID.randomUUID(),
        null,
        "",
        "",
        null,
        0,
        0,
        false
    )
}
