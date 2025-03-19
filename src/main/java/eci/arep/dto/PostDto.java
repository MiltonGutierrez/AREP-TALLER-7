package eci.arep.dto;



public class PostDto {

    private String username;
    private String content;
    

    public PostDto() {
    }

    public PostDto(String content, String username) {
        this.username = username;
        this.content = content;
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
