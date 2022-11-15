package mipl.icmodule.ic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mipl.icmodule.cover.CoverIc;
import mipl.icmodule.dto.PrepareIc;

@Repository
public interface IcRepository extends JpaRepository<Ic, Long> {

	@Query(value="SELECT tid,icstatus, b.billinvest_id, b.investigation,c.visit_id, c.bill_no, c.visit_date,c.pro_id, c.pro_name,a.client_id, a.client_name,a.client_type, a.ic, c.patient_name,d.iid, d.base_cost,d.min_cost,b.discount_amount FROM ic INNER JOIN client a ON ic.client_id=a.client_id INNER JOIN billinvest b ON ic.billinvest_id=b.billinvest_id INNER JOIN bill c ON ic.visit_id=c.visit_id INNER JOIN investigation d ON ic.iid=d.iid Where a.ic='No'", nativeQuery=true)
	public List<Ic> findAllNoIc();

	@Query(value="SELECT tid,icstatus, b.billinvest_id, b.investigation,c.visit_id, c.bill_no, c.visit_date,c.pro_id, c.pro_name,a.client_id, a.client_name,a.client_type, a.ic, c.patient_name,d.iid, d.base_cost,d.min_cost,b.discount_amount FROM ic INNER JOIN client a ON ic.client_id=a.client_id INNER JOIN billinvest b ON ic.billinvest_id=b.billinvest_id INNER JOIN bill c ON ic.visit_id=c.visit_id INNER JOIN investigation d ON ic.iid=d.iid Where a.pro=''", nativeQuery=true)
	public List<Ic> findAllUnAssignedPro();

	@Query(value="SELECT tid,icstatus, b.billinvest_id, b.investigation,c.visit_id, c.bill_no, c.visit_date,c.pro_id, c.pro_name,a.client_id, a.client_name,a.client_type, a.ic, c.patient_name,d.iid, d.base_cost,d.min_cost,b.discount_amount FROM ic INNER JOIN client a ON ic.client_id=a.client_id INNER JOIN billinvest b ON ic.billinvest_id=b.billinvest_id INNER JOIN bill c ON ic.visit_id=c.visit_id INNER JOIN investigation d ON ic.iid=d.iid  Where a.pro='' OR a.ic='No'", nativeQuery=true)
	public List<Ic> findAllNonEligibleIc();

	@Query(value="SELECT tid,icstatus, b.billinvest_id, b.investigation,c.visit_id, c.bill_no, c.visit_date,c.pro_id, c.pro_name,a.client_id, a.client_name,a.client_type, a.ic, c.patient_name,d.iid, d.base_cost,d.min_cost,b.discount_amount FROM ic INNER JOIN client a ON ic.client_id=a.client_id INNER JOIN billinvest b ON ic.billinvest_id=b.billinvest_id INNER JOIN bill c ON ic.visit_id=c.visit_id INNER JOIN investigation d ON ic.iid=d.iid  Where a.pro!='' OR a.ic!='No'", nativeQuery=true)
	public List<Ic> findAllEligibleIc();

	@Query(value="SELECT tid,icstatus, b.billinvest_id, b.investigation,c.visit_id, c.bill_no, c.visit_date,c.pro_id, c.pro_name,a.client_id, a.client_name,a.client_type, a.ic, c.patient_name,d.iid, d.base_cost,d.min_cost,b.discount_amount FROM ic INNER JOIN client a ON ic.client_id=a.client_id INNER JOIN billinvest b ON ic.billinvest_id=b.billinvest_id INNER JOIN bill c ON ic.visit_id=c.visit_id INNER JOIN investigation d ON ic.iid=d.iid  Where a.pro!='' OR a.ic!='No'", nativeQuery=true)
	public List<Ic> findAllPrepare();

	@Query(value="SELECT tid,icstatus, b.billinvest_id, b.investigation,c.visit_id, c.bill_no, c.visit_date,c.pro_id, c.pro_name,a.client_id, a.client_name,a.client_type, a.ic, c.patient_name,d.iid, d.base_cost,d.min_cost,b.discount_amount FROM ic INNER JOIN client a ON ic.client_id=a.client_id INNER JOIN billinvest b ON ic.billinvest_id=b.billinvest_id INNER JOIN bill c ON ic.visit_id=c.visit_id INNER JOIN investigation d ON ic.iid=d.iid  Where a.pro!='' OR a.ic!='No'", nativeQuery=true)
	public List<Ic> findAllCompleted();

	Ic findByibid(Long ibid);
	
	public Ic findByIcId(PrepareIc ic);

	public Ic findByIcId(CoverIc obj3);

	Ic findByIcId(Long icId);

}	
