package gestao.controller.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gestao.controle.web.command.AbstractCommand;
import gestao.controller.IFachada;
import gestao.dao.IDAO;
import gestao.dao.impl.VendedorDAO;
import gestao.dominio.EntidadeDominio;
import gestao.dominio.Vendedor;
import gestao.negocio.IStrategy;
import gestao.util.Resultado;


public class Fachada implements IFachada {

	private AbstractCommand abstractCommand;

	
	private Map<String, IDAO> daos;

	private Map<String, Map<String, List<IStrategy>>> rns;
	
	private Resultado resultado;
	
	VendedorDAO vendedorDAO = new VendedorDAO();

	
	
	
	public Fachada(){

		daos = new HashMap<String, IDAO>();

		rns = new HashMap<String, Map<String, List<IStrategy>>>();
		
		
		VendedorDAO vendedorDAO = new VendedorDAO();
		
		daos.put(Vendedor.class.getName(), vendedorDAO);
		
	}
	
	
	
	
	public Resultado salvar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "SALVAR");
		
		IDAO dao = daos.get(nmClasse);
		dao.salvar(entidade);
		List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
		entidades.add(0, entidade);
		resultado.setEntidades(entidades);
		
		
		
//		if(msg == null){
//			IDAO dao = daos.get(nmClasse);
//			try {
//				dao.salvar(entidade);
//				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
//				entidades.add(0, entidade);
//				resultado.setEntidades(entidades);
//			} catch (SQLException e) {
//				e.printStackTrace();
//				resultado.setMsg("N�o foi poss�vel realizar o registro!");
//				
//			}
//		}else{
//			resultado.setMsg(msg);
//					
//			
//		}
		
		return resultado;
	}


	public Resultado alterar(EntidadeDominio entidade) {
		
		return null;
	}

	public Resultado excluir(EntidadeDominio entidade) {
		
		return null;
	}

	public Resultado consultar(EntidadeDominio entidade) {
		
		return null;
	}

	public Resultado visualizar(EntidadeDominio entidade) {
		
		return null;
	}
	
	
	
	private String executarRegras(EntidadeDominio entidade, String operacao){
		String nmClasse = entidade.getClass().getName();		
		StringBuilder msg = new StringBuilder();
		
		Map<String, List<IStrategy>> regrasOperacao = rns.get(nmClasse);
		
		
		if(regrasOperacao != null){
			List<IStrategy> regras = regrasOperacao.get(operacao);
			
			if(regras != null){
				for(IStrategy s: regras){			
					String m = s.processar(entidade);			
					
					if(m != null){
						msg.append(m);
						msg.append("\n");
					}			
				}	
			}			
			
		}
		
		if(msg.length()>0)
			return msg.toString();
		else
			return null;
	}
	
	

}
