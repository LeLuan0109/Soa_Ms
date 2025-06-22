package com.project.app.core.helper;

import java.util.Map;
import org.springframework.data.domain.Sort;
//import org.springframework.data.mongodb.core.query.Query;

public class MongoQuerySortHelper {
//  private MongoQuerySortHelper() {
//    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
//  }
//  /**
//   * Applies sorting to the given MongoDB query based on the provided sorting string.
//   * The sorting string should start with a "+" for ascending order or a "-" for descending order,
//   * followed by the field name to sort by.
//   *
//   * The method will map the field name from the DTO to the corresponding entity field name
//   * using the provided mapping and apply the sorting to the query.
//   *
//   * @param query The MongoDB query to which sorting will be applied.
//   * @param sort The sorting string, e.g. "+fieldName" for ascending or "-fieldName" for descending.
//   * @param dtoToEntityFieldMapping A map of field names from DTO to the corresponding entity field names.
//   *                                This is used to map the field names correctly between the DTO and the entity.
//   */
//  public static void applySortingIfNeeded(Query query, String sort, Map<String, String> dtoToEntityFieldMapping) {
//    if (sort != null && !sort.isEmpty()) {
//      // Lấy hướng sắp xếp từ đầu chuỗi (dấu + hoặc -)
//      String direction = sort.substring(0, 1);  // Lấy dấu + hoặc -
//      // Lấy tên trường sau dấu + hoặc -
//      String fieldName = sort.substring(1);  // Tên trường (creator, title, etc.)
//
//      // Ánh xạ tên trường từ DTO sang Entity
//      String entityFieldName = getEntityFieldNameFromDtoField(fieldName, dtoToEntityFieldMapping);
//
//      // Áp dụng hướng sắp xếp
//      if (direction.equals("+")) {
//        query.with(Sort.by(Sort.Order.asc(entityFieldName)));
//      } else if (direction.equals("-")) {
//        query.with(Sort.by(Sort.Order.desc(entityFieldName)));
//      }
//    }
//  }
//
//  /**
//   * Retrieves the corresponding entity field name from the DTO field name using the provided mapping.
//   * If no mapping is found, it returns the DTO field name as the entity field name.
//   *
//   * @param dtoFieldName The field name in the DTO.
//   * @param dtoToEntityFieldMapping The map of field names from DTO to entity.
//   * @return The corresponding entity field name, or the DTO field name if no mapping is found.
//   */
//  private static String getEntityFieldNameFromDtoField(String dtoFieldName, Map<String, String> dtoToEntityFieldMapping) {
//    return dtoToEntityFieldMapping.getOrDefault(dtoFieldName, dtoFieldName); // Trả về chính tên trường nếu không tìm thấy ánh xạ
//  }
}
