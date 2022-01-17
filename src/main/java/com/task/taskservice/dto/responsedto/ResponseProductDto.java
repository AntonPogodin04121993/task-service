package com.task.taskservice.dto.responsedto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class ResponseProductDto {

    String title;

    String description;

    BigDecimal price;

}
