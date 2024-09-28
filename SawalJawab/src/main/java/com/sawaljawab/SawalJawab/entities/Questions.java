package com.sawaljawab.SawalJawab.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Questions {
    @Id
    @Column(name = "question_id")
    private ObjectId id;
    private String title;
    private String content;
    private List<String> tag;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private String userName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "Questions", cascade = CascadeType.ALL)
    private List<Answer> answer;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Answer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Answer> answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", tag=" + tag +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
