package example.com.jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.com.jwt.entity.JwtUsuario;

@Repository
public interface UsuarioRepository extends JpaRepository<JwtUsuario, Integer>{
	
	Optional<JwtUsuario> findOneByEmail(String email);

}
