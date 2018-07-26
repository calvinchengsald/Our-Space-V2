package data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Activations")
public class Activation {
	
	@Id
	@Column(name="activation_key")
	private String activationKey;
	
	@Column(name="email", nullable=false)
	private String email;
	
	public Activation() {
		
	}
	
	public Activation(String activationKey, String email) {
		this.activationKey = activationKey;
		this.email = email;
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Activation [activationKey=" + activationKey + ", email=" + email + "]";
	}
	
}
