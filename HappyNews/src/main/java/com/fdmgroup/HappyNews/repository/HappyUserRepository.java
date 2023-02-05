package com.fdmgroup.HappyNews.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fdmgroup.HappyNews.model.HappyUser;


public interface HappyUserRepository extends JpaRepository<HappyUser, Long>{
	Optional<HappyUser> findByName(String name);
	Optional<HappyUser> findByEmail(String email);
}
