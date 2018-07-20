package data.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Comments")
public class Comment {

	@Id
	@Column(name="commentId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int commentId;
	
	@Column(name="body", nullable=false)
	private String body;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="postId")
	@JsonIgnore
	private Post post;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="email")
	private User user;

	public Comment() {
		super();
	}

	public Comment(int commentId, String body, Post post, User user) {
		super();
		this.commentId = commentId;
		this.body = body;
		this.post = post;
		this.user = user;
	}
	
	public Comment(int commentId, String body) {
		super();
		this.commentId = commentId;
		this.body = body;
	}

	public Comment(String body, Post post, User user) {
		super();
		this.body = body;
		this.post = post;
		this.user = user;
	}

	public Comment(int commentId) {
		super();
		this.commentId = commentId;
	}
	
	public Comment(String body) {
		super();
		this.body= body;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", body=" + body + "]";
	}
	
	
	
	
}