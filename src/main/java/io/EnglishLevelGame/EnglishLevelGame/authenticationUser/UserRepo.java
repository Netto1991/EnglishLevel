package io.EnglishLevelGame.EnglishLevelGame.authenticationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserAuth, Long> {
	UserAuth findByUsername(String username);

}
