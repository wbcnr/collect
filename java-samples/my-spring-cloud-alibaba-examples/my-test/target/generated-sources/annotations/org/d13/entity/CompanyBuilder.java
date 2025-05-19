package org.d13.entity;

public class CompanyBuilder {

    private Company object = new Company();

    public Company build() {
        return object;
    }

    public CompanyBuilder setName(java.lang.String value) {
        object.setName(value);
        return this;
    }

    public CompanyBuilder setEmail(java.lang.String value) {
        object.setEmail(value);
        return this;
    }

}
