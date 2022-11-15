package mipl.icmodule.cover;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import mipl.icmodule.client.Client;

@Entity
@Table(name = "cover")
@Data
public class Cover {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cover_id", nullable=false)
	public Long coverId;
		
	@Column(name="prepared_by")
	public String preparedBy;
	
	@Column(name="status")
	public Byte status;
	
	@Column(name="pro_id")
	public Long proId;
	
	@Column(name="pro_name")
	public String proName;
	
	@Column(name="client_id")
	public Long clientId;
	
	@Column(name="client_name")
	public String clientName;
	
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name = "created_on")
	@CreationTimestamp
	public Date createdOn;
	
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name = "updated_on")
	@CreationTimestamp
	public Date updatedOn;
	
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name = "dispatch_date")
	@CreationTimestamp
	public Date dispatchDate;
	
	/*@ManyToOne(optional=false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "client_id", insertable=false, updatable=false)
    public PrepareIc cObj;*/
	
	
	/*@OneToMany(cascade = CascadeType.ALL)  
	@JoinColumn(name="cover_id")  
	private List<PrepareIc> cObj;*/  
	
}	
	
	