package org.example.presentation.grpc;

import lombok.Getter;
import lombok.Setter;

public class CustomException extends RuntimeException {

    @Getter
    @Setter
    Integer errCode;

    @Setter
    @Getter
    String errMsg;

    public static CustomException fromCodeAndMsg(Integer errCode, String errMsg) {
        CustomException ex = new CustomException();
        ex.setErrCode(errCode);
        ex.setErrMsg(errMsg);
        return ex;
    }
}
