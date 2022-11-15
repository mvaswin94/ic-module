package mipl.icmodule.bill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestigationsRepository extends JpaRepository<Investigations, Long> {

	Investigations findByIbid(Long ibid);

}
