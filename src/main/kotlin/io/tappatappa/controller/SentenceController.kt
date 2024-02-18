package io.tappatappa.controller

import io.tappatappa.service.SentenceService
import io.tappatappa.service.model.Sentence
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping(value = ["v1/sentence"])
class SentenceController(
    private val service: SentenceService
) {

    @GetMapping(value = ["/all"])
    fun findAll(
        @RequestHeader("USER_ID") userId: String
    ): List<Sentence> {
        return service.findAll(userId)
    }

    @GetMapping(value = ["/{id}"])
    fun findById(
        @PathVariable("id") uuid: String,
        @RequestHeader("USER_ID") userId: String
    ): Sentence? {
        return service.findByUUID(uuid, userId)
    }

    @PostMapping("/add")
    fun save(
        @RequestBody sentence: Sentence,
        @RequestHeader("USER_ID") userId: String
    ): ResponseEntity<Sentence> {
        val savedSentence = service.save(sentence, userId)
        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedSentence.uuid)
            .toUri()
        return ResponseEntity.created(location).body(savedSentence)
    }

    @DeleteMapping(value = ["/{id}"])
    fun delete(
        @PathVariable("id") uuid: String,
        @RequestHeader("USER_ID") userId: String
    ) {
        service.delete(uuid, userId)
    }

}

//
//добавь h2
//добавь POSTGRE
//добавь автоматизацию в Постман
//добавь нормальную авторизацию (кейклоак???)
//добавь тесты