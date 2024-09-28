package com.sawaljawab.SawalJawab.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Answer {
    @Id
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private String userName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private ObjectId questionId;
}
