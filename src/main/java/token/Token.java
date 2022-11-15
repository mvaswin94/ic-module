package mipl.icmodule.token;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
@Table(name = "token")
public class Token {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tid", nullable=false)
	public int tid;
	
	
	@JsonProperty("access_token")
	@Column(name="access_token")
	public String accessToken;
	
	@JsonProperty("token_type")
	@Column(name="token_type")
	public String tokenType;

	@JsonProperty("expires_in")
	@Column(name="expires_in")
	public int expiresIn;

}
