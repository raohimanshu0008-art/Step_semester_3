public class CompanyEmployeeRecord {
    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;

    static int totalRecords = 0;

    CompanyEmployeeRecord(String name, String empId, Employee employee, ParkingSlot slot) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;
        totalRecords++;
    }

    String fullProfile() {
        String pay;

        if (employee instanceof ManagerEmployee)
            pay = String.valueOf(((ManagerEmployee) employee).effectiveSalary());
        else if (employee instanceof InternEmployee)
            pay = String.valueOf(((InternEmployee) employee).effectiveSalary());
        else
            pay = String.valueOf(employee.getSalary());

        String parking = slot == null ? "no parking assigned" : slot.slotNo;

        return name + " | Pay: Rs " + pay + " | Slot: " + parking;
    }

    public static void main(String[] args) {

        Employee manager = new ManagerEmployee(1, "Divya", 70000, 8000);
        Employee employee = new Employee(2, "Karan", 40000);
        Employee intern = new InternEmployee(3, "Meera", 12000, 10000);

        ParkingSlot slot1 = new ParkingSlot("A1", 4, 0);
        ParkingSlot slot2 = new ParkingSlot("A2", 5, 0);

        CompanyEmployeeRecord r1 =
                new CompanyEmployeeRecord("Divya", "E101", manager, slot1);

        CompanyEmployeeRecord r2 =
                new CompanyEmployeeRecord("Karan", "E102", employee, slot2);

        CompanyEmployeeRecord r3 =
                new CompanyEmployeeRecord("Meera", "E103", intern, null);

        System.out.println(r1.fullProfile());
        System.out.println(r2.fullProfile());
        System.out.println(r3.fullProfile());

        System.out.println("Total records: " + totalRecords);
    }
}