package mvc.product_review.comment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


import mvc.product_review.comment.model.service.CommentService;
import mvc.product_review.comment.model.vo.Comment;


/**
 * Servlet implementation class BoardCommentEnrollServlet
 */
@WebServlet("/comment/commentEnroll")
public class CommentEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CommentService commentService = new CommentService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		id = "test0";

		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		String commentsType = request.getParameter("commentsType");
		String commentsTitle = request.getParameter("commentsTitle");
		int pCommentsNo = Integer.parseInt(request.getParameter("pCommentsNo"));
		
		Comment comment = new Comment(reviewNo,id,commentsType,commentsTitle,pCommentsNo);
		int commentsNo = commentService.insertComment(comment);
		comment = commentService.selectComment(commentsNo);
		comment.setCommentsNo(commentsNo);
		comment.setReviewNo(reviewNo);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(new Gson().toJson(comment));
	}
}	
