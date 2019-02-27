function showWaitStatus(){
	if(PF("waitingStatus")){
		PF("waitingStatus").show();
	}
}

function hideWaitStatus(){
	if(PF("waitingStatus")){
		PF("waitingStatus").hide();
	}
}

PrimeFaces.locales['pt_BR'] = {
                closeText: 'Fechar',
                prevText: 'Anterior',
                nextText: 'Próximo',
                currentText: 'Começo',
                monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
                monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun', 'Jul','Ago','Set','Out','Nov','Dez'],
                dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
                dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb'],
                dayNamesMin: ['D','S','T','Q','Q','S','S'],
                weekHeader: 'Semana',
                firstDay: 1,
                isRTL: false,
                showMonthAfterYear: false,
                yearSuffix: '',
                timeOnlyTitle: 'Só Horas',
                timeText: 'Tempo',
                hourText: 'Hora',
                minuteText: 'Minuto',
                secondText: 'Segundo',
                currentText: 'Data Atual',
                ampm: false,
                month: 'Mês',
                week: 'Semana',
                day: 'Dia',
                allDayText : 'Todo Dia'
            };

var removerAcento = (function () {
    var accent_map = {
        'à': 'a', 'á': 'a', 'â': 'a', 'ã': 'a', 'ä': 'a', 'å': 'a', // a
        'ç': 'c',                                                   // c
        'è': 'e', 'é': 'e', 'ê': 'e', 'ë': 'e',                     // e
        'ì': 'i', 'í': 'i', 'î': 'i', 'ï': 'i',                     // i
        'ñ': 'n',                                                   // n
        'ò': 'o', 'ó': 'o', 'ô': 'o', 'õ': 'o', 'ö': 'o', 'ø': 'o', // o
        'ß': 's',                                                   // s
        'ù': 'u', 'ú': 'u', 'û': 'u', 'ü': 'u',                     // u
        'ÿ': 'y'                                                    // y
    };

    return function removerAcento(s) {
        if (!s) { return ''; }
        var ret = '';
        for (var i = 0; i < s.length; i++) {
            ret += accent_map[s.charAt(i)] || s.charAt(i);
        }
        return ret;
    };
} ());

    function pesquisarMenu(field) {
		// Nao realiza a pesquisa quando nao ha nenhum item
		// no menu (estava entrando em um loop infinito)
		var isEmptyMenu = $('#menuform_menu').children('.subMenu').length == 0;
		if(isEmptyMenu) {
			return;
		}
		
		if(!field.val()) {
			$('#limparBtn').hide();
		} else {
			$('#limparBtn').show();
		}
		var searchValue = field.val().toLowerCase();
		var matchSub = false;
		   $('.subMenu').each(function() {
	            var MenuSideValue = removerAcento($.trim($(this).children('a').text()).toLowerCase()),
	            itemValue;
	            
	            if(MenuSideValue.search(searchValue) <0 || searchValue.length === 0) {  
	          	     $(this).removeClass('active-menu-parent destaque-menu');
	                 $(this).children().removeClass('active-menu destaque-menu');

	            	 var Sub = $(this).children('ul').children('li');
	            	 for(var i = 0; i < Sub.length; i++) {   
	                    itemValue = removerAcento($.trim(Sub.eq(i).text()).toLowerCase());
	       
	                     if(itemValue.search(searchValue) >= 0) {
	                         Sub.eq(i).show();
	                         if(searchValue.length != 0){
	                        	 Sub.eq(i).children().addClass('destaque-menu');
	                         }else{
	                        	 	Sub.eq(i).children().removeClass('destaque-menu');
		                     }
	                         matchSub = true;
	                     }
	                     else{
	                    	 Sub.eq(i).children().removeClass('destaque-menu');
	                         Sub.eq(i).hide();
	                     }
	                 }
	            	 if(matchSub) {
	                     $(this).show();
	                     if(searchValue.length != 0){
	                  	   $(this).addClass('active-menu-parent');
	                    	 $(this).children().addClass('active-menu');
	            	 	 }else{
	            	 		   $(this).removeClass('active-menu-parent');
		                    	 $(this).children().removeClass('active-menu');
			            	 }
	                     matchSub = false;
	                 }
	                 else {
	                     $(this).hide();
	                 }
	            }
	            else {
	                $(this).children().show();
	                $(this).show();
	                if(searchValue.length != 0){
	                   $(this).add- Class('active-menu-parent destaque-menu');
	                   $(this).children().addClass('active-menu destaque-menu');
	                }
	                var Sub = $(this).children('ul').children('li');
	            	 for(var i = 0; i < Sub.length; i++) { 
	            		 Sub.eq(i).show();
	            		 Sub.eq(i).children().removeClass('destaque-menu');
	            	 }
	            }

		   });
		
		// verifica se nao encontrou nenhum item
		var itemVisible = $("li[role='menuitem']:visible");
		if(!itemVisible.length) {
			// se nao encontrou, exibe o menu completo
			var x = field.val();
			field.val('');
			pesquisarMenu(field);
			field.val(x);
			$('#limparBtn').show();
			field.addClass('ui-state-error');
		} else {
			field.removeClass('ui-state-error');
		}
	};

	function pesquisarMenu2(field) {
		if(!field.val()) {
			limparPesquisa2(field);
			return;
		} else {
			$('#limparBtn').show();
		}
		var searchValue = removerAcento(field.val().toLowerCase());
		var listaSubMenu = $('#menuform_menu').find('.subMenu');
		listaSubMenu.each(function() {
			var textoSubMenuFilhosPai = removerAcento($.trim($(this).parent('ul').text()).toLowerCase());
			var textoSubMenuAtual = removerAcento($.trim($(this).children('a').text()).toLowerCase());
			var textoSubMenuFilhos = removerAcento($.trim($(this).children('ul').text()).toLowerCase());
			var isVisibleSubMenuPai = $(this).parent('ul').parent('li').is(':visible');
			var encontrouSubMenuPai = $(this).parent('ul').parent('li').children('a').hasClass('destaque-menu');
			var encontrouSubMenuFilhosPai = textoSubMenuFilhosPai.search(searchValue) >= 0;
			var encontrouSubMenuAtual = textoSubMenuAtual.search(searchValue) >= 0;
			var encontrouSubMenuFilhos = textoSubMenuFilhos.search(searchValue) >= 0;
			if(encontrouSubMenuAtual || encontrouSubMenuFilhos) {
				$(this).show();
				if(encontrouSubMenuAtual) {
					$(this).children('a').addClass('destaque-menu');
				} else {
					$(this).children('a').removeClass('destaque-menu');
				}
				if(encontrouSubMenuFilhos) {
					$(this).addClass('active-menu-parent');
					$(this).children().addClass('active-menu');
				} else {
					$(this).removeClass('active-menu-parent');
					$(this).children().removeClass('active-menu');
				}
			} else if(encontrouSubMenuPai || (isVisibleSubMenuPai && !encontrouSubMenuFilhosPai)) {
				$(this).show();
				$(this).removeClass('active-menu-parent');
				$(this).children().removeClass('active-menu');
				$(this).children('a').removeClass('destaque-menu');
			} else {
				$(this).hide();
				$(this).removeClass('active-menu-parent');
				$(this).children().removeClass('active-menu');
				$(this).children('a').removeClass('destaque-menu');
			}
			var listaMenuItem = $(this).children('ul').children('li').children('.menuItem');
			listaMenuItem.each(function() {
				var textoMenuItem = removerAcento($.trim($(this).text()).toLowerCase());
				var encontrouMenuItem = textoMenuItem.search(searchValue) >= 0;
				var encontrouSubMenuPaiItem = $(this).parent('li').parent('ul').parent('li').children('a').hasClass('destaque-menu');
				var isVisibleSubMenuPaiItem = $(this).parent('li').parent('ul').parent('li').is(':visible');
				if(encontrouMenuItem) {
					$(this).show();
					$(this).addClass('destaque-menu');
				} else if(encontrouSubMenuPaiItem || (isVisibleSubMenuPaiItem && !encontrouSubMenuFilhos)) {
					$(this).show();
					$(this).removeClass('destaque-menu');
				} else {
					$(this).hide();
					$(this).removeClass('destaque-menu');
				}
			});
		});
		// verifica se nao encontrou nenhum item
		var itemVisible = $("li[role='menuitem']:visible");
		if(!itemVisible.length) {
			// se nao encontrou, exibe o menu completo
			resetarMenu();
			field.addClass('ui-state-error');
		} else {
			field.removeClass('ui-state-error');
		}
	}

	function limparPesquisa(field) {
		// limpa o cookie que armazena o estado do menu
		$.removeCookie('adamantium_expandeditems', {path:'/'});
		// limpa o campo de busca e restaura o menu para o estado inicial
		field.val('');
		pesquisarMenu(field);
		// atualiza o valor do campo de pesquisa no bean (menuBean.textoBusca)
		updatePesquisa();
		
		$('#limparBtn').hide();
		field.focus();
	}

	function limparPesquisa2(field) {
		// limpa o cookie que armazena o estado do menu
		$.removeCookie('adamantium_expandeditems', {path:'/'});
		// limpa o campo de busca e restaura o menu para o estado inicial
		field.val('');
		resetarMenu();
		// atualiza o valor do campo de pesquisa no bean (menuBean.textoBusca)
		updatePesquisa();
		
		$('#limparBtn').hide();
		field.removeClass('ui-state-error');
		field.focus();
	}

	function resetarMenu() {
		var listaSubMenu = $('#menuform_menu').find('.subMenu');
		listaSubMenu.each(function() {
			$(this).show();
			$(this).removeClass('active-menu-parent');
			$(this).children().removeClass('active-menu');
			$(this).children('a').removeClass('destaque-menu');
			var listaMenuItem = $(this).children('ul').children('li').children('.menuItem');
			listaMenuItem.each(function() {
				$(this).show();
				$(this).removeClass('destaque-menu');
			});
		});
	}

	function removerSubMenuSemMenuItem() {
		var listaSubMenu = $('#menuform_menu').find('.subMenu');
		listaSubMenu.each(function() {
			var listaItensMenu = $(this).find('.menuItem');
			if(!listaItensMenu.length) {
				$(this).remove();
			}
		});
	}

