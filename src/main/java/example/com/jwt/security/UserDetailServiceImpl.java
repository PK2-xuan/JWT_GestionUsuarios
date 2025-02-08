package example.com.jwt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import example.com.jwt.entity.JwtUsuario;
import example.com.jwt.repository.UsuarioRepository;

@Service
public class UserDetailServiceImpl  implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		JwtUsuario usuario = usuarioRepository
						.findOneByEmail(email)
						.orElseThrow(() -> new UsernameNotFoundException("el usuario con email " + email + " no existe. "));
		return new UserDetailsImpl(usuario);
	}
	
}















