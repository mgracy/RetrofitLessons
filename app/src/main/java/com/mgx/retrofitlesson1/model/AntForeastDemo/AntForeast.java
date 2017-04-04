package com.mgx.retrofitlesson1.model.AntForeastDemo;

/**
 * Created by glmgracy on 17/4/4.
 */

public class AntForeast {

    /**
     * id : 1
     * topNum : 1
     * topImage : xxxx
     * isUsed : Y
     * topUnit : 20g
     */

    private int id;
    private int topNum;
    private String topName;

    public AntForeast(int id, int topNum, String topName, int topImage, String isUsed, String topUnit) {
        this.id = id;
        this.topNum = topNum;
        this.topName = topName;
        this.topImage = topImage;
        this.isUsed = isUsed;
        this.topUnit = topUnit;
    }

    public String getTopName() {
        return topName;
    }

    public void setTopName(String topName) {
        this.topName = topName;
    }

    private int topImage;
    private String isUsed;
    private String topUnit;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTopNum() {
        return topNum;
    }

    public void setTopNum(int topNum) {
        this.topNum = topNum;
    }

    public int getTopImage() {
        return topImage;
    }

    public void setTopImage(int topImage) {
        this.topImage = topImage;
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    public String getTopUnit() {
        return topUnit;
    }

    public void setTopUnit(String topUnit) {
        this.topUnit = topUnit;
    }
}
