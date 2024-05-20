package litc.cardona.elias.servlets.entity;

public class ResponseInterface {
    private String arrivalTime;

    public ResponseInterface() {}

    public ResponseInterface(String keyFieldOne) {
        this.arrivalTime= keyFieldOne;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String keyFieldOne) {
        this.arrivalTime= keyFieldOne;
    }

}
