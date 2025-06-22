package com.project.app.core.helper;

import java.util.Map;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

public class JpaSortHelper {
  private JpaSortHelper() {
    // Private constructor to prevent instantiation
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }
  /**
   * Xử lý giá trị sortField để tạo đối tượng Sort
   *
   * @param sortField Giá trị sort (ví dụ: "-pubTime", "+title")
   * @param dtoToEntityFieldMapping Ánh xạ giữa các trường DTO và entity
   * @return Đối tượng Sort hoặc Sort.unsorted() nếu không có sortField
   */
  public static Sort createSort(String sortField, Map<String, String> dtoToEntityFieldMapping) {
    // Sử dụng phiên bản đầy đủ với các giá trị mặc định là null
    return createSort(sortField, null, null, dtoToEntityFieldMapping);
  }

  /**
   * Xử lý giá trị sortField để tạo đối tượng Sort
   *
   * @param sortField Giá trị sort (ví dụ: "-pubTime", "+title")
   * @param defaultField Trường mặc định để sắp xếp nếu sortField không hợp lệ
   * @param defaultDirection Hướng mặc định nếu sortField không hợp lệ
   * @param dtoToEntityFieldMapping Ánh xạ giữa các trường DTO và entity
   * @return Đối tượng Sort
   */
  public static Sort createSort(String sortField, String defaultField, Sort.Direction defaultDirection,
      Map<String, String> dtoToEntityFieldMapping) {
    if (sortField != null && !sortField.isEmpty()) {
      Sort.Direction direction = Sort.Direction.ASC; // Mặc định tăng dần
      String field = sortField;

      // Kiểm tra dấu để xác định chiều sắp xếp
      if (sortField.startsWith("-")) {
        direction = Sort.Direction.DESC;
        field = sortField.substring(1); // Bỏ dấu '-'
      } else if (sortField.startsWith("+")) {
        field = sortField.substring(1); // Bỏ dấu '+'
      }

      // Ánh xạ tên trường từ DTO sang entity nếu ánh xạ được cung cấp
      if (dtoToEntityFieldMapping != null) {
        field = dtoToEntityFieldMapping.getOrDefault(field, field);
      }

      return Sort.by(direction, field);
    }

    // Nếu không có giá trị mặc định, trả về Sort.unsorted()
    if (defaultField == null || defaultDirection == null) {
      return Sort.unsorted();
    }

    // Ánh xạ tên trường mặc định nếu ánh xạ được cung cấp
    if (dtoToEntityFieldMapping != null) {
      defaultField = dtoToEntityFieldMapping.getOrDefault(defaultField, defaultField);
    }

    // Trả về sắp xếp mặc định
    return Sort.by(defaultDirection, defaultField);
  }
}
