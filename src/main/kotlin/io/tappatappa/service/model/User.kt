package io.tappatappa.service.model

import java.util.*
import javax.validation.constraints.Email

data class User (
    val id: UUID? = null,
    @Email
    var email: String,
    var login: String,
    var externalId: String,
    var firstname: String?,
    var lastname: String?,
    var avatar: String? = null
)
