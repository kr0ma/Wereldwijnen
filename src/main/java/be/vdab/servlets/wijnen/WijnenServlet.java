package be.vdab.servlets.wijnen;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Land;
import be.vdab.services.LandService;
import be.vdab.services.SoortService;

/**
 * Servlet implementation class WijnenServlet
 */
@WebServlet("/wijnen.htm")
public class WijnenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String VIEW = "/WEB-INF/JSP/wijnen.jsp";
	
	private final transient LandService landService = new LandService();
	private final transient SoortService soortService = new SoortService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getQueryString() != null){
			String landId = request.getParameter("landId");
			if (landId != null){
				try{
					Land land = landService.read(Long.parseLong(landId));
					request.setAttribute("land", land);
					String soortId = request.getParameter("soortId");
					if (soortId != null){
						request.setAttribute("soort", soortService.read(Long.parseLong(soortId)));
					}
				} catch (NumberFormatException ex){
					request.setAttribute("error", "Geen correct land gekozen");
				}
			}
		}
		request.setAttribute("landen", landService.findAll());
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
