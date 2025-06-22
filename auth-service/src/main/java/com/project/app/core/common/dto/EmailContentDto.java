package com.project.app.core.common.dto;

public interface EmailContentDto {
  String getTitle();
  String getContent();
  String[] getRecipients();
  String getUrl();
  String getTemplate();
  String getPubTimeDate();
  String getPubTimeHouse();
}