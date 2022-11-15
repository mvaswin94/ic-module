package mipl.icmodule.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import mipl.icmodule.cover.Cover;

@Entity
@Table(name = "v_ic")
@Data
public class PrepareIc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ibid")
	public Long ibid;

	@Column(name = "investigation_id")
	public Long investigationId;

	@Column(name = "investigation_Name")
	public String investigationName;

	@Column(name = "department_id")
	public Long departmentId;

	@Column(name = "department_name")
	public String departmentName;
	
	@Column(name = "bill_no")
	public String billNo;

	@Column(name = "visit_id")
	public Long visitId;

	@Column(name = "visit_date")
	public Date visitDate;

	@Column(name = "client_type")
	public String clientType;

	@Column(name = "client_id")
	public Long clientId;

	@Column(name = "client_name")
	public String clientName;
	
	@Column(name = "client_ic")
	public String clientIc;
	
	@Column(name = "client_status")
	public Byte clientStatus;
	
	@Column(name = "percentage")
	public Integer percentage;

	@Column(name = "pro_id")
	public Long proId;

	@Column(name = "pro_name")
	public String proName;

	@Column(name = "tenent_id")
	public Long tenentId;

	@Column(name = "tenent_name")
	public String tenentName;

	@Column(name = "agency")
	public String agency;

	@Column(name = "bill_amount")
	public BigDecimal billAmount;

	@Column(name = "net_amount")
	public BigDecimal netAmount;

	@Column(name = "discount_amount")
	public BigDecimal discountAmount;

	@Column(name = "min_cost")
	public BigDecimal minCost;

	@Column(name = "billinvest_status")
	public Byte billinvestStatus;

	@Column(name = "billinvest_hold")
	public Byte billinvestHold;
	
	@Column(name = "ic_id")
	public Long icId;

	@Column(name = "prepared_amount")
	public BigDecimal preparedAmount;

	@Column(name = "prepared_date")
	public Date preparedDate;
	
	@Column(name = "allocated_amount")
	public BigDecimal allocatedAmount;

	@Column(name = "allocated_date")
	public Date allocatedDate;
	
	@Column(name = "approved_amount")
	public BigDecimal approvedAmount;

	@Column(name = "approved_date")
	public Date approvedDate;
	
	@Column(name = "ic_status")
	public Byte icStatus;
	
	@Column(name = "ic_hold")
	public Byte icHold;

	@Column(name = "calculated_amount")
	public BigDecimal calculatedAmount;

	@Column(name = "cover_id")
	public Long coverId;

}
