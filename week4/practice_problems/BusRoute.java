public class BusRoute {
    String routeCode;
    String routeName;
    int priority;

    public BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 5);
    }

    int compareTo(BusRoute other) {
        if (this.priority != other.priority)
            return Integer.compare(this.priority, other.priority);

        int code = this.routeCode.compareToIgnoreCase(other.routeCode);
        if (code != 0)
            return code;

        return this.routeName.compareToIgnoreCase(other.routeName);
    }

    static BusRoute[] rankRoutes(BusRoute[] routes) {
        BusRoute[] result = routes.clone();

        for (int i = 0; i < result.length - 1; i++) {
            for (int j = 0; j < result.length - i - 1; j++) {
                if (result[j].compareTo(result[j + 1]) > 0) {
                    BusRoute temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        BusRoute[] routes = {
            new BusRoute("RT205L", "Airport Express", 3),
            new BusRoute("rt201j", "City Central", 4),
            new BusRoute("RT299T", "Night Service")
        };

        BusRoute[] ranked = rankRoutes(routes);

        for (BusRoute r : ranked)
            System.out.println(r.routeCode);
    }
}