package io.tappatappa.repository.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "word_table")
data class WordDto(
    @Id @GeneratedValue var id: Long? = null,
    val uuid: String,
    val groupId: Long,
    val externalId: String?,
    val word: String,
    val description: String,
    val imageUrl: String?,
    val repetitionProgress: Int = 0,
    val points: Int = 0,
    val isDeleted: Boolean = false
)
