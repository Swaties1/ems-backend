package ss.ems_backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ss.ems_backend.dto.EmployeeDto;
import ss.ems_backend.entity.Employee;
import ss.ems_backend.exception.ResourceNotFoundException;
import ss.ems_backend.mapper.EmployeeMapper;
import ss.ems_backend.repository.EmployeeRepository;
import ss.ems_backend.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl  implements EmployeeService {
    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee= EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee=employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
       Employee employee= employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee does not exist with given id : "+employeeId));
    return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
       List<Employee> employees=employeeRepository.findAll();
       return employees.stream().map((employee)-> EmployeeMapper.mapToEmployeeDto(employee))
               .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
       Employee employee= employeeRepository.findById(employeeId).orElseThrow(()->
               new ResourceNotFoundException("Employee does not exist with given id :"+employeeId));
    employee.setFirstName(updatedEmployee.getFirstName());
    employee.setLastName(updatedEmployee.getLastName());
    employee.setEmail(updatedEmployee.getEmail());
    Employee updatedEmployeeObj=employeeRepository.save(employee);
    return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee= employeeRepository.findById(employeeId).orElseThrow(()->
                new ResourceNotFoundException("Employee does not exist with given id :"+employeeId));
    employeeRepository.deleteById(employeeId);
    }
}
