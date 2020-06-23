package br.com.cast.ferias.Config;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import br.com.cast.ferias.Config.Token.CustomTokenEnhancer;


@Profile("oauth-security")
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{


@Autowired
private AuthenticationManager authenticationManager;

@Autowired
private UserDetailsService userDetailService;


	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	// configura o cliente. Quem o usuário está usando.
	
		clients.inMemory()
				.withClient("angular")
				.secret("$2a$10$xBFj4E0a7e.sF5nYl/4tLubpTBKWXQ/m9ac3Mp9bKTHmfRJeP4ygO") //@GastGroup2020@
				.scopes("read","write")
				.authorizedGrantTypes("password", "refresh_token")
				.accessTokenValiditySeconds(1800)
				.refreshTokenValiditySeconds(3600 * 24 * 5)	//3600 = 1 hora * 24 = 1 dia. Um dia para expirar o Refhresh Token.
			.and()
				.withClient("mobile")
				.secret("$2a$10$xBFj4E0a7e.sF5nYl/4tLubpTBKWXQ/m9ac3Mp9bKTHmfRJeP4ygO") //@GastGroup2020@
				.scopes("read","write")
				.authorizedGrantTypes("password", "refresh_token")
				.accessTokenValiditySeconds(1800)
				.refreshTokenValiditySeconds(3600 * 24 * 5); // default 5 dias =>  3600 = 1 hora * 24 = 1 dia * 5 dias
	
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
		endpoints.tokenStore(tokenStore())
				 .tokenEnhancer(tokenEnhancerChain)
				 .reuseRefreshTokens(false)	// Sempre que solicitar um novo Access Token usando o Refresh Token,  um novo Refhres Token será enviado.
				 // garante que o usuário não sofra um logoff enquanto estiver usando a aplicação.	
				 .userDetailsService(userDetailService)
				 .authenticationManager(authenticationManager);
	}
	
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
	
		JwtAccessTokenConverter tokenConverter =  new JwtAccessTokenConverter();
		tokenConverter.setSigningKey("castapi");
		return tokenConverter;
	}
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	
	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}

}
