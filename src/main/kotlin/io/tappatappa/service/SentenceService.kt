package io.tappatappa.service

import io.tappatappa.repository.SentenceRepository
import io.tappatappa.repository.model.SentenceDto
import io.tappatappa.service.model.Sentence
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Service
class SentenceService(
    private val serviceRepository: SentenceRepository
) {
    fun findAll(userId: UUID): List<Sentence> {
        return serviceRepository.findAllByUserId(userId)
            .map { sentenceDto ->
                sentenceDto.toSentence()
            }
    }

    fun findById(sentenceId: UUID): Sentence? {
        return serviceRepository.findById(sentenceId)
            .getOrNull()
            ?.toSentence()
    }

    fun save(sentence: Sentence, userId: UUID): Sentence {
        val sentenceDto = sentence.toSentenceDto(userId)
        return serviceRepository.save(sentenceDto)
            .toSentence()
    }

    fun delete(id: UUID) {
        serviceRepository.deleteById(id)
    }
}

private fun Sentence.toSentenceDto(userId: UUID): SentenceDto {
    return SentenceDto(
        id = UUID.randomUUID(),
        externalId = this.externalId,
        userId = userId,
        sentence = this.sentence,
        words = this.words.joinToString()
    )
}

private fun SentenceDto.toSentence() = Sentence(
    id = this.id,
    sentence = this.sentence,
    externalId = this.externalId,
    words = this.words.split(',')
)
