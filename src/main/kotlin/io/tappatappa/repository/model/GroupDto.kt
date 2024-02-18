package io.tappatappa.repository.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "group_table")
data class GroupDto(
    @Id @GeneratedValue val id: Long? = null,
    val uuid: String,
    val name: String,
    val userId: Long,
    val externalId: String? = null,
    val repetitionProgress: Int = 0,
    val isDeleted: Boolean = false
//    val words: List<WordDto> = emptyList()
)
