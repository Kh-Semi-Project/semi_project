package mvc.sale_product.product.model.dao;

import static mvc.sale_product.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import mvc.login_join_and_management.model.vo.Address;
import mvc.sale_product.product.model.vo.BuyAddress;
import mvc.sale_product.product.model.vo.Product;
import mvc.sale_product.product.model.vo.ProductBuy;
import mvc.sale_product.product.model.vo.ProductWriting;
import mvc.sale_product.product.model.vo.ProductWritingQuestion;

public class ProductDao {
	private Properties prop = new Properties();

	//properties에 한번은 연결해야 연결 가능
	public ProductDao() {
		String filepath = ProductDao.class.getResource("/sql/sale_product/product-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//제품 판매글 전체 데이터 수
	public int selectTotalContents(Connection conn, int category) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "";
		if(category == 0)
			sql = prop.getProperty("selectTotalContents");
		else
			sql = prop.getProperty("selectCategoryTotalContents");
		
		int totalContents = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			if(category != 0)
				pstmt.setInt(1, category);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContents = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContents;
	}
	
	//전체 제품 판매글 list 데이터 가져오기
	public List<ProductWriting> selectProductWritingList(Connection conn, int start, int end, int category) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "";
		if(category == 0) {
			sql = prop.getProperty("selectProductWritingList");
		}else {
			sql = prop.getProperty("selectProductWritingCategoryList");
		}
		List<ProductWriting> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			if(category == 0) {
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			}else {
				pstmt.setInt(1, category);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			}
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				// 테이블 record 1 -> VO객체 1
				ProductWriting pw = new ProductWriting();
				pw.setId(rset.getString("id"));
				pw.setProduct_writing_code(rset.getInt("product_writing_code"));
				pw.setProduct_code(rset.getInt("product_code"));
				pw.setRead_count(rset.getInt("read_count"));
				pw.setProduct_writing_date(rset.getDate("product_writing_date"));
				pw.setCategory_code(rset.getInt("category_code"));
				pw.setProduct_name(rset.getString("product_name"));
				pw.setProduct_price(rset.getInt("product_price"));
				pw.setProduct_img(rset.getString("product_img"));
				pw.setProduct_count(rset.getInt("product_count")); //남은 제품 개수
				pw.setCategory_name(rset.getString("category_name"));
				pw.setShipping_fee(rset.getInt("shipping_fee"));
				System.out.println(pw);
				list.add(pw);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	//물건 조회수 데이터 추가
	public int updateReadCount(Connection conn, int pw_code) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateReadCount");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pw_code);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	//클릭 제품 판매글 데이터 가져오기
	public ProductWriting selectOneProductWriting(Connection conn, int pw_code) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOneProductWriting");
		ProductWriting pw = new ProductWriting();
		System.out.println("dao@selectOneProductWriting@code : " + pw_code);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pw_code);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				pw.setId(rset.getString("id"));
				pw.setProduct_writing_code(rset.getInt("product_writing_code"));
				pw.setRead_count(rset.getInt("read_count")); // 조회수
				pw.setProduct_writing_date(rset.getDate("product_writing_date"));
				pw.setProduct_name(rset.getString("product_name"));
				pw.setCategory_code(rset.getInt("CATEGORY_CODE"));
				pw.setProduct_price(rset.getInt("product_price"));
				pw.setProduct_img(rset.getString("product_img"));
				pw.setProduct_count(rset.getInt("product_count")); //남은 제품 개수
				pw.setProduct_code(rset.getInt("product_code"));
				pw.setProduct_content(rset.getString("product_content")); //제품 정보
				pw.setShipping_fee(rset.getInt("shipping_fee"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return pw;
	}

	//문의 댓글 가져오기
	public List<ProductWritingQuestion> selectCommentList(Connection conn, int pw_code) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectQCommentList");
		List<ProductWritingQuestion> commentList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pw_code);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ProductWritingQuestion pwq = new ProductWritingQuestion();
				pwq.setProduct_question_code(rset.getInt("product_comment_code"));
				pwq.setComment_level(rset.getInt("comment_level"));
				pwq.setComment_date(rset.getDate("comment_date"));
				pwq.setComment_secret(rset.getString("comment_secret"));
				pwq.setComment_ref(rset.getInt("comment_ref"));
				pwq.setId(rset.getString("id"));
				pwq.setProduct_writing_code(rset.getInt("product_writing_code"));
				pwq.setComment_content(rset.getString("comment_content"));
				commentList.add(pwq);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return commentList;
	}

	//제품 주문 데이터 추가
	public int insertproductBuy(Connection conn, ProductBuy pb) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertproductBuy");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			//pb 값들 추가하기
			//values(?,?,?,?,default,default,default,?,?)
