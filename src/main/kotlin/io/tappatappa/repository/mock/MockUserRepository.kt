package io.tappatappa.repository.mock

import io.tappatappa.repository.model.UserDto
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class MockUserRepository {
    fun findAll(): List<UserDto> {
        return list
    }

    fun save(userDto: UserDto): UserDto {
        return list.find {
            !it.isDeleted && (it.email == userDto.email || it.externalId == userDto.externalId)
        } ?: run {
            val uuid = UUID.randomUUID()
            val userCopy = userDto.copy(id = indexCounter, uuid = uuid.toString())
            indexCounter++
            list.add(userCopy)
            return userCopy
        }
    }

    fun findUserIdByUUID(uuid: String): Long? {
        return list.find {
            it.uuid == uuid && !it.isDeleted
        }?.id
    }

    companion object {
        private var indexCounter = 0L;
        val list: MutableList<UserDto> = mutableListOf()
    }

}