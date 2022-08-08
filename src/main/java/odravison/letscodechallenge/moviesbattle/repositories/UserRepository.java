package odravison.letscodechallenge.moviesbattle.repositories;

import odravison.letscodechallenge.moviesbattle.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

    @Modifying
    @Query(value = "UPDATE user_account " +
            "SET last_login_time = ?2 " +
            "WHERE id = ?1", nativeQuery = true)
    void updateLastLoginTimeByUserId(Long id, Long lastLoginTime);

    User findByLogin(String login);

    @Query(value = "select * from user_account " +
            "where upper(login) = upper(?1) " +
            "and password = ?2 " +
            "and deleted = false;",
            nativeQuery = true)
    User login(String login, String encryptedPassword);
}
