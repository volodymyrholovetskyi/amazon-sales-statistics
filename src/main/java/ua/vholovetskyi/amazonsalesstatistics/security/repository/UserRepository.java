package ua.vholovetskyi.amazonsalesstatistics.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import ua.vholovetskyi.amazonsalesstatistics.security.model.UserEntity;

import java.util.Optional;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-02-21
 */
@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    @Query("{'email': ?0}")
    Optional<UserEntity> findByEmailAddress(String email);

}