package mx.com.backend.admin.reclutamiento.auth;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import mx.com.backend.admin.reclutamiento.core.exception.BussinesException;
import mx.com.backend.admin.reclutamiento.models.Usuario;
import mx.com.backend.admin.reclutamiento.services.login.impl.IUsuarioLoginService;

@Component
public class InfoAdicionalToken implements TokenEnhancer{
	
	private final static Logger log = LoggerFactory.getLogger(InfoAdicionalToken.class);
	@Autowired
	private IUsuarioLoginService usuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		try {
			Usuario	usuario = usuarioService.buscarUsuarioPorCorreo(authentication.getName());
			Map<String, Object> info = new HashMap<>();
			info.put("usuario", usuario);
			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		} catch (BussinesException e) {
			log.error("..::Error al obtener a consulta  ::..",e);
		}
		return accessToken;
	}

}
