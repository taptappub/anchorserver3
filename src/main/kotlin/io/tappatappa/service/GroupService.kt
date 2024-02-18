package io.tappatappa.service

import io.tappatappa.repository.GroupRepository
import io.tappatappa.repository.UserRepository
import io.tappatappa.repository.mock.MockGroupRepository
import io.tappatappa.repository.model.GroupDto
import io.tappatappa.service.model.Group
import io.tappatappa.service.model.GroupName
import org.springframework.stereotype.Service
import java.util.*

@Service
class GroupService(
    private val groupRepository: GroupRepository,
    private val userRepository: UserRepository
//    private val groupRepository: MockGroupRepository,
//    private val userRepository: MockUserRepository
) {
    fun findAll(userUUID: String): List<Group> {
        val userId = getUserId(userUUID)
        return groupRepository.findAllByUserId(userId)
            .map { groupDto ->
                groupDto.toGroup()
            }
    }

    fun findAllNames(userUUID: String): List<GroupName> {
        val userId = getUserId(userUUID)
        return groupRepository.findAllByUserId(userId)
            .filter { !it.isDeleted }
            .map { groupDto ->
                GroupName(
                    uuid = groupDto.uuid,
                    externalId = groupDto.externalId,
                    name = groupDto.name,
                    repetitionProgress = groupDto.repetitionProgress,
                )
            }
    }

    fun findByUUID(groupUUID: String, userUUID: String): Group? {
        val userId = getUserId(userUUID)
        return groupRepository.findByUUID(groupUUID, userId)
            ?.toGroup()
    }

    fun save(groupName: String, userUUID: String): Group {
        val groupDto = GroupDto(
            userId = getUserId(userUUID),
            name = groupName,
            uuid = UUID.randomUUID().toString()
        )
        return groupRepository.save(groupDto)
            .toGroup()
    }

    fun delete(uuid: String, userUUID: String) {
        val userId = getUserId(userUUID)
        groupRepository.deleteByUuidAndUserId(uuid, userId)
    }

    fun delete(uuid: String, userId: Long) {
        MockGroupRepository.list.removeIf { it.uuid == uuid && it.userId == userId}
    }

    private fun getUserId(userUUID: String): Long {
        return userRepository.findUserIdByUUID(userUUID)
            ?: throw IllegalStateException("there is no user with userUUID = $userUUID")
    }
}

private fun GroupDto.toGroup() = Group(
    uuid = this.uuid,
    externalId = this.externalId,
    name = this.name,
    repetitionProgress = this.repetitionProgress,
//    words = this.words.map { it.toWord() }
)
