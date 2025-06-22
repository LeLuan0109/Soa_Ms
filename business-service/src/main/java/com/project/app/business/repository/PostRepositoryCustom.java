package com.project.app.business.repository;

import com.project.app.business.dto.post.PostDto;
import java.util.List;

public interface  PostRepositoryCustom {
  List<PostDto> getBusinessForPost(Integer orgId, Integer businessId, Integer top, String optionSort);

}
