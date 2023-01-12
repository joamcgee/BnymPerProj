package com.ProjectForBNYM.ExceptionHandling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
//For creating custom message response
public class ErrorMessageModel {
    private int statusCode;
    private Date timeStamp;
    private String message;
    private String description;

    public ErrorMessageModel(int statusCode, Date timeStamp, String message, String description) {
        this.statusCode = statusCode;
        this.timeStamp = timeStamp;
        this.message = message;
        this.description = description;
    }

}
