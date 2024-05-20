<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Java servlets</title>
	<style>
		.body {
			font-family: Arial, 'sans-serif';
		}
		.card {
			display:grid;
			width:70%;
			height:300px;
			justify-self: center;
			place-items: center;
			gap: 1em;
			padding:2rem;
			border-radius:1em;
			box-shadow: rgba(100, 100, 100, 0.3) 0px 1px 4px 0px;
		}
		.a {
			font-size: 1.175em;
			font-size: 1.175em;
			text-decoration: none;
		}
	</style>
</head>
<body class="body">
	<main style="display:grid;width:100%;">
		<h2>Seleccione el programa a utilizar</h2>

		<div class="card">
			<span>
				<a href="/CardonaRodriguezEliasLITC/trip.jsp" class="a">
					Estimador de la hora de llegada de un vuelo
				</a>
			</span>
			<span>
				<a href="/CardonaRodriguezEliasLITC/artifacts.jsp" class="a">
					Creaci&oacute;n y modificaci&oacute;n de componentes de X artefacto
				</a>
			</span>
			<span>
				<a href="/CardonaRodriguezEliasLITC/othersome.jsp" class="a">
					Otro programa
				</a>
			</span>
		</div>
	</main>
</body>
</html>
