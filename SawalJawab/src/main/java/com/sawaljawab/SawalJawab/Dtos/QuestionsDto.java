package com.sawaljawab.SawalJawab.Dtos;

import com.sawaljawab.SawalJawab.entities.User;
import lombok.Data;
import org.bson.types.ObjectId;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;
@Data
public class QuestionsDto {
    private ObjectId id;
    private String title;
    private String content;
    private List<String> tag;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
