class SrmStudent {
    String name, regNo;
    int attendance;

    SrmStudent(String n, String r, int a) {
        name = n; regNo = r; attendance = a;
    }

    void addAttendanceUpdate(int a) {
        attendance = a;
    }

    boolean isEligible() {
        return attendance >= 75;
    }

    static double classAverage(SrmStudent[] s) {
        int sum = 0;
        for (SrmStudent x : s) sum += x.attendance;
        return (double) sum / s.length;
    }

    public static void main(String[] args) {
        SrmStudent[] s = {
            new SrmStudent("Ravi","R1",82),
            new SrmStudent("Anitha","R2",68),
            new SrmStudent("Karthik","R3",91),
            new SrmStudent("Meera","R4",74),
            new SrmStudent("Suresh","R5",60)
        };

        for (SrmStudent x : s)
            System.out.println(x.name+" - "+x.attendance+"% - "+(x.isEligible()?"Eligible":"Detained"));

        System.out.println("Class average: "+classAverage(s)+"%");
    }
}