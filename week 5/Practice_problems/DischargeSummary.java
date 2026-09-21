public class DischargeSummary {

    private final String patientId;
    private final String[] medicationCodes;

    private static int totalProcessed;

    static {
        totalProcessed = 0;
    }

    public DischargeSummary(String patientId, String[] medicationCodes) {

        if (patientId == null || medicationCodes == null)
            throw new IllegalArgumentException();

        String[] copy = new String[medicationCodes.length];

        for (int i = 0; i < medicationCodes.length; i++) {
            String code = medicationCodes[i];

            if (code == null || !code.matches("MED-[A-Z]"))
                throw new IllegalArgumentException();

            copy[i] = code;
        }

        this.patientId = patientId;
        this.medicationCodes = copy;
    }

    public String[] getMedicationCodes() {
        return medicationCodes.clone();
    }

    public DischargeSummary withCorrectedMedication(
            int index, String newCode) {

        if (index < 0 || index >= medicationCodes.length)
            throw new IndexOutOfBoundsException();

        if (newCode == null || !newCode.matches("MED-[A-Z]"))
            throw new IllegalArgumentException();

        String[] copy = medicationCodes.clone();
        copy[index] = newCode;

        return new DischargeSummary(patientId, copy);
    }

    static String processNightlyBatch(
            DischargeSummary[] summaries) {

        int processed = 0;
        int nullSkipped = 0;
        int critical = 0;
        int routine = 0;

        if (summaries == null)
            return "0 processed | 0 null skipped | 0 critical-care | 0 routine";

        for (DischargeSummary s : summaries) {

            if (s == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            totalProcessed++;

            if (s instanceof CriticalCareDischargeSummary)
                critical++;
            else
                routine++;
        }

        return processed + " processed | " +
               nullSkipped + " null skipped | " +
               critical + " critical-care | " +
               routine + " routine";
    }

    public static void main(String[] args) {

        try {
            new DischargeSummary(
                "MT2026-0142",
                new String[]{"MED-A", "bad"}
            );
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        DischargeSummary d =
            new DischargeSummary(
                "MT2026-0142",
                new String[]{"MED-A", "MED-B"}
            );

        String[] codes = d.getMedicationCodes();
        codes[0] = "TAMPERED";

        System.out.println(d.getMedicationCodes()[0]);

        DischargeSummary[] summaries = {
            new CriticalCareDischargeSummary(
                "MT001",
                new String[]{"MED-X"},
                4
            ),
            null,
            new DischargeSummary(
                "MT002",
                new String[]{"MED-Y"}
            )
        };

        System.out.println(
            processNightlyBatch(summaries)
        );
    }
}

class CriticalCareDischargeSummary extends DischargeSummary {

    private final int icuDays;

    public CriticalCareDischargeSummary(
            String patientId,
            String[] medicationCodes,
            int icuDays) {

        super(patientId, medicationCodes);
        this.icuDays = icuDays;
    }

    public int getIcuDays() {
        return icuDays;
    }
}