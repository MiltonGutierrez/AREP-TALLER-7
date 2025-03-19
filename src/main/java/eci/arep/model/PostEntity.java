package eci.arep.model;

import eci.arep.dto.PostDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    @Column(length = 150)
    private String content;

    public PostEntity() {
    }

    public PostEntity(Long id, String content, String username) {
        this.id = id;
        this.content = content;
        this.username = username;
    }

    public PostEntity(PostDto postDto) {
        this.content = postDto.getContent();
        this.username = postDto.getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
