package servlet.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import servlet.model.BookDAOImpl;
import servlet.model.BookVO;
import servlet.model.UserDAOImpl;
import servlet.model.UserVO;

@WebServlet(urlPatterns = "/front.do", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 요청이 어디에서 온 요청인지를 command 값 받습니다.
		String command = request.getParameter("command");
		String path = "index.jsp";
		if (command.equals("login")) {
			path = login(request, response);
		} else if (command.equals("register")) {
			path = register(request, response);
		} else if (command.equals("ShowAllBooks")) {
			path = showAllBooks(request, response);
		} else if (command.equals("logout")) {
			path = logout(request, response);
		} else if (command.equals("search")) {
			path = search(request, response);
		}

		request.getRequestDispatcher(path).forward(request, response);
	}

	private String search(HttpServletRequest request, HttpServletResponse response) {
		String path = "login.jsp";
		String method = request.getParameter("method");
		
		if(method.equals("all")) {
			path = showAllBooks(request, response);
		}else if(method.equals("title")) {
			path = findBooksByTitle(request, response);
		}else if(method.equals("publisher")) {
			path = findBooksByPublisher(request, response);
		}else if(method.equals("price")) {
			path = findBooksByPrice(request, response);
		}
			
		return path;
	}

	private String findBooksByPrice(HttpServletRequest request, HttpServletResponse response) {
		String path = "login.jsp";
		try {
			String price = request.getParameter("content");
			ArrayList<BookVO> rvo = BookDAOImpl.getInstance().findBooksByTitle(price);
			HttpSession session = request.getSession();
			session.setAttribute("vo", rvo);
			path = "showAll.jsp";
		} catch (SQLException e) {
			System.out.println(e);
		}
		return path;
	}

	private String findBooksByPublisher(HttpServletRequest request, HttpServletResponse response) {
		String path = "login.jsp";
		try {
			String publisher = request.getParameter("content");
			ArrayList<BookVO> rvo = BookDAOImpl.getInstance().findBooksByTitle(publisher);
			HttpSession session = request.getSession();
			session.setAttribute("vo", rvo);
			path = "showAll.jsp";
		} catch (SQLException e) {
			System.out.println(e);
		}
		return path;
	}

	private String findBooksByTitle(HttpServletRequest request, HttpServletResponse response) {
		String path = "login.jsp";
		try {
			String title = request.getParameter("content");
			ArrayList<BookVO> rvo = BookDAOImpl.getInstance().findBooksByTitle(title);
			HttpSession session = request.getSession();
			session.setAttribute("vo", rvo);
			path = "showAll.jsp";
		} catch (SQLException e) {
		}
		return path;
	}

	private String logout(HttpServletRequest request, HttpServletResponse response) {
		String path = "index.jsp";
		HttpSession session = request.getSession();
		if (session.getAttribute("vo") != null) {// 로그인한 상태라면
			session.invalidate();
		}
		path = "logout.jsp";
		return path;
	}

	private String showAllBooks(HttpServletRequest request, HttpServletResponse response) {
		String path = "login.jsp";
		try {
			ArrayList<BookVO> rvo = BookDAOImpl.getInstance().showAllBook();

			HttpSession session = request.getSession();
			session.setAttribute("vo", rvo);
			path = "showAll.jsp";
		} catch (SQLException e) {
			System.out.println("e");
		}

		return path;
	}

	private String register(HttpServletRequest request, HttpServletResponse response) {
		String path = "login.jsp";
		String[] isbn = request.getParameterValues("isbn");
		String title = request.getParameter("title");
		String catalogue = request.getParameter("catalogue");
		String nation = request.getParameter("nation");
		String author = request.getParameter("author");
		int price = Integer.parseInt(request.getParameter("price"));
		String currency = request.getParameter("currency");
		String description = request.getParameter("description");
		String publish_date = request.getParameter("publish_date");
		String publisher = request.getParameter("publisher");

		description = description.equals("") ? "향후기입" : description;
		publish_date = publish_date.equals("") ? "0000-00-00" : publish_date;
		publisher = publisher.equals("") ? "향후기입" : publisher;

		BookVO pvo = new BookVO(isbn, title, catalogue, nation, publish_date, publisher, author, price, currency,
				description);
		System.out.println(pvo);
		try {
			BookDAOImpl.getInstance().registerBook(pvo);
			path = "result.jsp";
		} catch (SQLException e) {
			System.out.println(e);
		}
		return path;
	}

	private String login(HttpServletRequest request, HttpServletResponse response) {
		String path = "error.jsp";
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		try {
			UserVO rvo = UserDAOImpl.getInstance().login(id, password);

			HttpSession session = request.getSession();
			if (rvo != null) {
				session.setAttribute("vo", rvo);

				path = "loginSuccess.jsp";
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return path;
	}

}
