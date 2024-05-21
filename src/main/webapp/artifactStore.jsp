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
        <h3>Aniadir componente a un Artefacto</h3>
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
                <button type="submit">Aniadir Componente</button>
            </form>
        </div>

        <br>
        <button type="button" id="enviarBtn" class="formBtn">
			enviar al server
		</button>
        <br>
        <button type="button" id="costoBtn" class="formBtn">
            Obtener Costo Total
		</button>
        <p id="respHere"></p>
    <main>


    <!-- JavaScript -->
    <script>
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

        // aniadir componente - funcion manejadora
        function addComponent(compp) {
            componentsGlobal.push(compp)
        }

        // aniadir componente - evento
        form.addEventListener('submit', (e) => {
            e.preventDefault()
            let comp = {
                cname: e.target.cname.value,
                quantity: parseFloat(e.target.cqt.value),
                price: parseFloat(e.target.cprice.value)
            }

            addComponent(comp)
            console.log(componentsGlobal)
        })

        // servidor - funcion
        async function sendComponent(componentsArr) {
            let fetchOptions = {
                ...baseOptionsPOST,
                body: JSON.stringify(componentsArr)
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

        // enviar lista de componentes al servidor
        enviarBtn.addEventListener('click', async (e) => {
            e.preventDefault()
            let respArr = await sendComponent(componentsGlobal)
            respHere.innerText = respArr[0].responsePayload
        })


        // estimar precio del total de articulos
        async function getTotalCost() {
            try{
                const r = await fetch('/CardonaRodriguezEliasLITC/artifactServlet', baseOptionsGET)
                const j = await r.json()
                respHere.innerText = `El costo total de producir tu artefacto es de ${j.responsePayload} mxn`
            } catch(e) {
                console.log("err on fetch", e)
            }

        }

        costoBtn.addEventListener('click', async (e) => {
            e.preventDefault()
            await getTotalCost()
        })

    </script>
</body>
</html>



