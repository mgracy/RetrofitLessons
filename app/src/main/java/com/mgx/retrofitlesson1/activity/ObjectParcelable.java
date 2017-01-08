package com.mgx.retrofitlesson1.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import com.mgx.retrofitlesson1.model.Tutorial;

public class ObjectParcelable extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        Tutorial tutorial = getIntent().getParcelableExtra(ObjectTran.PAR_KEY);
        textView.setText("Book name is " + tutorial.getBookName() + "\n" +
        "Author is " + tutorial.getAuthor() + "\n" +
        "PublishTime is " + tutorial.getPublishTime());
        setContentView(textView);
    }
}
