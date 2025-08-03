package com.example.kakeibo.dto;
import java.util.List;

public class ApiResponse<T> {

    private final boolean success;
    private final String message;
    private final T data;
    private final String errorCode;
    private final List<ErrorDetail> errors;
    

    public ApiResponse(Builder<T> builder){
        this.success = builder.success;
        this.message = builder.message;
        this.data = builder.data;
        this.errorCode = builder.errorCode;
        this.errors = builder.errors;        
    }

    public boolean isSuccess(){ return success; }
    public String getMessage(){ return message; }
    public T getData() { return data; }
    public String getErrorCode(){ return errorCode; }
    public List<ErrorDetail> getErrors(){ return errors; }
    public static <T> Builder<T> builder(){
        return new Builder<>();
    }
    
    public static class Builder<T>{
        private  boolean success;
        private  String message;
        private  T data;
        private  String errorCode;
        private  List<ErrorDetail> errors;

        public Builder<T> success(boolean success){
            this.success = success;
            return this;
        }

        public Builder<T> message(String message){
            this.message = message;
            return this;
        }

        public Builder<T> data(T data){
            this.data = data;
            return this;
        }

        public Builder<T> errorCode(String errorCode){
            this.errorCode = errorCode;
            return this;
        }

        public Builder<T> errors(List<ErrorDetail> errors){
            this.errors = errors;
            return this;
        }

        public ApiResponse<T> build(){
            return new ApiResponse<>(this);
        }
    }
}
