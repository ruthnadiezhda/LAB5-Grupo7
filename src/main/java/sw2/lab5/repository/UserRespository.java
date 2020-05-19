package sw2.lab5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sw2.lab5.entity.User;

public interface UserRespository extends JpaRepository<User,Integer> {
    public User findByEmail (String email);

}
