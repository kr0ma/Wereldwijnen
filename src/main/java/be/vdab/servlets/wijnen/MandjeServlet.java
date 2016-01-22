package be.vdab.servlets.wijnen;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.entities.Bestelbon;
import be.vdab.entities.Wijn;
import be.vdab.services.BestelbonService;
import be.vdab.services.LandService;
import be.vdab.services.WijnService;
import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.Bestelbonlijn;

/**
 * Servlet implementation class MandjeServlet
 */
@WebServlet("/mandje/bevestigen.htm")
public class MandjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String VIEW = "/WEB-INF/JSP/wijnen/mandje.jsp";
	private static final String MANDJE = "mandje";
	private static final String BESTELBON = "bestelbon";
	
	private static final String REDIRECT_URL = "%s/overzicht.htm?id=%s";
	
	
	private static final String NAAM = "naam";
	private static final String STRAAT = "straat";
	private static final String HUISNR = "huisNr";
	private static final String POSTCODE = "postCode";
	private static final String GEMEENTE = "gemeente";
	private static final String BESTELWIJZE = "bestelwijze";
	
	private final transient WijnService wijnService = new WijnService();
	private final transient BestelbonService bestelbonService = new BestelbonService();
	private final transient LandService landService = new LandService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null){
			@SuppressWarnings("unchecked")
			Map<Long, Integer> mandje = (Map<Long, Integer>) session.getAttribute(MANDJE);
			List<Wijn> wijnInMandje = wijnService.findByIDS(mandje.keySet());
			Bestelbon bestelbon = new Bestelbon();
			for (Wijn wijn: wijnInMandje){
				bestelbon.addBestelbonlijn(new Bestelbonlijn(wijn, mandje.get(wijn.getId())));
			}
			request.setAttribute(BESTELBON, bestelbon);
		}
		request.setAttribute("landen", landService.findAll());
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();	
		String naam = request.getParameter(NAAM);
		String straat = request.getParameter(STRAAT);
		String huisnr = request.getParameter(HUISNR);
		String postcode = request.getParameter(POSTCODE);
		String gemeente = request.getParameter(GEMEENTE);
		Boolean bestelwijze = true;
		
		if (!Bestelbon.isInputValid(naam)){
			fouten.put(NAAM ,NAAM + " niet ingevuld");
		} 
		
		if (!Adres.isInputValid(straat)){
			fouten.put(STRAAT,STRAAT + " niet ingevuld");
		}
		if (!Adres.isInputValid(huisnr)){
			fouten.put(HUISNR,HUISNR + " niet ingevuld");
		}
		if (!Adres.isInputValid(postcode)){
			fouten.put(POSTCODE,POSTCODE + " niet ingevuld");
		}
		if (!Adres.isInputValid(gemeente)){
			fouten.put(GEMEENTE,GEMEENTE + " niet ingevuld");
		}
		
				
		if (!Bestelbon.isBestelwijzeValid(request.getParameter(BESTELWIJZE))){
			fouten.put(BESTELWIJZE, BESTELWIJZE + " niet ok");
		} else {			
			if (request.getParameter(BESTELWIJZE).equals("0")){
				bestelwijze = false;
			}			
		}
		
		if (fouten.isEmpty()){
			HttpSession session = request.getSession(false);
			if (session != null){
				@SuppressWarnings("unchecked")
				Map<Long, Integer> mandje = (Map<Long, Integer>) session.getAttribute(MANDJE);
				Adres adres = new Adres(straat, huisnr, postcode, gemeente);
				Bestelbon bestelBon = new Bestelbon(new Date(), naam ,adres, bestelwijze);
				try {
					bestelbonService.create(bestelBon, mandje);
					session.removeAttribute(MANDJE);
					response.sendRedirect(String.format(REDIRECT_URL, request.getContextPath(), bestelBon.getId()));
				} catch (RollbackException ex){
					fouten.put("bestelling", "Bestelling niet bevestigd, probeer opnieuw");
					request.setAttribute("landen", landService.findAll());
					request.setAttribute("fouten", fouten);
					request.getRequestDispatcher(VIEW).forward(request, response);
				}
				
			}
		} else {
			HttpSession session = request.getSession(false);
			if (session != null){
				@SuppressWarnings("unchecked")
				Map<Long, Integer> mandje = (Map<Long, Integer>) session.getAttribute(MANDJE);
				List<Wijn> wijnInMandje = wijnService.findByIDS(mandje.keySet());
				Bestelbon bestelbon = new Bestelbon();
				for (Wijn wijn: wijnInMandje){
					bestelbon.addBestelbonlijn(new Bestelbonlijn(wijn, mandje.get(wijn.getId())));				
				}
				request.setAttribute(BESTELBON, bestelbon);
			}
			request.setAttribute("landen", landService.findAll());
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

}
