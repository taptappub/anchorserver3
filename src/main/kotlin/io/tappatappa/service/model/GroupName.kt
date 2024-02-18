package io.tappatappa.service.model

data class GroupName (
    val uuid: String? = null,
    val name: String,
    val externalId: String?,
    val repetitionProgress: Int = 0,
)
