package com.project.app.core.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.app.core.exception.exceptionSub.RuntimeExceptionSls;
import com.project.app.core.util.LocalizationUtils;
import com.project.app.core.validation.ValidateRowData;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;

@Component
public class GenericExcelHandler {
  @Autowired
  private LocalizationUtils localizationUtils;

  public <T> List<T> readExcelFile(MultipartFile file, Class<T> type) throws Exception {
    List<T> entities = new ArrayList<>();

    try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
      Sheet sheet = workbook.getSheetAt(0);
      Iterator<Row> rowIterator = sheet.iterator();

      Row headerRow = rowIterator.next();
      List<String> headers = new ArrayList<>();
      headerRow.forEach(cell -> headers.add(cell.getStringCellValue()));

      int rowIndex = 1;
      while (rowIterator.hasNext()) {
        Row row = rowIterator.next();
        Map<String, Object> rowData = new HashMap<>();
        for (int i = 0; i < headers.size(); i++) {
          Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
          Object cellValue = getCellValue(cell);

          if (cellValue instanceof String value) {
            if (value.startsWith("[") && value.endsWith("]")) {
              try {
                List<String> list = new ObjectMapper().readValue(value, new TypeReference<List<String>>() {
                });
                rowData.put(headers.get(i), list);
              } catch (Exception e) {
                rowData.put(headers.get(i), value);
              }
            } else if (value.startsWith("{") && value.endsWith("}")) {
              String value1 = value.replace("\\", "");
              ObjectMapper mapper = new ObjectMapper();
              mapper.readValue(value1, JsonNode.class);
              rowData.put(headers.get(i), value1);
            } else {
              rowData.put(headers.get(i), value);
            }
          } else {
            rowData.put(headers.get(i), cellValue);
          }
        }
        ValidateRowData validateRowData = new ValidateRowData();
        validateRowData.validateRowData(rowData, type, rowIndex);

        T entity = mapToEntity(rowData, type);
        entities.add(entity);
        rowIndex++;
      }
    } catch (RuntimeExceptionSls ex) {
      throw new RuntimeExceptionSls(localizationUtils.getLocalizedMessage(ex.getMessage()));
    }

    return entities;
  }

  private <T> T mapToEntity(Map<String, Object> rowData, Class<T> type) {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.convertValue(rowData, type);
  }

  public Object getCellValue(Cell cell) {
    switch (cell.getCellType()) {
      case STRING -> {
        return cell.getStringCellValue();
      }
      case NUMERIC -> {
        if (DateUtil.isCellDateFormatted(cell)) {
          return cell.getDateCellValue();
        } else {
          return cell.getNumericCellValue();
        }
      }
      case BOOLEAN -> {
        return cell.getBooleanCellValue();
      }
      case FORMULA -> {
        return cell.getCellFormula();
      }
      case BLANK -> {
        return "";
      }
      default -> {
        return null;
      }
    }
  }


}
