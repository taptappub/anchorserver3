package io.tappatappa.service.model

import java.util.UUID

data class Sentence(
    val id: UUID? = null,
    val externalId: String?,
    val sentence: String,
    val words: List<String>
)
