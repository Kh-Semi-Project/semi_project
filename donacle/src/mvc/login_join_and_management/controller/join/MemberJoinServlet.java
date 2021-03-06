package mvc.login_join_and_management.controller.join;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.login_join_and_management.common.MvcUtils;
import mvc.login_join_and_management.model.service.MemberService;
import mvc.login_join_and_management.model.vo.Address;
import mvc.login_join_and_management.model.vo.Member;

/**
 * Servlet implementation class MemberJoinController
 */
@WebServlet("/memberJoin")
public class MemberJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login_join_and_management/join/joinForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		String password = MvcUtils.getEncryptedPassword(request.getParameter("password"));
		String name = request.getParameter("name");
		String email = request.getParameter("emailId");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		String _birthday = request.getParameter("birthday");
		String kind = request.getParameter("kind") ;
		
		
		Date birthday = null;
		if(_birthday != null && !"".equals(_birthday)) {
			SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyyMMdd");
			java.util.Date beforeDate = null;
			
			try {
				beforeDate = beforeFormat.parse(_birthday);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-MM-dd");
			String format = afterFormat.format(beforeDate);
			birthday = Date.valueOf(format);
		}
		
		Member member = new Member(id, password, name, email, phone, gender, birthday, kind, null, null);
		
		if(MemberService.MEMBER_KIND_CUSTOMER.equals(kind)) {
			
			String zipCode = request.getParameter("zipCode");
			String address = request.getParameter("address");
			String _detailAddress = request.getParameter("detailAddress");
			String subAddress = request.getParameter("subAddress");
			
			String detailAddress = _detailAddress + subAddress;
			Address addr = new Address(id, zipCode, address, detailAddress);
			
			member.setAddress(addr);
		}
		System.out.println("member@joinServlet = " + member);
		
		int result = memberService.insertMember(member);
		String msg = "";
		HttpSession session = request.getSession();
		if(result > 0) {
			msg = "???????????????! ?????? ????????? ?????????????????????. ????????? ???????????? ???????????????.";
			session.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/memberLogin");
		} else {
			msg = "?????? ????????? ??????????????????. ???????????? ?????? ?????? ??? ??????????????? ?????? ????????????.";
			session.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/memberJoin");
		}
		
	}

}
