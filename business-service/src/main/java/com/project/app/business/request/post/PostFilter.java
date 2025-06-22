package com.project.app.business.request.post;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostFilter {
  private Integer businessId;
  private int top;
  private String optionSort;

}
