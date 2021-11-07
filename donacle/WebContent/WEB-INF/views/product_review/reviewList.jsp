<%@page import="mvc.product_review.comment.model.vo.Comment"%>
<%@page import="mvc.product_review.review.model.vo.Review"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	Member id = (Member) session.getAttribute("loginMember");
	String loginId = "";
	if(id != null &&  id.getId() != null ) loginId = id.getId(); 
	
	List<Review> list = (List<Review>) request.getAttribute("reviewList");
%>

<%@ include file="/WEB-INF/views/homepage_introduce/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/product_review/reviewList.css" />
<script>
	var loginUser = "<%=loginId%>";
	if(loginUser == ""){
	alert("로그인 후 이용 가능합니다.");
	location.href="/donacle/memberLogin";
}
</script>
<div class="subVeiew">
	<div style="background:#F9F4F4; text-align:center; width: 1300px; height:180px; padding-top:50px; margin:auto;">
     	<h1 class="subVeiewTitle">제 품 후 기</h1>
     	
        <span class="subVeiewcontent">남겨주신 후기는 콘텐츠로 활용 될 수 있습니다. :)</span>
    </div>     
</div> 
<section id="review-container">
	<div class="reivew-head">
		<h3 style="display: inline;">실시간 제품 후기<span class="small">donacle의 제품 후기를 남겨주세요 :)<img src="<%=request.getContextPath()%>/img/flower.png" style="width:50px; transform: scaleX(-1);"></span></h3>
		
		<button class="enroll-btn" onclick="javascript:location.href='<%=request.getContextPath()%>/review/reviewForm'">후기 작성하기</button>
	</div>
	<table id="tbl-review">

		<%for(Review review : list){%>	
			<%boolean isContain = review.getAttach() != null && review.getAttach().getAttachNo() != 0;%>
		<tr>
			<td class="review-item">
				<div class="review-head">
					<div class="review-head-title" onclick="reviewToggle(this)"><%= review.getReviewTitle()  %> <span style="color:red; font-size:15px;"> <%= review.getName() == null ? "[탈퇴회원]" : ""%> 
						<%if(isContain){%><img class="review-icon" src="<%=request.getContextPath()%>/img/icon.png" /><%}%>
					</div>
					<div class="review-head-comment" style= "font-size: 13px; margin-top:10px;">[<span><%= review.getReviewCommentCnt() %></span>]개의 댓글이 있습니다</div>
				</div>	
								
				<div class="review-content">
					<div class="review-content-date"><%= review.getName() == null ? "탈퇴회원" : review.getName() %> [<%= review.getReviewDate() %> 등록]</div>
					<%if(isContain){%>
						<div class="review-content-img" style="margin-top: 12px;"><img alt="" class="review-img" src="<%=request.getContextPath()%>/common/imgLoad?attachNo=<%=review.getAttach().getAttachNo()%>"></div>
					<%}%>
					<div class="review-content-content" style="margin-top: 15px; font-size: 16px;"><%= review.getReviewContent() %></div>
					<div class="review-content-btn">
					<%if(review.getId() != null && review.getId().equals(loginId));{%>  <!-- 이 부분 세션 물어봐서 다시 적용할것  -->
						<button class="btn-red" onclick="javascript:location.href='<%=request.getContextPath()%>/review/reviewDelete?reviewNo=<%=review.getReviewNo()%>'">삭제</button>
						<button class="btn-mint" onclick="javascript:location.href='<%=request.getContextPath()%>/review/reviewUpdate?reviewNo=<%=review.getReviewNo()%>'">수정</button>
					<%}%>	
					</div>
					
					<div class="review-content-comment">
						<div class="comment-new">
							<form action="<%=request.getContextPath()%>/comment/commentEnroll" method="post">
								<input class="comment-input" type="text" name="commentsTitle">
								<input type="hidden" name="commentsType" value="1">
								<input type="hidden" name="pCommentsNo" value="0">
								<input type="hidden" name="reviewNo" value="<%=review.getReviewNo()%>"/>
								<button class="btn-blue" type="button" onclick="cmtInsert(this,<%=review.getReviewNo()%>)">등록</button>
							</form>
						</div>
						<%if(review.getCommentList() != null && review.getCommentList().size() > 0){
							for(Comment comment : review.getCommentList()){%>
								<div class="comment-box <%if(Integer.parseInt(comment.getCommentsType())==2){%> reply<%}%>">
									
									<input type="hidden" name="commentsNo" value="<%=comment.getCommentsNo()%>"/>
									<input type="hidden" name="pCommentsNo" value="<%=comment.getpCommentsNo()%>"/>
									<span class="cmt-content" style="font-size: 16px;"><%=comment.getCommentsTitle()%></span>
									
									<%if(Integer.parseInt(comment.getCommentsType())==1){ %>
									<button class="btn-a" onclick="replyToggle(this)">답글</button>
									<%} %>
									<div class="comment-footer">
										<span class="cmt-nm"><%=comment.getName() == null ? "탈퇴회원" : comment.getName()%></span> 
										<span class="cmt-dt"><%=comment.getCommentsDate().substring(0, 10)%></span>
										<%if(comment.getId() != null && comment.getId().equals(loginId)){%> <!--  세션정보 수정하기 -->
										<span class="cmt-up" onclick="cmtUpdate(this, <%=comment.getCommentsNo()%>)">수정</span>
										<span class="cmt-del" onclick="cmtDelete(this, <%=comment.getCommentsNo()%> , <%=comment.getCommentsType()%>)">삭제</span>
										<%}%>
									</div>
									<%if(Integer.parseInt(comment.getCommentsType()) == 1){ %>
									<div class="cmt-reply">
										<input class="comment-input" name="reply" />
										<span class="cmt-reply-add" onclick="cmtInsert(this, <%=review.getReviewNo()%> , <%=comment.getCommentsNo()%>)">등록</span>
										<!-- <span class="cmt-reply-cle" onclick="replyCancle(this)" >취소</span>-->
									</div>
									<%}%>
									
								</div>
			
							<%}%>
						<%}%>
					</div>
					
					<input type="hidden" name="reviewNo" value="<%=review.getReviewNo()%>"/>
				</div>
			</td>
		</tr>
		<%}%>
	</table>
	
	<div id='pageBar'><%= request.getAttribute("pagebar") %></div>
