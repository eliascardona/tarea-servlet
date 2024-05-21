package litc.cardona.elias.servlets.entity.response;

public class ResponseInterface {
    private String responsePayload;

    public ResponseInterface() {}

    public ResponseInterface(String keyFieldOne) {
        this.responsePayload = keyFieldOne;
    }

    public String getResponsePayload() {
        return responsePayload;
    }

    public void setResponsePayload(String keyFieldOne) {
        this.responsePayload = keyFieldOne;
    }

}
