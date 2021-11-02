package mvc.sale_product.product.model.service;

import static mvc.sale_product.common.JdbcTemplate.close;
import static mvc.sale_product.common.JdbcTemplate.getConnection;
import static mvc.sale_product.common.JdbcTemplate.commit;
import static mvc.sale_product.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;


import mvc.sale_product.product.model.dao.ProductDao;
import mvc.sale_product.product.model.vo.Product;
import mvc.sale_product.product.model.vo.ProductBuy;
import mvc.sale_product.product.model.vo.ProductWriting;
import mvc.sale_product.product.model.vo.ProductWritingQuestion;

public class ProductService {
	ProductDao pwdao = new ProductDao();
	
	public List<ProductWriting> selectProductWritingList(int start, int end, int category) {
		Connection conn = getConnection();
		List<ProductWriting> list = pwdao.selectProductWritingList(conn, start, end, category);
		close(conn);
		return list;
	}

	public int selectTotalContents(int categry) {
		Connection conn = getConnection();
		int totalContents = pwdao.selectTotalContents(conn,categry);
		close(conn);
		return totalContents;
	}

	public int updateReadCount(int pw_code) {
		Connection conn = getConnection();
		int result = pwdao.updateReadCount(conn, pw_code);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	//선택한 판매 게시글 가져오기
	public ProductWriting selectOneProductWriting(int pw_code) {
		Connection conn = getConnection();
		ProductWriting pw = pwdao.selectOneProductWriting(conn, pw_code);
		close(conn);
		return pw;
	}

	//문의 댓글 리스트
	public List<ProductWritingQuestion> selectCommentList(int pw_code) {
		Connection conn = getConnection();
		List<ProductWritingQuestion> commentList = pwdao.selectCommentList(conn, pw_code);
		close(conn);
		return commentList;
	}

	//제품 구매를 눌렀을 경우
	public int ProductBuy(ProductBuy pb) {
		Connection conn = getConnection();
		int result = pwdao.insertproductBuy(conn, pb);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	// 제품 구매를 눌러서 해당 상품에 대한 정보를 출력
	// 이 단계에서 주문하면 주문 되게 끔
	public ProductBuy ProductBuyInfo(ProductBuy pb) {
		Connection conn = getConnection();
		ProductBuy pbInfo = pwdao.selectproductBuyInfo(conn, pb);
		close(conn);
		return pbInfo;
	}

	//구매했던 제품 출력하기
	public List<ProductBuy> ProductBuyList(String id) {
		Connection conn = getConnection();
		List<ProductBuy> productBuyList = pwdao.selectProductBuyList(conn, id);
		close(conn);
		return productBuyList;
	}

	//수령완료 버튼을 클릭했을때
	public int updateProductOrderCheck(int product_buy_code) {
		Connection conn = getConnection();
		int result = pwdao.updateProductOrderCheck(conn, product_buy_code);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	// 제품 구매 취소
	// product_buy_code 로 delete
	public int ProductBuyCancel(String[] codes) {
		Connection conn = getConnection();
		int result = pwdao.deleteProductBuy(conn, codes);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	// 제품 추가
	public int ProductAdd(Product p) {
		Connection conn = getConnection();
		int result = pwdao.insertProduct(conn, p);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	// 제품 리스트 출력
	public List<ProductWriting> productList(String id) {
		Connection conn = getConnection();
		List<ProductWriting> pw = pwdao.selectAllProductList(conn, id);
		close(conn);
		return pw;
	}

	// 제품 정보 수정
	public int updateProduct(Product p) {
		Connection conn = getConnection();
		int result = pwdao.updateProduct(conn, p);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	// 제품 정보 수정시 관리자 승인 수정
	public int updateProductAdmin(Product p) {
		Connection conn = getConnection();
		int result = pwdao.updateProductAdmin(conn, p);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	// 판매자 배송 시작 여부 변경
	public int updateproductShppingStatus(int product_buy_code) {
		Connection conn = getConnection();
		int result = pwdao.updateProductShappingStatus(conn, product_buy_code);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public List<ProductBuy> productOrderList(String id) {
		Connection conn = getConnection();
		List<ProductBuy> productBuyList = pwdao.selectProductOrderList(conn, id);
		close(conn);
		return productBuyList;
	}

	public int ProductWritingAdd(int product_code, String id) {
		Connection conn = getConnection();
		int result = pwdao.insertproductWriting(conn, product_code,id);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	//제품 삭제
	public int ProductDelete(String[] codes) {
		Connection conn = getConnection();
		int result = pwdao.deleteProduct(conn, codes);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int selectProductynList(String[] codes) {
		Connection conn = getConnection();
		int pw = pwdao.selectProductynList(conn, codes);
		close(conn);
		if(pw != 0) return 0;
		else {
			return ProductDelete(codes);
		}
	}

	public List<ProductWriting> productWritingAdminCheckList() {
		Connection conn = getConnection();
		List<ProductWriting> productBuyList = pwdao.selectProductWritingAdminCheckList(conn);
		close(conn);
		return productBuyList;
	}

	public int updateProductWritingAdminCheck(int product_writing_code) {
		Connection conn = getConnection();
		int result = pwdao.updateProductAdminCheck(conn, product_writing_code);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int insertDonate(int product_donate_price, String id) {
		Connection conn = getConnection();
		int result = pwdao.insertDonate(conn, product_donate_price, id);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}


	public int InsertProductComment(ProductWritingQuestion pwq) {
		Connection conn = getConnection();
		int result = pwdao.InsertProductComment(conn, pwq);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int DeleteProductComment(int product_question_code) {
		Connection conn = getConnection();
		int result = pwdao.DeleteProductComment(conn, product_question_code);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public List<ProductWriting> selectProductWritingTop3List(int category) {
		Connection conn = getConnection();
		List<ProductWriting> list = pwdao.selectProductWritingTop3List(conn, category);
		close(conn);
		return list;
	}
}
