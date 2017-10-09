package org.harvey.solve.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.harvey.solve.service.FibonacciSequenceFunction;
import org.harvey.solve.service.impl.FibonacciSequenceFunctionImpl;


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
		FibonacciSequenceFunction fibonacciSequenceFunction = new FibonacciSequenceFunctionImpl();
		List<BigDecimal> list = fibonacciSequenceFunction
				.getSequenceResult(length);

		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		session.setAttribute("result", list);
		response.sendRedirect("/solve/jsp/FibonacciResult.jsp");
		//request.getRequestDispatcher("/jsp/FibonacciResult.jsp").forward(request, response);
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
