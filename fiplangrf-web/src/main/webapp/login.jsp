<%@page import="javax.ejb.Init"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<link rel="icon" type="image/x-icon" href="resources/images/favicon.ico" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta charset="utf-8">
<meta name="_csrf" content="${_csrf.token}" />
<script src="resources/jquery/jquery-1.12.1.min.js"></script>
<script src="resources/jquery/jquery-ui-1.11.4/jquery-ui.min.js"></script>
<script src="resources/jquery/validate-1.1.2/jquery-validate.min.js"></script>
<script src="resources/jquery/jquery.mask.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css"
	href="resources/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="resources/adamantium-layout/css/font-awesome.css">

<!-- Website CSS style -->
<link rel="stylesheet" type="text/css"
	href="resources/login/css/theme.css">

<!-- Fonts -->
<link href='https://fonts.googleapis.com/css?family=Passion+One'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Oxygen'
	rel='stylesheet' type='text/css'>

<link rel="stylesheet"
	href="resources/jquery/jquery-ui-1.11.4/jquery-ui.css">
<link rel="stylesheet"
	href="resources/jquery/jquery-ui-1.11.4/jquery-ui.theme.css">
<link rel="stylesheet"
	href="resources/jquery/jquery-ui-1.11.4/jquery-ui.structure.css">
<title>Fiplan - GMA</title>



<!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
<script>
	function validateCPF() {
	    var Soma;
	    var Resto;
	    var cpf = $("#login").val().replace(/\D/g,'');
	    Soma = 0;	    
	    <c:if test="${ambiente eq 'des'}">
	    	if(cpf == '99999999999'){
		    	return true;
		    }
	    </c:if>
		if (cpf == "00000000000") {
			return false;
		}
		for (i=1; i<=9; i++) Soma = Soma + parseInt(cpf.substring(i-1, i)) * (11 - i);
		Resto = (Soma * 10) % 11;
		
	    if ((Resto == 10) || (Resto == 11))  Resto = 0;
	    if (Resto != parseInt(cpf.substring(9, 10)) ) return false;
		
		Soma = 0;
	    for (i = 1; i <= 10; i++) Soma = Soma + parseInt(cpf.substring(i-1, i)) * (12 - i);
	    Resto = (Soma * 10) % 11;
		
	    if ((Resto == 10) || (Resto == 11))  Resto = 0;
	    if (Resto != parseInt(cpf.substring(10, 11) ) ) {
		    alert("O Nº CPF informado é inválido.");
		    $("#login").val('');
		    $("#login").focus();
		    return false;
	    } else {
	    	return true;
	    }
	}

	function PopupCenter(url, title, w, h) {
		// Fixes dual-screen position Most browsers Firefox
		var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : window.screenX;
		var dualScreenTop = window.screenTop != undefined ? window.screenTop : window.screenY;

		var width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
		var height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;

		var left = ((width / 2) - (w / 2)) + dualScreenLeft;
		var top = ((height / 2) - (h / 2)) + dualScreenTop;
		var newWindow = window.open(url, title, 'scrollbars=yes, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);

		// Puts focus on the newWindow
		if (window.focus) {
			newWindow.focus();
		}
	}

	$(document).ready(function(){
		var $campo = $("#login");
		$campo.mask('000.000.000-00', {reverse: true});
		$campo.blur(function(){
			validateCPF();
		});

		if($campo.val() != '') {
			$("#senha").focus();
		} else {
			$campo.focus();
		}
	});
</script>
<style>
@font-face {
	font-family: 'FontAwesome';
	src: url('resources/adamantium-layout/fonts/fontawesome-webfont.eot');
	src: url('resources/adamantium-layout/fonts/fontawesome-webfont.eot')
		format('embedded-opentype'),
		url('resources/adamantium-layout/fonts/fontawesome-webfont.woff2')
		format('woff2'),
		url('resources/adamantium-layout/fonts/fontawesome-webfont.woff')
		format('woff'),
		url('resources/adamantium-layout/fonts/fontawesome-webfont.ttf')
		format('truetype'),
		url('resources/adamantium-layout/fonts/fontawesome-webfont.svg')
		format('svg');
	font-weight: normal;
	font-style: normal;
}

.fa-lock.fa-lg {
	font-size: 16px;
}
</style>
</head>
<body>

	<div class="background">
		<div class="ambiente"></div>
	</div>
	<div class="container loginContainer">
		<div class="row main">
			<div class="panel-heading">
				<div class="panel-title text-center">
					<img src="resources/adamantium-layout/images/logo2b.png">
					<!-- <hr /> -->
				</div>
			</div>
			<div class="main-login main-center">

				<c:url value="/login" var="loginUrl" />

				<form action="${loginUrl}" class="login" method="post">
					<div class="form-group">
						<label for="login" class="cols-sm-2 control-label">Login </label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-user fa"
									aria-hidden="true"></i></span> <input type="text" name="login"
									id="login" value='<c:out value="${sessionScope.login}"/>'
									class="form-control">
								<c:remove var="login" scope="session" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="senha" class="cols-sm-2 control-label">Senha</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-lock fa-lg" aria-hidden="true"></i></span> <input
									type="password" name="senha" placeholder="Insira sua senha"
									id="senha" value="" class="form-control">
							</div>
						</div>
					</div>
					<c:if test="${param.error != null}">
						<div class="alert alert-danger" role="alert">
							<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
							<c:out value="${appInfoBean.authenticationInfo.msg}" />
						</div>
					</c:if>
					
					<c:if test="${param.logout != null}">
						<div class="alert alert-info" role="alert">
							<i class="fa fa-exclamation-circle" aria-hidden="true"></i> <span
								class="sr-only">Informação:</span> Deslogado.
						</div>
					</c:if>

					<c:if test="${param.maxSessionLimit != null}">
						<div class="alert alert-warning" role="alert">
							<i class="fa fa-exclamation-circle" aria-hidden="true"></i> <span
								class="sr-only">Aviso:</span> O usuário já se encontra logado em
							outro browser ou máquina.
						</div>
					</c:if>

					<div class="form-group ">
						<input type="hidden" name="${_csrf.parameterName}"
							id="${_csrf.parameterName}" value="${_csrf.token}" />

						<button id="btnSubmit" type="submit"
							class="btn btn-primary btn-lg btn-block login-button"
							onclick="return validate()">Entrar</button>
					</div>

					<%-- <div class="login-register">
						<a href="#" onclick="PopupCenter('public/senha/esqueci;jsessionid=<%= session.getId() %>', 'Resetar senha', '600', '400');return false;">Resetar Senha</a>
					</div> --%>

				</form>
			</div>
		</div>
	</div>

	<div id="login-footer">
		<div id="divVersao">Versão: ${appInfoBean.version} -
			${appInfoBean.buildTime}</div>
	</div>

	<script type="text/javascript"
		src="resources/bootstrap/js/bootstrap.js"></script>

	<script>
		var ambiente = '<%=getServletContext().getAttribute("ambiente")%>';
		if (ambiente == "int") {
			$(".ambiente").css("background-color", "#1B3281");
			$(".ambiente").css("border-bottom", "3px solid #fff");
			$(".ambiente").text('Integração');
		} else if (ambiente == "hom") {
			$(".ambiente").css("background-color", "#900");
			$(".ambiente").css("border-bottom", "3px solid #fff");
			$(".ambiente").text('Homologação');
		}
	</script>
</body>
</html>