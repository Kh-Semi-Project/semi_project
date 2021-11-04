package mvc.login_join_and_management.controller.join;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class EmailCheckServelt
 */
@WebServlet("/emailCheck")
public class EmailCheckServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email= request.getParameter("email");
		
		String host = "smtp.gmail.com";
		String user = "donacle3@gmail.com";
		String password = "semiproject12!";
		
		   //SMTP 서버 정보를 설정한다.
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 465);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", host);
        
        //인증 번호 생성기
        StringBuffer temp =new StringBuffer();
        Random rnd = new Random();
		for (int i = 0; i < 10; i++) {
			int rIndex = rnd.nextInt(3);
			switch (rIndex) {
			case 0:
				// a-z
				temp.append((char) ((int) (rnd.nextInt(26)) + 97));
				break;
			case 1:
				// A-Z
				temp.append((char) ((int) (rnd.nextInt(26)) + 65));
				break;
			case 2:
				// 0-9
				temp.append((rnd.nextInt(10)));
				break;
			}
        }
		
        String AuthenticationKey = temp.toString();
        System.out.println(AuthenticationKey);
        
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user,password);
            }
        });
        
        //email 전송
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(user, "Donacle"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            
            //메일 제목
            msg.setSubject("Donacle에서 인증번호를 보내드립니다.");
            //메일 내용
            msg.setText("인증번호 : " + temp + "\n"
            		+ "회원가입 페이지에서 인증번호를 정확히 입력해주시기 바랍니다. :)");
            
            Transport.send(msg);
            
        }catch (Exception e) {
            e.printStackTrace();// TODO: handle exception
        }
    
       response.setContentType("text/html; charset=utf-8");
       PrintWriter out = response.getWriter();
       
       out.print(AuthenticationKey);
		
		
	}
}
