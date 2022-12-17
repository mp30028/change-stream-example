package com.example.changestream.domain.post;

import java.time.LocalDateTime;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Comment {



	private String content;
    private LocalDateTime createdAt;

    public static Comment from(CommentRequest request) {
        return new Comment(request.getContent(), LocalDateTime.now());
    }
    
    public Comment(String content, LocalDateTime createdAt) {
		super();
		this.content = content;
		this.createdAt = createdAt;
	}
    
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
