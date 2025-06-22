package com.project.app.business.repository;

import com.project.app.business.domain.Post;
import com.project.app.business.dto.approval.ApprovalPostDto;
import com.project.app.business.dto.post.PostDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {

//  @Query(
//      "Select new com.project.app.business.dto.post.PostDto("
//          + "p.postId,"
//          + "p.pubTime,"
//          + "p.title, "
//          + "p.description,"
//          + "p.content,"
//          + "p.url,"
//          + "p.mediaUrls,"
//          + "p.comments,"
//          + "p.shares,"
//          + "p.reactions,"
//          + "p.authId,"
//          + "p.authName,"
//          + "p.sourceId,"
//          + "p.sourceName,"
//          + "p.sourceType,"
//          + "p.crawlSource, "
//          + "p.webTags,"
//          + "p.webKeywords,"
//          + "p.crawlTime,"
//          + "p.docType,"
//          + "p.replyTo,"
//          + "p.level,"
//          + "p.favors,"
//          + "p.views,"
//          + "p.authType,"
//          + "p.category,"
//          + "p.negativeKeywords,"
//          + "tr.rankingAuto,"
//          + "tr.rankingApproval"
//          + ") "
//          + "FROM Post AS p "
//          + "JOIN Business AS b ON b.businessId = p.business.businessId "
//          + "JOIN TransDetailPost AS tr ON  tr.post.postId = p.postId "
//          + "WHERE b.orgId = :orgId "
//          + "and :businessId = b.businessId "
//  )
//  List<PostDto> getBusinessForPost(
//      @Param("orgId") Integer orgId,
//      @Param("businessId") Integer businessId,
//      Pageable pageable
//  );

  @Query(
      "Select new com.project.app.business.dto.approval.ApprovalPostDto("
          + "p.postId,"
          + "p.pubTime,"
          + "p.title, "
          + "p.description,"
          + "p.content,"
          + "p.url,"
          + "p.mediaUrls,"
          + "p.comments,"
          + "p.shares,"
          + "p.reactions,"
          + "p.authId,"
          + "p.authName,"
          + "p.sourceId,"
          + "p.sourceName,"
          + "p.sourceType,"
          + "p.crawlSource, "
          + "p.webTags,"
          + "p.webKeywords,"
          + "p.crawlTime,"
          + "p.docType,"
          + "p.replyTo,"
          + "p.level,"
          + "p.favors,"
          + "p.views,"
          + "p.authType,"
          + "p.category,"
          + "p.negativeKeywords,"
          + "tr.rankingAuto,"
          + "tr.rankingApproval,"
          + "tr.warningLevel,"
          + "tr.status,"
          + "tr.transDetailPostsId,"
          + "b.businessCode,"
          + "b.companyName,"
          + "b.taxCode,"
          + "b.stockCode,"
          + "tr.reasonChangeRank"
          + ") "
          + "FROM Post AS p "
          + "JOIN Business AS b ON b.businessId = p.business.businessId "
          + "JOIN TransDetailPost AS tr ON  tr.post.postId = p.postId "
          + "WHERE b.orgId = :orgId "
          + "AND (:startDate is null OR p.pubTime >= :startDate) "
          + "AND (:endDate is null OR p.pubTime <= :endDate) "
          + "AND (:source is null OR CONCAT(p.crawlSource, '-', p.sourceType) IN (:source)) "
          + "AND(:keyword is null  OR (p.title LIKE %:keyword% OR p.description LIKE %:keyword% OR p.content LIKE %:keyword% )) "
          + "AND(:status is null OR tr.status in (:status) ) "

  )
  Page<ApprovalPostDto> filterApprovalBusinessForPost(
      @Param("orgId") Integer orgId,
      @Param("startDate") Long startDate,
      @Param("endDate") Long endDate,
      @Param("source") List<String> source,
      //@Param("spam") Integer spam,
      //@Param("completed") Integer completed,
      @Param("keyword") String keyword,
      @Param("status") List<Integer> status,
      //@Param("domains") List<String> domains,
      Pageable pageable
  );

}
