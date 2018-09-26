<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="css/bootstrap.css" type="text/css" media="screen" />

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<title>Veículos</title>
</head>
<body>
	<jsp:include page = "layout/cabecalho.jsp"/>
	
	<c:if test="${sucesso == true}">
		<div class="alert alert-dismissible alert-success" id="alert" style="position: fixed; bottom: 0; width:100%; text-align:center;" >
			<h5>Veículo salvo com sucesso!</h5>
		</div>
	</c:if>
	
	<c:if test="${sucesso == false}">
		<div class="alert alert-dismissible alert-danger" id="alert" style="position: fixed; bottom: 0; width:100%; text-align:center;" >
			<h5>Houve um erro ao salvar o veículo!</h5>
		</div>
	</c:if>

	<div class="modal" id="add">
		<div class="modal-dialog" role="document" >
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Adicionar Veículo</h5>
					
					<button type="button" onClick="$('#add').css({'display':'none'});" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					
				</div>
				<form action="execute?control=veiculos&view=Add" method="post" id="formVeiculo">
				<div class="modal-body">
						<fieldset>
							<div class="row">
								<div class="col">
									<div class="form-group">
										Marca<br> 
										<select class="form-control" name="id_marca" id="idMarca" onChange="buscaModelo(this.value);">
											<option value="">Selecione</option>
											<c:forEach items="${marcas}" var="mar">
												<option value="${mar.id}"><c:out value="${mar.nome}" /></option>
											</c:forEach>
										</select>
										<small id="idMarcaHelp" class="form-text text-muted">Selecione a marca do veículo.</small>
									</div>
								</div>
								<div class="col">
									<div class="form-group">
										Modelo<br> 
										<select class="form-control" name="id_modelo" id="idModelo" onChange="buscaAnoModelo(this.value);">
											<option value="">Selecione</option>
										</select>
										<small id="idModeloHelp"class="form-text text-muted">Selecione o modelo do veículo.</small>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col">
									<div class="form-group">
										Ano Modelo<br>
										<select class="form-control" name="id_ano_modelo" id="idAnoModelo">
											<option value="">Selecione</option>
										</select>
										<small id="idAnoModeloHelp" class="form-text text-muted">Selecione o ano do modelo do veículo.</small>
									</div>
								</div>
								<div class="col">
									<div class="form-group">
										Placa<br> <input class="form-control" name="placa" id="placa" aria-describedby="placaHelp" placeholder="Ex. BTG-0101" type="text"> 
										<small id="placaHelp" class="form-text text-muted">Informe a placa do veículo.</small>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col">
									<div class="form-group">
										Ano de fabricação<br>
										<input class="form-control" name="ano_fabricacao" id="anoFabricacao" aria-describedby="anoFabricacaoHelp" placeholder="Ex. 2011" type="text">
										<small id="anoFabricacaoHelp"class="form-text text-muted">Informe o ano de fabricação do veículo.</small>
									</div>
								</div>
								<div class="col">
									<div class="form-group">
										Tipo de veículo<br> 
										<select class="form-control" name="tipo" id="tipo">
											<option value="">Selecione</option>
											<option value="C">Carro</option>
											<option value="M">Moto</option>
											<option value="P">Caminhão/Ônibus</option>
										</select>
										<small id="tipoHelp"class="form-text text-muted">Selecione o tipo de veículo.</small>
									</div>
								</div>
							</div>
						</fieldset>

				</div>
				<div class="modal-footer">
				<input type="hidden" name="id" id="id" value="" />
					<button type="button" class="btn btn-primary" onClick="valida();">Salvar</button>
				</div>
				
				</form>
			</div>
		</div>
	</div>
	<div class="container">
		<fieldset>
			<legend>Veículos </legend>
				
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">MODELO</th>
						<th scope="col">PLACA</th>
						<th scope="col"></th>
						<th scope="col" style="text-align:right;"><button type="button" class="btn btn-primary btn-sm" id="novo_veiculo">+Novo veículo</button></th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${veic}" var="veiculo">
						<tr style="cursor: pointer;" class="table-info">
							<td onclick="buscaEdt(${veiculo.id});" scope="row">${veiculo.id}</td>
							<td onclick="buscaEdt(${veiculo.id});" scope="row">${veiculo.getIdMarca().getNome()} - ${veiculo.getIdModelo().getNome()} - ${veiculo.getIdAnoModelo().getNome()}</td>
							<td onclick="buscaEdt(${veiculo.id});" scope="row">${veiculo.placa}</td>
							<td colspan="2" style="text-align: right;">
								<button type="button" class="btn btn-danger btn-sm" onClick="confirmaDelete(${veiculo.id})">Excluir</button>

							</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</fieldset>
	</div>
	
	<script type="text/javascript">


	function valida(){

		$(".text-muted").removeClass("text-muted");
		$(".text-danger").removeClass("text-danger");
		$(".is-invalid").removeClass("is-invalid");

		var arrErro = new Array();
		var arrCerto = new Array();

		if($("#idMarca").val() == ""){
			arrErro.push("idMarca");
		}else{
			arrCerto.push("idMarca");
		}

		if($("#idModelo").val() == ""){
			arrErro.push("idModelo");
		}else{
			arrCerto.push("idModelo");
		}

		if($("#idAnoModelo").val() == ""){
			arrErro.push("idAnoModelo");
		}else{
			arrCerto.push("idAnoModelo");
		}

		if($("#placa").val() == ""){
			arrErro.push("placa");
		}else{
			arrCerto.push("placa");
		}

		if($("#anoFabricacao").val() == ""){
			arrErro.push("anoFabricacao");
		}else{
			arrCerto.push("anoFabricacao");
		}

		if($("#tipo").val() == ""){
			arrErro.push("tipo");
		}else{
			arrCerto.push("tipo");
		}

		if(arrErro.length > 0){
			for(x in arrErro){
				$("#"+arrErro[x]).addClass("is-invalid");
				$("#"+arrErro[x]).next().addClass("text-danger");
			}
		}

		if(arrCerto.length > 0){
			for(x in arrCerto){
				$("#"+arrCerto[x]).addClass("is-valid");
				$("#"+arrCerto[x]).next().addClass("text-success");
			}
		}

		if(arrErro.length <= 0){
			$("#formVeiculo").submit();
		}
		
	}

	$(document).ready(function(){

		$(".text-danger").css("display", "none");

		if($("#alert").attr("id") == "alert"){
			 setTimeout(function(){
				 $("#alert").fadeOut(1000)
			 }, 3000);
		}
		
	});

	$("#novo_veiculo").on('click', function(){

		$(":text, select").next().removeClass("text-muted");
		$(":text, select").next().removeClass("text-danger");
		$(":text, select").next().removeClass("text-success");
		$(":text, select").next().addClass("text-muted");

		$(":text, select").removeClass("is-invalid");
		$(":text, select").removeClass("is-valid");
		
		$(":text, select").val("");
		
		$("#add").css({"display":"block"});
		
	});

	function buscaAnoModelo(idModelo){

		$.ajax({
			url: "/Java_EE_Básico/json-fipe?id_modelo="+idModelo,
			async: true,
	        dataType: 'json',
			success: function(json){

				var options = "<option value=''>Selecione</option>";
				
				$.each(json, function(i, val) {
			        
					options += "<option alt='"+val.valor+"' value='"+val.id+"'>"+val.nome+" - R$ "+val.valor+",00</option>";

			    });
	
				$("#idAnoModelo").html(options);
	        
	    	}
    	
		});
		
	}
	

	function buscaModelo(idMarca){

		$.ajax({
			url: "/Java_EE_Básico/json-fipe?id_marca="+idMarca,
			async: true,
	        dataType: 'json',
			success: function(json){

				var options = "<option value=''>Selecione</option>";
				
				$.each(json, function(i, val) {
			        
					options += "<option value='"+val.id+"'>"+val.nome+"</option>";

			    });
	
				$("#idModelo").html(options);
	        
	    	}
    	
		});
		
	}
	

	function buscaEdt(id){

		$(":text, select").next().removeClass("text-muted");
		$(":text, select").next().removeClass("text-danger");
		$(":text, select").next().removeClass("text-success");
		$(":text, select").next().addClass("text-muted");

		$(":text, select").removeClass("is-invalid");
		$(":text, select").removeClass("is-valid");

		$.ajax({
			url: "/Java_EE_B%C3%A1sico/json-veiculo?id="+id,
			async: true,
	        dataType: 'json',
			success: function(json){

				$.each(json, function(i, val) {

					if(typeof(val) === "object"){

						if(i == "idMarca"){
							buscaModelo(val.id);
						}

						if(i == "idModelo"){
							buscaAnoModelo(val.id);
						}

						$( document ).ajaxComplete(function(){
							$("#"+i).val(val.id);
						});
						
					}else{

						$("#"+i).val(val);
						
					}

			    });
	
				$("#add").css({"display":"block"});
	        
	    	}
    	
		});

	}

	function confirmaDelete(id){
		
		if(confirm("Deseja realmente excluir este veículo")){
			
			window.location.href="execute?control=veiculos&view=Delete&id="+id;
			
		}
		
	}
	
	</script>

</body>
</html>