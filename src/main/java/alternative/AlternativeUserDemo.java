package alternative;

public class AlternativeUserDemo {
    public static void main(String[] args) {

        AlternativeUser user =
                new AlternativeUser.AlternativeUserBuilder()
                        .name()
                        .email()
                        .build();

        AlternativeUser user2 =
                new AlternativeUser.AlternativeUserBuilder()
                        .name()
                        .email()
                        .phone()
                        .company()
                        .build();

        AlternativeUser user3 =
                new AlternativeUser.AlternativeUserBuilder()
                        .name()
                        .build();
    }
}
