package com.mani.carpoolsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Servlet implementation class CarpoolServlet
 */
//WebServlet("/Carpool")
public class CarpoolServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Thread-safe list to store rides using CopyOnWriteArrayList
    private List<String> rides = new CopyOnWriteArrayList<>();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarpoolServlet() {
        super();
    }

    /**
     * Handles GET request to display available rides and offer form.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        // HTML structure to display available rides
        resp.getWriter().println("<html><head><title>Carpooling System</title></head><body>");
        resp.getWriter().println("<h1 style='color: #0066cc;'>Available Carpool Rides</h1>");

        // Display the list of rides in a thread-safe manner
        if (rides.isEmpty()) {
            resp.getWriter().println("<p style='color: #cc0000;'>No rides available at the moment.</p>");
        } else {
            resp.getWriter().println("<ul>");
            for (String ride : rides) {
                resp.getWriter().println("<li>" + ride + "</li>");
            }
            resp.getWriter().println("</ul>");
        }

        // Display form to offer a new ride
        resp.getWriter().println("<h2 style='color: #0066cc;'>Offer a New Carpool Ride</h2>");
        resp.getWriter().println("<form method='POST' action='Carpool'>");
        resp.getWriter().println("Start Location: <input type='text' name='start' required><br><br>");
        resp.getWriter().println("Destination: <input type='text' name='destination' required><br><br>");
        resp.getWriter().println("Seats Available: <input type='number' name='seats' min='1' required><br><br>");
        resp.getWriter().println("<input type='submit' value='Offer Ride' style='background-color: #0066cc; color: white;'>");
        resp.getWriter().println("</form>");

        // End of HTML
        resp.getWriter().println("</body></html>");
    }

    /**
     * Handles POST request to offer a new carpool ride.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve form parameters
        String start = req.getParameter("start");
        String destination = req.getParameter("destination");
        String seats = req.getParameter("seats");

        // Business logic: Add new ride to the thread-safe list
        String newRide = "From " + start + " to " + destination + " - Seats: " + seats;
        rides.add(newRide); // Thread-safe add operation

        // Redirect to the GET method to display updated list of rides
        resp.sendRedirect(req.getContextPath() + "/Carpool");
    }
}
