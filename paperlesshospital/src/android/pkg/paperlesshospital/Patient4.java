package android.pkg.paperlesshospital;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;

public class Patient4 extends Activity {
	Spinner hoslist;
	Button next;
	Bundle last;
	CheckBox visithos;
	EditText id, doc;
	ArrayList<String> al;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patient4);

		hoslist = (Spinner) findViewById(R.id.hosplist);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.hospital_array,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		hoslist.setAdapter(adapter);

		id = (EditText) findViewById(R.id.refid);
		doc = (EditText) findViewById(R.id.prefdoc);
		next = (Button) findViewById(R.id.preview);
		visithos = (CheckBox) findViewById(R.id.hyvthb);
		al = new ArrayList<String>();

		last = getIntent().getExtras();
		al = new ArrayList<String>();
		al.addAll(last.getStringArrayList("last"));

		visithos.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					id.setEnabled(true);
					doc.setEnabled(true);
				} else {
					id.setText("");
					doc.setText("");
					id.setEnabled(false);
					doc.setEnabled(false);
				}
			}
		});

		next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				al.add((String) hoslist.getSelectedItem());
				al.add(id.getText().toString());
				al.add(doc.getText().toString());

				Bundle bun = new Bundle();
				bun.putStringArrayList("last", al);

				Intent nextWind = new Intent(Patient4.this, Preview.class);

				nextWind.putExtras(bun);
				startActivity(nextWind);
			}
		});
	}
}