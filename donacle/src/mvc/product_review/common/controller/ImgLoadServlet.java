package mvc.product_review.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import mvc.product_review.common.FileUploadPath;
import mvc.product_review.common.model.service.AttachService;
import mvc.product_review.common.vo.Attach;


/**
 * Servlet implementation class BoardCommentEnrollServlet
 */
@WebServlet("/common/imgLoad")
public class ImgLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AttachService attachService = new AttachService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int attachNo = Integer.parseInt(request.getParameter("attachNo"));
		Attach attach = attachService.selectAttach(attachNo);
		response.setContentType("image/jpeg");
		byte[] image = IOUtils.toByteArray(new FileInputStream(new File(FileUploadPath.FILE_SAVE_PATH+"/"+attach.getRenamedFilename())));
		response.getOutputStream().write(image);
	}
}	
