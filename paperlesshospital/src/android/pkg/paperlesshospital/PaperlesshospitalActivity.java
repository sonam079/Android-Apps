package android.pkg.paperlesshospital;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class PaperlesshospitalActivity extends Activity {
	EditText name, age, phoneno, emailid, address;
	RadioButton m, f;
	Button next;
	ArrayList<String> al;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patient1);

		next = (Button) findViewById(R.id.ni1);
		al = new ArrayList<String>();

		next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				name = (EditText) findViewById(R.id.name);
				al.add(name.getText().toString());

				m = (RadioButton) findViewById(R.id.male);
				String sx;
				if (m.isChecked())
					sx = "Male";
				else
					sx = "Female";
				al.add(sx);

				age = (EditText) findViewById(R.id.age);
				al.add(age.getText().toString());

				phoneno = (EditText) findViewById(R.id.phno);
				al.add(phoneno.getText().toString());

				emailid = (EditText) findViewById(R.id.mailid);
				al.add(emailid.getText().toString());

				address = (EditText) findViewById(R.id.address);
				al.add(address.getText().toString());

				Bundle bun = new Bundle();
				bun.putStringArrayList("last", al);

				Intent nextWind = new Intent(PaperlesshospitalActivity.this,
						Patient2.class);

				nextWind.putExtras(bun);
				startActivity(nextWind);
			}
		});
	}
}
