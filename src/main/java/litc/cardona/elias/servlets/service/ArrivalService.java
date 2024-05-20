package litc.cardona.elias.servlets.service;

import com.google.gson.Gson;
import litc.cardona.elias.servlets.entity.TripDetails;


public class ArrivalService {
    public String finalEstimation;
    private Integer departureHours;
    private Integer departureMinutes;

    public ArrivalService() {}


    public boolean calculateArrival(String jsonPayload) {
        if (jsonPayload == null) {
            return false;
		}

        Gson gson = new Gson();
        try {
            TripDetails parsedTripDetails = gson.fromJson(jsonPayload, TripDetails.class);

            if (parsedTripDetails != null) {
                float distanceV = parsedTripDetails.getDistance();
                float speedV = parsedTripDetails.getSpeed();
                String timeStr = parsedTripDetails.getInitialTime();

                System.out.println("\n-----------------");
                System.out.println(timeStr);
                System.out.println(speedV);
                System.out.println("-----------------\n");


                // convert the resquest body from strings to numbers
                String[] timeParts = timeStr.split(":");
                this.departureHours = Integer.parseInt(timeParts[0]);
                this.departureMinutes = Integer.parseInt(timeParts[1]);

                this.estimatation(distanceV, speedV);
                return true;
            }

        }
        catch (Exception e) {}
        return false;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////// inner methods ///////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private void estimatation(float distance, float speed) {
        float tripInitialTime = distance / speed;

        int tripHours = (int) tripInitialTime;
        int tripMinutes = (int) ((tripInitialTime - tripHours) * 60);

        int arrivalHours = (departureHours + tripHours) % 24;
        int arrivalMinutes = (departureMinutes + tripMinutes) % 60;
    
        if(departureMinutes + tripMinutes >= 60) {
            arrivalHours += 1;
        }
        arrivalHours = arrivalHours % 24;

        this.finalEstimation = String.format("%02d:%02d", arrivalHours, arrivalMinutes);
    }

}

