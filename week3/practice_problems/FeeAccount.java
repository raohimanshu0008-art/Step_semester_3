class FeeAccount {
    private String regNo;
    private double totalFee, amountPaid;

    FeeAccount(String r, double f) {
        regNo = r; totalFee = f;
    }

    void pay(double a) {
        if (a > 0) amountPaid += a;
    }

    double getDue() {
        return totalFee - amountPaid;
    }

    public static void main(String[] args) {
        FeeAccount p = new FeeAccount("R1",150000);
        HostelFeeAccount h = new HostelFeeAccount("R2",200000);
        ScholarshipFeeAccount s = new ScholarshipFeeAccount("R3",180000,20);

        p.pay(150000);
        h.payInTwoInstallments(60000);

        System.out.println("Plain account due: Rs "+p.getDue());
        System.out.println("Hostel account due: Rs "+h.getDue());
        System.out.println("Scholarship account effective due: Rs "+s.effectiveDue());
    }
}

class HostelFeeAccount extends FeeAccount {
    HostelFeeAccount(String r,double f) { super(r,f); }

    void payInTwoInstallments(double a) {
        pay(a);
    }
}

class ScholarshipFeeAccount extends FeeAccount {
    private double scholarshipPercent;

    ScholarshipFeeAccount(String r,double f,double p) {
        super(r,f);
        scholarshipPercent = p;
    }

    double effectiveDue() {
        return getDue() * (1-scholarshipPercent/100);
    }
}