package com.aspromedic.response;

public class ObservacionResponseRest extends ResponseRest {
    
    private ObservacionResponse observacionResponse = new ObservacionResponse();

    public ObservacionResponse getObservacionResponse() {
        return observacionResponse;
    }

    public void setObservacionResponse(ObservacionResponse observacionResponse) {
        this.observacionResponse = observacionResponse;
    }
}
