package io.tappatappa.service

import io.tappatappa.repository.GroupRepository
import io.tappatappa.repository.model.GroupDto
import io.tappatappa.service.model.Group
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Service
class GroupService(
    private val groupRepository: GroupRepository
) {
    fun findAll(userId: UUID): List<Group> {
        return groupRepository.findAllByUserId(userId)
            .map { groupDto ->
                groupDto.toGroup()
            }
    }

    fun findById(groupId: UUID): Group? {
        return groupRepository.findById(groupId)
            .getOrNull()
            ?.toGroup() //todo кидать exception
    }

    fun save(groupName: String, userId: UUID): Group {
        val groupDto = GroupDto(
            id = UUID.randomUUID(),
            userId = userId,
            name = groupName
        )
        return groupRepository.save(groupDto)
            .toGroup()
    }

    fun delete(groupId: UUID) {
        groupRepository.deleteById(groupId)
    }
}

private fun GroupDto.toGroup() = Group(
    id = this.id,
    externalId = this.externalId,
    name = this.name,
    repetitionProgress = this.repetitionProgress,
//    words = this.words.map { it.toWord() }
)
