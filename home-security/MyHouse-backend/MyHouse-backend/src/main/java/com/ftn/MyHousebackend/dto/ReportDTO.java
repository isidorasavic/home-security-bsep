package com.ftn.MyHousebackend.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {

    private ObjectDTO object;
    private String dateForm;
    private String dateTo;
    private List<ObjectMessageDTO> messages;

}