</section>
<%@ include file="/WEB-INF/views/homepage_introduce/footer.jsp" %>




<script>

	function reviewToggle(obj){
		$(obj).parent("div").siblings("div.review-content").slideToggle();
	}
	
	function cmtInsert(obj, reviewNo, pCommentNo, replyContent){
		var url = "<%=request.getContextPath()%>/comment/commentEnroll";
		var data;
		
		if(pCommentNo){
			var commentsTitle = $(obj).siblings("input[name=reply]").val();
			if(nullCheck(commentsTitle)){
				alert("내용이 없습니다.");
				return;
			}
			data = "reviewNo="+reviewNo+"&pCommentsNo="+pCommentNo+"&commentsTitle="+commentsTitle+"&commentsType=2";
		}else{	
			if(nullCheck($(obj).siblings("input[name='commentsTitle']").val())){
				alert("내용이 없습니다.");
				return;
			}
			data = $(obj).parent().serialize();
		}
		
		$.post(url,data,function(res){
			
			if(pCommentNo){
				var tag = $(renderingComment(res,pCommentNo));
				tag.addClass("reply");
	
				if($("input[name=pCommentsNo][value="+pCommentNo+"]").parent("div.reply").length > 0){
					$("input[name=pCommentsNo][value="+pCommentNo+"]").parent("div.reply").last().after(tag);
				}else{
					$("input[name=commentsNo][value="+pCommentNo+"]").parent().after(tag);
				}
				
				$(obj).siblings("input[name=reply]").val("");
				$(obj).parent().slideToggle();
			}else{
				var tag = $(renderingComment(res));
				$(obj).closest("div.review-content-comment").append(tag);
				$(obj).siblings("input[name='commentsTitle']").val('');				
			}
		});
	}
	
	function cmtUpdate(obj, commentNo, upContent){

		var content = $(obj).parent().siblings("input[name=cmtUp]").val();

		if(!content || content == "") {
			setCmtUpForm(obj);
			return;
		}
		
		var url = "<%=request.getContextPath()%>/comment/commentUpdate";
		var data = "commentsNo="+commentNo+"&commentsTitle="+content;
		
		if(content.length == 0){
			alert("내용이 없습니다.");
			return;
		}else{
			$.post(url,data,function(res){
				cmtUpRes(obj,content);
				$(obj).parent().siblings("span.cmt-content").text(content);
			});
		}
	}

	function cmtDelete(obj, commentNo, commentsType){
		if (!confirm("댓글을 삭제하시겠습니까?")) return;
	
		var url = "<%= request.getContextPath()%>/comment/commentDelete";
		var data = "commentsNo="+commentNo+"&commentsType="+commentsType;
		$.post(url,data,function(res){
			$(obj).closest("div.comment-box").remove();
			
			if(commentsType == 1 || commentsType == "1"){
				$("input[name=pCommentsNo][value="+commentNo+"]").parent("div.reply").remove();
			}
		});
	}
	
	function renderingDate(str){
		return str.substring(0,10);
	}
	
	function nullCheck(obj){
		return !obj || obj.length == 0;
	}
	
	function renderingComment(res,pCommentNo){
		
		//loginUser = "test0"; // 위 아래 변수명 수정하기
		
		var str =  '<div class="comment-box">'
					+'<input type="hidden" name="commentsNo" value="'+res.commentsNo+'"/>'
					+'<input type="hidden" name="pCommentsNo" value="'+res.pCommentsNo+'"/>'
					+'<span class="cmt-content">'+res.commentsTitle+' </span>';
		
		if(!pCommentNo) str += '<button class="btn-a" onclick="replyToggle(this)">답글</button>';
		
		str += '<div class="comment-footer">'
				+'<span class="cmt-nm">'+nonName(res.name)+' </span>'
				+'<span class="cmt-dt">'+renderingDate(res.commentsDate)+' </span>';

		if(res.id == loginUser){
			str+=	'<span class="cmt-up"  onclick="cmtUpdate(this,'+res.commentsNo+')">수정</span>' 
					+'<span class="cmt-del" onclick="cmtDelete(this,'+res.commentsNo+','+res.commentsType+')">삭제</span>';	
		}				
		str += '</div>';
		
		if(!pCommentNo){
			str += '<div class="cmt-reply">'
				 	+'<input class="comment-input" name="reply" />'
				 	+'<span class="cmt-reply-add" onclick="cmtInsert(this,'+res.reviewNo+','+res.commentsNo+')">등록</span>'
				 	<!--+'<span class="cmt-reply-cle" onclick="replyCancle(this)" >취소</span>'-->
				 	+'</div>'
			;
		}
		
		str +='</div>';
		
		return str;
	}
	
	function goPage(url){
		location.href = url;
	}
	
	function nonName(str){
		if(!str || str == "") return "탈퇴회원";
		return str;
	}
	
	function replyToggle(obj){
		$(obj).siblings("div.cmt-reply").slideToggle();
	}
	function replyCancle(obj){
		$(obj).siblings("input[name=reply]").val("");
		$(obj).parent("div.cmt-reply").slideUp();
	}
	
	function setCmtUpForm(obj){
		<!--$(obj).after($('<span class="cmt-clc" onclick="backCmtUpForm(this)">취소</span>'));-->
		$(obj).siblings('span.cmt-del').hide();<!-- hide() : 안보이게 하는기능  -->
		$(obj).parent().siblings('span.cmt-content').hide();
		var vl = $(obj).parent().siblings('span.cmt-content').text(); <!-- text(); 기존에 작성한 내용이 보이게 해주는 기능 --> 
		$(obj).parent().siblings('span.cmt-content').after($("<input name='cmtUp' value='"+vl+"'/>"));<!--($("<input name='cmtUp' value='"+vl+"'/>")) 쿼리생성 가능 -->
		$(obj).text('확인'); <!-- (obj) 수정버튼이 아니라 확인버튼이 될수있도록! -->
	}
	
	function backCmtUpForm(obj){
		$(obj).siblings('span.cmt-up').text('수정');
		$(obj).siblings('span.cmt-del').show();
		$(obj).parent().siblings("input[name=cmtUp]").remove();
		$(obj).parent().siblings('span.cmt-content').show();
		$(obj).remove();
	}
	
	function cmtUpRes(obj,res){
		$(obj).parent().siblings("input[name=cmtUp]").remove();
		$(obj).parent().siblings('span.cmt-content').show();
		$(obj).text('수정');
		$(obj).siblings('span.cmt-clc').remove();
		$(obj).siblings('span.cmt-del').show();
	}
	
	
</script>
