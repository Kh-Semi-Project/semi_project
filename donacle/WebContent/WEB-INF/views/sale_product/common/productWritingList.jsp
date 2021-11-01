<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.List"%>
    <%@page import="mvc.sale_product.seller.model.vo.ProductWriting"%>
   <link rel="stylesheet" href="<%= request.getContextPath() %>/css/sale_product/productList.css" />
<%
	List<ProductWriting> list = (List<ProductWriting>) request.getAttribute("list");
%>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
	<style>
		#pro_Counts {
			display : none
		}
	</style>
				<nav>
				<ul class="main-nav">
				<li class="home"><a>전체</a>
					<li class="accessories"><a>악세사리</a>
						<ul class="main-nav access-sub">
							<li><a>목걸이</a></li>
							<li><a>팔찌</a></li>
						</ul>
					
					</li>
					<li class="badge"><a>뱃지</a></li>
					<li class="keyring"><a>키링</a></li>
				</ul>
			</nav>
	<section id = "product-List-container">
	<h2>조회수가 높은 Top3 제품</h2>
		<table>
		<!--조회수가 높은 제품 3개 -->
			<tr>
				<td>사진</td>
			</tr>
			<tr>
				<td>판매자이름</td>
			</tr>
			<tr>
				<td>제품명</td>
			</tr>
			<tr>
				<td>가격</td>
			</tr>
			<tr style="display:none;">
				<td>제품코드</td>
			</tr>
		</table>
	<h2>모든 제품</h2>
		<!-- 모든 리스트 -->
		<table>
		<!-- 
			리스트로 불러와서 출력하기  
			사진/판매자이름/제품명 클릭시 productDetil로 이동
			클릭시 form에 정보 저장 후 이동 -->
			<tr>
				<td>사진</td>
			</tr>
			<tr>
				<td>판매자이름</td>
			</tr>
			<tr>
				<td>제품명</td>
			</tr>
			<tr>
				<td>가격</td>
			</tr>
			<tr style="display:none;">
				<td>제품코드</td>
			</tr>
			
<%for(ProductWriting pw : list){ %>
			<tr>
				<td><%= pw.getProduct_img() %></td>
				<td>
					<%= pw.getId() %>
				</td>
				<td><%= pw.getProduct_name() %></td>
				<td><%= pw.getProduct_price() %></td>
			</tr>
<%} %>
		</table>
		<div id='pageBar'><%= request.getAttribute("pagebar") %></div>

	<form
		name = "productAddFrm"
		action = ""
		method = "get"
	>
		<input type="hidden" name = "productCode"/>
	</form>
	</section>
	<script>
	
		$(".accessories").hover(function(){ 
			$(".access-sub").css("display", "block");
		},function(){
			$(".access-sub").css("display", "none");
		}); 

		
		$("[name=productAddFrm]").submit(function(){	
		
		});
	</script>