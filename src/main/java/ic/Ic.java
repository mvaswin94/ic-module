package mipl.icmodule.ic;

import java.math.BigDecimal;
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
@Table(name = "ic")
@Data
public class Ic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ic_id", nullable=false)
	public Long icId;
	
	@Column(name="ibid")
	public Long ibid;
	
	@Column(name="bill_no")
	public String billNo;
	
	@Column(name="investigation_id")
	public Long investigationId;
	
	@Column(name="calculated_amount")
	public BigDecimal calculatedAmount;
	
	@Column(name="prepared_by")
	public String preparedBy;
	
	@Column(name="prepared_amount")
	public BigDecimal preparedAmount;
	
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name = "prepared_date")
	@CreationTimestamp
	public Date preparedDate;
	
	@Column(name="allocated_by")
	public String allocatedBy;

	@Column(name="allocated_amount")
	public BigDecimal allocatedAmount;
	
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name = "allocated_date")
	@CreationTimestamp
	public Date allocatedDate;
	
	@Column(name="approved_by")
	public String approvedBy;
	
	@Column(name="approved_amount")
	public BigDecimal approvedAmount;
	
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name = "approved_date")
	@CreationTimestamp
	public Date approvedDate;
	
	@Column(name="approval_id")
	public String approvalId;
	
	@Temporal(TemporalType.TIMESTAMP) 
	@CreationTimestamp
	@Column(name="created_on")
	public Date createdOn;
	
	@Column(name="updated_on")
	public Date updatedOn;
	
	@Column(name="ic_status")
	public Byte icStatus;	
	
	@Column(name="hold")
	public Byte hold;
	
	/*@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bill_no", nullable = false, insertable=false)
    private Bill bill;*/
	
	/*@ManyToOne(optional=false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "bill_no", insertable=false, updatable=false)
    public Bill bill;*/
	
}
