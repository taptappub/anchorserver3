package io.tappatappa.repository.mock

import io.tappatappa.repository.model.WordDto
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class MockWordRepository {
    fun findAll(groupId: Long): List<WordDto> {
        return list.filter {
            it.groupId == groupId
        }
    }

    fun findByUUID(groupId: Long): WordDto? {
        return list.find {
            it.groupId == groupId && !it.isDeleted
        }
    }

    fun save(word: WordDto): WordDto {
        val uuid = UUID.randomUUID()
        val wordCopy = word.copy(id = indexCounter, uuid = uuid.toString())
        indexCounter++
        list.add(wordCopy)
        return wordCopy
    }

    fun delete(wordUUID: String, groupId: Long) {
        list.removeIf { it.uuid == wordUUID && it.groupId == groupId}
    }

    companion object {
        private var indexCounter = 0L;
        val list: MutableList<WordDto> = mutableListOf()
    }

}