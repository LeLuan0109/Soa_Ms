package com.project.app.business.dto.approval;

import com.project.app.business.dto.post.PostDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class ApprovalPostDto extends PostDto {
  private Integer id;
  private String businessCode;
  private String companyName;
  private String taxCode;
  private String stockCode;
  private String reason;

  public ApprovalPostDto(String postId, Long pubTime, String title, String description,
      String content, String url, String mediaUrls, Long comments, Long shares, Long reactions,
      String authId, String authName, String sourceId, String sourceName, Integer sourceType,
      Integer source, String webTags, String webKeywords, Long crawlTime, Integer doctype,
      String replyTo, Long level, Long favors, Long views, Long authType, String category,
      String negativeKeywords, Integer rankingAuto, Integer rankingApproval, String warningLevel, Integer status,
      Integer id, String businessCode, String companyName, String taxCode, String stockCode,
      String reason) {
    super(postId, pubTime, title, description, content, url, mediaUrls, comments, shares, reactions,
        authId, authName, sourceId, sourceName, sourceType, source, webTags, webKeywords, crawlTime,
        doctype, replyTo, level, favors, views, authType, category, negativeKeywords, rankingAuto,
        rankingApproval, warningLevel, status);
    this.id = id;
    this.businessCode = businessCode;
    this.companyName = companyName;
    this.taxCode = taxCode;
    this.stockCode = stockCode;
    this.reason = reason;
  }

  public ApprovalPostDto(Integer id, String businessCode, String companyName, String taxCode,
      String stockCode, String reason) {
    this.id = id;
    this.businessCode = businessCode;
    this.companyName = companyName;
    this.taxCode = taxCode;
    this.stockCode = stockCode;
    this.reason = reason;
  }
}
