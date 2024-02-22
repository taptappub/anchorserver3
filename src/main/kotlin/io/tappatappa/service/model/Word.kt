package io.tappatappa.service.model

import java.util.UUID

data class Word(
    val id: UUID? = null,
    val externalId: String?,
    var word: String,
    var description: String,
    var imageUrl: String?,
    val points: Int = 0,
    var repetitionProgress: Int = 0
)