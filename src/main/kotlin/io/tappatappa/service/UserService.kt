package io.tappatappa.service

import io.tappatappa.repository.UserRepository
import io.tappatappa.repository.model.UserDto
import io.tappatappa.service.model.User
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository
//    private val userRepository: MockUserRepository
) {

    fun findAll(): List<User> {
        return userRepository.findAll()
            .filterNotNull()
            .map { userDto ->
            User(
                id = userDto.id,
                login = userDto.login,
                externalId = userDto.externalId,
                firstname = userDto.firstname,
                lastname = userDto.lastname,
                email = userDto.email,
                avatar = userDto.avatar
            )
        }
    }

    fun save(user: User): User {
        val uuid = UUID.randomUUID()
        val userDto = user.toUserDto(uuid)
        return userRepository.save(userDto).toUser()
    }

}

private fun UserDto.toUser(): User = User(
    id = this.id,
    login = this.login,
    email = this.email,
    firstname = this.firstname,
    lastname = this.lastname,
    externalId = this.externalId,
    avatar = this.avatar
)

private fun User.toUserDto(uuid: UUID): UserDto = UserDto(
    id = uuid,
    login = this.login,
    email = this.email,
    firstname = this.firstname,
    lastname = this.lastname,
    externalId = this.externalId,
    avatar = this.avatar
)
