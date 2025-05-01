package com.aspromedic.response;

public class PracticaResponseRest extends ResponseRest {
    
    private PracticaResponse practicaResponse = new PracticaResponse();

    public PracticaResponse getPracticaResponse() {
        return practicaResponse;
    }

    public void setPracticaResponse(PracticaResponse practicaResponse) {
        this.practicaResponse = practicaResponse;
    }
}
