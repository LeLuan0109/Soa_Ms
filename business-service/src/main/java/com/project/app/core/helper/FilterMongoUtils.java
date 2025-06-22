package com.project.app.core.helper;

import com.project.app.core.common.request.FilterPaging;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FilterMongoUtils {

//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    /**
//     * Filters and paginates MongoDB entities and converts them into DTOs.
//     *
//     * @param filterPost The filter and pagination parameters (page index, page size, and sort information).
//     * @param filterType The type of the filter object.
//     * @param dtoClass The DTO class that will receive the filtered and paginated results.
//     * @param entityClass The entity class that will be queried.
//     * @param converter A function that converts a list of entities to a list of DTOs.
//     * @param fieldMapping A map that maps filter field names to the corresponding entity field names.
//     * @param baseCriteria The base criteria that will be used in the query, allowing additional filters to be applied.
//     *
//     * @return A paginated result containing a list of DTOs.
//     */
//    public <T, D , K> Page<D> filterAndPaginate(
//            FilterPaging<K> filterPost,
//            Class<K> filterType,
//            Class<D> dtoClass,
//            Class<T> entityClass,
//            Function<List<T>, List<D>> converter,
//            Map<String, String> fieldMapping,
//            Criteria baseCriteria
//    ){
//        Criteria finalCriteria = baseCriteria;
//
//        if (filterPost != null) {
//            Criteria filterCriteria = buildFilterCriteria(filterPost, filterType , fieldMapping);
//            finalCriteria = new Criteria().andOperator(finalCriteria, filterCriteria);
//        }
//
//        Query query = new Query(finalCriteria);
//        if(filterPost != null){
//            applySortingIfNeeded(query, filterPost , fieldMapping);
//        }
//        // Get the total count of matching entities
//        long totalCount = mongoTemplate.count(query, entityClass);
//
//        // Fetch the entities with pagination applied
//        List<T> entityList = mongoTemplate.find(query.with(PageRequest.of(
//            filterPost.getPageIndex(),
//            filterPost.getPageSize()
//        )), entityClass);
//
//        // Convert the entities to DTOs
//        List<D> dtoList = converter.apply(entityList);
//
//        // Return a paginated result
//        return new PageImpl<>(dtoList, PageRequest.of(
//                filterPost.getPageIndex(),
//                filterPost.getPageSize()
//        ), totalCount);
//    }
//
//    /**
//     * Applies sorting to the MongoDB query if sorting information is provided.
//     *
//     * @param query The MongoDB query to apply sorting to.
//     * @param filterBase The FilterBasePaging object that contains the sort information.
//     * @param fieldMapping The field mapping between the DTO and entity classes.
//     */
//    private <K> void applySortingIfNeeded(Query query, FilterPaging<K> filterBase, Map<String, String> fieldMapping) {
//        if (filterBase.getSort() != null) {
//            MongoQuerySortHelper.applySortingIfNeeded(query, filterBase.getSort(), fieldMapping);
//        }
//    }
//
//    /**
//     * Builds the MongoDB criteria based on the filters provided in FilterBasePaging.
//     *
//     * @param filterPost The filter object containing the filtering criteria.
//     * @param filterType The type of the filter object.
//     * @param fieldMapping A map that maps filter field names to the corresponding entity field names.
//     * @return A MongoDB criteria object representing the filtering conditions.
//     */
//    private <K> Criteria buildFilterCriteria(FilterPaging<K> filterPost, Class<K> filterType, Map<String, String> fieldMapping) {
//        Criteria criteria = new Criteria();
//
//        try {
//            K filters = filterPost.getFilter();
//            if (filters != null) {
//                Class<?> currentClass = filterType;
//                while (currentClass != null && currentClass != Object.class) {
//                    for (Field field : currentClass.getDeclaredFields()) {
//                        field.setAccessible(true);
//                        Object value = field.get(filters);
//
//                        if (value != null) {
//                            String entityFieldName = fieldMapping.getOrDefault(field.getName(), field.getName());
//
//                            if (Number.class.isAssignableFrom(field.getType())) {
//                                criteria.and(entityFieldName).is(value); // Exact match for numbers
//                            } else if (String.class.isAssignableFrom(field.getType())) {
//                                criteria.and(entityFieldName).regex(".*" + value + ".*", "i");
//                            }
//                        }
//                    }
//                    currentClass = currentClass.getSuperclass(); // class inherited class
//                }
//            }
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException("Error accessing filter fields", e);
//        }
//
//        return criteria;
//    }

}
