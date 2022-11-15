package mipl.icmodule.dto;

import java.util.Date;

import javax.persistence.Column;

import lombok.Data;

@Data
public class Filter {
	
	@Column(name="pro_name")
	public String proName;
	
	@Column(name="client_name")
	public String clientName;
	
	@Column(name="cycle_days")
	public String cycleDays;
	
	@Column(name="enagement_model")
	public String enagementModel;
	
	@Column(name="enagement_model")
	public String enagementModelSearch;
	
	@Column(name="visit_date")
	public Date visitDate;
	
	@Column(name="investigation")
	public String investigation;
	
	@Column(name="department_name")
	public String departmentName;
	
	@Column(name="bill_no")
	public String billNo;	

}
