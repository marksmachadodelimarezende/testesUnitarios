package com.mmlrdev.springMVC.mocks;

import com.mmlrdev.springMVC.models.Address;
import com.mmlrdev.springMVC.models.Company;
import com.mmlrdev.springMVC.models.Geo;
import com.mmlrdev.springMVC.models.User;

import java.math.BigDecimal;

public class MocksObjects {

    public static User getUserMock() {
        return User.builder()
                .id(1L)
                .name("Mock Obj Test")
                .username("Mock UserName")
                .email("email@mock.test")
                .address(getAddressMock())
                .phone("+55 99 9 9999-9999")
                .website("www.mock.test")
                .company(getCompanyMock())
                .build();
    }

    public static Address getAddressMock() {
        Address address = new Address();
        address.setStreet("Rua Mock");
        address.setSuite("test");
        address.setCity("City Mocks");
        address.setZipcode("74000000");
        address.setGeo(getGeoMock());
        return address;
    }

    public static Geo getGeoMock() {
        Geo geo = new Geo();
        geo.setLat(BigDecimal.valueOf(-37.3159));
        geo.setLng(BigDecimal.valueOf(81.1496));
        return geo;
    }

    public static Company getCompanyMock() {
        Company company = new Company();
        company.setName("Mock Company Tests");
        company.setCatchPhrase("Multi-layered client-server neural-net");
        company.setBs("harness real-time e-markets");
        return company;
    }
}
