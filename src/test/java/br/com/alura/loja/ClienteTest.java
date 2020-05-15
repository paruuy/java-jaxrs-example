package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.modelo.Projeto;

public class ClienteTest {

	private HttpServer server;

	// Roda antes de cada teste (colocamos o start server para usar em todos os que
	// seja necessario sem repetir codigo)
	@Before
	public void startServer() {
		// Startsa server
		this.server = Servidor.startServer();
	}

	// Roda depois dos testes
	@After
	public void stopServer() {

		server.stop();
	}

	@Test
	public void testQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		String conteudo = target.path("/carrinhos/1").request().get(String.class);

		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());

	}

	@Test
	public void testaQueBuscarUmProjetoTrazOProjetoEsperado() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		String conteudo = target.path("/projetos/1").request().get(String.class);
		
		Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
		Assert.assertEquals("Minha loja", projeto.getNome());
	}
	
	@Test
	public void testQueAdicionaCarrinho() {
		Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        Carrinho carrinho = new Carrinho();
        //Criamos carrinho e logo transformamos em XML
        carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
        carrinho.setRua("Rua Vergueiro");
        carrinho.setCidade("Sao Paulo");
        String xml = carrinho.toXML();
        
        //Para representar o XML utilizamos a clase Entity
        Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);

        Response response = target.path("/carrinhos").request().post(entity);
        Assert.assertEquals("<status>sucesso</status>", response.readEntity(String.class));
		
	}
}
