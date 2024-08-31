package JavaStreams.GroupByExamples;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Employee implements Comparable{
    int empId;
    String empName;
    long salary;
    Department department;
    String homeLocation;

    public Employee(int empId, String empName, long salary, Department department, String homeLocation) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
        this.department = department;
        this.homeLocation = homeLocation;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", salary=" + salary +
                ", department=" + department.toString() +
                ", homeLocation='" + homeLocation + '\'' +
                '}';
    }
    @Override
    public int compareTo(Object o) {
        Employee e = (Employee) o;
        return (int) (this.salary - e.salary);
    }
}
class Department {
    int deptId;
    String departmentName;

    public Department(int deptId, String departmentName) {
        this.deptId = deptId;
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptId=" + deptId +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
public class Main {
    public static void main(String[] args) {
        List<Employee> empList = new ArrayList<>();

        // CS-2, PE-1, HR-2, IT-2
        empList.add(new Employee(1, "John", 20000, new Department(1, "HR"), "GKP"));
        empList.add(new Employee(2, "watson", 30000, new Department(2, "CS"), "GZB"));
        empList.add(new Employee(3, "Sherlock", 40000, new Department(3, "IT"), "NOIDA"));
        empList.add(new Employee(4, "Holmes", 50000, new Department(4, "PE"), "GKP"));
        empList.add(new Employee(5, "Rocky", 60000, new Department(5, "CS"), "GZB"));
        empList.add(new Employee(6, "Wick", 70000, new Department(6, "HR"), "NOIDA"));
        empList.add(new Employee(7, "Kohli", 80000, new Department(7, "IT"), "GKP"));

        // learn groupby from https://stackabuse.com/guide-to-java-8-collectors-groupingby/
//        getDepartmentWiseEmployeeList(empList);
//        getSortedDepartmentWiseEmployeeList(empList);
//        getDepartmentHighestSalariedEmployee(empList);
//        getNthHighestSalariedEmployeePerDept(empList, 2);
//        getEmployeeCountPerDepartment(empList);
        getDepartmentEmployeesSalaryGreaterThan(empList, 50000);
//        getAvgSalaryOFDepartment(empList);
//        getEmployeeCountCityWise(empList);
    }

    private static void getDepartmentEmployeesSalaryGreaterThan(List<Employee> empList, int i) {
    }

    private static void getEmployeeCountPerDepartment(List<Employee> empList) {
        empList.stream().collect(Collectors.groupingBy(e->e.department.departmentName, Collectors.counting()))
        .forEach((k,v) -> System.out.println(k+" , "+v.toString()));
    }

    private static void getDepartmentWiseEmployeeList(List<Employee> empList) {
        Map<String, List<Employee>> map = empList.stream()
                .collect(Collectors.groupingBy(e -> e.department.departmentName, Collectors.toCollection(ArrayList::new)));
        map.forEach((k,v) -> System.out.println(k+" , "+v.toString()));
    }
    private static void getSortedDepartmentWiseEmployeeList(List<Employee> empList) {
//        Learn toMap merge function from here -> https://stackabuse.com/guide-to-java-8-collectors-tomap/
        Map<String, List<Employee>> map = empList.stream()
                .collect(Collectors.groupingBy(e -> e.department.departmentName, Collectors.toCollection(ArrayList::new)))
                .entrySet().stream().sorted(Map.Entry.comparingByKey())
                // (e1, e2) -> e.getKey().compareTo(e2.getKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (old, latest) -> old, LinkedHashMap::new));
        map.forEach((k,v) -> System.out.println(k+" , "+v.toString()));
    }
    private static void getDepartmentHighestSalariedEmployee(List<Employee> empList) {
        Map<String, Employee> collect = empList.stream()
                .collect(Collectors.groupingBy(e -> e.department.departmentName,
                Collectors.collectingAndThen(Collectors.maxBy((e1, e2) -> (int) (e1.salary - e2.salary)), Optional::get)));
        /*
        Map<String, Employee> collect = empList.stream()
                .collect(
                        Collectors.toMap(e -> e.department.departmentName,
                                Function.identity(), BinaryOperator.maxBy((e1, e2) -> (int) (e1.salary - e2.salary))));
         */
        collect.forEach((k,v) -> System.out.println(k+" , "+v.toString()));
    }
// TODO: complete this method
    private static void getNthHighestSalariedEmployeePerDept(List<Employee> empList, int i) {
        empList.stream()
                .collect(
                        Collectors.groupingBy(e -> e.department.departmentName)).entrySet().stream()
                .sorted(Comparator.comparing(e -> ((Employee) e.getValue())));
    }
}
