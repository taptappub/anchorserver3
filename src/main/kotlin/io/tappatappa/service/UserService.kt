package io.tappatappa.service

import io.tappatappa.repository.UserRepository
import io.tappatappa.repository.model.UserDto
import io.tappatappa.service.model.User
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
//    private val userRepository: MockUserRepository
) {

    fun findAll(): List<User> {
        return userRepository.findAll()
            .filterNotNull()
            .filter{ !it.isDeleted }
            .map { userDto ->
            User(
                uuid = userDto.uuid,
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
        val userDto = user.toUserDto()
        return userRepository.save(userDto).toUser()
    }

}

private fun UserDto.toUser(): User = User(
    uuid = this.uuid,
    login = this.login,
    email = this.email,
    firstname = this.firstname,
    lastname = this.lastname,
    externalId = this.externalId,
    avatar = this.avatar
)

private fun User.toUserDto(): UserDto = UserDto(
    login = this.login,
    email = this.email,
    firstname = this.firstname,
    lastname = this.lastname,
    externalId = this.externalId,
    avatar = this.avatar,
    uuid = ""
)
