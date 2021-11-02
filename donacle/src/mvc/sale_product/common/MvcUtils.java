package mvc.sale_product.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

public class MvcUtils {

	
	/**
	 * 
	 *  
	 * @param cPage
	 * @param numPerPage
	 * @param totalContents
	 * @param url
	 * @return
	 */
	public static String getPagebar(int cPage, int numPerPage, int totalContents, String url, int category) {
		StringBuilder pagebar = new StringBuilder();
		String categorys = "&category=" + category;
		// 전체페이지수 
		int totalPage = (int) Math.ceil((double) totalContents / numPerPage);
		
		// 페이지번호를 클릭했을때 링크
		url = url + "?cPage="; // /sale_product/productwritingList?cPage=
		
		// 페이지바크기 
		int pagebarSize = 5;
		
		/* 
		 		1 2 3 4 5 다음
		 		
		 	이전	6 7 8 9 10 다음
		 	
		 	이전 11 12
		 	
		 	pageStart : 시작하는 pageNo
		 		- cPage와 pagebarSize에 의해 결정
		 */
		int pageStart = (cPage - 1) / pagebarSize * pagebarSize + 1;
		int pageEnd = pageStart + pagebarSize - 1;
		
		int pageNo = pageStart;
		
		// 1.이전
		if(pageNo == 1) {
			
		}
		else {
			pagebar.append("<a href='" + url + (pageNo - 1) + categorys+"'>prev</a>\n");
		}
		
		// 2.pageNo
		while(pageNo <= pageEnd && pageNo <= totalPage) {
			if(pageNo == cPage) {
				// 현재페이지인 경우 링크 제공안함.
				pagebar.append("<span class='cPage'>" + pageNo + "</span>");
			}
			else {
				// 현재페이지가 아닌 경우 링크를 제공.
				pagebar.append("<a href='" + url + pageNo + categorys+"'>" + pageNo + "</a>");
			}
			
			pageNo++;
		}
		
		// 3.다음
		if(pageNo > totalPage) {
			
		}
		else {
			pagebar.append("<a href='" + url + pageNo + "'>next</a>\n");
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
