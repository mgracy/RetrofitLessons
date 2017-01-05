package com.mgx.retrofitlesson1.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by glmgr on 2017/1/5.
 */

public class User implements Parcelable {
    public User(int userId, String userName, boolean isMale) {
        this.userId = userId;
        this.userName = userName;
        this.isMale = isMale;
    }

    /**
     * userId : 1
     * userName : gracy.ma
     * isMale : true
     */

    private int userId;
    private String userName;
    private boolean isMale;

    public Book book;

    protected User(Parcel in) {
        userId = in.readInt();
        userName = in.readString();
        isMale = in.readByte() != 0;
        book = in.readParcelable(Thread.currentThread().getContextClassLoader());

    }

    /**
     * 反序列化，其内部标明了如何创建序列化对象和数组，并通过Parcel的一系列。。。
     */
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isIsMale() {
        return isMale;
    }

    public void setIsMale(boolean isMale) {
        this.isMale = isMale;
    }

    /**
     * 几乎在所有情况下这个方法都应该返回 0，仅当当前对象中存在文件描述符时（CONTENTS_FILE_DESCRIPTOR），此方法返回1.
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 序列化功能由writeToParcel来完成，最终是通过Parcel中的一系列write方法来完成
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(userName);
        dest.writeByte((byte) (isMale ? 1 : 0));
    }
}
