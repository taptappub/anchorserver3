package io.tappatappa.repository.model

import org.springframework.hateoas.server.core.Relation
import javax.persistence.Embedded
import javax.persistence.Entity

//@Entity
//data class GroupWithWordsDto(
//    @Embedded val group: GroupDto,
//    @Relation( - доделай
//        parentColumn = "groupId",
//        entityColumn = "wordId",
//        associateBy = Junction(WordGroupCrossRef::class)
//    )
//    val words: List<WordDto>
//)