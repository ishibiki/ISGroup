package com.ishibiki.temple;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class TempleEditActivity extends Activity {
	/**
	 * お寺リスト画面で選択されたリストの行番号
	 */
	private int _selectedTempleNo = 0;
	/**
	 * お寺リスト画面で選択されたお寺名
	 */
	private String _selectedTempleName = "";

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activiy_temple_edit);

		Intent intent = getIntent();
		Bundle extras = intent.getExtras();

		if(extras != null){
			_selectedTempleNo = extras.getInt("selectedTempleNo");
			_selectedTempleName = extras.getString("selectedTempleName");
		}

		HashMap<String, String> content = DataAccess.findContentByPk(TempleEditActivity.this,_selectedTempleNo);
		EditText et_honson =(EditText) findViewById(R.id.et_honson);
		EditText et_syusi =(EditText) findViewById(R.id.et_syusi);
		EditText et_syozai =(EditText) findViewById(R.id. et_syozai);
		EditText  et_url =(EditText) findViewById(R.id. et_url);
		EditText etContent =(EditText) findViewById(R.id.etContent);

		et_honson.setText(content.get("honson"));
		et_syusi.setText(content.get("syusi"));
		et_syozai.setText(content.get("syozai"));
		et_url.setText(content.get("url"));
		etContent.setText(content.get("content"));

		TextView tvTemple = (TextView) findViewById(R.id.tvTemple);
		tvTemple.setText(_selectedTempleName);

		Button btnSave = (Button) findViewById(R.id.btnSave);
		btnSave.setOnClickListener(new ButtonClickListener());
	}

	/*
	 * このメソッドは自動生成のまま
	 * 改変は不要
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.temple_list, menu);
		return true;
	}

	/**
	 * ボタンがクリックされたときの処理が記述されたメンバクラス
	 * DBにデータを保存する
	 *
	 * @author ohs05032
	 */
	private class ButtonClickListener implements View.OnClickListener{
		@Override
		public void onClick(View v){
			HashMap<String, String> content = DataAccess.findContentByPk(TempleEditActivity.this,_selectedTempleNo);
			EditText et_honson =(EditText) findViewById(R.id.et_honson);
			EditText et_syusi =(EditText) findViewById(R.id.et_syusi);
			EditText et_syozai =(EditText) findViewById(R.id. et_syozai);
			EditText  et_url =(EditText) findViewById(R.id. et_url);
			EditText etContent =(EditText) findViewById(R.id.etContent);

			HashMap<String,String> inputdata  = new HashMap<String,String>();

			inputdata.put("honson", et_honson.getText().toString() );
			inputdata.put("syusi", et_syusi.getText().toString() );
			inputdata.put("syozai", et_syozai.getText().toString() );
			inputdata.put("url", et_url.getText().toString() );
			inputdata.put("content", etContent.getText().toString() );


			boolean exist = DataAccess.findRowByPK(TempleEditActivity.this,_selectedTempleNo);
			if(exist){
				DataAccess.update(TempleEditActivity.this,_selectedTempleNo, _selectedTempleName,inputdata);
			}else{
				DataAccess.insert(TempleEditActivity.this,_selectedTempleNo,_selectedTempleName,inputdata);
			}
			finish();
		}
	}
}
