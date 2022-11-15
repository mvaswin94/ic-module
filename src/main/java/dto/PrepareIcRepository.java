package mipl.icmodule.dto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PrepareIcRepository extends JpaRepository<PrepareIc, Long> {

	
	//@Query(value="SELECT * FROM v_ic WHERE client_status='1' AND client_ic='YES' AND calculated_amount >0 and billinvest_status ='0' AND billinvest_hold ='0' and billinvest_hold ='0'", nativeQuery=true)
	@Query("SELECT p FROM PrepareIc p WHERE p.clientStatus='1' AND p.clientIc='YES' AND p.calculatedAmount >0 AND p.billinvestStatus ='0' AND p.billinvestHold ='0' and p.billinvestHold ='0'")
	Page<PrepareIc> findAllPrepare(Pageable pageable);
	
	@Query(value="SELECT * FROM v_ic WHERE ic_status='1' AND client_status='1' AND ic_hold='0'", nativeQuery=true)
	Page<PrepareIc> findAllAllocate(Pageable pageable);

	@Query(value="SELECT * FROM v_ic WHERE ic_status='3' AND client_status='1' AND ic_hold='0'", nativeQuery=true)
	Page<PrepareIc> findAllApprove(Pageable pageable);
	
	@Query(value="SELECT * FROM v_ic WHERE ic_status='5' AND client_status='1'AND ic_status='7' OR ic_status='8'  OR ic_status='9' AND cover_id !=''", nativeQuery=true)
	Page<PrepareIc> findAllDispatch(Pageable pageable);
	
	@Query(value="SELECT * FROM v_ic WHERE pro_id='0'", nativeQuery=true)
	Page<PrepareIc> findAllNoIc(Pageable pageable);
	
	@Query(value="SELECT * FROM v_ic WHERE pro_id='0' AND pro_name=''", nativeQuery=true)
	Page<PrepareIc> findAllUnAssignedPro(Pageable pageable);

	@Query(value="SELECT * FROM v_ic WHERE pro_id='0'", nativeQuery=true)
	Page<PrepareIc> findAllNonEligibleIc(Pageable pageable);

	@Query(value="SELECT * FROM v_ic  order by visit_date desc limit 1000", nativeQuery=true)
	List<PrepareIc> findAllIc();

	PrepareIc findByibid(Long ibid);

	@Query(value="SELECT * FROM v_ic WHERE ic_status = '2'", nativeQuery=true)
	Page<PrepareIc> findAllNotPreparedIc(Pageable pageable);

	@Query(value="SELECT * FROM v_ic WHERE ic_status = '4'", nativeQuery=true)
	Page<PrepareIc> findAllNotAllocatedIc(Pageable pageable);

	@Query(value="SELECT * FROM v_ic WHERE ic_status = '6'", nativeQuery=true)
	Page<PrepareIc> findAllNotApprovedIc(Pageable pageable);

	@Query(value="SELECT * FROM v_ic WHERE ic_status = '9'", nativeQuery=true)
	Page<PrepareIc> findAllCompletedIc(Pageable pageable);

	List<PrepareIc> findAllByCoverId(Long keyword);

	//@Query("SELECT p FROM PrepareIc p WHERE p.billinvestHold ='1' OR p.icHold ='1'")
	@Query(value="SELECT * FROM v_ic WHERE billinvest_hold ='1'", nativeQuery=true)
	Page<PrepareIc> findAllHoldIc(Pageable pageable);

	@Query(value="SELECT * FROM v_ic WHERE pro_id='0' OR calculated_amount <=0", nativeQuery=true)
	Page<PrepareIc> findAllReconciliationIc(Pageable pageable);

	@Query(value="SELECT * FROM v_ic WHERE ic_status != '0'", nativeQuery=true)
	Page<PrepareIc> findAllIcStages(Pageable pageable);

}
