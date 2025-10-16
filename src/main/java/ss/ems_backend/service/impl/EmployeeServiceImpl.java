package ss.ems_backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ss.ems_backend.dto.EmployeeDto;
import ss.ems_backend.entity.Employee;
import ss.ems_backend.mapper.EmployeeMapper;
import ss.ems_backend.repository.EmployeeRepository;
import ss.ems_backend.service.EmployeeService;

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
}
