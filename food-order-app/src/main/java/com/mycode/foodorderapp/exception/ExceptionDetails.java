package com.mycode.foodorderapp.exception;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExceptionDetails {
    private String statusCode;
    private List<String> errorMessages = new ArrayList<>();

}
