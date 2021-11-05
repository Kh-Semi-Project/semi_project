<%@page import="mvc.homepage_introduce.model.vo.Statistics"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/homepage_introduce/header.jsp" %>
<% 
	List<Statistics> introducelist = (List<Statistics>)request.getAttribute("introduce");
%>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<div class="layout-content">
            <div id="content_menu">
                <nav id="gnb">
                    <ul>
                        <li><a href="#content1">BRAND</a></li>
                        <li><a href="#content2">WHY</a></li>
                        <li><a href="#content3">HOW</a></li>
                        <li><a href="#content4">ABOUT US</a></li>
                    </ul>
                </nav>
            </div>
            <div class="content-wrap">
                <section id="content1">
                    <div id="content1_box">
                        <span class="title">donacle</span>
                        <br>
                        <span id="sub-title">: donation + cycle</span>
                        <div id="content1_box_content">
                            작은 민들레 홀씨가 바람에 날려 민들레밭을 이루듯, 선한 영향력이 민들레 홀씨처럼 곳곳에 널리 퍼지면<br>
                            어제보다 오늘이, 오늘보다 내일이 더 따뜻한 행복한 세상을 만들 수 있다고 생각합니다.<br><br>
                            "donacle"은 donation(후원)과 cycle(순환)이 합쳐진 단어로,<br>
                            이러한 선한 영향력이 순환되기를 바라는 마음에서 생겨난 브랜드입니다.<br><br>
                            저희 donacle이 도움의 손길이 필요한 모든 분들에게 민들레밭처럼 따뜻한 공간이 되기를 바랍니다.
                        </div>
                        <div id=content1_box_img></div>
                    </div>
                </section>
                <section id="content2">
                    <div class="content2_box">
                        <div class="content2_wrapper">
                            <p id="content2_title"><mark style="background-Color:#faeeb7;">Why</mark></p>
                            <p id="content2_sub_title">donacle을 이용해야 할까요?</p>
                            <div id="why_content">
                            	정답은 '직접 후원을 할 수 있다'는 점입니다.<br>
                                오른쪽 표를 보면 알 수 있듯, 다양한 기부형태들 중 대부분은 단체나 기관을 통한 기부가 차지하고 있고,
                                '대상자에게 직접' 후원을 하는 경우는 현저히 낮은 비율을 차지하고 있습니다. <br><br>
                                저희는 바로 여기에서 아이디어를 얻었습니다.<br>
                                도움이 필요한 누구나 판매자가 되어 사용자들에게 상품을 판매하고 후원을 받을 수 있는,
                                또한 사용자들이 복잡한 절차 없이 직접 후원을 할 수 있는 그런 공간을 만들어 보면 어떨까 하고요!<br><br>
                                어렵게만 생각했던 첫 걸음을 donacle과 함께 해 보세요. 😊      
                            </div>
                        </div>
                        <div class="myChart_box">
                            <canvas id="myChart"></canvas>
                            <p><2020년 서울시 기부 경험여부 및 기부형태 통계></2020></p>
                        </div>
                    </div>
                </section>
                <section id="content3">
                    <div class="content3_box">
                        <input type="radio" name="pos" id="pos1" checked>
                        <input type="radio" name="pos" id="pos2">
                        <ul>
                            <div class="content3_wrapper">
                                <p class="method_no">01</p>
                                <div class="method_content">
                                    <p class="method_title">How to Donate</p>
                                    <span class="mc">1. 마음에 드는 상품을 장바구니에 담아주세요.</span><br>
                                    <span class="mc">2. 구매하기 버튼을 눌러 결제를 진행해주세요.</span><br>
                                    <span class="mc">3. 결제 완료 후, 제품 구매 확정시 제품 가격의 <mark>10%</mark>가 후원됩니다.</span>
                                </div>
                            </div>
                            <div class="content3_wrapper">
                                <p class="method_no">02</p>
                                <div class="method_content">
                                    <p class="method_title">How to Donate</p>
                                    <span class="mc">1. DONATION 카테고리를 클릭해주세요.</span><br>
                                    <span class="mc">2. 후원하기 버튼을 클릭하시면 클릭 한 번에 <mark>100원</mark>의 후원금이 적립됩니다.
                                                        (하루에 5번 한정)</span>
                                </div>
                            </div>
                        </ul>
                        <p class="pos">
                            <label for="pos1"></label>
                            <label for="pos2"></label>
                        </p>
                    </div>
                </section>
                <section id="content4">
                    <div class="content4_box">
                        <div id="content4-1">
                            <p>jeong hyeon Kim</p>
                            <div id="showDetail1">
                                - 제품 판매 MENU<br>
                                - 상품 판매/상품 문의<br>
                                - 주문/배송
                            </div>
                        </div>
                        <div id="content4-2">
                            <p>jin ki Yeom</p>
                            <div id="showDetail2">
                                - 후원 MENU<br>
                                - 장바구니
                            </div>
                        </div>
                        <div id="content4-3">
                            <p>chae a Oh</p>
                            <div id="showDetail3">
                                - 제품 후기 MENU (제품 후기글/ 후기댓글)
                            </div>
                        </div>
                        <div id="content4-4">
                            <p>chan woo Kim</p>
                            <div id="showDetail4">
                                - 로그인/회원가입<br>
                                - 아이디/비밀번호 찾기<br>
                                - 회원관리
                            </div>
                        </div>
                        <div id="content4-5">
                            <p>seon young Choi</p>
                            <div id="showDetail5">
                                - 홈페이지 틀 디자인<br>
                                - 홈페이지 소개 MENU
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>
            </div>
        </div>
