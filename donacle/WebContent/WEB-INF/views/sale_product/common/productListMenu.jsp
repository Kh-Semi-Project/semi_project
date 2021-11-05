
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/sale_product/common.css"/>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/sale_product/productList.css" />
    <script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
	<nav style="margin-top:200px; position: fixed;  top: 0;">
		<ul class="main-nav">
		<li class="home"><a href="<%= request.getContextPath() %>/sale_product/productwritingList?category=0">All</a>
			<li class="accessories"><a>Accessories</a>
				<ul class="main-nav access-sub">
					<li><a href="<%= request.getContextPath() %>/sale_product/productwritingList?category=1">necklace</a></li>
					<li><a href="<%= request.getContextPath() %>/sale_product/productwritingList?category=2">bracelet</a></li>
				</ul>
			
			</li>
			<li class="badge"><a href="<%= request.getContextPath() %>/sale_product/productwritingList?category=3">badge</a></li>
			<li class="keyring"><a href="<%= request.getContextPath() %>/sale_product/productwritingList?category=4">key ring</a></li>
		</ul>
	</nav>
<script>
$(".accessories").hover(function(){ 
	$(".access-sub").css("display", "block");
},function(){
	$(".access-sub").css("display", "none");
}); 


</script>