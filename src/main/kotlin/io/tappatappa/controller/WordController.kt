package io.tappatappa.controller

import io.tappatappa.service.WordService
import io.tappatappa.service.model.Word
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping(value = ["v1/{groupId}/word"])
class WordController(
    private val service: WordService
) {

    @GetMapping(value = ["/all"])
    fun findAll(
        @PathVariable("groupId") uuid: String,
        @RequestHeader("USER_ID") userId: String
    ): List<Word> {
        return service.findAll(uuid, userId)
    }

    @GetMapping(value = ["/{id}"])
    fun findById(
        @PathVariable("groupId") groupId: String,
        @PathVariable("id") uuid: String,
        @RequestHeader("USER_ID") userId: String
    ): Word? {
        return service.findByUUID(groupId, uuid, userId)
    }

    @PostMapping("/add")
    fun save(
        @PathVariable("groupId") groupId: String,
        @RequestBody word: Word,
        @RequestHeader("USER_ID") userId: String
    ): ResponseEntity<Word> {
        val savedGroup = service.save(word, groupId, userId)
        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedGroup.uuid)
            .toUri()
        return ResponseEntity.created(location).body(savedGroup)
    }

    @DeleteMapping(value = ["/{id}"])
    fun delete(
        @PathVariable("groupId") groupId: String,
        @PathVariable("id") uuid: String,
        @RequestHeader("USER_ID") userId: String
    ) {
        service.delete(uuid, groupId, userId)
    }

}
//
//добавь Sentence
//добавь h2
//добавь POSTGRE
//добавь автоматизацию в Постман
//добавь нормальную авторизацию (кейклоак???)
//добавь тесты