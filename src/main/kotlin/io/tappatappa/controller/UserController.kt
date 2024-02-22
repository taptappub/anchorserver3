package io.tappatappa.controller

import io.tappatappa.service.UserService
import io.tappatappa.service.model.User
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping(value = ["v1/user"])
class UserController(
    private val service: UserService
) {

    @GetMapping("/all")
    fun retrieveAllUsers(): List<User> {
        return service.findAll()
    }

    //если такой пользователь есть, то вернется он
    @PostMapping("/add")
    fun createUser(@Valid @RequestBody user: User): ResponseEntity<User> {
        val savedUser: User = service.save(user)
        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedUser.id)
            .toUri()
        return ResponseEntity.created(location).body(savedUser)
    }

}