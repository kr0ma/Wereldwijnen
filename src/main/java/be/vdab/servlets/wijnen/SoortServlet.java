package be.vdab.servlets.wijnen;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.services.LandService;
import be.vdab.services.SoortService;

/**
 * Servlet implementation class SoortServlet
 */
@WebServlet("/wijnen/soort.htm")
public class SoortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String VIEW = "/WEB-INF/JSP/wijnen/soort.jsp";
	
	private final transient SoortService soortService = new SoortService();
	private final transient LandService landService = new LandService();
	private final static Logger logger = Logger.getLogger(SoortServlet.class.getName());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getQueryString() != null){
			String soortId = request.getParameter("id");
			if (soortId != null){
				try {					
					request.setAttribute("soort", soortService.read(Long.parseLong(soortId)));
				} catch (NumberFormatException ex){
					logger.log(Level.SEVERE, "Some hacker did something!", ex);	
				}				
			}
		}
		request.setAttribute("landen", landService.findAll());
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
