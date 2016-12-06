package hello.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import hello.model.Employee;

@Transactional
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
  List<Employee> findByFirstNameLike(String firstName);
}
