package mipl.icmodule.log;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "log_dispatch")
@Entity
@Data
public class DispatchLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="dispatchlog_id", nullable=false)
	public Long dispatchlogId;
	
	@Column(name="cover_id")
	public Long coverId;
	
	/*@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp*/
	@Column(name="date")
	public Date date;
	
	@Column(name="type")
	public String type;
	
	@Column(name="pro_id")
	public Long proId;
	
	@Column(name="remarks")
	public String remarks;
	
	@Column(name="created_by")
	public String createdBy;
	
	/*@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp*/
	@Column(name = "created_on")
	public Date createdOn;

}
