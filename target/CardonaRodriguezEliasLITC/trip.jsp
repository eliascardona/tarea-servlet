<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Estimador de la hora de llegada de un vuelo</title>
	<style>
		.body {
			font-family: Arial, 'sans-serif';
		}
		.card {
			display:grid;
			width: 70%;
			height: 300px;
			place-self:center;
			place-items:center;
			padding:2rem;
			border-radius:1em;
			box-shadow: rgba(100, 100, 100, 0.3) 0px 1px 4px 0px;
		}
		small {
			color: #ccc;
			display: block;
		}
		.input {
			display: block;
			margin: 1em;
			padding: 5px 40px 5px 15px;
			border: 0px;
			border-style: none;
			box-shadow: rgba(100, 100, 100, 0.3) 0px 1px 4px 0px;
		}
	</style>
</head>
<body class="body">
	<main style="display:grid;width:100%;">
		<h2>Estimador de la hora de llegada de un vuelo</h2>
		<h3>Todos los calculos se realizan en el backend</h3>
		<h4>
            Front-end envia los datos iniciales al server 
            y recibe la solucion de la operacion solicitada
        </h4>

		<div class="card">
			<span>
				<form id="studentForm">
					<label for="tdate">Date</label>
					<input type="text" id="tdate" name="tdate" placeholder="format hh:mm">
					<!-- br -->

					<label for="distance">Distance of your trip</label>
					<small> *just the number. dist in km</small>
					<input type="number" id="distance" name="distance">
					<!-- br -->


					<label for="speed">Spped of flight</label>
					<small> *just the number. speed in km/hr</small>
					<input type="number" id="speed" name="speed">
					<!-- br -->

					<button type="submit">Submit</button>
				</form>
			</span>

			<span>
				<span>Seg&uacute;n los datos ingresados, tu tiempo de llegada ser&aacute;  -&gt;  </span>
				<span id="loadsHere"></span>
			</span>

			<span id="respHere">
				
			</span>

		</div>


		<script defer>
			const studentForm = document.getElementById("studentForm")
			const respHere = document.getElementById("respHere")
			const loadsHere = document.getElementById("loadsHere")

			let baseOptions = {
				method: "POST",
				headers: {
					"Content-Type": "application/json"
				},
				body: "json"
			}

			async function handleSubmit(evt) {
				let formData = {
					tdate: evt.target.tdate.value,
					distance: parseFloat(evt.target.distance.value),
					speed: parseFloat(evt.target.speed.value)
				}
				let fetchOptions = {
					...baseOptions,
					body: JSON.stringify(formData)
				}

				try {
					const r = await fetch("/CardonaRodriguezEliasLITC/tripServlet", fetchOptions)
					const j = await r.json()
					console.log(j)

					let aux = [...j]
					return aux

				} catch(fetchError) {
					console.error("Error fetching the API: ", fetchError)
				}
			}

			studentForm.addEventListener("submit", async (e) => {
				e.preventDefault()
				let respArr = await handleSubmit(e)

				let jsonString = JSON.stringify(respArr)

				if(jsonString.length > 0) {
					let responsePayload = JSON.parse(jsonString)

					let arrivalTimeObj = {}
					arrivalTimeObj = { ...responsePayload[0] }

					loadsHere.innerText = arrivalTimeObj.arrivalTime


				} else {
					respHere.innerText = "error al obtener la resp. del servidor"
				}

			})
		</script>
	</main>
</body>
</html>


