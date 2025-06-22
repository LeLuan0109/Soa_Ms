package com.project.app.core.common.request;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class  BaseFilter<T> {
  @Valid
  private T filter;
  private String sort;
  private Integer TopicId;
}
