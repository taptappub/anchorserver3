package io.tappatappa.service

import io.tappatappa.repository.mock.MockSentenceRepository
import io.tappatappa.repository.mock.MockUserRepository
import io.tappatappa.repository.model.SentenceDto
import io.tappatappa.service.model.Sentence
import org.springframework.stereotype.Service

@Service
class SentenceService(
    //private val groupRepository: GroupRepository
    private val serviceRepository: MockSentenceRepository,
    private val userRepository: MockUserRepository
) {
    fun findAll(userUUID: String): List<Sentence> {
        val userId = getUserId(userUUID)
        return serviceRepository.findAll(userId)
            .filterNotNull()
            .filter { !it.isDeleted }
            .map { sentenceDto ->
                sentenceDto.toSentence()
            }
    }

    fun findByUUID(groupUUID: String, userUUID: String): Sentence? {
        val userId = getUserId(userUUID)
        return serviceRepository.findByUUID(groupUUID, userId)
            ?.toSentence()
    }

    fun save(sentence: Sentence, userUUID: String): Sentence {
        val userId = getUserId(userUUID)
        val sentenceDto = sentence.toSentenceDto(userId)
        return serviceRepository.save(sentenceDto)
            .toSentence()
    }

    fun delete(uuid: String, userUUID: String) {
        val userId = getUserId(userUUID)
        serviceRepository.delete(uuid, userId)
    }

    private fun getUserId(userUUID: String): Long {
        return userRepository.findUserIdByUUID(userUUID)
            ?: throw IllegalStateException("there is no user with userUUID = $userUUID")
    }
}

private fun Sentence.toSentenceDto(userId: Long): SentenceDto {
    return SentenceDto(
        uuid = "",
        externalId = this.externalId,
        userId = userId,
        sentence = this.sentence,
        words = this.words.joinToString()
    )
}

private fun SentenceDto.toSentence() = Sentence(
    uuid = this.uuid,
    sentence = this.sentence,
    externalId = this.externalId,
    words = this.words.split(',')
)
