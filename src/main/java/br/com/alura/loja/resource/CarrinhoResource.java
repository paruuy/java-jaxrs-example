package br.com.alura.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;

@Path("carrinhos")
public class CarrinhoResource {
	
	//A / esta explicita carrinhos/id
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca(@PathParam("id") long id) {
	    Carrinho carrinho = new CarrinhoDAO().busca(id);
	    return carrinho.toXML();
	}
	
	
	// Para fazer um teste na linha de comandos fazemos:
	// curl -d "<br.com.alura.loja.modelo.Carrinho>  <produtos>    <br.com.alura.loja.modelo.Produto>      <preco>4000.0</preco>      <id>6237</id>      <nome>Videogame 4</nome>      <quantidade>1</quantidade>    </br.com.alura.loja.modelo.Produto>  </produtos>  <rua>Rua Vergueiro 3185, 8 andar</rua>  <cidade>São Paulo</cidade>  <id>1</id></br.com.alura.loja.modelo.Carrinho>" http://localhost:8080/carrinhos
	@POST
	@Produces(MediaType.APPLICATION_XML)
	public String adiciona(String conteudo) {
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		new CarrinhoDAO().adiciona(carrinho);
        return "<status>sucesso</status>";
	}
}
