/**
 * 
 */
var idValid = 0;
var passValid = 0;
var emailValid = 0;
var passEmailValid = 0;

$(document).ready(function() {
	$("[name=loginFrm]").submit(function() {
		var id = $('#id').val();
		var password = $('#password').val();

		if (/^[a-zA-Z0-9]{4,}$/.test(id) == false) {
			$('#loginMsg').text("아이디를 입력해주세요.").css('color', 'red');
			$('#id').focus();
			return false;
		}
		if (/^[a-zA-Z0-9!@#$$%^&*()]{4,}/.test(password) == false) {
			$('#loginMsg').text("비밀번호를 입력해주세요.").css('color', 'red');
			$('#password').focus();
			return false;
		}
		return true;
	});

	$('#kind').change(function() {
		var state = $('#kind option:selected').val();
		console.log(state);
		if (state == 'C') {
			$('.address').show();
			$('#addressMsg').show();
		} else {
			$('.address').hide();
			$('#addressMsg').hide();
		}
	});


	$('#sendEmail').click(function() {
		var email = $("#email").val();
		
		var key = '';

		$.ajax({
			url: 'http://localhost:9090/donacle/emailCheck',
			type: 'post',
			data: {
				email : email
			},
			success: function(data) {
				alert("인증번호가 발송되었습니다.");
				key = data;
			},
			error: function() {
				console.log(error);
			}
		});
		
		$("#sendEmail").val('인증번호 재발송');
		$("#writeKey").show();
		$("#sendKey").show();

		$('#sendKey').click(function() {
			var writeKey = $('#writeKey').val();

			if (writeKey == key) {
				$('#emailMsg').text("성공적으로 인증되었습니다.").css("color", "green");
				$("#email").attr("readonly", true);
				$("#sendEmail").hide();
				$("#writeKey").hide();
				$('#sendKey').hide();
				emailValid = 1;
			} else {
				$('#emailMsg').text("잘못된 인증번호입니다. 인증번호를 확인하세요.").css("color", "red");
			}
		});
	});


	$('#findPassSendEmail').click(function() {
		var email = $("#findPassEmail").val();


		var passKey = '';

		$.ajax({
			url: 'http://localhost:9090/donacle/emailCheck',
			type: 'post',
			data: {
				email : email
			},
			success: function(data) {
				alert("인증번호가 발송되었습니다.");
				passKey = data;
			},
			error: function() {
				console.log(error);
			}
		});
		$("#findPassSendEmail").val('인증번호 재발송');
		$("#findPassWriteKey").show();
		$("#findPassSendKey").show();

		$('#findPassSendKey').click(function() {
			var passWriteKey = $('#findPassWriteKey').val();

			if (passWriteKey == passKey) {
				$('#findPassEmailMsg').text("성공적으로 인증되었습니다.").css("color", "green");
				$("#findPassEmail").attr("readonly", true);
				$("#findPassSendEmail").hide();
				$("#findPassWriteKey").hide();
				$('#findPassSendKey').hide();
				passEmailValid = 1;
			} else {
				$('#findPassEmailMsg').text("잘못된 인증번호입니다. 인증번호를 확인하세요.").css("color", "red");
			}
		});
	});

	$("[name=memberJoinFrm]").submit(function() {
		var name = $('#name').val();
		var phone = $('#phone').val();
		var birthday = $('#birthday').val();
		var detailAddress = $('#detailAddress').val();

		if (idValid == 1 && passValid == 1 && emailValid == 1) {
			if (name == '') {
				$('#nameMsg').text("이름은 필수입력값입니다.").css("color", "red").focus();
				return false;
			} else if (/^[가-힣]{2,}$/.test(name) == false) {
				$('#nameMsg').text("이름은 한글 두 글자 이상이어야 합니다.").css("color", "red").focus();
				return false;
			} else {
				$('#nameMsg').hide();
			}

			if (phone == '') {
				$('#phoneMsg').text("전화번호는 필수입력값입니다.").css("color", "red").focus();
				return false;
			} else {
				$('#phoneMsg').hide();
			}
			
			if (birthday == '') {
				$('#birthdayMsg').text("생년월일은 필수입력값입니다.").css("color", "red").focus();
				return false;
			} else {
				$('#birthdayMsg').hide();
			}

			if ($('#kind').val() === 'C') {
				if (detailAddress == '') {
					$('#addressMsg').text("구매자이실 경우, 배송지 주소는 필수입력값입니다.").css("color", "red").focus();
					return false;
				} else {
					$('#addressMsg').hide();
				}
			}
			
			return true;
		} else {
			alert("아이디, 비밀번호, 이메일 인증 절차를 확인해주세요.");
			return false;
		}
	});

	$("[name=findPasswordFrm]").submit(function() {

		if (passEmailValid == 1) {
			return true;
		} else {
			alert("이메일 인증 절차를 확인해주세요.");
			return false;
		}
	});
});


function checkId() {
	var id = $("#id").val();
	$.ajax({
		url: 'http://localhost:9090/donacle/idCheck',
		type: 'POST',
		data: {
			id: id
		},
		success: function(data) {
			if (data == 'usable') {
				if (id == "") {
					$('#idMsg').text("아이디를 입력해주세요.").css('color', 'red');
				} else if (/^[a-zA-Z0-9]{4,}$/.test(id) == false) {
					$('#idMsg').text("아이디는 영문/숫자 포함 최소 4자리 이상이어야 합니다.").css("color", "red");
				} else {
					$('#idMsg').text("사용 가능한 아이디입니다.").css('color', 'green');;
					idValid = 1;
				}
			} else {
				$('#idMsg').text("이미 사용중인 아이디입니다.").css('color', 'red');
			}
		},
		error: function() {
			console.log(error);
		}
	});
}

function checkPassword() {
	var password = $('#password').val();
	var checkPass = $('#checkPass').val();
	if (password == "") {
		$('#passMsg').text("비밀번호를 입력해주세요.").css('color', 'red');
	} else if (/^[a-zA-Z0-9!@#$$%^&*()]{4,}/.test(password) == false) {
		$('#passMsg').text("비밀번호는 최소 4자리 이상이어야 합니다.").css('color', 'red');
	} else {
		$('#passMsg').text("사용 가능한 비밀번호입니다.").css('color', 'green');
	}
	if (password != checkPass) {
		$('#checkPassMsg').text("비밀번호가 일치하지 않습니다.").css('color', 'red');
	} else {
		$('#checkPassMsg').text("비밀번호가 일치합니다.").css('color', 'green');
		passValid = 1;
	}
}


function checkNewPassword() {
	var oldPass = $('#oldPass').val();
	var newPass = $('#newPass').val();
	var checkNewPass = $('#checkNewPass').val();
	
	if (newPass == "") {
		$('#newPassMsg').text("비밀번호를 입력해주세요.").css('color', 'red');
	} else if (/^[a-zA-Z0-9!@#$$%^&*()]{4,}/.test(newPass) == false) {
		$('#newPassMsg').text("비밀번호는 최소 4자리 이상이어야 합니다.").css('color', 'red');
	} else {	
		$('#newPassMsg').text("사용 가능한 비밀번호입니다.").css('color', 'green');
	}
	if (newPass != checkNewPass) {
		$('#checkNewPassMsg').text("비밀번호가 일치하지 않습니다.").css('color', 'red');
	} else {
		$('#checkNewPassMsg').text("비밀번호가 일치합니다.").css('color', 'green');
	}
	
}

function findAddress() {
	new daum.Postcode({
		oncomplete: function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 각 주소의 노출 규칙에 따라 주소를 조합한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var addr = ''; // 주소 변수
			var extraAddr = ''; // 참고항목 변수

			//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
			if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				addr = data.roadAddress;
			} else { // 사용자가 지번 주소를 선택했을 경우(J)
				addr = data.jibunAddress;
			}

			// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
			if (data.userSelectedType === 'R') {
				// 법정동명이 있을 경우 추가한다. (법정리는 제외)
				// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
					extraAddr += data.bname;
				}
				// 건물명이 있고, 공동주택일 경우 추가한다.
				if (data.buildingName !== '' && data.apartment === 'Y') {
					extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				}
				// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				if (extraAddr !== '') {
					extraAddr = ' (' + extraAddr + ')';
				}
				// 조합된 참고항목을 해당 필드에 넣는다.
				document.getElementById("subAddress").value = extraAddr;

			} else {
				document.getElementById("subAddress").value = '';
				document.getElementById("subAddress").style.visibility = "hidden";
			}

			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById('zipCode').value = data.zonecode;
			document.getElementById('myAddress').value = addr;

			// 커서를 상세주소 필드로 이동한다.
			document.getElementById('detailAddress').value = '';
			document.getElementById("detailAddress").focus();
		}
	}).open();
}

function deleteMember() {
	if (confirm("회원 탈퇴를 진행합니다. 정말 탈퇴하시겠습니까?")) {
		$(document.memberDeleteFrm).submit();
	}
}

function adminMemberDelete() {
	if (confirm("회원 정보 삭제를 진행합니다. 정말 삭제하시겠습니까?")) {
		$(document.adminMemberDeleteFrm).submit();
	}
}
