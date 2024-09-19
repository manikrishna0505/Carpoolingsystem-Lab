package com.mani.carpoolsystem;

import jakarta.servlet.ServletException;
import java.io.IOException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ArrayList;

/**
 * Servlet implementation class CarpoolServlet
 */
public class CarpoolServlet extends HttpServlet {
	 private List<String> rides;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init() throws ServletException {
        rides = new ArrayList<>();
        log("CarpoolServlet initialized with available rides.");
    }
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log("Received request: " + req.getMethod() + " " + req.getRequestURI());
        super.service(req, resp);
    }
    public CarpoolServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.getWriter().println("Carpooling System is up and running!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	 public void destroy() {
	        log("CarpoolServlet is being terminated.");
	    }

}
