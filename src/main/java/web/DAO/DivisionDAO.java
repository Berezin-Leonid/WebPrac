package web.DAO;

import lombok.Builder;
import lombok.Getter;
import web.models.Division;
import web.models.Employee;

import java.util.List;

public interface DivisionDAO extends CommonDAO<Division, Long> {

    List<Division> getAllDivisionByName(String divisionName);
    Division getSingleDivisionByName(String divisionName);
    List<Division> getAllDivisionByParentId(Long id);


    // Эта функция включает в себя формирование отображения древовидной структуры для подразделения
    //List<Division> getByFilter(Filter filter);

    @Builder
    @Getter
    class Filter {
        private String name;
        private Integer parentId;
    }

    static Filter.FilterBuilder getFilterBuilder() {
        return Filter.builder();
    }
}