package com.cathay.HomeworkDemo.model.feign.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Time {
    private String updated;
    private String updatedISO;

    @JsonProperty("updateduk")
    private String updatedUK;
}
