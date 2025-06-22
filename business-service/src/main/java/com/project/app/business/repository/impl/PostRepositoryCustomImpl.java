package com.project.app.business.repository.impl;

import com.project.app.business.dto.post.PostDto;
import com.project.app.business.repository.PostRepository;
import com.project.app.business.repository.PostRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

  @PersistenceContext
  private final EntityManager entityManager;

  @Override
  public List<PostDto> getBusinessForPost(Integer orgId, Integer businessId, Integer top, String optionSort) {
    String baseQuery = """
            SELECT new com.project.app.business.dto.post.PostDto(
                p.postId, p.pubTime, p.title, p.description, p.content, p.url, p.mediaUrls,
                p.comments, p.shares, p.reactions, p.authId, p.authName, p.sourceId, p.sourceName,
                p.sourceType, p.crawlSource, p.webTags, p.webKeywords, p.crawlTime, p.docType,
                p.replyTo, p.level, p.favors, p.views, p.authType, p.category, p.negativeKeywords,
                tr.rankingAuto, tr.rankingApproval, tr.warningLevel, tr.status
            )
            FROM Post p
            JOIN p.business b
            JOIN TransDetailPost tr ON tr.post.postId = p.postId
            WHERE b.orgId = :orgId AND b.businessId = :businessId
        """;

    String orderBy = switch (optionSort == null ? "NEW" : optionSort.toUpperCase()) {
      case "OLD" -> " ORDER BY p.pubTime ASC ";
      case "INT" -> " ORDER BY p.reactions DESC, p.comments DESC, p.shares DESC ";
      case "SBC" -> " ORDER BY p.content ASC, p.description ASC, p.title ASC ";
      case "RANK" -> " ORDER BY tr.rankingApproval DESC, p.reactions DESC, p.comments DESC, p.shares DESC ";
      default -> " ORDER BY p.pubTime DESC ";
    };

    TypedQuery<PostDto> query = entityManager.createQuery(baseQuery + orderBy, PostDto.class);
    query.setParameter("orgId", orgId);
    query.setParameter("businessId", businessId);
    query.setMaxResults(top);
    return query.getResultList();
  }
}
