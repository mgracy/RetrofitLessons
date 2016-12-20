package com.mgx.retrofitlesson1.model;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by glmgracy on 16/12/20.
 */

public class City extends DataSupport{

    /**
     * id : 111
     * city_name : gz
     * city_code : 020
     * province : null
     * countries : null
     */

    private int id;
    private String city_name;
    private String city_code;
    private Province province;
    private List<Country> countries;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
