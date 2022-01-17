package com.task.taskservice.dto.requestdto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestUserDto {

    @NotNull
    @Length(max = 128)
    String username;

    @NotNull
    @Length(max = 255)
    String password;
}
