package mipl.icmodule.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "user_master")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name=" user_id")
	public int userId;

	@Column(name="user_name")
	public String userName;
	
	@Column(name="contact_number")
	public String contactNumber;
	
	@Column(name="email_address")
	public String emailAddress;
	
	@Column(name="role")
	public String role;

}
