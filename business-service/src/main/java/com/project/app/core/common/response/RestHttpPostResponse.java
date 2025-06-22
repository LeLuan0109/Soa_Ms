package com.project.app.core.common.response;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestHttpPostResponse {
  private Long id;
  private Long updated;
  public long timeNow(){
    ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
    LocalDateTime currentDateTime = LocalDateTime.now(zoneId);
    ZonedDateTime zonedDateTime = currentDateTime.atZone(zoneId);
    return zonedDateTime.toEpochSecond();
  }
  public RestHttpPostResponse() {
    this.id = null;
    this.updated =timeNow();
  }

  public RestHttpPostResponse(Long id) {
    this.id = id;
    this.updated = timeNow();
  }
}
