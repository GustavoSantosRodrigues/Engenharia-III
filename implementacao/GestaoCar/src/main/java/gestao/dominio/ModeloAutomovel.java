package gestao.dominio;

import java.util.Collection;

public class ModeloAutomovel extends EntidadeDominio {

	private String nome;

	private String ano_lancamento;

	private String potencia;

	private String TipoCambio;

	private int acento;

	private int portas;

	private float media_preco;

	private String combustivel;

	private float kmLitro;

	private Collection<Automovel> automovel;

	private Marca marca;

	private TipoAutomovel tipoAutomovel;

	public class OpcionaisVeiculo implements EntidadeDominio {

		private boolean aquecedor;

		private boolean arcondicionado;

		private boolean airBag;

		private boolean alarme;

		private boolean direcaoHidraulica;

		private boolean freioABS;

		private boolean tracao4x4;

		private Automovel automovel;

	}

}
