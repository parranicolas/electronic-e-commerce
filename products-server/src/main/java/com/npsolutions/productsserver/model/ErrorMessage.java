package com.npsolutions.productsserver.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter@Setter
@Builder
public class ErrorMessage {

    private String msg_code;
    private List<Map<String,String>> messages;
}
