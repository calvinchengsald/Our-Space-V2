package data.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Likes")
public class Like {
	
	@EmbeddedId
    private LikeId id;

	public Like() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LikeId getId() {
		return id;
	}
	

	public Like(LikeId id) {
		super();
		this.id = id;
	}
	
	public Like(String email, int id) {
		super();
		LikeId lid = new LikeId(email, id);
		this.id = lid;
	}

	public void setId(LikeId id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Like [id=" + id + "]";
	}
	
	
}
