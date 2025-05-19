package org.d13.entity;

public class Company2Builder {

    private Company2 object = new Company2();

    public Company2 build() {
        return object;
    }

    public Company2Builder setName(java.lang.String value) {
        object.setName(value);
        return this;
    }

    public Company2Builder setEmail(java.lang.String value) {
        object.setEmail(value);
        return this;
    }

}
