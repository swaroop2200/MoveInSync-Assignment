package com.example.moveinsync.sms.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentDTO {

    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String className;

}
