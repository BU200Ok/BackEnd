package com.bu200.common.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
@Getter@Setter@ToString
public class ResponseDTO {
    private int code;
    private String msg;
    private Object obj;
    public ResponseDTO (HttpStatus httpStatus, String msg, Object data){
        this.code = httpStatus.value();
        this.msg = msg;
        obj = data;
    }
}
