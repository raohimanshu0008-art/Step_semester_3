public class EmployeeTest {
    public static void main(String[] args) {

        Employee e = new Employee(1, "Aman", 40000);
        Employee m = new ManagerEmployee(2, "Rohan", 70000, 8000);
        Employee i = new InternEmployee(3, "Priya", 12000, 10000);

        if (e instanceof ManagerEmployee)
            System.out.println("Manager effective pay: Rs " + ((ManagerEmployee)e).effectiveSalary());
        else if (e instanceof InternEmployee)
            System.out.println("Intern effective pay: Rs " + ((InternEmployee)e).effectiveSalary());
        else
            System.out.println("Plain employee pay: Rs " + e.getSalary());

        if (m instanceof ManagerEmployee)
            System.out.println("Manager effective pay: Rs " + ((ManagerEmployee)m).effectiveSalary());

        if (i instanceof InternEmployee)
            System.out.println("Intern effective pay: Rs " + ((InternEmployee)i).effectiveSalary());
    }
}