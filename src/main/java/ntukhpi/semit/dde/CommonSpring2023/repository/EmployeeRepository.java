package ntukhpi.semit.dde.CommonSpring2023.repository;

import ntukhpi.semit.dde.CommonSpring2023.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
