package com.ss.spring_asynch_demo.exceptions;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StandardErrorResponse {
    private final String message;
    private final int status;
    private final String errorCode;
}
