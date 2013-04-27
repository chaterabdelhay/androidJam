package com.qlteam.watermanager;

import greendroid.app.GDActivity;
import greendroid.widget.ActionBarItem;
import greendroid.widget.ActionBarItem.Type;
import greendroid.widget.QuickAction;
import greendroid.widget.QuickActionBar;
import greendroid.widget.QuickActionWidget;
import greendroid.widget.QuickActionWidget.OnQuickActionClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainSreenActivity extends GDActivity {

	Button btnlogin;

	EditText txtIdentif;
	EditText txtPass;

	private final int INFO = 0;
	private QuickActionWidget quickActions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setActionBarContentView(R.layout.login_layout);
		initActionBar();
		initQuickActionBar();
		btnlogin = (Button) findViewById(R.id.btnLogin);
		txtIdentif = (EditText) findViewById(R.id.loginclientID);
		txtPass = (EditText) findViewById(R.id.loginPassword);

		btnlogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {

				Intent in = new Intent(getApplicationContext(),
						CleintespaceActivity.class);
				// sending pid to next activity
				String idclient = txtIdentif.getText().toString();
				String pass = txtIdentif.getText().toString();
				in.putExtra("identifient", idclient);
				in.putExtra("pass", pass);
			}
		});
	}

	private void initActionBar() {
		addActionBarItem(Type.Info, INFO);
	}

	private void initQuickActionBar() {
		quickActions = new QuickActionBar(this);

		quickActions.addQuickAction(new QuickAction(this,
				R.drawable.info_balloon, "this app has"
						+ " been developed by QLteam at androidJAM"));

		quickActions
				.setOnQuickActionClickListener(new OnQuickActionClickListener() {
					public void onQuickActionClicked(QuickActionWidget widget,
							int position) {

					}
				});
	}

	@Override
	public boolean onHandleActionBarItemClick(ActionBarItem item, int position) {
		switch (item.getItemId()) {

		case INFO:
			quickActions.show(item.getItemView());

			break;
		default:
			return super.onHandleActionBarItemClick(item, position);
		}

		return true;
	}

}
