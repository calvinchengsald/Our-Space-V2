package data.dao;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import data.model.Activation;
import data.model.User;

@Repository("activationDao")
@Transactional
public class ActivationDao {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Autowired
	private SessionFactory sesFact;
	
	public ActivationDao() {
	}
	
	public Activation selectById(String key) {
		return sesFact.getCurrentSession().get(Activation.class, key);
	}
	
	public void insert(Activation activation) {
		sesFact.getCurrentSession().save(activation);
	}
	
	public void delete(Activation activation) {
		sesFact.getCurrentSession().delete(activation);
	}
}
