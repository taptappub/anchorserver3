package io.tappatappa.service.model

data class Sentence(
    val uuid: String? = null,
    val externalId: String?,
    val sentence: String,
    val words: List<String>
)
