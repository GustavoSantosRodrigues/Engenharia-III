package gestap.controle.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Resultado;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import gestao.controle.web.command.ICommand;
import gestao.controle.web.viewhelper.FornecedorViewHelper;
import gestao.controle.web.viewhelper.IViewHelper;
import gestao.dominio.EntidadeDominio;

/**
 * Servlet implementation class Controle
 */
public class Controle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static String uri = null;
	private static HttpServletRequest request;
	private static String operacao = null;
       
    public Controle() {
        super();
     
    }
    
    
	protected void doProcessRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		uri = request.getRequestURI();

		this.request = request;

		operacao = request.getParameter("operacao");

		Resultado resultado = doProcess();

		vh.setView(resultado, request, response);
	}
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        
        String           uri = request.getRequestURI();
        IViewHelper      vh = vhs.get(uri);
        EntidadeDominio  entidade = vh.getEntidade(request);
        String           operacao = request.getParameter("operacao");
        ICommand         command = commands.get(operacao);
        Resultado        resultado = command.executar(entidade);
        
        vh.setView(resultado, request, response);
           
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
