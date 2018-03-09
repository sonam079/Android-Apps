package android.pkg.paperlesshospital;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Preview extends Activity {
	ArrayList<String> total;
	String ipList[] = { "172.16.86.156", "169.254.127.127", "169.254.160.23",
			"xxx.xxx.xx.xx", "xxx.xxx.xx.xx", "xxx.xxx.xx.xx" }, server, ip;
	Bundle last;
	Button submit;
	FileInputStream fis;
	HttpURLConnection connection;
	DataOutputStream outputstream;
	int bytesRead, bytesAvailable, maxBufferSize = 1024 * 1024, bufferSize;
	byte buffer[];
	String tags[] = { "Name", "Sex", "Age", "Phone", "EMail", "Address",
			"Problem", "Problem", "Problem", "Problem", "Problem",
			"Has Suffered", "Using Meds", "Current Meds", "Suffered From",
			"Allergies", "Preffered Hospital", "RefID", "Doctor" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preview);

		final TextView myTV = (TextView) findViewById(R.id.finalPrev);
		myTV.setMovementMethod(new ScrollingMovementMethod());

		submit = (Button) findViewById(R.id.submit);

		total = new ArrayList<String>();
		total = new ArrayList<String>();
		last = getIntent().getExtras();
		total.addAll(last.getStringArrayList("last"));

		for (int i = 0; i < total.size(); i++)
			myTV.append(total.get(i) + "\n");

		String hospital = total.get(16);
		if (hospital.equalsIgnoreCase("Hospital A"))
			ip = ipList[0];
		else if (hospital.equalsIgnoreCase("Hospital B"))
			ip = ipList[1];
		else if (hospital.equalsIgnoreCase("Hospital C"))
			ip = ipList[2];
		else if (hospital.equalsIgnoreCase("Hospital D"))
			ip = ipList[3];
		else if (hospital.equalsIgnoreCase("Hospital E"))
			ip = ipList[4];
		else
			ip = ipList[5];

		submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String id;
				Random rnd = new Random();
				if (total.get(17).equals(""))
					id = "temp" + rnd.nextInt(Integer.MAX_VALUE);
				else
					id = total.get(17);

				server = "http://" + ip + "/uploadservice/reciever.ashx?filename="
						+ id;

				File f = new File(getFilesDir().getAbsolutePath()
						+ File.separator + "patientInfo");

				String path = f.getAbsolutePath();

				try {
					BufferedWriter br = new BufferedWriter(new FileWriter(f));

					for (int i = 0; i < total.size(); i++)
						br.write(tags[i] + ":" + total.get(i) + "\n");

					br.close();

					FileInputStream inputstream = new FileInputStream(new File(
							path));
					URL url = new URL(server);

					// System.out.println(server);
					connection = (HttpURLConnection) url.openConnection();

					connection.setDoInput(true);
					connection.setDoOutput(true);
					connection.setUseCaches(false);
					connection.setRequestMethod("POST");
					connection.setRequestProperty("Connection", "Keep-Alive");

					outputstream = new DataOutputStream(connection
							.getOutputStream());

					bytesAvailable = inputstream.available();
					bufferSize = Math.min(maxBufferSize, bytesAvailable);
					buffer = new byte[bufferSize];

					while ((bytesRead = inputstream.read(buffer, 0, bufferSize)) > 0)
						outputstream.write(buffer, 0, bytesRead);

					System.out.println("CODE :: "
							+ connection.getResponseCode());
					System.out.println("MESSAGE :: "
							+ connection.getResponseMessage());

					inputstream.close();
					outputstream.flush();
					outputstream.close();

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}