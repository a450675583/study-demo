package com.elgin.study.springboot.advice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomErrorType {

    private int status;

    private String message;
}
