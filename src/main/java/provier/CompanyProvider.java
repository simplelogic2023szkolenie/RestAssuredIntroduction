package provier;

import models.user.Company;

public class CompanyProvider {
    public static Company getFullCompanyData() {
        return
                Company.builder()
                        .name("Example Company")
                        .catchPhrase("We lead, others follow.")
                        .bs("innovative solutions")
                        .build();
    }
}
