package Classes.Entidade;

import Classes.util.String;
import java.util.Collection;
import Classes.util.Postagem;

public class Vendedor implements EntidadeDominio {

	private String cpf;

	private String nome;

	private String email;

	private String telefone;

	private Endereco endereco;

	private Collection<Postagem> postagem;

	private Collection<Automovel> automovel;

}
