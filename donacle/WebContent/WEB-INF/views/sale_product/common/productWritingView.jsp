<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.List"%>
    <%@page import="mvc.sale_product.product.model.vo.ProductWriting"%>
    <%@page import="mvc.sale_product.product.model.vo.ProductBuy"%>
    <%@page import="mvc.sale_product.product.model.vo.ProductWritingQuestion"%>
    <%@ include file="/WEB-INF/views/sale_product/common/productListMenu.jsp" %>
<%
	ProductWriting pw = (ProductWriting) request.getAttribute("product_writing");
	List<ProductWritingQuestion> pwqlist = (List<ProductWritingQuestion>) request.getAttribute("commentList");
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/sale_product/common.css"/>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
	<style>
	.plus-minusBtn{
		border : 0px;
		text-align : center;
	    position: relative;
	    width: 34px;
	    height: 100%;
	    background-color: #f1f2f4;
	}
	.productwritingview{
		width : 500px;
	}
	.productwritingviewBtn{
		width : 100%;
	}
	#tbl-comment{
		display : none;
	}
	/*댓글등록버튼*/
	div.comment-container button#btn-insert{width:60px; height:50px; color:white; background:#3300ff; position:relative; top:-20px;}
	
	/*댓글테이블*/
	table#tbl-comment{width:580px; margin:0 auto; border-collapse:collapse;} 
	table#tbl-comment tr td{border-bottom:1px solid; border-top:1px solid; padding:5px; text-align:left; line-height:120%;}
	table#tbl-comment tr td:first-of-type{padding: 5px 5px 5px 50px;}
	table#tbl-comment tr td:last-of-type {text-align:right; width: 100px;}
	table#tbl-comment button.btn-reply{display:none;}
	table#tbl-comment tr:hover {background:lightgray;}
	table#tbl-comment tr:hover button.btn-reply{display:inline;}
	table#tbl-comment sub.comment-writer {color:navy; font-size:14px}
	table#tbl-comment sub.comment-date {color:tomato; font-size:10px}
	
	table#tbl-comment tr.level2 {color:gray; font-size: 14px;}
	table#tbl-comment tr.level2 td:first-of-type{padding-left:100px;}
	table#tbl-comment tr.level2 sub.comment-writer {color:#8e8eff; font-size:14px}
	table#tbl-comment tr.level2 sub.comment-date {color:#ff9c8a; font-size:10px}
	
	/*답글관련*/
	table#tbl-comment textarea{margin: 4px 0 0 0;}
	table#tbl-comment button.btn-insert2{width:60px; height:23px; color:white; background:#3300ff; position:relative; top:-5px; left:10px;}
	
	/* 삭제버튼관련 */
	table#tbl-comment button.btn-delete{background:red; color:white; display:none;}
	table#tbl-comment tr:hover button.btn-delete{display:inline;}
	
	</style>
	<section id = "product-writing_view-container">
	<h2>판매 제품 글 보기</h2>
		<table class = "productwritingview">
			<tr>
				<td rowspan ="7"><img src="<%= pw.getProduct_img()%>" width="200px" height="300px"></td>
				<td>판매자 이름</td>
				<td colspan="3">: <%= pw.getId()%></td>
			</tr>
			<tr>
				<td>제품 이름</td>
				<td colspan="3">: <%= pw.getProduct_name()%></td>
			</tr>
			<tr>
				<td>제품 내용</td>
				<td colspan="3">: <%= pw.getProduct_content()%></td>
			</tr>
			<tr>
				<td>판매 가격</td>
				<td colspan="3">: <%= pw.getProduct_price()%></td>
			</tr>
			<tr>
				<td>배송비</td>
				<td colspan="3">: <%= pw.getShipping_fee()%></td>
			</tr>
			<tr>
				<td>남은 수량</td>
				<td colspan="3">: <%= pw.getProduct_count()%>개</td>
			</tr>
			<tr>
				<td>구매 수량</td>
				<td><input type="button" id = "minus" class = "plus-minusBtn" value = '-'></td>
				<td><input type="text" id = "pm_value" class = "plus-minusBtn" value = 1></td>
				<td><input type="button" id = "plus" class = "plus-minusBtn"  value = '+'></td>
			</tr>
		</table>
			<form 
				name = "buyProductFrm" 
				action="<%= request.getContextPath() %>/sale_product/productBuyInfo"
				method = "POST">
				<input type="hidden" name = "product_code" id = "product_code" value="<%= pw.getProduct_code()%>"/>
				<input type="hidden" name = "product_writing_code" id = "product_writing_code" value="<%= pw.getProduct_writing_code()%>"/>
				<!-- 여기에는 사용자 아이디 가져와서 넣기 -->
				<input type="hidden" name = "buy_id" id= "buy_id" value="<%= pw.getProduct_code()%>"/>
				<input type="hidden" name = "product_buy_count" id = "product_buy_count" value="1"/>
				<input type="hidden" name = "product_shipping_fee" id = "product_shipping_fee" value="<%= pw.getShipping_fee()%>"/>
				<input type="hidden" name = "sum_price" id = "sum_price" value ="<%= pw.getProduct_price()%>"/>
				<table class="productwritingview">
					<tr>
						<td><input name = "cart" class="productwritingviewBtn" type="button" value="장바구니"/></td>
						<td><input name = "buy" class="productwritingviewBtn" type="submit" value="구매하기"/></td>
					</tr>
					<tr>
						<td colspan="2"><input name = "comment" class="productwritingviewBtn" type="button" value="문의▼"/></td>
					</tr>
				</table>
			</form>
		<div>
			<div class="comment-editor">
				<form 
					action="<%=request.getContextPath() %>/sale_product/productWritingCommentInsert" 
					name="commentInsertFrm" 
					method="POST">					
					<input type="hidden" name="commentLevel" value="1" />
					
					<!-- 아이디는 session에서 가져와서 수정 필요 -->
					<input type="hidden" name="writer" value="test0" />
					<input type="hidden" name="product_writing_code" value="<%= pw.getProduct_writing_code()%>" />
					<input type="hidden" name="commentRef" value="0" />
					<input type="hidden" name="comment_secret" value="0" /> <!-- 0이면 공개댓글, 1이면 비밀댓글 -->
					<input type="hidden" name="comment_content" value="" /> <!-- 0이면 공개댓글, 1이면 비밀댓글 -->
				</form>
			</div>
			<table class = "productwritingview" id = "tbl-comment">
			<tr>
				<td><textarea name="commentcontent" cols="60" rows="3"></textarea></td>
				<td><input type="checkbox" name="checkboxs"><br/><button id="commentInsertBtn">등록</button></td>
			</tr>
