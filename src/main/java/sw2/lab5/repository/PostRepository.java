package sw2.lab5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sw2.lab5.entity.Post;

public interface PostRepository  extends JpaRepository<Post, Integer> {



}
