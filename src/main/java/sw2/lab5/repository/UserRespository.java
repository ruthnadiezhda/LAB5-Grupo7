package sw2.lab5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sw2.lab5.entity.User;

@Repository
public interface UserRespository extends JpaRepository<User,Integer> {

}