<%
if(pwqlist != null && !pwqlist.isEmpty()){
	for(ProductWritingQuestion pwq : pwqlist){ 
	if(pwq.getComment_level() == 1){
%>
			<%-- 댓글 --%>
			<tr class="level1">
				<td>
					<sub class="comment-writer"><%= pwq.getId() %></sub>
					<sub class="comment-date"><%= pwq.getComment_date() %></sub>
					<br />
					<%-- 댓글 내용 --%><!-- 자신의 댓글은 확인 가능 -->
<%if(pwq.getComment_secret().equals("n") || pwq.getId().equals("test0")) {%>
					<%= pwq.getComment_content() %>
<%} else{%>
				비밀댓글입니다.
<% }%>
				</td>
				<td>
					<button class="btn-reply" value="<%= pwq.getProduct_question_code() %>">답글</button>
<!-- 후에 id 수정 필요 -->
	<%if(pwq.getId().equals("test0")) {%>
						<form action="<%= request.getContextPath() %>/sale_product/CommentDelete" name="deleteCommentFrm" method="POST">
							<input type="hidden" name="product_question_code" value="<%= pwq.getProduct_question_code() %>" />
							<input type="hidden" name="product_writing_code" value="<%= pwq.getProduct_writing_code() %>" />
						</form>
						<button class="btn-delete">삭제</button>
	<% } %>
				</td>
			</tr>
<%}else{%>
			<%-- 대댓글(답글) --%>
			<tr class="level2">
				<td>
					<sub class="comment-writer"><%= pwq.getId() %></sub>
					<sub class="comment-date"><%= pwq.getComment_date() %></sub>
					<br />
					<%-- 댓글 내용 --%>
				<!-- session에서 같은지 확인 -->
				<% if(pwq.getComment_secret().equals("n")||pwq.getId().equals("test0")){%>
				<%=pwq.getComment_content() %>
				<%}else{%>
				비밀댓글입니다.
				<%}%>
				</td>
				<td>
				
<!-- 답글은 판매자만 가능하게 -->
<%if(pwq.getId().equals("test0")){ %>
					<form action="<%= request.getContextPath() %>/sale_product/CommentDelete" name="deleteCommentFrm" method="POST">
						<input type="hidden" name="product_question_code" value="<%= pwq.getProduct_question_code() %>" />
						<input type="hidden" name="product_writing_code" value="<%= pwq.getProduct_writing_code() %>" />
					</form>
					<button class="btn-delete">삭제</button>
<% } %>
				</td>
			</tr>
			
<%}%>
<%}
}%>
			</table>
		</div>
	</section>
	<script>
	$("[name=cart]").click((e)=>{
		$("[name=buyProductFrm]").attr("action","<%= request.getContextPath() %>/sale_product/cartAdd");
		$("[name=buyProductFrm]").submit();
	});
	
	$(".btn-delete").click((e) => {
		if(confirm("정말 이 댓글을 삭제하시겠습니까?")){
			const $btn = $(e.target).prev();
			$($btn).submit();
			/* $(document.deleteCommentFrm).submit(); */
		}
	});	
	
	//비밀글인지 체크
	$("[name=checkboxs]").change(function(){
		if($(this).is(":checked") == true) {
			$("[name=comment_secret]").val("1");
		}else{
			$("[name=comment_secret]").val("0");
		}
	});

	//문의 댓글 작성시 내용 체크
		$("#commentInsertBtn").on("click",function(){
			if($("[name=commentcontent]").val() == ""){
				alert("내용을 입력해주세요.")
			}else{				
				$("[name=comment_content]").val($("[name=commentcontent]").val());
				$("[name=commentInsertFrm]").submit();
			}
		});
	
	
		$("[name=comment]").on("click",function(){
			if($(this).val() == '문의▼'){
				$("#tbl-comment").css("display","block");	
				$("#tbl-comment").css("display","block");	
				$(this).val('문의▲');
			}else{			
				$("#tbl-comment").css("display","none");				
				$(this).val('문의▼');
			}
		});
		
		//후에 아이디쪽 수정필요
		$(".btn-reply").click((e) => {
			const commentRef = $(e.target).val();
			const parent_secret = $(this).attr("id");
			alert(parent_secret);
			const tr = `<tr>
			<td colspan="2" style="text-align: left;">
				<form 
					name = "commentInsertFrm"
					action="<%= request.getContextPath() %>/sale_product/productWritingCommentInsert" 
					method="POST">
					<textarea name="comment_content" cols="60" rows="1"></textarea>
					<button class="btn-insert2">등록</button>
					
					<input type="hidden" name="commentLevel" value="2" />
						<input type="hidden" name="writer" value="test0" />
						<input type="hidden" name="product_writing_code" value="<%=pw.getProduct_writing_code()%>" />
						<input type="hidden" name="commentRef" value="\${commentRef}" />
						<input type="hidden" name="comment_secret" value="\${parent_secret}" />
				</form>
			</td>
		</tr>`;
			console.log(tr);
			
			const $trOfBtn = $(e.target).parent().parent();
			// $trOfBtn.after(tr);
			$(tr)
				.insertAfter($trOfBtn)
				.find("form")
				.submit((e) => {
					// 내용검사
					// const textarea = $("[name=content]", document.boardCommentFrm);	
					const $textarea = $("[name=content]", e.target);	
					
					if(!/^(.|\n)+$/.test($textarea.val())){
						alert("댓글 내용을 작성해주세요.");
						$textarea.focus();
						return false;
					}
				})
				.find("[name=content]")
				.focus();
			
			// 현재버튼의 handler 제거
			$(e.target).off('click');
		});
		
	</script>