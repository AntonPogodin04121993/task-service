package com.task.taskservice.dto.responsedto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Value
@Builder
public class ResponseCustomerDto implements Serializable {

    String title;

}