$(document).ready(function() {
	// limpa o cookie que armazena o estado
	// do menu ao carregar a pagina inicial
	if(window.location.pathname == '/fiplangma/home') {
		$.removeCookie('adamantium_expandeditems', {path:'/'});
		$.removeCookie('layout_menu_static_inactive', {path:'/'});
	}

	removerSubMenuSemMenuItem();
	var pesquisaMenu = $('#menuform_menuPesquisa');
	if(pesquisaMenu){
		pesquisaMenu.on('keypress', function(e) {
			if (e.keyCode == 13) { // enter key
				e.preventDefault();
			}
		});
		pesquisaMenu.on('keyup', function(e) {
			if (e.keyCode == 27) { // esc key
				limparPesquisa2(pesquisaMenu);
			} else {
				pesquisarMenu2(pesquisaMenu);
			}
		});
		$('#limparBtn').click(function() {
			limparPesquisa2(pesquisaMenu);
		});
		if(pesquisaMenu.val()) {
			pesquisarMenu2(pesquisaMenu);
		}
	}
});



var popupBlockerChecker = {
        check: function(popup_window){
            var _scope = this;
            if (popup_window) {
                if(/chrome/.test(navigator.userAgent.toLowerCase())){
                    setTimeout(function () {
                        _scope._is_popup_blocked(_scope, popup_window);
                     },500);
                }else{
                    popup_window.onload = function () {
                        _scope._is_popup_blocked(_scope, popup_window);
                    };
                }
            }else{
                _scope._displayError();
            }
        },
        _is_popup_blocked: function(scope, popup_window){
            if ((popup_window.innerHeight > 0)==false){ scope._displayError(); }
        },
        _displayError: function(){
	        PF('popBloqueadoWV').show();
        }
};

