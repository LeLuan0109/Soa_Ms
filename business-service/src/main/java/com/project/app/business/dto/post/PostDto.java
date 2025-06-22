package com.project.app.business.dto.post;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
  private String postId ;
  private Long pubTime ;
  private String title;
  private String description;
  private String content;
  private String url;
  private String mediaUrls;
  private Long comments;
  private Long shares;
  private Long reactions;
  private String authId;
  private String authName;
  private String sourceId;
  private String sourceName;
  private Integer sourceType;
  private Integer source;
  private String webTags;
  private String webKeywords;
  private Long crawlTime;
  private Integer doctype;
  private String replyTo;
  private Long level;
  private Long favors;
  private Long views;
  private Long authType;
  private String category;
  private String negativeKeywords;
  private Integer rankingAuto;
  private Integer rankingApproval;
  private String warningLevel;
  private Integer status;
}
