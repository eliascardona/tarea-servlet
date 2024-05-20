package litc.cardona.elias.servlets;

import java.util.stream.Collectors;
import java.io.OutputStream;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// local packages
import litc.cardona.elias.servlets.service.ResponseService;
import litc.cardona.elias.servlets.service.ArrivalService;


@WebServlet(name = "TripServlet", urlPatterns = "/tripServlet")



public class TripServlet extends HttpServlet {

    private ArrivalService arrivalService;
    private ResponseService responseService;

    public TripServlet() {
        this.arrivalService = new ArrivalService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // add student from request body
        String reqBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        int responseCode = HttpServletResponse.SC_OK;
        boolean res = this.arrivalService.calculateArrival(reqBody);
        
        String tripEstimation = arrivalService.finalEstimation;

        this.responseService = new ResponseService(tripEstimation);
        String fmtJson = this.responseService.formatResponse();

        if(!res) {
            responseCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        }
        this.outputResponse(resp, fmtJson, responseCode);


    }

    /////////////////////////////////////////////////////////////////////
    /////////////////////// private methods ////////////////////////////
    ////////////////////////////////////////////////////////////////////

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












