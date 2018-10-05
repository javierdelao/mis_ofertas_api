package com.mis_ofertas_api.app.response;

public class SuccessResponse {

    private Boolean state;

    private String text;

    public SuccessResponse() {
    }


    public SuccessResponse(Boolean state, String text) {
        this.state = state;
        this.text = text;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
