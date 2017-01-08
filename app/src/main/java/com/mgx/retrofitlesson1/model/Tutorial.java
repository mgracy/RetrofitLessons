package com.mgx.retrofitlesson1.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by glmgracy on 17/1/7.
 */

public class Tutorial implements Parcelable {

    /**
     * bookName : Head first
     * author : gracyma
     * publishTime : 1
     */

    private String bookName;
    private String author;
    private int publishTime;

    private Tutorial(Parcel in) {
        bookName = in.readString();
        author = in.readString();
        publishTime = in.readInt();
    }

    public Tutorial(){

    }
//    public Tutorial(String bookName, String author, int publishTime){
//        this.bookName = bookName;
//        this.author = author;
//        this.publishTime = publishTime;
//    }
    public static final Creator<Tutorial> CREATOR = new Creator<Tutorial>() {
        @Override
        public Tutorial createFromParcel(Parcel in) {
            return new Tutorial(in);
        }

        @Override
        public Tutorial[] newArray(int size) {
            return new Tutorial[size];
        }
    };

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(int publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(bookName);
        parcel.writeString(author);
        parcel.writeInt(publishTime);
    }
}
