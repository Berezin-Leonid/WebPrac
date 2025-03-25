package ru.msu.cmc.webprak.DAO;

import lombok.Builder;
import lombok.Getter;
import ru.msu.cmc.webprak.models.EmployeePostDivision;

import java.util.List;

public interface EmployeePostDivisionDAO extends CommonDAO<EmployeePostDivision, Long> {
    List<EmployeePostDivision> getByFilter(Filter filter);

    @Builder
    @Getter
    class Filter {
        private String fullName;
        private String position;
        private String division;
    }

    static Filter.FilterBuilder getFilterBuilder() {
        return Filter.builder();
    }
}