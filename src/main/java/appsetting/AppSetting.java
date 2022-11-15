package mipl.icmodule.appsetting;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "app_setting")
@Data
public class AppSetting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pref")
	public String key1;
	
	@Column(name="value")
	public String value;
	
}
