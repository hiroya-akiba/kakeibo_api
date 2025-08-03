package com.example.kakeibo.dto;

public class ErrorDetail {
    private final String field;
    private final String message;

    private ErrorDetail(Builder builder){
        this.field = builder.field;
        this.message = builder.message;
    }

    public String getField(){ return this.field; }
    public String getMessage(){ return this.message; }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private String field;
        private String message;

        public Builder field(String field){
            this.field = field;
            return this;
        }

        public Builder message(String message){
            this.message = message;
            return this;
        }

        public ErrorDetail build(){
            return new ErrorDetail(this);
        }
    }
}
