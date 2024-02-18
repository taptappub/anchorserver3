package io.tappatappa.controller

import io.tappatappa.service.GroupService
import io.tappatappa.service.model.Group
import io.tappatappa.service.model.GroupName
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping(value = ["v1/group"])
class GroupController(
    private val service: GroupService
) {

    @GetMapping(value = ["/all"])
    fun findAll(@RequestHeader("USER_ID") userId: String): List<Group> {
        return service.findAll(userId)
    }

    @GetMapping(value = ["/names"])
    fun findAllNames(@RequestHeader("USER_ID") userId: String): List<GroupName> {
        return service.findAllNames(userId)
    }

    @GetMapping(value = ["/{id}"])
    fun findById(
        @PathVariable("id") uuid: String,
        @RequestHeader("USER_ID") userId: String
    ): Group? {
        return service.findByUUID(uuid, userId)
    }

    @PostMapping("/add")
    fun save(
        @RequestBody group: String,
        @RequestHeader("USER_ID") userId: String
    ): ResponseEntity<Group> {
        val savedGroup = service.save(group, userId)
        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedGroup.uuid)
            .toUri()
        return ResponseEntity.created(location).body(savedGroup)
    }

    @DeleteMapping(value = ["/{id}"])
    fun delete(
        @PathVariable("id") uuid: String,
        @RequestHeader("USER_ID") userId: String
    ) {
        service.delete(uuid, userId)
    }

}