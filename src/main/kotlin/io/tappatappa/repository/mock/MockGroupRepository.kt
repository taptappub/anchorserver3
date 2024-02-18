package io.tappatappa.repository.mock

import io.tappatappa.repository.model.GroupDto
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class MockGroupRepository {
    fun findAll(userId: Long): List<GroupDto> {
        return list.filter {
            it.userId == userId
        }
    }

    fun save(groupName: String, userId: Long): GroupDto {
        val uuid = UUID.randomUUID()
        val groupDto = GroupDto(
            id = indexCounter,
            userId = userId,
            name = groupName,
            uuid =uuid.toString()
        )
        indexCounter++
        list.add(groupDto)
        return groupDto
    }

    fun findByUUID(uuid: String, userId: Long): GroupDto? {
        return list.find {
            it.uuid == uuid && !it.isDeleted && it.userId == userId
        }
    }

    fun findGroupIdByUUID(uuid: String, userId: Long): Long? {
        return list.find {
            it.uuid == uuid && !it.isDeleted && it.userId == userId
        }?.id
    }

    fun delete(uuid: String, userId: Long) {
        list.removeIf { it.uuid == uuid && it.userId == userId}
    }

    companion object {
        private var indexCounter = 0L;
        val list: MutableList<GroupDto> = mutableListOf()
    }

}