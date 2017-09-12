package com.harvey.solve.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.harvey.solve.function.FibonacciSequenceFunction;

/**
 * Servlet implementation class FibonacciServlet
 */
public class FibonacciServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public FibonacciServlet() {
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
		int length = Integer.valueOf(request.getParameter("length"));
		List<BigDecimal> list = FibonacciSequenceFunction.getSequenceResult(length);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Fibonacci Result</title>");
		out.println("<style type=\"text/css\">.span-result{font-size:20px;font-weight:bold}</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>");
		out.println("<span class=\"span-result\">Fibonacci Sequence:</span>");

		for(int i = 0;i<list.size();i++){
			out.println("<br/><span class=\"span-result\">" + list.get(i)
					+ "</span>");
		}
	
		out.println("</p>");
		response.getWriter().println("</body>");
		response.getWriter().println("</html>");

		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
