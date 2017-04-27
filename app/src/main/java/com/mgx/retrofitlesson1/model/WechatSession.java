package com.mgx.retrofitlesson1.model;

/**
 * Created by glmgracy on 17/4/27.
 */

public class WechatSession {

    /**
     * Image : 111
     * Name : Gracy.Ma
     * Description : welcome to vianam
     * time : morning
     * mute : null
     */

    private int Image;
    private String Name;
    private String Description;
    private String time;
    private String mute;

    public WechatSession(int image, String name, String description, String time, String mute) {
        Image = image;
        Name = name;
        Description = description;
        this.time = time;
        this.mute = mute;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int Image) {
        this.Image = Image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMute() {
        return mute;
    }

    public void setMute(String mute) {
        this.mute = mute;
    }
}
