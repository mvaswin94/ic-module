package mipl.icmodule.token;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface TokenRepository extends JpaRepository<Token, Integer>{

}
