package br.com.darlan.tasks.controller.exception;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorMessageModel {
    private Integer status;
    private String message;
}
