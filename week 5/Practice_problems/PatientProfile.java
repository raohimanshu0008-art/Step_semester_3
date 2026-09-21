import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class PatientProfile {

    private String patientId;
    private String name;
    private boolean discharged;
    private String lockerPinHash;

    public PatientProfile() {
        this(null, null);
    }

    public PatientProfile(String name) {
        this(null, name);
    }

    public PatientProfile(String patientId, String name) {
        this.patientId = patientId;
        this.name = name;
        this.discharged = false;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String id) {
        if (patientId == null)
            patientId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDischarged() {
        return discharged;
    }

    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    public void setLockerPin(String pin) {
        if (pin == null || !pin.matches("\\d{4,6}"))
            throw new IllegalArgumentException();

        lockerPinHash = hash(pin);
    }

    private String hash(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes =
                md.digest(value.getBytes(StandardCharsets.UTF_8));

            StringBuilder result = new StringBuilder();

            for (byte b : bytes)
                result.append(String.format("%02x", b));

            return result.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(
            new PatientProfile("Arjun Iyer").getPatientId()
        );

        System.out.println(
            new PatientProfile(
                "MT2026-0142",
                "Arjun Iyer"
            ).getPatientId()
        );

        PatientProfile p = new PatientProfile();

        p.setPatientId("MT2026-0142");
        p.setPatientId("HACKED-0000");

        System.out.println(p.getPatientId());
    }
}