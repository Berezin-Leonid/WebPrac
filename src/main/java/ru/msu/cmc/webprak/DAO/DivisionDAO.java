package ru.msu.cmc.webprak.DAO;

import lombok.Builder;
import lombok.Getter;
import ru.msu.cmc.webprak.models.Division;

import java.util.List;

public interface DivisionDAO extends CommonDAO<Division, Long> {


    Division getSingleDivisionByName(String divisionName);



    // Эта функция включает в себя формирование отображения древовидной структуры для подразделения
    List<Employee> getByFilter(Filter filter);

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