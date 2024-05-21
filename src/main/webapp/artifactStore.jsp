<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="./static/assets/css/styles.css">
    <title>Artefactos</title>
</head>
<body class="body">
    <main style="display:grid;width:100%;">
        <h3>Aniadir componente a un artefacto</h3>
        <div class="smCard">
            <span>ponle nombre a tu artefacto</span>
            <input type="text" id="art-name" placeholder="algo genial...">
        </div>
        <div class="card">
            <form id="form">
                <label for="cname">Nombre del Componente:</label>
                <input type="text" id="cname" name="cname">
                <br>
                <label for="cqt">Cantidad:</label>
                <input type="number" id="cqt" name="cqt">
                <br>
                <label for="cprice">Precio:</label>
                <input type="number" id="cprice" name="cprice">
                <br>
                <button type="submit" class="formBtn">Aniadir Componente</button>
            </form>
        </div>
        <!-- button type="button" id="enviarBtn" class="formBtn" -->
		<!-- enviar al server -->
		<!-- /button -->
        <div class="card">
            <span id="nameHere">tu articulo...</span>
            <span id="arayHere"></span>

            <button type="button" id="costoBtn" class="formBtn">
                Preguntar al servidor por el costo total de nuestro articulo
	    	</button>
            <span id="respHere"></span>

        </div>
    <main>

    <!-- JavaScript -->
    <script>
        const artName = document.getElementById('art-name')
        const nameHere = document.getElementById('nameHere')
        const arrayHere = document.getElementById('arrayHere')

        const form = document.getElementById('form')
        const costoBtn = document.getElementById('costoBtn')
        const enviarBtnn = document.getElementById('enviarBtn')
        const respHere = document.getElementById('respHere')

        let componentsGlobal = []
        let baseOptionsGET = {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        }
        let baseOptionsPOST = {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: "json"
        }

        /*
        function addComponent(compp) {
            componentsGlobal.push(compp)
        }
        form.addEventListener('submit', (e) => {
            e.preventDefault()
            let comp = {
                cname: e.target.cname.value,
                quantity: parseFloat(e.target.cqt.value),
                price: parseFloat(e.target.cprice.value)
            }
            addComponent(comp)
        })*/

        // enviar un componente al servidor - funcion
        async function sendComponent(evt) {
            evt.preventDefault()
            let comp = {
                cname: evt.target.cname.value,
                quantity: parseFloat(evt.target.cqt.value),
                price: parseFloat(evt.target.cprice.value)
            }
            componentsGlobal.push(comp)
            let fetchOptions = {
                ...baseOptionsPOST,
                body: JSON.stringify(comp)
            }
            try {
                const r = await fetch('/CardonaRodriguezEliasLITC/artifactServlet', fetchOptions)
                const j = await r.json()
                let aux = [...j]
                return aux

            } catch(e) {
                console.log("err on fetch", e)
            }
        }

        // enviar un componente al servidor - manejo del evento
        form.addEventListener('submit', async (e) => {
            let respArr = await sendComponent(e)
            respHere.innerText = "el mensaje del servidor es " + respArr[0].responsePayload + "."
            console.log(componentsGlobal)
        })


        // estimar precio del total de articulos
        async function getTotalCost() {
            try{
                const r = await fetch('/CardonaRodriguezEliasLITC/artifactServlet', baseOptionsGET)
                const j = await r.json()
                respHere.innerText = "El costo total por producir tu artefacto es de " + j[0].responsePayload + "mxn"
            } catch(e) {
                console.log("err on fetch", e)
            }

        }

        costoBtn.addEventListener('click', async (e) => {
            e.preventDefault()
            await getTotalCost()
        })

        artName.addEventListener('change', (e) => {
            nameHere.innerText = "es" + e.target.value + "."
        })

        componentsGlobal.map((el, i) => {
            let spanEl = document.createElement("span")
            spanEl.style.display = "grid"
            spanEl.style.paddingBottom = "1rem"
            spanEl.style.borderBottom = "1px solid #000"
            spanEl.innerText = "nombre: " + el.cname + " cantidad: " + el.quantity + " precio: " + el.price + "."
            arrayHere.appendChild(spanEl)
        })

    </script>
</body>
</html>



