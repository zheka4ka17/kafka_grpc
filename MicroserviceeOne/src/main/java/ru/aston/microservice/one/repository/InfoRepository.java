package ru.aston.microservice.one.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.microservice.one.entity.Info;

@Repository
public interface InfoRepository extends JpaRepository<Info, Long> {
}
