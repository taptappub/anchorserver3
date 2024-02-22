package io.tappatappa.service


import io.tappatappa.repository.WordRepository
import io.tappatappa.repository.model.WordDto
import io.tappatappa.service.model.Word
import org.springframework.stereotype.Service
import java.util.UUID
import kotlin.jvm.optionals.getOrNull

@Service
class WordService(
    //private val groupRepository: GroupRepository
    private val wordRepository: WordRepository
) {

    fun findAll(groupId: UUID, userId: UUID): List<Word> {
        return wordRepository.findAllByGroupId(groupId)
            .map { wordDto -> wordDto.toWord() }
    }

    fun findByUUID(groupId: UUID, wordId: UUID): Word? {
        return wordRepository.findAllByGroupIdAndWordId(groupId, wordId)
            .getOrNull()
            ?.toWord()
    }

    fun save(word: Word, groupId: UUID): Word {
        val wordDto = word.toWordDto(groupId)
        return wordRepository.save(wordDto)
            .toWord()
    }

    fun delete(wordId: UUID) {
        wordRepository.deleteById(wordId)
    }

}

fun Word.toWordDto(groupId: UUID) = WordDto(
    id = UUID.randomUUID(),
    groupId = groupId,
    externalId = this.externalId,
    description = this.description,
    word = this.word,
    imageUrl = this.imageUrl,
    repetitionProgress = this.repetitionProgress,
    points = this.points
)

fun WordDto.toWord() = Word(
    id = this.id,
    externalId = this.externalId,
    word = this.word,
    description = this.description,
    imageUrl = this.imageUrl,
    points = this.points,
    repetitionProgress = this.repetitionProgress
)
