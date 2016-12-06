package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.model.Employee;
import hello.repository.EmployeeRepository;

@RestController
public class ServiceController {

  @Autowired
  EmployeeRepository employeeRepository;
  

  @RequestMapping("/employees")
  public Iterable<Employee> employees() {
    return employeeRepository.findAll();
  }
  
  @RequestMapping("/employees/count")
  public long employeesCount() {
    return employeeRepository.count();
  }
  
  @RequestMapping("/employees/search")
  public Iterable<Employee> employeesSearch(@RequestParam(value = "firstName", defaultValue="Erwin") String firstName) {
    return employeeRepository.findByFirstNameLike(firstName);
  }
}
