package litc.cardona.elias.servlets;

import java.util.stream.Collectors;
import java.io.OutputStream;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
// local packages - services
import litc.cardona.elias.servlets.service.response.ResponseService;
import litc.cardona.elias.servlets.entity.component.ComponentRequest;
import litc.cardona.elias.servlets.service.artifact.ArtifactService;

@WebServlet(name = "ArtifactServlet", urlPatterns = "/artifactServlet")


public class ArtifactServlet extends HttpServlet {
    private ArtifactService artifactService;
    private ResponseService responseService;

    public ArtifactServlet() {
        this.artifactService = new ArtifactService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int responseCode = HttpServletResponse.SC_OK;
        // read the request body
        String reqBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        // convert JSON into a java class
        Gson gson = new Gson();
        ComponentRequest componentRequest = gson.fromJson(reqBody, ComponentRequest.class);

        boolean res = this.artifactService.addComponents(componentRequest);

        this.responseService = new ResponseService("agregado con exito");
        String fmtJson = this.responseService.formatResponse();

        if(!res) {
            responseCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        }
        this.outputResponse(resp, fmtJson, responseCode);

    }

    /////////////////////////////////////////////////////////////////////
    /////////////////////// private methods ////////////////////////////
    ////////////////////////////////////////////////////////////////////

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int responseCode = HttpServletResponse.SC_OK;
        double totalCost = this.artifactService.calculateTotalCost();
        String parsedCost = Double.toString(totalCost);

        this.responseService = new ResponseService(parsedCost);
        String fmtJson = this.responseService.formatResponse();

        this.outputResponse(resp, fmtJson, responseCode);
    }



    private void outputResponse(HttpServletResponse response, String payload, int status) {
        response.setHeader("Content-Type", "application/json");
        try {
            response.setStatus(status);
            if(payload != null) {
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(payload.getBytes());
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

