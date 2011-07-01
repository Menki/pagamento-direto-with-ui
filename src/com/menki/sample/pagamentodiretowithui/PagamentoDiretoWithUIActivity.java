package com.menki.sample.pagamentodiretowithui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.menki.moip.PaymentInfo;
import com.menki.moip.paymentmethods.OnPaymentListener;
import com.menki.moip.paymentmethods.PagamentoDireto;
import com.menki.moip.utils.Config.RemoteServer;
import com.menki.moip.utils.MoIPResponse;

public class PagamentoDiretoWithUIActivity extends Activity implements OnPaymentListener {
	private EditText value;
	private Button pay;
	private PagamentoDireto pagDir;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		pagDir = new PagamentoDireto();
		pagDir.setOnPaymentListener(this);
		pagDir.setServerType(RemoteServer.TEST);
		pagDir.setToken("SEU TOKEN DO MOIP");
		pagDir.setKey("SUA KEY DO MOIP");
		
		value = (EditText) findViewById(R.id.value);

		pay = (Button) findViewById(R.id.pay);
		pay.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				pagDir.setValue(value.getText().toString());
				Intent intent = new Intent(PagamentoDiretoWithUIActivity.this, PaymentInfo.class);
				intent.putExtra("PagamentoDireto", pagDir);
				startActivity(intent);				
			}
		});
	}

	@Override
	public void onPaymentSuccess(MoIPResponse response) {
		Toast.makeText(this, "SUCESS!", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onPaymentFail(MoIPResponse response) {
		Toast.makeText(this, "FAIL!", Toast.LENGTH_LONG).show();
	}
}