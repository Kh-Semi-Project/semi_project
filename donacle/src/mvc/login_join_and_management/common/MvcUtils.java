package mvc.login_join_and_management.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;


/**
 * 
 *
 */
public class MvcUtils {
	/**
	 * 1. 단방향 암호화
	 * - 입력값을 고정 길이의 hash값으로 출력, hash값에서 입력값을 유추할 수 없다.
	 * - md5. sha1, sha256, sha512...
	 * 2. 64개 문자열 인코딩
	 * - 알파벳 대소문자, 숫자, +, / 64개 문자를 이용해 인코딩처리
	 * - == 는 끝을 의미하는 문자.
	 */
	public static String getEncryptedPassword(String password) {
		// 1. 암호화
		MessageDigest md = null;
		byte[] encrypted = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
			byte[] input = password.getBytes("utf-8");
			md.update(input);
			encrypted = md.digest();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("암호화전 : " + password);
//		System.out.println("암호화된 이진데이터 : " + new String(encrypted));
		// 2. 인코딩
		Encoder encoder = Base64.getEncoder();
		String encryptedPassword = encoder.encodeToString(encrypted);
		
//		System.out.println("암호화 후 : " + encryptedPassword);
		
		return encryptedPassword;
	}
	/**
	 * 
	 * 
	 */
	public static String getPagebar(int cPage, int numPerPage, int totalContents, String url) {
		StringBuilder pagebar = new StringBuilder();
		
		// 전체 페이지수 
		int totalPage = (int) Math.ceil((double) totalContents / numPerPage);
		
		// 페이지 번호를 클릭했을 때 링크
		String delimeter = url.contains("?") ? "&" : "?";
		url = url + delimeter + "cPage="; // /mvc/admin/memberList?cPage=
		
		// 페이지 바 크기 : 5
		int pagebarSize = 5;
		
		/*
		 *     1 2 3 4 5 다음
		 * 이전 6 7 8 9 10 다음
		 * 이전 11 12
		 * 
		 * pageStart : 시작하는 pageNo - cPage와 pagebarSize에 의해 결정
		 * 
		 */
		int pageStart = (cPage - 1) / pagebarSize * pagebarSize + 1;
		int pageEnd = pageStart  + pagebarSize - 1;
		
		int pageNo = pageStart;
		
		// 1. 이전
		if(pageNo == 1) {
			
		} else {
			pagebar.append("<a href='" + url + (pageNo - 1) + "'>이전</a>\n");
		}
		// 2. pageNo
		while(pageNo <= pageEnd && pageNo <= totalPage) {
			if(pageNo == cPage) {
				// 현재 페이지인 경우 링크를 제공하지 않는다
				pagebar.append("<span class = 'cPage'>" + pageNo + "</span>\n");
			} else {
				// 현재 페이지가 아닌 경우 링크를 제공한다.
				pagebar.append("<a href='" + url + pageNo + "'>" + pageNo + "</a>\n");
				
			}
			pageNo++;
		}
		// 3. 다음
		if (pageNo > totalPage) {
			
		} else {
			pagebar.append("<a href='" + url + pageNo + "'>다음</a>\n");
		}
		return pagebar.toString();
	}
	
	/**
	 * XSS공격대비 < > escaping처리
	 * 
	 * @param s
	 * @return
	 */
	public static String escapeHtml(String s) {
		return s.replaceAll("<", "&lt;").replaceAll(">","&gt;");
	}
	/**
	 * \n 문자 br태그로 변환
	 *  
	 * @param s
	 * @return
	 */
	public static String convertLineFeedToBr(String s) {
		return s.replaceAll("\\n", "<br/>");
	}
	
}
