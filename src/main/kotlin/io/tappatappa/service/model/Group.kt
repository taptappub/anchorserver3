package io.tappatappa.service.model

data class Group (
    val uuid: String? = null,
    val name: String,
    val externalId: String?,
    val repetitionProgress: Int = 0,
//    val words: List<Word>
)