<script>
const labels = [];
const datas = [];

$(document).ready(function() {
	//스크롤시 헤더메뉴 상단 고정
    $(window).scroll(function(){
        scrollGnb();
    });
	
  	//링크 클릭시 헤당 섹션으로 부드럽게 이동시키기
    $(document).on('click','#gnb a',function(event){
        var headerHeight = $('#content_menu').outerHeight() + $('header').outerHeight();   
        event.preventDefault();
	        $("html,body").animate({
	        scrollTop : $(this.hash).offset().top - headerHeight
        },1000)
    });
  
	//스크롤시 'BRAND' 메뉴 애니메이션
	$(window).scroll( function(){
        $('#content1_box_content').each( function(i){
            
            var bottom_of_element = $(this).offset().top + $(this).outerHeight();
            var bottom_of_window = $(window).scrollTop() + $(window).height();
            
            if( bottom_of_window > bottom_of_element ){
                $(this).animate({'opacity':'1','margin-left':'40px'},1800);
            }
        }); 
    });
		
	
	a();
	const data = {
			labels: labels,
			datasets: [{
				label: 'My First dataset',
				backgroundColor: ['rgb(233, 232, 165)',
	          				  'rgb(173, 173, 109)',
	          				  'rgb(151, 151, 107)',
	          				  'rgb(126, 126, 90)',
	          				  'rgb(105, 105, 75)',
	          				  'rgb(82, 82, 59)',
	          				  'rgb(61, 61, 44)'],
				hoverBackgroundColor:[
					'rgb(233, 232, 165)',
				  	'rgb(173, 173, 109)',
				  	'rgb(151, 151, 107)',
				  	'rgb(126, 126, 90)',
				  	'rgb(105, 105, 75)',
				  	'rgb(82, 82, 59)',
				  	'rgb(61, 61, 44)'
				],
	        	borderColor: [
				  	'rgb(233, 232, 165)',
				  	'rgb(173, 173, 109)',
				  	'rgb(151, 151, 107)',
				  	'rgb(126, 126, 90)',
				  	'rgb(105, 105, 75)',
				  	'rgb(82, 82, 59)',
				  	'rgb(61, 61, 44)'],
				hoverBorderColor:[
						'rgb(233, 232, 165)',
					  	'rgb(173, 173, 109)',
					  	'rgb(151, 151, 107)',
					  	'rgb(126, 126, 90)',
					  	'rgb(105, 105, 75)',
					  	'rgb(82, 82, 59)',
					  	'rgb(61, 61, 44)'
					],
				data: datas
			}]
	};
	
	const config = {
		  type: 'pie',
		  data: data,
		  options: {
			  responsive: false
		  }
	};

	const myChart = new Chart(
		  document.getElementById('myChart'),
		  config
	);
	
    $(function() {
    	$('#showDetail1').hide();
    	$('#showDetail2').hide();
    	$('#showDetail3').hide();
    	$('#showDetail4').hide();
    	$('#showDetail5').hide();
        $('#content4-1').hover(function(){
            $('#showDetail1').fadeIn();
        }, function() {
            $('#showDetail1').fadeOut();
        });
        $('#content4-2').hover(function(){
            $('#showDetail2').fadeIn();
        }, function() {
            $('#showDetail2').fadeOut();
        });
        $('#content4-3').hover(function(){
            $('#showDetail3').fadeIn();
        }, function() {
            $('#showDetail3').fadeOut();
        });
        $('#content4-4').hover(function(){
            $('#showDetail4').fadeIn();
        }, function() {
            $('#showDetail4').fadeOut();
        });
        $('#content4-5').hover(function(){
            $('#showDetail5').fadeIn();
        }, function() {
            $('#showDetail5').fadeOut();
        });
    });
});

function a(){
	var test_rate = 0;
	var test_type = 0;
	<%
		for(Statistics s : introducelist){
	%>
		test_rate = <%=s.getDe_rate()%>;
		datas.push(test_rate);
		test_type = "<%=s.getDe_type()%>";//() 함수인식 << undefinded <<
		labels.push(test_type);
	<%}%>
}
//헤더메뉴 상단고정 함수
function scrollGnb(){
    var windowTop = $(window).scrollTop();
    if(windowTop > 0) {
            $("#content_menu").addClass('scroll');
    } else {
            $("#content_menu").removeClass('scroll');
    }
}
scrollGnb();//새로고침, 최초 접속시에 확인

</script>
<%@ include file="/WEB-INF/views/homepage_introduce/footer.jsp" %>