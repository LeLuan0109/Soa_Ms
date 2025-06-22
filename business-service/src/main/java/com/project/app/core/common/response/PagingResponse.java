package com.project.app.core.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingResponse<K, T> extends DataResponse<T> {
  private long totalCount;
  private K pageInfo ;
  public PagingResponse( long totalCount, K pageInfo, T data) {
    super(data);
    this.totalCount = totalCount;
    this.pageInfo = pageInfo;
  }
}
