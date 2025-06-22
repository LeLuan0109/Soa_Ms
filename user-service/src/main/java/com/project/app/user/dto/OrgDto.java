package com.project.app.user.dto;

import static com.project.app.core.util.DateUtils.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MM;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrgDto {
  @JsonProperty("orgId")
  private Integer id;
  private String name;
  private String description;
  private String address;
  private String companyName;
  private String email;
  private String phone;
  private String logo;
  private String taxCode;
  private String website;
  @DateTimeFormat(pattern = DATE_TIME_FORMAT_YYYY_MM_DD_HH_MM)
  private LocalDateTime createTime;
  @DateTimeFormat(pattern = DATE_TIME_FORMAT_YYYY_MM_DD_HH_MM)
  private LocalDateTime modifyTime;

  public OrgDto(Integer id, String address, String companyName, String email, String phone,
      String logo) {
    this.id = id;
    this.address = address;
    this.companyName = companyName;
    this.email = email;
    this.phone = phone;
    this.logo = logo;
  }


}
