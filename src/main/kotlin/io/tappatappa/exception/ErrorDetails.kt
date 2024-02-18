package io.tappatappa.exception

import java.time.LocalDateTime

class ErrorDetails(
    val timestamp: LocalDateTime,
    val message: String,
    val details: String
)