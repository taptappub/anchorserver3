package io.tappatappa.repository.mock

import io.tappatappa.repository.model.SentenceDto
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class MockSentenceRepository {
    fun findAll(userId: Long): List<SentenceDto> {
        return list.filter {
            it.userId == userId
        }
    }

    fun save(sentence: SentenceDto): SentenceDto {
        val uuid = UUID.randomUUID()
        val sentenceCopy = sentence.copy(
            id = indexCounter,
            uuid = uuid.toString()
        )
        indexCounter++
        list.add(sentenceCopy)
        return sentenceCopy
    }

    fun findByUUID(uuid: String, userId: Long): SentenceDto? {
        return list.find {
            it.uuid == uuid && !it.isDeleted && it.userId == userId
        }
    }

    fun delete(uuid: String, userId: Long) {
        list.removeIf { it.uuid == uuid && it.userId == userId}
    }

    companion object {
        private var indexCounter = 0L;
        val list: MutableList<SentenceDto> = mutableListOf()
    }

}