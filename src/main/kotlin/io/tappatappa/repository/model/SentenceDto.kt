package io.tappatappa.repository.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "sentence_table")
data class SentenceDto(
    @Id @GeneratedValue var id: Long? = null,
    val uuid: String,
    val externalId: String?,
    val sentence: String,
    var userId: Long,
    val words: String,
    val isDeleted: Boolean = false
)
