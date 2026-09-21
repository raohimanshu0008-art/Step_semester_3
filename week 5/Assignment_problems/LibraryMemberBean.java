import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LibraryMemberBean {

    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswerHash;

    public LibraryMemberBean() {
        this(null, null);
    }

    public LibraryMemberBean(String name) {
        this(null, name);
    }

    public LibraryMemberBean(String membershipId, String name) {
        this.membershipId = membershipId;
        this.name = name;
        this.premiumMember = false;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {
        if (membershipId == null)
            membershipId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    public void setSecurityAnswer(String answer) {

        if (answer == null)
            securityAnswerHash = null;
        else
            securityAnswerHash = hash(answer);
    }

    private static String hash(String text) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] bytes = md.digest(
                text.getBytes(StandardCharsets.UTF_8)
            );

            StringBuilder result = new StringBuilder();

            for (byte b : bytes)
                result.append(String.format("%02x", b));

            return result.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        LibraryMemberBean m1 =
            new LibraryMemberBean("Priya Nair");

        System.out.println(m1.getMembershipId());

        LibraryMemberBean m2 =
            new LibraryMemberBean("LIB-8841", "Priya Nair");

        System.out.println(m2.getMembershipId());

        LibraryMemberBean m3 =
            new LibraryMemberBean();

        m3.setMembershipId("LIB-8841");
        m3.setMembershipId("FAKE-0000");

        System.out.println(m3.getMembershipId());
    }
}