package mipl.icmodule.client;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	@Query("SELECT DISTINCT c.clientName FROM Client c")
	public List<Client> findAllClientName();

	/*@Query("SELECT DISTINCT c.ic FROM Client c")
	public List<Client> findAllClientIc();*/

	@Query("SELECT DISTINCT c.clientType FROM Client c")
	public List<Client> findAllClientType();

	/*@Query("SELECT DISTINCT c.paymentMode FROM Client c")
	public List<Client> findAllPaymentMode();*/
	
	@Query("SELECT DISTINCT c.pro FROM Client c")
	public List<Client> findAllPro();

	@Query("SELECT DISTINCT c.pro FROM Client c")
	public List<Client> findAllStatus();

	@Query("SELECT DISTINCT c.tenentName FROM Client c")
	public List<Client> findAllTenentName();

	public Client findByClientId(Long clientId);

	/*@Query("SELECT n.Client_Name FROM Client n where n.Client_Name like :keyword%")
	public List<Client> findAllClientByName(@Param("keyword") String keyword);*/
	
}
