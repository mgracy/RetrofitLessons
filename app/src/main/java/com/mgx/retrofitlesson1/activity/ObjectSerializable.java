package com.mgx.retrofitlesson1.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.mgx.retrofitlesson1.model.Person;

/**
 * Created by glmgracy on 17/1/7.
 */

public class ObjectSerializable extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        Person person = (Person) getIntent().getSerializableExtra(ObjectTran.SER_KEY);
        textView.setText("Your name is " + person.getName() + "\n" +
            "Your age is " + person.getAge());
        setContentView(textView);
    }
}
