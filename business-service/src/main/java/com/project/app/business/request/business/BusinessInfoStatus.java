package com.project.app.business.request.business;

import com.project.app.business.request.StatusRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessInfoStatus extends StatusRequest {
    private String businessCode;
    private String companyName;
    private String taxCode;
    private String stockCode;
    private String operationStatus;
    private Integer taxRanking;
    private String updated;
}
