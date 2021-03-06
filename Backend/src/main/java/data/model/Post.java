package data.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import util.CommentTimeComparator;

@Entity
@Table(name="Posts")
public class Post {

	@Id
	@Column(name="postId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int postId;
	
	@Column(name="body", nullable=false)
	private String body;
	
	@Column(name="imgSrc")
	private String imgSrc;
	
	@Column(name="youtubeLink")
	private String youtubeLink;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="email")
	private User user;
	
	@Column(name="created")
	private Timestamp created;
	
	
//	
//	@ManyToMany( fetch= FetchType.EAGER, mappedBy="likedPosts")
//	private List<User> likedUsers;
	
	
	@OneToMany(mappedBy="post", fetch=FetchType.EAGER, cascade= CascadeType.ALL)
	private List<Comment> comments;
	
//	@OneToMany(mappedBy="likes", fetch=FetchType.EAGER, cascade= CascadeType.ALL)
//    private Like likes;
	
//	private List<Like> likes;
	
	public void orderComments() {
		comments.sort(new CommentTimeComparator());
	}

	public Post() {
		super();
	}
	public Post(int postId) {
		super();
		this.postId = postId;
	}
	public Post(int postId, String body, String imgSrc, String youtubeLink, User user, List<Comment> comments,
			List<User> likedUsers) {
		super();
		this.postId = postId;
		this.body = body;
		this.imgSrc = imgSrc;
		this.youtubeLink = youtubeLink;
		this.user = user;
		this.comments = comments;
		likedUsers = new ArrayList<User>();
	}
	public Post(String body, String imgSrc, String youtubeLink, User user, List<Comment> comments, List<User> likedUsers) {
		super();
		this.body = body;
		this.imgSrc = imgSrc;
		this.youtubeLink = youtubeLink;
		this.user = user;
		this.comments = comments;
	}
	
	public Post( String body, String imgSrc, String youtubeLink, User user) {
		super();
		this.body = body;
		this.imgSrc = imgSrc;
		this.youtubeLink = youtubeLink;
		this.user = user;
	}

	public Post(String body, String imgSrc, String youtubeLink, User user, Timestamp created, List<User> likedUsers,
			List<Comment> comments) {
		super();
		this.body = body;
		this.imgSrc = imgSrc;
		this.youtubeLink = youtubeLink;
		this.user = user;
		this.created = created;
		this.comments = comments;
	}
	
	public Post( String body, String imgSrc, String youtubeLink, User user, Timestamp created) {
		super();
		this.body = body;
		this.imgSrc = imgSrc;
		this.youtubeLink = youtubeLink;
		this.user = user;
		this.created = created;
	}
	public Post( String body) {
		super();
		this.body = body;
	}
	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public String getYoutubeLink() {
		return youtubeLink;
	}

	public void setYoutubeLink(String youtubeLink) {
		this.youtubeLink = youtubeLink;
	}

	
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	


//	public List<Like> getLikes() {
//		return likes;
//	}
//
//	public void setLikes(List<Like> likes) {
//		this.likes = likes;
//	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", body=" + body + ", imgSrc=" + imgSrc + ", youtubeLink=" + youtubeLink
				 + "]";
	}
	
	
	
}

