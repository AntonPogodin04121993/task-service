package com.task.taskservice.dto.requestdto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestCustomerDto implements Serializable {

    @NotNull
    @Length(max = 255)
    private final String title;

    @JsonCreator
    public RequestCustomerDto(String title) {
        this.title = title;
    }

}
