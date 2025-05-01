package com.aspromedic.response;

public class DosimetroResponseRest extends ResponseRest{
    
    private DosimetroResponse dosimetroResponse = new DosimetroResponse();

    public DosimetroResponse getDosimetroResponse() {
        return dosimetroResponse;
    }

    public void setDosimetroResponse(DosimetroResponse dosimetroResponse) {
        this.dosimetroResponse = dosimetroResponse;
    }
}
