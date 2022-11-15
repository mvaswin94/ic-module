package mipl.icmodule.cover;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "cover_ic")
@Data
public class CoverIc {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public Long id;
	
	
	@Column(name="cover_id", nullable=false)
	public Long coverId;
	
	@Column(name="ic_id", nullable=false)
	public Long icId;
	
/*	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cover_id", nullable = false, insertable=false)
    private PrepareIc ic;*/
	
	
	

}
