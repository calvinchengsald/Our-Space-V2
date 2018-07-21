package data.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="Users")
public class User {

	@Id
	@Column(name="email")
	private String email;
	
	@Column(name="firstName", nullable=false)
	private String firstName;
	
	@Column(name="lastName", nullable=false)
	private String lastName;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	@JsonIgnore
	private List<Post> posts;
	
	

	
	
	@ManyToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
	@JoinColumn(name="postId")
	@JsonIgnore
	private List<Post> likes;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(String email) {
		super();
		this.email = email;
	}
	


	public User(String firstName, String lastName, String password, List<Post> posts) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.posts = posts;
	}
	public User(String email, String firstName, String lastName, String password) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}


	public User(String email, String firstName, String lastName, String password, List<Post> posts) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.posts = posts;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public List<Post> getPosts() {
		return posts;
	}


	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	

	public List<Post> getLikes() {
		return likes;
	}


	public void setLikes(List<Post> likes) {
		this.likes = likes;
	}


	@Override
	public String toString() {
		return "User [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+ "]";
	}
	
	
	
}