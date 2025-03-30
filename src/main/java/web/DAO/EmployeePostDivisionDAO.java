package web.DAO;

import lombok.Builder;
import lombok.Getter;
import web.models.EmployeePostDivision;

import java.util.List;

public interface EmployeePostDivisionDAO extends CommonDAO<EmployeePostDivision, Long> {
//    List<EmployeePostDivision> getByFilter(Filter filter);

    List<EmployeePostDivision> getByPostDivisionId(Long postdivisionID);
    List<EmployeePostDivision> getByEmployeeId(Long EmployeeID);


    /*
    @Builder
    @Getter
    class Filter {
        private Long postdivisionid;
        private Long employeeid;
        private Long division;
    }

    static Filter.FilterBuilder getFilterBuilder() {
        return Filter.builder();
    }

     */
}