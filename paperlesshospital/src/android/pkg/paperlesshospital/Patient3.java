package android.pkg.paperlesshospital;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class Patient3 extends Activity {
	Button next;
	Bundle last;
	CheckBox currmeds;
	EditText meds, genprobs, prevsufffrom, allergies;
	ArrayList<String> al;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patient3);

		next = (Button) findViewById(R.id.ni3);
		currmeds = (CheckBox) findViewById(R.id.cuam);
		meds = (EditText) findViewById(R.id.medname);
		meds.setEnabled(false);
		al = new ArrayList<String>();

		last = getIntent().getExtras();
		al = new ArrayList<String>();
		al.addAll(last.getStringArrayList("last"));

		currmeds.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					meds.setEnabled(true);
				} else {
					meds.setText("");
					meds.setEnabled(false);
				}
			}
		});

		next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				al.add(meds.getText().toString());
				genprobs = (EditText) findViewById(R.id.genprb);
				al.add(genprobs.getText().toString());
				prevsufffrom = (EditText) findViewById(R.id.prevsuff);
				al.add(prevsufffrom.getText().toString());
				allergies = (EditText) findViewById(R.id.allergies);
				al.add(allergies.getText().toString());

				Bundle bun = new Bundle();
				bun.putStringArrayList("last", al);

				Intent nextWind = new Intent(Patient3.this, Patient4.class);

				nextWind.putExtras(bun);
				startActivity(nextWind);
			}
		});
	}
}