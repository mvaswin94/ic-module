package mipl.icmodule.bill;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

	 @Query("SELECT DISTINCT b.proId FROM Bill b ORDER BY b.proId")
	 public List<Bill> findAllProId();

	 @Query("SELECT DISTINCT n.proName FROM Bill n where n.proName like :keyword%")
	 public List<Bill> findAllProByName(@Param("keyword") String keyword);

	 /*@Query("SELECT DISTINCT n.clientId n.clientType FROM Bill n where n.clientType like :keyword%")
	 public List<Bill> findAllClientTypeByName(@Param("keyword") String keyword);*/
	 
	 @Query("SELECT DISTINCT n.billNo FROM Bill n where n.billNo like :keyword%")
	 public ArrayList<Bill> findAllBillNoByName(@Param("keyword") String keyword);
	 
	 @Query("SELECT DISTINCT n.tenentName FROM Bill n where n.tenentName like :keyword%")
	 public List<Bill> findAllTenentByName(@Param("keyword") String keyword);
	 
	 @Query("SELECT DISTINCT n.tenentName,n.tenentId FROM Bill n")
	 public List<Bill> findAllBill();

	 @Query(value="SELECT DISTINCT tenent_name FROM bill", nativeQuery=true)
	 public List<String> findSample();

	 @Query(value="SELECT * FROM bill WHERE pro_id!=''", nativeQuery=true)
	 public List<Bill> findAllPro();

	public Bill findByBillNo(String billNo);
	 
}
