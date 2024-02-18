package io.tappatappa.service.model

data class Word(
    val uuid: String? = null,
    val externalId: String?,
    var word: String,
    var description: String,
    var imageUrl: String?,
    val points: Int = 0,
    var repetitionProgress: Int = 0
)