//			pstmt.setInt(1,pb.getProduct_buy_code());
			pstmt.setInt(1,pb.getProduct_buy_count());
			pstmt.setInt(2,pb.getProduct_buy_price());
			pstmt.setInt(3,pb.getProduct_donate_price());
			pstmt.setString(4,pb.getId());
			pstmt.setInt(5,pb.getProduct_code());
			pstmt.setInt(6,pb.getPrice_sum());
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	//구매 제품 정보 가져오기
	public ProductBuy selectproductBuyInfo(Connection conn, ProductBuy pb) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectProductInfo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pb.getProduct_code());
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				pb.setProduct_img(rset.getString("product_img"));
				pb.setProduct_code(rset.getInt("product_code"));
				pb.setProduct_name(rset.getString("product_name"));
				pb.setProduct_price(rset.getInt("product_price"));
				pb.setShipping_fee(rset.getInt("shipping_fee"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return pb;
	}

	//구매했던 제품 출력하기
	public List<BuyAddress> selectProductBuyList(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectProductBuyList");
		List<BuyAddress> productBuyList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				BuyAddress pb = new BuyAddress();
				pb.setProduct_img(rset.getString("product_img"));
				pb.setProduct_code(rset.getInt("product_code"));
				pb.setProduct_name(rset.getString("product_name"));
				pb.setProduct_donate_price(rset.getInt("product_donate_price"));
				pb.setProduct_buy_code(rset.getInt("product_buy_code"));
				pb.setProduct_buy_count(rset.getInt("product_buy_count"));
				pb.setProduct_buy_date(rset.getDate("product_buy_date"));
				pb.setProduct_receipt_yn(rset.getString("PRODUCT_RECEIPT_YN"));
				pb.setBuy_writing_yn(rset.getString("product_buy_writing_yn"));
				pb.setProduct_shipping_status(rset.getString("PRODUCT_SHIPPING_STATUS"));
				pb.setPrice_sum(rset.getInt("PRICE_SUM"));
				pb.setAddress(rset.getString("address"));
				pb.setDetail_address(rset.getString("detail_address"));
				pb.setZip_code(rset.getString("zip_code"));
				
				productBuyList.add(pb);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return productBuyList;
	}

	//수령완료 버튼을 클릭했을때 update
	public int updateProductOrderCheck(Connection conn, int product_buy_code) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateProductOrderCheck");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,product_buy_code);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	//주문했던 제품 삭제
	public int deleteProductBuy(Connection conn, String[] codes) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteproductBuy");
		String sql_plus = "";
		for(int i = 0; i < codes.length ; i++) {
			sql_plus += (i != codes.length-1 ?  "? ," : "?");
		}
		System.out.println("sql_plus@"+sql_plus);
		sql = sql.replace("#", sql_plus);			
		int result = 0;
		System.out.println("deleteProductBuy@sql@"+sql);
		System.out.println("codes.length : "+ codes.length);
		try {
			pstmt = conn.prepareStatement(sql);
			//pb 값들 추가하기
			//values(?,?,?,?,default,default,default,?,?)
			for(int i = 0; i < codes.length ; i++) {
				pstmt.setInt(i+1,Integer.parseInt(codes[i]));
			}
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	//제품 코드 수정 필요 
	public int insertProduct(Connection conn, Product p) {
		PreparedStatement pstmt = null;
//		String sql = prop.getProperty("insertProducttest");	
		String sql = prop.getProperty("insertProduct");	
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,p.getCategory_code());
			pstmt.setString(2,p.getProduct_name());
			pstmt.setInt(3,p.getProduct_price());
			pstmt.setString(4,p.getProduct_img());
			pstmt.setInt(5,p.getProduct_count());
			pstmt.setString(6,p.getId());
			pstmt.setString(7,p.getProduct_content());
			pstmt.setInt(8,p.getShipping_fee());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	//제품 리스트 출력
	public List<ProductWriting> selectAllProductList(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectProductAllList");	
		List<ProductWriting> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				// 테이블 record 1 -> VO객체 1
				ProductWriting pw = new ProductWriting();
				pw.setId(rset.getString("id"));
				pw.setProduct_writing_code(rset.getInt("product_writing_code"));
				pw.setProduct_code(rset.getInt("product_code"));
				pw.setRead_count(rset.getInt("read_count"));
				pw.setProduct_writing_date(rset.getDate("product_writing_date"));
				pw.setCategory_code(rset.getInt("category_code"));
				pw.setProduct_name(rset.getString("product_name"));
				pw.setProduct_price(rset.getInt("product_price"));
				pw.setProduct_img(rset.getString("product_img"));
				pw.setProduct_count(rset.getInt("product_count")); //남은 제품 개수
				pw.setShipping_fee(rset.getInt("shipping_fee"));
				pw.setProduct_content(rset.getString("product_content")); //제품 정보
				pw.setAdmin_check(rset.getString("admin_check") == null ? "" : rset.getString("admin_check"));
				pw.setProduct_writing_yn(rset.getString("product_writing_yn") == null ? "" :rset.getString("product_writing_yn"));
				
				pw.setImg_1(rset.getString("img_1"));
				pw.setImg_2(rset.getString("img_2"));
				pw.setImg_3(rset.getString("img_3"));
				
				list.add(pw);
				System.out.println(pw);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	//제품 정보 수정
	public int updateProduct(Connection conn, Product p) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateProduct");
		int result = 0;
		
		try {

			/* update product set 
			 * category_code = ?, 
			 * product_name = ?, 
			 * product_price =?, 
			 * product_img =?,
			 *  product_count =?, 
			 * product_content = ?, 
			 * shipping_fee =? 
			 * where product_code = ?
			*/
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,p.getCategory_code());
			pstmt.setString(2,p.getProduct_name());
			pstmt.setInt(3,p.getProduct_price());
			pstmt.setString(4,p.getProduct_img());
			pstmt.setInt(5,p.getProduct_count());
			pstmt.setString(6,p.getProduct_content());
			pstmt.setInt(7,p.getShipping_fee());
			pstmt.setInt(8,p.getProduct_code());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	//관리자 승인 여부 업데이트
	public int updateProductAdmin(Connection conn, Product p) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateProductAdmin");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,p.getProduct_code());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	//배송 상태 변경
	public int updateProductShappingStatus(Connection conn, int product_buy_code) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateProductShappingStatus");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,product_buy_code);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	//주문 리스트 출력
	public List<BuyAddress> selectProductOrderList(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("productOrderList");
		List<BuyAddress> productBuyList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				BuyAddress pb = new BuyAddress();
				pb.setProduct_img(rset.getString("product_img"));
				pb.setId(rset.getString("id")); // id는 구매자, id_1는 판매자
				pb.setProduct_code(rset.getInt("product_code"));
				pb.setProduct_buy_code(rset.getInt("product_buy_code"));
				pb.setProduct_name(rset.getString("product_name"));
				pb.setProduct_buy_count(rset.getInt("product_buy_count"));
				pb.setProduct_buy_date(rset.getDate("product_buy_date"));
				pb.setProduct_receipt_yn(rset.getString("PRODUCT_RECEIPT_YN"));
				pb.setBuy_writing_yn(rset.getString("product_buy_writing_yn"));
				pb.setProduct_shipping_status(rset.getString("PRODUCT_SHIPPING_STATUS"));
				pb.setPrice_sum(rset.getInt("PRICE_SUM"));
				
				pb.setAddress(rset.getString("address"));
				pb.setDetail_address(rset.getString("detail_address"));
				pb.setZip_code(rset.getString("zip_code"));
				productBuyList.add(pb);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return productBuyList;
	}

	//판매제품글 추가
	public int insertproductWriting(Connection conn, int product_code, String id) {
		PreparedStatement pstmt = null;
//		String sql = prop.getProperty("insertproductWritingtest");	
		String sql = prop.getProperty("insertProductWriting");	
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setInt(2,product_code);

			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	//제품 삭제
	public int deleteProduct(Connection conn, String[] codes) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteproduct");
		String sql_plus = "";
		for(int i = 0; i < codes.length ; i++) {
			sql_plus += (i != codes.length-1 ?  "? ," : "?");
		}
		System.out.println("sql_plus@"+sql_plus);
		sql = sql.replace("#", sql_plus);			
		int result = 0;
		System.out.println("deleteProduct@sql@"+sql);
		System.out.println("codes.length : "+ codes.length);
		try {
			pstmt = conn.prepareStatement(sql);
			//pb 값들 추가하기
			//values(?,?,?,?,default,default,default,?,?)
			for(int i = 0; i < codes.length ; i++) {
				pstmt.setInt(i+1,Integer.parseInt(codes[i]));
			}
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	//주문 수령이 되지 않은 제품 or 배송 출발하지 않은 제품이 있는지 체크
	public int selectProductynList(Connection conn, String[] codes) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectProductynList");
		String sql_plus = "";
		ResultSet rset = null;
		int result = 0;
		
		for(int i = 0; i < codes.length ; i++) {
			sql_plus += (i != codes.length-1 ?  "? ," : "?");
		}
		System.out.println("sql_plus@"+sql_plus);
		sql = sql.replace("#", sql_plus);			

		System.out.println("selectProductynList@sql@"+sql);
		System.out.println("codes.length : "+ codes.length);
		
		try {
			pstmt = conn.prepareStatement(sql);
			for(int i = 0; i < codes.length ; i++) {
				pstmt.setInt(i+1,Integer.parseInt(codes[i]));
			}
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = (rset.getInt("count"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	// 관리자 승인이 필요한 제품 리스트
	public List<ProductWriting> selectProductWritingAdminCheckList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectProductWritingAdminCheckList");	
		List<ProductWriting> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				// 테이블 record 1 -> VO객체 1
				ProductWriting pw = new ProductWriting();
				pw.setId(rset.getString("id"));
				pw.setProduct_writing_code(rset.getInt("product_writing_code"));
				pw.setProduct_code(rset.getInt("product_code"));
				pw.setProduct_writing_date(rset.getDate("product_writing_date"));
				pw.setCategory_code(rset.getInt("category_code"));
				pw.setProduct_name(rset.getString("product_name"));
				pw.setProduct_price(rset.getInt("product_price"));
				pw.setProduct_img(rset.getString("product_img"));
				pw.setProduct_count(rset.getInt("product_count")); //남은 제품 개수
				pw.setShipping_fee(rset.getInt("shipping_fee"));
				pw.setProduct_content(rset.getString("product_content")); //제품 정보
				
				pw.setImg_1(rset.getString("img_1"));
				pw.setImg_2(rset.getString("img_2"));
				pw.setImg_3(rset.getString("img_3"));
				
				list.add(pw);
				System.out.println(pw);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	//관리자 승인 여부 수정
	public int updateProductAdminCheck(Connection conn, int product_writing_code) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateProductAdminCheck");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,product_writing_code);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	//제품 수령 완료시 후원 테이블에 제품 추가
	public int insertDonate(Connection conn, int product_donate_price, String id) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertdonate");	
//		String sql = prop.getProperty("insertProductWriting");	
		int result = 0;
		try {
			// B / C
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,product_donate_price);
			pstmt.setString(2,"B");
			pstmt.setString(3,id);
		
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	//댓글 추가
	public int InsertProductComment(Connection conn, ProductWritingQuestion pwq) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertProductComment");	
//		System.out.println("InsertProductComment@"+pwq);
		int result = 0;
		try {
			// B / C
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,pwq.getComment_level());
			pstmt.setString(2,pwq.getComment_secret());
			pstmt.setString(3,pwq.getId());
			pstmt.setInt(4,pwq.getProduct_writing_code());
			pstmt.setString(5,pwq.getComment_content());
			pstmt.setObject(6,pwq.getComment_ref() == 0 ? null : pwq.getComment_ref());
		
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	//문의 댓글 삭제
	public int DeleteProductComment(Connection conn, int product_question_code) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteproductWritingComment");	
		int result = 0;
		try {
			// B / C
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,product_question_code);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	//조회수 top3 판매제품글 가져오기
	public List<ProductWriting> selectProductWritingTop3List(Connection conn, int category) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "";
		if(category == 0) {
			sql = prop.getProperty("selectProductWritingAllTop3List");
		}else {
			sql = prop.getProperty("selectProductWritingTop3List");
		}
		List<ProductWriting> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			if(category != 0)
				pstmt.setInt(1, category);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				// 테이블 record 1 -> VO객체 1
				ProductWriting pw = new ProductWriting();
				pw.setId(rset.getString("id"));
				pw.setProduct_writing_code(rset.getInt("product_writing_code"));
				pw.setRead_count(rset.getInt("read_count"));
				pw.setProduct_writing_date(rset.getDate("product_writing_date"));
				pw.setCategory_code(rset.getInt("category_code"));
				pw.setProduct_code(rset.getInt("product_code"));
				pw.setProduct_name(rset.getString("product_name"));
				pw.setProduct_price(rset.getInt("product_price"));
				pw.setProduct_img(rset.getString("product_img"));
				pw.setProduct_count(rset.getInt("product_count")); //남은 제품 개수
				pw.setCategory_name(rset.getString("category_name"));
				pw.setShipping_fee(rset.getInt("shipping_fee"));
				System.out.println(pw);
				list.add(pw);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	// 주문하기 위해 구매자 주소 가져오기
	public Address MemberAddress(Connection conn, String cos_id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("MemberAddress");
		Address address = new Address();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cos_id);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				address.setAddress(rset.getString("ADDRESS"));
				address.setDetailAddress(rset.getString("DETAIL_ADDRESS"));
				address.setZipCode(rset.getString("ZIP_CODE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return address;
	}

	public ProductWriting selectOneProduct(Connection conn, int product_code) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOneProduct");
		ProductWriting pw = new ProductWriting();
		System.out.println("dao@selectOneProductWriting@code : " + product_code);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_code);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				pw.setId(rset.getString("id"));
				pw.setProduct_name(rset.getString("product_name"));
				pw.setCategory_code(rset.getInt("CATEGORY_CODE"));
				pw.setProduct_price(rset.getInt("product_price"));
				pw.setProduct_img(rset.getString("product_img"));
				pw.setProduct_count(rset.getInt("product_count")); //남은 제품 개수
				pw.setProduct_code(rset.getInt("product_code"));
				pw.setProduct_content(rset.getString("product_content")); //제품 정보
				pw.setShipping_fee(rset.getInt("shipping_fee"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return pw;
	}
}
