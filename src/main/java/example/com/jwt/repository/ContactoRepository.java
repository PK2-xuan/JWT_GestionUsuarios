package example.com.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.com.jwt.entity.JwtContacto;

@Repository
public interface ContactoRepository extends JpaRepository<JwtContacto, Integer>{

}
