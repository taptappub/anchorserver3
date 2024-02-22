package io.tappatappa.repository.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "sentence_table")
data class SentenceDto(
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID,
    val externalId: String?,
    val sentence: String,
    var userId: UUID,
    val words: String,
    val isDeleted: Boolean = false
) {
    constructor() : this(
        UUID.randomUUID(),
        "",
        "",
        UUID.randomUUID(),
        "",
        false
    )
}
