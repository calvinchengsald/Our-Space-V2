package data.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Comments")
public class Comment {

	@Id
	@Column(name="commentId")
	private String commentId;
	
	@Column(name="body", nullable=false)
	private String body;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
	@JoinColumn(name="postId")
	private Post post;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
	@JoinColumn(name="email")
	private User user;

	public Comment() {
		super();
	}

	public Comment(String commentId, String body, Post post, User user) {
		super();
		this.commentId = commentId;
		this.body = body;
		this.post = post;
		this.user = user;
	}

	public Comment(String body, Post post, User user) {
		super();
		this.body = body;
		this.post = post;
		this.user = user;
	}

	public Comment(String commentId) {
		super();
		this.commentId = commentId;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
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