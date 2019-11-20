package mx.com.backend.admin.reclutamiento.services.usuario.impl;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mx.com.backend.admin.reclutamiento.core.exception.BussinesException;
import mx.com.backend.admin.reclutamiento.core.exception.DataAccesException;
import mx.com.backend.admin.reclutamiento.dao.usuario.IUsuarioDao;
import mx.com.backend.admin.reclutamiento.models.EnvioEmailBeans;
import mx.com.backend.admin.reclutamiento.models.Usuario;
import mx.com.backend.admin.reclutamiento.services.mail.IEnvioEmail;
import mx.com.backend.admin.reclutamiento.services.usuario.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private IEnvioEmail envioEmail;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Usuario crearUsuario(Usuario usuario) throws BussinesException {

		try {
			Usuario user = usuarioDao.buscarUsuarioPorCorreo(usuario.getEmail());
			if (user != null) {
				throw new BussinesException("El correo ya existe");
			}
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			user = usuarioDao.crearUsuario(usuario);
			EnvioEmailBeans objMail = new EnvioEmailBeans();
			objMail.setBodyEmail("<style type=\"text/css\">\n" + "  /* MOBILE STYLES */\n" + "  .table-responsive{\n"
					+ "    width: 240px;\n" + "  }\n" + "  @media only screen and (max-device-width : 600px) {\n"
					+ "      /* ALLOWS FOR FLUID TABLES */\n" + "      td{\n" + "        font-size: 45px !important;\n"
					+ "        display: block;\n" + "      }\n" + "      h1{\n"
					+ "         font-size: 50px !important;\n" + "      }\n" + "      h2{\n"
					+ "         font-size: 45px !important;\n" + "      }\n" + "      h3{\n"
					+ "        font-size: 40px !important;\n" + "      }\n" + "      span,p,a{\n"
					+ "        font-size: 35px !important;\n" + "      }\n" + "      .cell-responsive{\n"
					+ "        text-align: center !important;\n" + "      }\n" + "      .text-responsive{\n"
					+ "         font-size: 40px !important;\n" + "      }\n"
					+ "      .table-responsive, .image-responsive{\n" + "        width: 100% !important;\n"
					+ "        margin-bottom: 10px;\n" + "      }\n" + "      .image-responsive{\n"
					+ "        height: auto;\n" + "      }\n" + "  }\n" + "</style>\n"
					+ "<div style=\"background-color: #f4f4f4; margin: 0 !important; padding: 0 !important;font-family: 'Lato';font-style: normal;font-weight: 400;\">\n"
					+ "  <div style=\"line-height: 5;background-color:#FFA73B; margin-bottom:0;\">&nbsp;\n"
					+ "    <table border=\"0\" cellpadding=\"0\" align=\"center\" cellspacing=\"0\" style=\"background-color:#ffffff; width:600px;\">\n"
					+ "      <tr>\n" + "        <td>\n" + "          <table align=\"center\">\n" + "            <tr>\n"
					+ "              <td style=\"line-height: 40px;\">&nbsp;</td>\n" + "            </tr>\n"
					+ "            <tr>\n" + "              <td align=\"center\">\n"
					+ "                <a href=\"http://www.salesup.com\" target=\"_blank\">\n"
					+ "                  <img src=\"https://lh3.googleusercontent.com/-VOk2iBWTO9M/WYHhG3lh8WI/AAAAAAAAANU/wzYYarLltnE2XHFM1lXyJtzyN8W8mjcOACK8BGAs/s418/2017-08-02.png\" width=\"px\" height=\"\" style=\"display: block; width: 80px; max-width: 300px; min-width: 150px; font-family: 'Lato', Helvetica, Arial, sans-serif; color: #ffffff; font-size: 18px;\" border=\"0\">\n"
					+ "                </a>\n" + "              </td>\n" + "            </tr>\n" + "            <tr>\n"
					+ "              <td align=\"center\" style=\"padding: 40px 20px 20px 20px;\">\n"
					+ "                <h1 style=\"color: #111111; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 48px; font-weight: 400; letter-spacing: 4px; line-height: 48px;margin: 0;\"><strong>¡Bienvenido!</strong></h1>\n"
					+ "              </td>\n" + "            </tr>\n" + "          </table>\n" + "        </td>\n"
					+ "      </tr>\n" + "    </table>\n" + "  </div>\n"
					+ "  <table align=\"center\" style=\"background-color:#ffffff; width:600px;\">\n" + "    <tr>\n"
					+ "      <td>\n"
					+ "        <p style=\"margin: 0;padding: 20px 30px 40px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400;\">Hola "
					+ usuario.getNombreUsuario()
					+ ", quiero darte la bienvenida a (Nombre de tu empresa), es un gusto tenerte con nosotros. Tenemos muchas información que queremos compartirte y esperamos que sea de tu agrado. Puedes acceder a ella en el siguiente botón.</p>\n"
					+ "      </td>\n" + "    </tr>\n" + "    <tr>\n"
					+ "      <td style=\"line-height: 20px;\">&nbsp;</td>\n" + "    </tr>\n" + "    <tr>\n"
					+ "      <td align=\"center\">\n"
					+ "        <a href=\"https://www.salesup.com\" target=\"_blank\" style=\"font-size: 20px; font-family: Helvetica, Arial, sans-serif; color: #ffffff; text-decoration: none; color: #ffffff; text-decoration: none; padding: 15px 25px; border-radius: 2px; border: 1px solid #FFA73B; display: inline-block;background-color:#FFA73B;\">¡Quiero más información!</a>\n"
					+ "      </td>\n" + "    </tr>\n" + "    <tr>\n"
					+ "      <td style=\"line-height: 60px;\">&nbsp;</td>\n" + "    </tr>\n" + "    <tr>\n"
					+ "      <td align=\"left\" style=\"padding: 0px 30px 0px 30px;\" >\n"
					+ "        <p style=\"margin: 0; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400;\">Si esta liga no funciona, copia y pega el siguiente link dentro de tu navegador.<p>\n"
					+ "      </td>\n" + "    </tr>\n" + "    <tr>\n"
					+ "      <td align=\"left\" style=\"padding: 20px 30px 20px 30px;\" >\n"
					+ "        <p style=\"margin: 0;\"><a href=\"http://www.salesup.com\" target=\"_blank\" style=\"color: #FFA73B;font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\">www.xxxx.com/xxx</a></p>\n"
					+ "      </td>\n" + "    </tr>\n" + "    <tr>\n"
					+ "      <td align=\"left\" style=\"padding: 0px 30px 20px 30px;\" >\n"
					+ "        <p style=\"margin: 0; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400;\">De igual forma quisiera decirte que si llegaras a tener alguna duda o pregunta, estoy a tus órdenes y puedes consultarme cualquier cosa por este medio.</p>\n"
					+ "      </td>\n" + "    </tr>\n" + "    <tr>\n"
					+ "      <td align=\"left\" style=\"padding: 0px 30px 40px 30px;\" >\n"
					+ "        <p style=\"margin: 0; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\">¡Saludos!</p>\n"
					+ "      </td>\n" + "    </tr>\n" + "    <tr>\n"
					+ "      <td style=\"line-height: 8px;\">&nbsp;</td>\n" + "    </tr>\n"
					+ "    <tr style=\"background-color:#FFECD1\">\n"
					+ "      <td align=\"center\" style=\"padding: 30px 30px 30px 30px; font-family: 'Lato', Helvetica, Arial, sans-serif;\" >\n"
					+ "        <h2 style=\"font-size: 20px; font-weight: 400; color: #111111; margin: 0;\">¿Necesitas más ayuda?</h2>\n"
					+ "        <p style=\"margin: 0;font-size: 18px; font-weight: 400;\"><a href=\"http://salesup.com\" target=\"_blank\" style=\"color: #FFA73B;\">Estamos listos para aclarar dudas</a></p>\n"
					+ "      </td>\n" + "    </tr>\n" + "  </table>\n"
					+ "  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"background-color:#ffffff\" align=\"center\">\n"
					+ "    <tr>\n" + "      <td style=\"line-height: 20px;\">&nbsp;</td>\n" + "    </tr>\n"
					+ "    <tr>\n" + "      <td align=\"center\">\n"
					+ "        <p style=\"font-family: 'Montserrat', Arial, sans-serif; font-size:12px; color:#666666; padding:0 20px; margin:0;\">675 Av Portillo, 2do Piso, Villahermosa, Villahermosa 02139</p>\n"
					+ "      </td> \n" + "    </tr>\n" + "    <tr>\n"
					+ "      <td style=\"line-height: 10px;\">&nbsp;</td>\n" + "    </tr>\n" + "    <tr>\n"
					+ "      <td align=\"center\" style=\"font-family: 'Montserrat', Arial, sans-serif; font-size:12px; line-height:18px; padding:0 20px;\">\n"
					+ "        <a href=\"http://www.salesup.com\" target=\"_blank\" style=\"color:#666666; text-decoration:underline;\">info@miempresa.com</a>  \n"
					+ "        <span style=\"line-height:20px;\">&nbsp;|&nbsp;</span>\n"
					+ "        <a href=\"http://www.salesup.com\" target=\"_blank\" style=\"color:#666666; text-decoration:underline;\">Vistar en navegador</a>\n"
					+ "      </td>\n" + "    </tr>\n" + "    <tr>\n"
					+ "      <td style=\"line-height: 10px;\">&nbsp;</td>\n" + "    </tr>\n" + "    <tr>\n"
					+ "      <td>\n" + "        <hr style=\"border-top:1px solid #dddddd;\">\n" + "      </td>\n"
					+ "    </tr>\n" + "    <tr>\n" + "      <td style=\"line-height: 10px;\">&nbsp;</td>\n"
					+ "    </tr>\n" + "    <tr>\n" + "      <td align=\"center\" >\n"
					+ "        <p style=\"font-family: 'Montserrat', Arial, sans-serif; font-size:12px; line-height:18px; color:#666666; padding:0; margin:0;\">Plantilla creada por <a href=\"https://www.salesup.com/plantillas-correo.shtml\">SalesUp!®</a> CRM de ventas</p></p>\n"
					+ "      </td>\n" + "    </tr>\n" + "  </table>\n" + "</div>");
			objMail.setEmail(user.getEmail());

			envioEmail.envioEmail(objMail);

			return user;

		} catch (DataAccesException e) {

			throw new BussinesException(e);

		} catch (BussinesException e) {

			throw new BussinesException(e);

		} catch (MessagingException e) {
			throw new BussinesException(e);
		} catch (IOException e) {
			throw new BussinesException(e);
		}
	}

}
