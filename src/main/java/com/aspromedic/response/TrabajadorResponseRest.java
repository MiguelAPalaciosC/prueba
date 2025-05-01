package com.aspromedic.response;

public class TrabajadorResponseRest extends ResponseRest{
    
    private TrabajadorResponse trabajadorResponse = new TrabajadorResponse();

    public TrabajadorResponse getTrabajadorResponse() {
        return trabajadorResponse;
    }

    public void setTrabajadorResponse(TrabajadorResponse trabajadorResponse) {
        this.trabajadorResponse = trabajadorResponse;
    }
}
