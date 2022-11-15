package mipl.icmodule.log;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "log_user")
public class UserLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userlog_id", nullable=false)
	public Long userlogId;
	
	@Column(name="user_id")
	public Long userId;
	
	@Column(name="type")
	public String type;
	
	@Column(name="wef_date")
	public Date wefDate;
	
	@Column(name="created_by")
	public String createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "created_on")
	public Date createdOn;

}
