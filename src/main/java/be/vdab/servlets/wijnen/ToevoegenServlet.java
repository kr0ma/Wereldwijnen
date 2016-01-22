package be.vdab.servlets.wijnen;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.entities.Wijn;
import be.vdab.services.LandService;
import be.vdab.services.WijnService;

/**
 * Servlet implementation class ToevoegenServlet
 */
@WebServlet("/wijn/toevoegen.htm")
public class ToevoegenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String VIEW = "/WEB-INF/JSP/wijnen/toevoegen.jsp";
	private static final String MANDJE = "mandje";
	
	private final transient WijnService wijnService = new WijnService();
	private final transient LandService landService = new LandService();
	
	private final static Logger logger = Logger.getLogger(ToevoegenServlet.class.getName());
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getQueryString() != null){
			String wijnId = request.getParameter("id");
			if (wijnId != null){
				try{
					Wijn wijn = wijnService.read(Long.parseLong(wijnId)); 
					request.setAttribute("wijn", wijn);
					HttpSession session = request.getSession(false);
					if (session != null){
						@SuppressWarnings("unchecked")
						Map<Long, Integer> mandje = (Map<Long, Integer>) session.getAttribute(MANDJE);	
						if (mandje != null) {
							if (mandje.containsKey(wijn.getId())) {
								request.setAttribute("aantal",mandje.get(wijn.getId()));
							}
						}
					}					
				} catch (NumberFormatException ex){
					logger.log(Level.SEVERE, "Some hacker did something!", ex);	
				}				
			}
		}
		request.setAttribute("landen", landService.findAll());
		request.getRequestDispatcher(VIEW).forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		int aantal = 0;
		long wijnId = 0;
		
		if (request.getParameter("aantal") != null){			
			try {
				aantal = Integer.parseInt(request.getParameter("aantal"));
				if (aantal <= 0){
					fouten.put("aantal", "tik een positief getal");
				}
			} catch (NumberFormatException ex) {
				fouten.put("aantal", "tik een positief getal");
			}
			
			if (request.getParameter("wijnID") != null){
				try {
					wijnId = Long.parseLong(request.getParameter("wijnID"));
				} catch (NumberFormatException ex){
					fouten.put("wijn", "Wijn niet gevonden");
					logger.log(Level.SEVERE, "Some hacker may have done something!", ex);	
				}
			} else {
				fouten.put("wijn", "Wijn niet gevonden");
			}
		}
		
		if (!fouten.isEmpty()){
			request.setAttribute("fouten", fouten);
			request.setAttribute("wijn", wijnService.read(wijnId));
			request.getRequestDispatcher(VIEW).forward(request,response);
		} else {
			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			Map<Long, Integer> mandje = (Map<Long, Integer>) session.getAttribute(MANDJE);
			if (mandje == null) {
				mandje = new HashMap<Long, Integer>();
			}
			mandje.put(wijnId, aantal);
			session.setAttribute(MANDJE, mandje);
			response.sendRedirect(request.getContextPath());
		}		
	}

}
