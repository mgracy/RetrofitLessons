package com.mgx.retrofitlesson1.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.Button;

import com.mgx.retrofitlesson1.R;
import com.mgx.retrofitlesson1.model.Person;
import com.mgx.retrofitlesson1.model.Tutorial;

public class ObjectTran extends Activity implements View.OnClickListener {
    private Button sButton, pButton;
    public static final String SER_KEY = "com.mgx.ipc.ser";
    public static final String PAR_KEY = "com.mgx.ipc.par";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serializeparcel);
        initUI();
    }

    private void initUI() {
        sButton = (Button) findViewById(R.id.btnSerializable);
        pButton = (Button) findViewById(R.id.btnParcelable);
        sButton.setOnClickListener(this);
        pButton.setOnClickListener(this);
    }

    public void serializeMethod(){
        Person person = new Person();
        person.setName("Gracy");
        person.setAge(22);
        Intent intent = new Intent(this, ObjectSerializable.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(SER_KEY, person);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void parcelableMethod(){
        Tutorial tutorial = new Tutorial();
        tutorial.setBookName("Android Developer Legend");
        tutorial.setAuthor("Gracy");
        tutorial.setPublishTime(2022);
        Intent intent = new Intent(this, ObjectParcelable.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(PAR_KEY, tutorial);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(view == sButton){
            serializeMethod();
        }else{
            parcelableMethod();
        }
    }
}
