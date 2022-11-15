package mipl.icmodule.bill;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Value;

@Entity
@Table(name = "billinvest")
@Data
public class Investigations{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ibid")
	public Long ibid;
	
	@Column(name="bid")
	public Long bid;
	
	@JsonProperty("InvestIgationId")
	@Column(name="investigation_id")
	public Long investigationId;

	@JsonProperty("Investigation_Name")
	@Column(name="investigation_name")
	public String investigationName;
	
	@JsonProperty("AccessionNo")
	@Column(name="accession_no")
	public String accessionNo;
	
	@JsonProperty("DepartmentId")
	@Column(name="department_id")
	public Long departmentId;
	
	@JsonProperty("Department_Name")
	@Column(name="department_name")
	public String departmentName;
     
	@JsonProperty("Main_Department")
    @Column(name="main_department")
	public String mainDepartment;

	@JsonProperty("Bill_Amount")
	@Column(name="bill_amount")
	public BigDecimal billAmount;
	
	@JsonProperty("Discount_Amount")
	@Column(name="discount_amount")
	public BigDecimal discountAmount;
	
	@JsonProperty("Net_Amount")
	@Column(name="net_amount")
	public BigDecimal netAmount;
	
	@JsonProperty("ProfileType")
	@Column(name="profile_type")
	public String profileType;

	@JsonProperty("ProfileName")
	@Column(name="profile_name")
	public String profileName;

	@JsonProperty("IsSTAT")
	@Column(name="is_stat")
	public String isStat;
	
	@JsonProperty("MinCost")
	@Column(name="min_cost")
	public BigDecimal minCost;
	
	@JsonProperty("RatecardName")
	@Column(name="ratecard_name")
	public String ratecardName;
	
	@Column(name="ic_amount")
	public BigDecimal icAmount;
	
	@Column(name="ic_remarks")
	public String icRemarks;
	
	@Column(name="ic_status")
	public Byte icStatus;
	
	@Column(name="hold")
	public Byte hold;
	
	@Column(name="iseligible_ic")
	public Byte iseligibleIc;

	}
