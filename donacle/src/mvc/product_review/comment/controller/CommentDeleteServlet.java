package mvc.product_review.comment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.product_review.comment.model.service.CommentService;


/**
 * Servlet implementation class BoardCommentEnrollServlet
 */
@WebServlet("/comment/commentDelete")
public class CommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentService commentService = new CommentService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int commentsNo = Integer.parseInt(request.getParameter("commentsNo"));
		int commentsType = Integer.parseInt(request.getParameter("commentsType"));
		int res = commentService.deleteComments(commentsNo,commentsType);
		
		response.getWriter().write(res);
	}
}	
