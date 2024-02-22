package io.tappatappa.service.model

import java.util.UUID

data class Group (
    val id: UUID? = null,
    val name: String,
    val externalId: String?,
    val repetitionProgress: Int = 0,
//    val words: List<Word>
)
