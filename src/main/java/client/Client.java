package mipl.icmodule.client;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "client")
@Data
public class Client {
			
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id")
		public Long id;	
	
		@JsonProperty("ClientID")
		@Column(name="client_id")
		public Long clientId;

		@JsonProperty("Client_Type")
		@Column(name="client_type")
		public String clientType;
		
		@JsonProperty("Client_Name")
		@Column(name="client_name")
		public String clientName;
		
		@JsonProperty("Qualification")
		@Column(name="qualification")
		public String qualification;
		
		@JsonProperty("Hospital")
		@Column(name="hospital")
		public String hospital;
		
		@JsonProperty("Mobile_No")
		@Column(name="mobile_no")
		public String mobileNo;
		
		@JsonProperty("PRO")
		@Column(name="pro")
		public String pro;
		
		@JsonProperty("Specialization")
		@Column(name="specialization")
		public String specialization;
		
		@JsonProperty("Email")
		@Column(name="email")
		public String email;
		
		/*@JsonProperty("TenentID")
		@Column(name="tenent_id")
		public int tenentId; */
		
		@JsonProperty("Tenent_Name")
		@Column(name="tenent_name")
		public String tenentName;
		
		@Column(name="enagement_model")
		public String enagementModel;
		
		@Column(name="location")
		public String location;
		
		@Column(name="ic")
		public String ic;
		
		@Column(name="ic_type")
		public String icType;
		
		@Column(name="payment_mode")
		public String paymentMode;
		
		@Column(name="cycle_days")
		public String cycleDays;
		
		@Column(name="sms")
		public String sms;
		
		@Column(name="agency")
		public String agency;
		
		@Column(name="percentage")
		public Integer percentage;
		
		/*@Temporal(TemporalType.TIMESTAMP)
		@CreationTimestamp*/
		
		@Column(name = "effective_from")
		public Date effectiveFrom;
		
		/*@Temporal(TemporalType.TIMESTAMP)
		@CreationTimestamp*/
		
		@Column(name = "effective_to")
		public Date effectiveTo;
		
		@Column(name="status")
		public Byte status;

}