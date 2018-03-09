package android.pkg.paperlesshospital;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class Patient2 extends Activity {
	Button next;
	Bundle last;
	ArrayList<String> al;
	EditText ails1, ails2, ails3, ails4, ails5;
	CheckBox psufaprb;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patient2);

		next = (Button) findViewById(R.id.ni2);

		last = getIntent().getExtras();
		al = new ArrayList<String>();
		al.addAll(last.getStringArrayList("last"));

		next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ails1 = (EditText) findViewById(R.id.ail1);
				al.add(ails1.getText().toString());
				ails2 = (EditText) findViewById(R.id.ail2);
				al.add(ails2.getText().toString());
				ails3 = (EditText) findViewById(R.id.ail3);
				al.add(ails3.getText().toString());
				ails4 = (EditText) findViewById(R.id.ail4);
				al.add(ails4.getText().toString());
				ails5 = (EditText) findViewById(R.id.ail5);
				al.add(ails5.getText().toString());
				psufaprb = (CheckBox) findViewById(R.id.psfaotaa);
				String suffers;
				if (psufaprb.isChecked())
					suffers = "yes";
				else
					suffers = "no";
				al.add(suffers);

				Bundle bun = new Bundle();
				bun.putStringArrayList("last", al);

				Intent nextWind = new Intent(Patient2.this, Patient3.class);

				nextWind.putExtras(bun);
				startActivity(nextWind);
			}
		});
	}
}