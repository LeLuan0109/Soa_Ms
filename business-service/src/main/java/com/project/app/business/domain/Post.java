package com.project.app.business.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

  @Id
  @Column(name = "post_id")
  private String postId;

  @ManyToOne
  @JoinColumn(name = "business_id")
  private Business business;

  @Column(name = "doc_type")
  private Integer docType;
  @Column(name = "pub_time")
  private Long pubTime;
  @Column(name = "crawl_time")
  private Long crawlTime;
  @Column(name = "subject_id")
  private String subjectId;
  @Column(name = "title")
  private String title;
  @Column(name = "description")
  private String description;
  @Column(name = "content")
  private String content;
  @Column(name = "media_urls")
  private String mediaUrls;
  @Column(name = "url")
  private String url;
  @Column(name = "web_tags")
  private String webTags;
  @Column(name = "web_keywords")
  private String webKeywords;
  @Column(name = "reply_to")
  private String replyTo;
//  //////

  @Column(name = "comments")
  private Long comments;
  @Column(name = "shares")
  private Long shares;
  @Column(name = "reactions")
  private Long reactions;
  @Column(name = "views")
  private Long views;
  // ///////

  @Column(name = "auth_id")
  private String authId;

  @Column(name = "auth_name")
  private String authName;

  @Column(name = "auth_type")
  private Long authType;

  @Column(name = "auth_url")
  private String authUrl;

  ////
  @Column(name = "source_id")
  private String sourceId;
  @Column(name = "crawl_source")
  private Integer crawlSource;
  @Column(name = "source_type")
  private Integer sourceType;
  @Column(name = "source_name")
  private String sourceName;
  @Column(name = "source_url")
  private String sourceUrl;

  @Column(name = "favors")
  private Long favors;
  @Column(name = "level")
  private Long level;

  @Column(name = "category")
  private String category;
  @Column(name = "negative_keywords")
  private String negativeKeywords;

}
