package com.mgx.retrofitlesson1.model;

import java.io.Serializable;

/**
 * Created by glmgracy on 17/1/7.
 */

public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * name : gracyma
     * age : 30
     */

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
