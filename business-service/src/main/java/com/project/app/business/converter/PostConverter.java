package com.project.app.business.converter;

import static com.project.app.core.util.Constants.NEWS;
import static com.project.app.core.util.Constants.SOCIAL;


import com.project.app.business.domain.enums.doc.PDocType;
import com.project.app.business.domain.enums.sort.PSortValue;
import com.project.app.business.domain.enums.source.PSourceTypeName;
import com.project.app.business.domain.enums.source.PSourceTypeValue;
import com.project.app.business.domain.enums.source.PSourceValue;
import com.project.app.business.dto.post.PostSourceTypeDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PostConverter {

  private static List<String> initSource(List<String> sources) {
    if (Objects.isNull(sources) || sources.isEmpty()) {
      return Arrays.stream(PSourceTypeName.values())
          .map(PSourceTypeName::getName)
          .toList();
    }
    return sources;
  }

  public static List<Integer> sourceStringToNumber(List<String> sources) {
    Set<Integer> result = new HashSet<>();

    if (Objects.isNull(sources) || sources.isEmpty()) {
      return null;
    }
    else {
      for (String source : sources) {
        PSourceTypeName pSourceName = PSourceTypeName.fromString(source);

        switch (pSourceName) {
          case FACEBOOK_PAGE_NAME, FACEBOOK_GROUP_NAME, FACEBOOK_USER_NAME ->
              result.add(PSourceValue.FACEBOOK_VALUE.getValue());
          case TIKTOK_NAME ->
              result.add(PSourceValue.TIKTOK_VALUE.getValue());
          case YOUTUBE_NAME ->
              result.add(PSourceValue.YOUTUBE_VALUE.getValue());
          case WEBSITE_NAME ->
              result.add(PSourceValue.WEBSITE_VALUE.getValue());
          case LINKEDIN_NAME ->
              result.add(PSourceValue.LINKEDIN_VALUE.getValue());
        }
      }
    }

    return result.isEmpty() ? null : result.stream().toList();
  }

  public  static  List<String> listSourceToString(List<String> sources) {
    List<PostSourceTypeDto> data = new ArrayList<>();
    List<String> result = new ArrayList<>();

    if (Objects.isNull(sources) || sources.isEmpty()) {
      return null;
    }
    for(String source : sources) {
      PSourceTypeName pSourceTypeName = PSourceTypeName.fromString(source);
      PSourceTypeName pSourceName = PSourceTypeName.fromString(source);

      switch (pSourceName) {
        case FACEBOOK_PAGE_NAME, FACEBOOK_GROUP_NAME, FACEBOOK_USER_NAME -> {
          switch (pSourceTypeName) {
            case FACEBOOK_PAGE_NAME ->
                data.add(new PostSourceTypeDto(PSourceValue.FACEBOOK_VALUE.getValue(), PSourceTypeValue.FACEBOOK_PAGE_VALUE.getValue()));
            case FACEBOOK_GROUP_NAME ->
                data.add(new PostSourceTypeDto(PSourceValue.FACEBOOK_VALUE.getValue(), PSourceTypeValue.FACEBOOK_GROUP_VALUE.getValue()));
            case FACEBOOK_USER_NAME ->
                data.add(new PostSourceTypeDto(PSourceValue.FACEBOOK_VALUE.getValue(), PSourceTypeValue.FACEBOOK_USER_VALUE.getValue()));
            default -> {}
          }
        }
        case TIKTOK_NAME ->
            data.add(new PostSourceTypeDto(PSourceValue.TIKTOK_VALUE.getValue(), PSourceTypeValue.TIKTOK_USER_VALUE.getValue()));
        case YOUTUBE_NAME ->
            data.add(new PostSourceTypeDto(PSourceValue.YOUTUBE_VALUE.getValue(), PSourceTypeValue.YOUTUBE_USER_VALUE.getValue()));
        case WEBSITE_NAME -> {
          data.add(new PostSourceTypeDto(PSourceValue.WEBSITE_VALUE.getValue(), PSourceTypeValue.NEWS_LOCAL_VALUE.getValue()));
          data.add(new PostSourceTypeDto(PSourceValue.WEBSITE_VALUE.getValue(), PSourceTypeValue.NEWS_MAINSTREAM_VALUE.getValue()));
        }
        case LINKEDIN_NAME -> {
          data.add(new PostSourceTypeDto(PSourceValue.LINKEDIN_VALUE.getValue(), PSourceTypeValue.LINKEDIN_USER_VALUE.getValue()));
//          data.add(new PostSourceTypeDto(PSourceValue.LINKEDIN_VALUE.getValue(), PSourceTypeValue.LINKEDIN_USER_VALUE.getValue()));
        }
        default -> {}
      }
    }

    for (PostSourceTypeDto dto : data) {
      Integer source = dto.getSource();
      Integer sourceType = dto.getSourceType();

      result.add(source + "-" + sourceType);
    }

    return result.isEmpty() ? null : result;
  }

  public  static  List<Integer> sourceTypeToNumber(List<String> sources) {
    Set<Integer> result = new HashSet<>();
    if (Objects.isNull(sources) || sources.isEmpty()) {
      initSource(sources);
    }
    else{
      for (String source : sources) {
        PSourceTypeName pSourceName = PSourceTypeName.fromString(source);
        switch (pSourceName) {
          case FACEBOOK_PAGE_NAME -> result.add(PSourceTypeValue.FACEBOOK_PAGE_VALUE.getValue());
          case FACEBOOK_GROUP_NAME -> result.add(PSourceTypeValue.FACEBOOK_GROUP_VALUE.getValue());
          case FACEBOOK_USER_NAME -> result.add(PSourceTypeValue.FACEBOOK_USER_VALUE.getValue());
          case TIKTOK_NAME -> result.add(PSourceTypeValue.TIKTOK_USER_VALUE.getValue());
          case YOUTUBE_NAME -> result.add(PSourceTypeValue.YOUTUBE_USER_VALUE.getValue());
          case WEBSITE_NAME -> {
            result.add(PSourceTypeValue.NEWS_LOCAL_VALUE.getValue());
            result.add(PSourceTypeValue.NEWS_MAINSTREAM_VALUE.getValue());
          }
          case LINKEDIN_NAME -> {
            result.add(PSourceTypeValue.LINKEDIN_USER_VALUE.getValue());
            result.add(PSourceTypeValue.LINKEDIN_PAGE_VALUE.getValue());
          }
        }
      }
    }
    return result.isEmpty() ? null : result.stream().toList();
  }

  public static String filterSource(Integer source) {
    for (PSourceValue pSourceValue : PSourceValue.values()) {
      if (pSourceValue.getValue() == source) {
        switch (pSourceValue) {
          case FACEBOOK_VALUE, TIKTOK_VALUE, YOUTUBE_VALUE, LINKEDIN_VALUE:
            return SOCIAL;
          case WEBSITE_VALUE:
            return NEWS;
          default:
            return null;
        }
      }
    }
    return null;
  }

  public static Integer docTypeToNumber(String docType) {
    PDocType pDocType = PDocType.fromString(docType);
    if (pDocType != null) {
      return pDocType.getValue();
    }
    return null;
  }

  public static String convertSortNative(String sort) {
    PSortValue sortValue = PSortValue.fromString(sort);
    switch (sortValue) {
      case NEW -> {
        return " p.pub_time DESC";
      }
      case OLD -> {
        return " p.pub_time ASC";
      }
      case INT -> {
        return " p.reactions DESC, p.comments DESC, p.shares DESC"; // Sort by interactions
      }
      case SBC -> {
        return " p.content ASC, p.description ASC, p.title ASC"; // Sort by content, description, title
      }
      default -> {
        return " p.pub_time DESC"; // Default sorting
      }
    }
  }

  public static Sort convertSort(String sort) {
    Sort ordersPost = Sort.by(Sort.Order.desc("pubTime"));
    PSortValue sortValue = PSortValue.fromString(sort);
    switch (sortValue) {
      case NEW -> {
        return ordersPost;
      }
      case OLD -> {
        return Sort.by(Sort.Order.asc("pubTime"));
      }
      case INT -> {
        return Sort.by(Sort.Order.desc("reactions"), Sort.Order.desc("comments"), Sort.Order.desc("shares")); // Sort by interactions
      }
      case SBC -> {
        return Sort.by(Sort.Order.asc("content"), Sort.Order.asc("description"), Sort.Order.asc("title")); // Sort by content, description, title
      }
      default -> {
        return ordersPost;
      }
    }
  }

  public static Sort convertSortForQuery(String sort) {
    Sort ordersPost = Sort.by(Sort.Order.desc("pub_time"));
    PSortValue sortValue = PSortValue.fromString(sort);
    switch (sortValue) {
      case NEW -> {
        return ordersPost;
      }
      case OLD -> {
        return Sort.by(Sort.Order.asc("pub_time"));
      }
      case INT -> {
        return Sort.by(Sort.Order.desc("reactions"), Sort.Order.desc("comments"), Sort.Order.desc("shares")); // Sort by interactions
      }
      case SBC -> {
        return Sort.by(Sort.Order.asc("content"), Sort.Order.asc("description"), Sort.Order.asc("title")); // Sort by content, description, title
      }
      case RANK -> {
        return Sort.by(Sort.Order.desc("rankingApproval"), Sort.Order.desc("reactions"), Sort.Order.desc("comments"), Sort.Order.desc("shares")); // Sort by ranking
      }
      default -> {
        return ordersPost;
      }
    }
  }

  // Build a Sort object for Pageable from the sort string returned from the native converter
  public static Sort convertNativeSortStringToSortObject(String sortString) {
    List<Sort.Order> orders = new ArrayList<>();

    if (sortString != null && !sortString.trim().isEmpty()) {
      // Retrieving different sorting criteria: p.content ASC, p.description ASC, p.title ASC, etc
      String[] sortParts = sortString.split(",");

      for (String sortPart : sortParts) {
        sortPart = sortPart.trim();
        String[] fieldAndOrder = sortPart.split("\\s+"); // Split by space

        if (fieldAndOrder.length == 2) {
          String field = fieldAndOrder[0].trim().split("\\.")[1]; // Split by a dot to remove the "p." or whatever it is
          String direction = fieldAndOrder[1].trim().toUpperCase();

          // Add sorting order to the list
          if ("DESC".equals(direction)) {
            orders.add(Sort.Order.desc(field));
          } else if ("ASC".equals(direction)) {
            orders.add(Sort.Order.asc(field));
          }
        }
      }
    }
    return Sort.by(orders);
  }

  // Returns null if input is invalid
  public static String convertToTextSearchQuery(String input, Map<String, Object> parameters) {
    // Check for leading or trailing operators
    if (input.startsWith(",") || input.startsWith("|") || input.endsWith(",") || input.endsWith("|")) {
      return null;
    }

    // Check for consecutive operators
    if (input.contains("||") || input.contains(",,") || input.contains("|,") || input.contains(",|")) {
      return null;
    }

    // Check if string contains only | and , (for example: " |    |   | ") is not valid
    String cleanInput = input.replaceAll("[,|]", "").trim();
    if (cleanInput.isEmpty()) {
      return null;
    }

    StringBuilder result = new StringBuilder();
    String[] tokens = input.split("(?=[,|])|(?<=[,|])"); // Preserve ',' and '|'
    List<String> queryParts = new ArrayList<>();
    int index = 1; // Ensure unique parameter names

    for (String token : tokens) {
      token = token.trim();
      if (token.equals(",")) {
        queryParts.add(" AND ");
      } else if (token.equals("|")) {
        queryParts.add(" OR ");
      } else if (!token.isEmpty()) {
        String paramKey = "keyword" + index++; // Unique key per token
        queryParts.add("(p.title ILIKE :" + paramKey + " OR p.content ILIKE :" + paramKey + ")");
        parameters.put(paramKey, "%" + token + "%"); // Add wildcards for ILIKE
      }
    }
    return String.join("", queryParts);
  }

}
