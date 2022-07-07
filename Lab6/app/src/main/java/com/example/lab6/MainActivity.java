package com.example.lab6;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import static com.example.lab6.SettingsDialog.PROG;

public class MainActivity extends AppCompatActivity implements com.example.lab6.FragA.FragAListener, FragB.FragBListener, SettingsDialog.SettingsListener {
	private int mProgress;
	public static String FRAG_TAG = "resBTag";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FragB fragB = (FragB) getSupportFragmentManager().findFragmentByTag("FRAGB");

		if ((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)) {
			if (fragB != null) {
				getSupportFragmentManager().beginTransaction()
						.show(fragB)
						.commit();
			} else {
				getSupportFragmentManager().beginTransaction()
						.add(R.id.fragContainer, FragB.class, null, "FRAGB")
						.commit();
			}
			getSupportFragmentManager().executePendingTransactions();
		}
	}

	@Override
	public void OnClickEvent(String p1, String p2, int operandId) {
		FragB fragB;
		double result = 0;
		double p1d = Double.parseDouble(p1);
		double p2d = Double.parseDouble(p2);
		if (!p1.isEmpty() || !p2.isEmpty())
			switch (operandId) {
				case R.id.button3:
					result = p1d + p2d;
					break;
				case R.id.button4:
					result = p1d - p2d;
					break;
				case R.id.button5:
					result = p1d * p2d;
					break;
				case R.id.button6:
					result = p1d / p2d;
					break;
				default:
					return;
			}
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
			getSupportFragmentManager().beginTransaction()
					.setReorderingAllowed(true)
					.add(R.id.fragContainer, FragB.class, null, "FRAGB")
					.addToBackStack("BBB")
					.commit();
			getSupportFragmentManager().executePendingTransactions();
		}
		fragB = (FragB) getSupportFragmentManager().findFragmentByTag("FRAGB");
		fragB.onNewClick(result, mProgress);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			case R.id.exit:
				showExitDialog();
				return true;
			case R.id.settings:
				SettingsDialog settingsDialog = new SettingsDialog();
				Bundle bundle = new Bundle();
				bundle.putInt(PROG, mProgress);
				settingsDialog.setArguments(bundle);
				settingsDialog.show(getSupportFragmentManager(), "settingsDialog");
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private void showExitDialog() {
		FragmentManager fm = getSupportFragmentManager();
		ExitDialog exitDialog = new ExitDialog();
		exitDialog.show(fm, "exitDialog");
	}

	@Override
	public void onSeekBarChanged(int progress) {
		Log.i("tag", "test");
		mProgress = progress;
		FragB result = null;
		//if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
		//	result = (FragB) getSupportFragmentManager().findFragmentById(R.id.fragB);
		//else
			result = (FragB) getSupportFragmentManager().findFragmentByTag("FRAGB");
		if (result != null)
			result.setResultText(progress);
	}
}