$.mask.definitions['A']= "[A-Z]";
$.mask.definitions['a']= "[a-z,A-Z]";


function FormataValor(campo, tammax, teclapres) {

	var tecla = teclapres.keyCode;
	var vr = campo.value;
	vr = vr.replace("/", "");
	vr = vr.replace("/", "");
	vr = vr.replace(",", "");
	vr = vr.replace(".", "");
	vr = vr.replace(".", "");
	vr = vr.replace(".", "");
	vr = vr.replace(".", "");
	tam = vr.length;

	if (tam < tammax && tecla != 8) {
		tam = vr.length + 1;
	}

	if (tecla == 8) {
		tam = tam - 1;
	}

	if (tecla == 8 || (tecla >= 48 && tecla <= 57) || (tecla >= 96 && tecla <= 105)) {
		if (tam <= 1) {
			campo.value = vr;
		}
		tam = tam - 1;
		if ((tam > 1) && (tam <= 5)) {
			campo.value = vr.substr(0, tam - 1) + ',' + vr.substr(tam - 1, tam);
		}

		if ((tam > 5) && (tam <= 8)) {
			campo.value = vr.substr(0, tam - 1) + ',' + vr.substr(tam - 1, tam);
		}
	}
}

function onkeypressTableEdit() {
	if(event.keyCode == 13) { // enter key
		jQuery(event.srcElement).closest('tr').find('.ui-row-editor-check').click();
		event.preventDefault()
	}
}