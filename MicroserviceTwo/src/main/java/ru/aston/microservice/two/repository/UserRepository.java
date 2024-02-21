package ru.aston.microservice.two.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.microservice.two.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
