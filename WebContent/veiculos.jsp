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

	<div class="modal" id="add">
		<div class="modal-dialog" role="document" >
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Adicionar Veículo</h5>
					
					<button type="button" onClick="$('#add').css({'display':'none'});" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					
				</div>
				<form action="execute?control=veiculos&view=Add" method="post">
				<div class="modal-body">

					
						<fieldset>
							<legend>Veículo</legend>
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
										<small id="emailHelp"class="form-text text-muted">Selecione a marca do veículo.</small>
									</div>
								</div>
								<div class="col">
									<div class="form-group">
										Modelo<br> 
										<select class="form-control" name="id_modelo" id="idModelo" onChange="buscaAnoModelo(this.value);">
											<option>Selecione</option>
											<option value="1">Modelo 1</option>
										</select>
										<small id="emailHelp"class="form-text text-muted">Selecione o modelo do veículo.</small>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col">
									<div class="form-group">
										Ano Modelo<br>
										<select class="form-control" name="id_ano_modelo" id="idAnoModelo">
											<option>Selecione</option>
											<option value="1">Modelo 1</option>
										</select>
										<small id="emailHelp" class="form-text text-muted">Selecione o ano do modelo do veículo.</small>
									</div>
								</div>
								<div class="col">
									<div class="form-group">
										Placa<br> <input class="form-control" name="placa" id="placa" aria-describedby="emailHelp" placeholder="Ex. BTG-0101" type="text"> 
										<small id="emailHelp" class="form-text text-muted">Informe a placa do veículo.</small>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col">
									<div class="form-group">
										Ano de fabricação<br>
										<input class="form-control" name="ano_fabricacao" id="anoFabricacao" aria-describedby="emailHelp" placeholder="Ex. 2011" type=""text"">
										<small id="emailHelp"class="form-text text-muted">Informe o ano de fabricação do veículo.</small>
									</div>
								</div>
								<div class="col">
									<div class="form-group">
										Tipo de veículo<br> 
										<select class="form-control" name="tipo" id="tipo">
											<option>Selecione</option>
											<option value="C">Carro</option>
											<option value="M">Moto</option>
											<option value="P">Caminhão/Ônibus</option>
										</select>
										<small id="emailHelp"class="form-text text-muted">Selecione o tipo de veículo.</small>
									</div>
								</div>
							</div>
						</fieldset>

				</div>
				<div class="modal-footer">
				<input type="hidden" name="id" id="id" value="" />
					<button type="submit" class="btn btn-primary">Salvar</button>
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
						<th scope="col">Modelo</th>
						<th scope="col">Placa</th>
						<th scope="col"></th>
						<th scope="col" style="text-align:right;"><button type="button" class="btn btn-primary btn-sm" id="novo_veiculo">+Novo veículo</button></th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${veic}" var="veiculo">
		                <tr style="cursor: pointer;" class="table-info">
		                    <td onclick="buscaEdt(${veiculo.id})" scope="row"><c:out value="${veiculo.id}" /></td>
		                    <td onclick="buscaEdt(${veiculo.id})" scope="row"><c:out value="${veiculo.idModelo}" /></td>
		                    <td onclick="buscaEdt(${veiculo.id})" scope="row"><c:out value="${veiculo.placa}" /></td>
		                	<td colspan="2" style="text-align:right;">
		                    	<button type="button" class="btn btn-danger btn-sm" onClick="confirmaDelete(${veiculo.id})" >Excluir</button>
	                    	
                    		</td>
		                </tr>
		            </c:forEach>
					
				</tbody>
			</table>
		</fieldset>
	</div>
	
	<script type="text/javascript">

	$("#novo_veiculo").on('click', function(){

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

		$.ajax({
			url: "/Java_EE_B%C3%A1sico/json-veiculo?id="+id,
			async: true,
	        dataType: 'json',
			success: function(json){

				$.each(json, function(i, val) {
			        
					$("#"+i).val(val);

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