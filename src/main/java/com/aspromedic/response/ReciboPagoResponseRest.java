package com.aspromedic.response;

public class ReciboPagoResponseRest extends ResponseRest {

    public static final String HttpStatus = null;
    private ReciboPagoResponse reciboPagoResponse = new ReciboPagoResponse();

    public ReciboPagoResponse getReciboPagoResponse() {
        return reciboPagoResponse;
    }

    public void setReciboPagoResponse(ReciboPagoResponse reciboPagoResponse) {
        this.reciboPagoResponse = reciboPagoResponse;
    }

}
