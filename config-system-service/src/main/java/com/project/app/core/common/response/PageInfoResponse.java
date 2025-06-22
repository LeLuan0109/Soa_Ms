package com.project.app.core.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfoResponse {
  private int pageIndex;
  private int pageSize;
  private int startCursor;
  private boolean hasNextPage;
  private int totalPage;
}
