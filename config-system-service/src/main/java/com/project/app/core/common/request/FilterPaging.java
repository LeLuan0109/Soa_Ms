package com.project.app.core.common.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterPaging <T> extends BaseFilter<T> {
  private int pageIndex;
  private int pageSize;
}
