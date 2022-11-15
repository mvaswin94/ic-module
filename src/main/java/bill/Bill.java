package mipl.icmodule.bill;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "bill")
@Data
public class Bill {	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bid")
	public Long bid;
		
	@JsonProperty("VisitId")
	@Column(name="visit_id")
	public Long visitId;

	@JsonProperty("LabNo")
	@Column(name="lab_no")
	public String labNo;
	
	@JsonProperty("PatientID")
	@Column(name="patient_id")
	public String patientID;
	
	@JsonProperty("Patient_Name")
	@Column(name="patient_name")
	public String patientName;
	
	@JsonProperty("Referred_Doctor")
	@Column(name="referred_doctor")
	public String referredDoctor;
     
	@JsonProperty("BillNo")
    @Column(name="bill_no")
	public String billNo;

	@JsonProperty("BillUser")
    @Column(name="bill_user")
	public String billUser;
	
	@JsonProperty("VisitDate")
	@Column(name="visit_date")
	public Date visitDate;
	
	@JsonProperty("Total_Amount")
	@Column(name="total_amount")
	public BigDecimal totalAmount;
	
	@JsonProperty("Paid_Amount")
	@Column(name="paid_amount")
	public BigDecimal paidAmount;
	
	@JsonProperty("Client_Type")
	@Column(name="client_type")
	public String clientType;

	@JsonProperty("ClientID")
	@Column(name="client_id")
	public Long clientId;
	
	@JsonProperty("Client_Name")
	@Column(name="client_name")
	public String clientName;
	
	@JsonProperty("PROId")
	@Column(name="pro_id")
	public Long proId;
	
	@JsonProperty("PROName")
	@Column(name="pro_name")
	public String proName;
	
	@JsonProperty("TenentID")
	@Column(name="tenent_id")
	public Long tenentId;

	@JsonProperty("Tenent_Name")
	@Column(name="tenent_name")
	public String tenentName;
	
	@JsonProperty("Divison")
	@Column(name="divison")
	public String divison;
	
	@Column(name="ic_status")
	public Byte icstatus;
	
	/*@JsonProperty("Investigations")
	@OneToMany(mappedBy="visitId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public List<Investigations> Investigations = new ArrayList<Investigations>();*/
	
	
	@JsonProperty("Investigations")
	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="bid")
	@NotNull
	public List<Investigations> Investigations = new ArrayList<Investigations>();
	
}
