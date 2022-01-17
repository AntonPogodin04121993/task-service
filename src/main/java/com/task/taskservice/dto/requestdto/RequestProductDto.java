package com.task.taskservice.dto.requestdto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestProductDto implements Serializable {

    @NotNull
    String title;

    @Length(max = 1024)
    String description;

    @NotNull
    BigDecimal price;
}
