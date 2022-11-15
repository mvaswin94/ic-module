package mipl.icmodule.appsetting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppSettingRepository extends JpaRepository<AppSetting, String> {

	public void save(String lastSyncTimeString);

	/*@Query(value="SELECT value FROM app_setting WHERE key1 IN ('date')", nativeQuery=true)
	public String findByKey1();*/
	
	@Query(value="SELECT value FROM app_setting WHERE pref = :key", nativeQuery=true)
	String findKey(@Param("key") String key);

	public AppSetting findBykey1(String key1);


}
