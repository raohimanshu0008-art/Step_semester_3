class HostelRoom {
    String roomNo;
    int beds, occupied;

    HostelRoom(String r,int b,int o) {
        roomNo=r; beds=b; occupied=o;
    }

    void allot(String name) {
        if (occupied < beds) {
            occupied++;
            System.out.println(name+" allotted to room "+roomNo);
        }
    }

    static HostelRoom findAvailableRoom(HostelRoom[] r) {
        for (HostelRoom x:r)
            if (x.occupied < x.beds) return x;
        return null;
    }

    static void safeAllot(HostelRoom[] r,String name) {
        HostelRoom x=findAvailableRoom(r);
        if (x!=null) x.allot(name);
        else System.out.println("No rooms available for "+name);
    }

    public static void main(String[] args) {
        HostelRoom[] r={
            new HostelRoom("C-214",3,2),
            new HostelRoom("C-507",2,2)
        };
        safeAllot(r,"Divya");

        r[0].occupied=3;
        safeAllot(r,"Divya");
    }
}