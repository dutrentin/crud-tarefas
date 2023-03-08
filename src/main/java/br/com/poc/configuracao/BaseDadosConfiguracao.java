package br.com.poc.configuracao;

import java.net.InetAddress;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import br.com.poc.enuns.AmbienteEnum;


@Configuration
@Profile("BaseDados")
public class BaseDadosConfiguracao {

	@Autowired
	private Environment env;
	
	@Bean
	public DataSource conexaoBancoDados() {

		DriverManagerDataSource  	conexao 		= new DriverManagerDataSource();

		String 						nomeAmbiente 	= InetAddress.getLoopbackAddress().getHostName();
		String 					ipAmbiente 		= InetAddress.getLoopbackAddress().getHostAddress();

		String username = env.getProperty("spring.datasource.username");
		String password = env.getProperty("spring.datasource.password");
		String url = env.getProperty("spring.datasource.url");

		conexao.setUrl(url);
		conexao.setUsername(username);
		conexao.setPassword(password);


		return conexao;
	}
	
	

}
