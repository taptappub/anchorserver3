package io.tappatappa.service

import io.tappatappa.repository.mock.MockGroupRepository
import io.tappatappa.repository.mock.MockUserRepository
import io.tappatappa.repository.mock.MockWordRepository
import io.tappatappa.repository.model.WordDto
import io.tappatappa.service.model.Word
import org.springframework.stereotype.Service

@Service
class WordService(
    //private val groupRepository: GroupRepository
    private val wordRepository: MockWordRepository,
    private val groupRepository: MockGroupRepository,
    private val userRepository: MockUserRepository
) {

    fun findAll(groupUUID: String, userId: String): List<Word> {
        val groupId = getGroupId(groupUUID, userId)
        return wordRepository.findAll(groupId)
            .filterNotNull()
            .filter { !it.isDeleted }
            .map { wordDto -> wordDto.toWord() }
    }

    fun findByUUID(groupUUID: String, uuid: String, userId: String): Word? {
        val groupId = getGroupId(groupUUID, userId)
        return wordRepository.findByUUID(groupId)
            ?.toWord()
    }

    fun save(word: Word, groupUUID: String, userId: String): Word {
        val groupId = getGroupId(groupUUID, userId)
        return wordRepository.save(
            word = word.toWordDto(groupId)
        ).toWord()
    }

    fun delete(wordUUID: String, groupUUID: String, userId: String) {
        val groupId = getGroupId(groupUUID, userId)
        wordRepository.delete(wordUUID, groupId)
    }

    private fun getGroupId(groupUUID: String, userUUID: String): Long {
        val userId = userRepository.findUserIdByUUID(userUUID)
            ?: throw IllegalStateException("there is no user with userUUID = $userUUID")
        return groupRepository.findGroupIdByUUID(groupUUID, userId)
            ?: throw IllegalStateException("there is no group with groupUUID = $groupUUID")
    }
}

fun Word.toWordDto(groupId: Long) = WordDto(
    uuid = "",
    groupId = groupId,
    externalId = this.externalId,
    description = this.description,
    word = this.word,
    imageUrl = this.imageUrl,
    repetitionProgress = this.repetitionProgress,
    points = this.points
)

fun WordDto.toWord() = Word(
    uuid = this.uuid,
    externalId = this.externalId,
    word = this.word,
    description = this.description,
    imageUrl = this.imageUrl,
    points = this.points,
    repetitionProgress = this.repetitionProgress
)
