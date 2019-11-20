package mx.com.backend.admin.reclutamiento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import mx.com.backend.admin.reclutamiento.dao.usuario.IUsuarioDao;
import mx.com.backend.admin.reclutamiento.services.mail.impl.EnvioEmail;

@SpringBootApplication
public class SpringBootBackendApirestApplication implements CommandLineRunner{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private EnvioEmail envioEmail;
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootBackendApirestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String password = "12345";
		
		for (int i = 0; i < 4; i++) {
			String passwordBcrypt = passwordEncoder.encode(password);
			System.out.println(passwordBcrypt);
		}
		
		//envioEmail.envioEmail(new EnvioEmailBeans());
//		
//		Usuario unuevo = new Usuario();
//		unuevo.setEmail("peuba");
//		unuevo.setPassword("wedewdw");
//		unuevo.setNombreUsuario("nombre443");
//		Usuario u= usuarioDao.crearUsuario(unuevo);
		
	}
}
