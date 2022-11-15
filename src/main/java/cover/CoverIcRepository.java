package mipl.icmodule.cover;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mipl.icmodule.checkbox.OrderCheckboxDAO;

@Repository
public interface CoverIcRepository extends JpaRepository<CoverIc, Long> {

	void save(OrderCheckboxDAO objLoop2);

	public CoverIc findByCoverId(Long coverId);

	

}
