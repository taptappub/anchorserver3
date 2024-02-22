package io.tappatappa.controller

import io.tappatappa.service.GroupService
import io.tappatappa.service.model.Group
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*

@RestController
@RequestMapping(value = ["v1/group"])
class GroupController(
    private val service: GroupService
) {

    @GetMapping(value = ["/all"])
    fun findAll(@RequestHeader("USER_ID") userId: UUID): List<Group> {
        return service.findAll(userId)
    }

    @GetMapping(value = ["/{id}"])
    fun findById(
        @PathVariable("id") id: UUID
    ): Group? {
        return service.findById(id)
    }

    @PostMapping("/add")
    fun save(
        @RequestBody group: String,
        @RequestHeader("USER_ID") userId: UUID
    ): ResponseEntity<Group> {
        val savedGroup = service.save(group, userId)
        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedGroup.id)
            .toUri()
        return ResponseEntity.created(location).body(savedGroup)
    }

    @DeleteMapping(value = ["/{id}"])
    fun delete(
        @PathVariable("id") uuid: UUID
    ) {
        service.delete(uuid)
    }

}