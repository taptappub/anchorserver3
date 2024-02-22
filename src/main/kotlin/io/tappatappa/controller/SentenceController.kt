package io.tappatappa.controller

import io.tappatappa.service.SentenceService
import io.tappatappa.service.model.Sentence
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.UUID

@RestController
@RequestMapping(value = ["v1/sentence"])
class SentenceController(
    private val service: SentenceService
) {

    @GetMapping(value = ["/all"])
    fun findAll(
        @RequestHeader("USER_ID") userId: UUID
    ): List<Sentence> {
        return service.findAll(userId)
    }

    @GetMapping(value = ["/{id}"])
    fun findById(
        @PathVariable("id") id: UUID
    ): Sentence? {
        return service.findById(id)
    }

    @PostMapping("/add")
    fun save(
        @RequestBody sentence: Sentence,
        @RequestHeader("USER_ID") userId: UUID
    ): ResponseEntity<Sentence> {
        val savedSentence = service.save(sentence, userId)
        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedSentence.id)
            .toUri()
        return ResponseEntity.created(location).body(savedSentence)
    }

    @DeleteMapping(value = ["/{id}"])
    fun delete(
        @PathVariable("id") id: UUID
    ) {
        service.delete(id)
    }

}

//
//добавь h2
//добавь POSTGRE
//добавь автоматизацию в Постман
//добавь нормальную авторизацию (кейклоак???)
//добавь тесты