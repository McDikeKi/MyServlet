package com.harvey.function;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		final int FIRST_NUM = 1;
		final BigDecimal FIRST_VALUE = new BigDecimal(0);
		final int SECOND_NUM = 1;
		final BigDecimal SECOND_VALUE = new BigDecimal(1);
		int length = Integer.valueOf(request.getParameter("length"));

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

		if (length == FIRST_NUM) {
			out.println("<br/><span class=\"span-result\">" + FIRST_VALUE
					+ "</span>");
		} else if (length == SECOND_NUM) {
			out.println("<br/><span class=\"span-result\">" + FIRST_VALUE
					+ "</span>");
			out.println("<br/><span class=\"span-result\">" + SECOND_VALUE
					+ "</span>");
		} else {
			out.println("<br/><span class=\"span-result\">" + FIRST_VALUE
					+ "</span>");
			out.println("<br/><span class=\"span-result\">" + SECOND_VALUE
					+ "</span>");
			BigDecimal previous0 = FIRST_VALUE;
			BigDecimal previous1 = SECOND_VALUE;
			BigDecimal current;
			for (int i = 1; i < length - 1; i++) {
				current = previous0.add(previous1);
				out.println("<br/><span class=\"span-result\">" + current
						+ "</span>");
				previous0 = previous1;
				previous1 = current;
			}
			out.println("</p>");
			response.getWriter().println("</body>");
			response.getWriter().println("</html>");

			out.flush();
			out.close();
		}
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
