package com.harvey.solve.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.harvey.solve.function.JosephProblemFunction;

/**
 * Servlet implementation class FibonacciServlet
 */

public class JosephServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public JosephServlet() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int startIndex = Integer.valueOf(request.getParameter("start"));
		int interval = Integer.valueOf(request.getParameter("interval"));
		String circle = request.getParameter("circle");
		
		String lastName = JosephProblemFunction.getFinalElement(circle, startIndex, interval);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Joseph Result</title>");
		out.println("<style type=\"text/css\">.span-result{font-size:20px;font-weight:bold}</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>");
		out.println("The last person is " + lastName);
		out.println("</p>");
		response.getWriter().println("</body>");
		response.getWriter().println("</html>");

		out.flush();
		out.close();
	}

}
