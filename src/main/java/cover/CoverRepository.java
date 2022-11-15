package mipl.icmodule.cover;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mipl.icmodule.checkbox.OrderCheckboxDAO;

@Repository
public interface CoverRepository extends JpaRepository<Cover, Long> {

	void save(OrderCheckboxDAO objLoop2);

	public Cover findByCoverId(Long coverId);

}
