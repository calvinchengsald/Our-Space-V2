package data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LikeId implements Serializable{
	
	@Column( name="email")
	private String email;
	
	@Column( name="postId")
	private int postId;

	public LikeId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LikeId(String email, int postId) {
		super();
		this.email = email;
		this.postId = postId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	@Override
	public String toString() {
		return "LikeId [email=" + email + ", postId=" + postId + "]";
	}
	
	
}
