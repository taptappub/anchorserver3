package io.tappatappa.controller

import io.tappatappa.service.WordService
import io.tappatappa.service.model.Word
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*

@RestController
@RequestMapping(value = ["v1/{groupId}/word"])
class WordController(
    private val service: WordService
) {

    @GetMapping(value = ["/all"])
    fun findAll(
        @PathVariable("groupId") uuid: UUID,
        @RequestHeader("USER_ID") userId: UUID
    ): List<Word> {
        return service.findAll(uuid, userId)
    }

    @GetMapping(value = ["/{id}"])
    fun findById(
        @PathVariable("groupId") groupId: UUID,
        @PathVariable("id") wordId: UUID,
    ): Word? {
        return service.findByUUID(groupId, wordId)
    }

    @PostMapping("/add")
    fun save(
        @PathVariable("groupId") groupId: UUID,
        @RequestBody word: Word
    ): ResponseEntity<Word> {
        val savedGroup = service.save(word, groupId)
        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedGroup.id)
            .toUri()
        return ResponseEntity.created(location).body(savedGroup)
    }

    @DeleteMapping(value = ["/{id}"])
    fun delete(
        @PathVariable("id") id: UUID
    ) {
        service.delete(id)
    }

}
////
////добавь Sentence
////добавь h2
////добавь POSTGRE
////добавь автоматизацию в Постман
////добавь нормальную авторизацию (кейклоак???)
////добавь тесты