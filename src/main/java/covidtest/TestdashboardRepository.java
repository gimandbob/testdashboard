package covidtest;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestdashboardRepository extends CrudRepository<Testdashboard, Long> {

    List<Testdashboard> findByInspectionId(Long inspectionId);

}