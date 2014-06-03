package com.ishibiki.temple;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 *
 * SA41 Androidサンプル　Chapter04 Ex01 データベース
 *
 * 第一画面表示用アクティビティクラス
 * お寺リストを表示する *
 * @author ohs05032
*/

public class TempleListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_temple_list);

		ListView lvTemple = (ListView) findViewById(R.id.lv_temple);

		List<String> templeList = createPrefectureList();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,templeList);

		lvTemple.setAdapter(adapter);
		lvTemple.setOnItemClickListener(new  ListItemClickListener());




    /*    LinearLayout linearLayout2 = new LinearLayout(this);       //リニアレイアウトインスタンスの生成
        linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
        setContentView(linearLayout2);


        LinearLayout linearLayout = new LinearLayout(this);       //リニアレイアウトインスタンスの生成
        linearLayout.setOrientation(LinearLayout.VERTICAL);     //向き垂直方向
        setContentView(linearLayout);

	    ImageView iv1 = new ImageView(this);
	    iv1.setImageResource(R.drawable.petmark_g);
	    iv1.setLayoutParams(new LinearLayout.LayoutParams(
	            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(iv1);

	    ImageView iv2 = new ImageView(this);
	    iv2.setImageResource(R.drawable.petmark_t);
	    iv2.setLayoutParams(new LinearLayout.LayoutParams(
	            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(iv2);
*/


	/*	ImageView iv1 = new ImageView(this);
	 //   iv1.setImageResource(R.drawable.petmark_g);
		Resources res = getResources();

	    Drawable drawable = res.getDrawable(R.drawable.petmark_g);
        iv1.setImageDrawable(drawable);

       // layout.addView(iv1);
*/
    /*    CustomAdapter customAdapater = new CustomAdapter(this, 0, objects);

        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(customAdapater);
*/
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
	 * 都道府県を生成するメソッド
	 * @return 都道府県リストオブジェクト
	 */

/*    ImageView iv = new ImageView(this);
    iv.setImageResource(R.drawable.petmark_bs);
*/

	private  List<String> createPrefectureList(){
		List<String> templeList = new ArrayList<String>();

				templeList.add("読売ジャイアンツ");
				templeList.add("阪神タイガース");
				templeList.add("広島東洋カープ");
				templeList.add("中日ドラゴンズ");
				templeList.add("横浜DeNAベイスターズ");
				templeList.add("東京ヤクルトスワローズ");
				templeList.add("東北楽天ゴールデンイーグルス");
				templeList.add("埼玉西武ライオンズ");
				templeList.add("千葉ロッテマリーンズ");
				templeList.add("福岡ソフトバンクホークス");
				templeList.add("オリックス・バファローズ");
				templeList.add("北海道日本ハムファイターズ");

				return  templeList;
	}
	/**
	 * リストが選択されたときの処理が記述されたメンバクラス
	 * 第2画面へ処理を移管する
	 *
	 * @author ohs05032
	 */

	private class ListItemClickListener implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?>  parent, View view, int position,long id){
			ListView listView = (ListView) parent;
			String templeName = (String) listView.getItemAtPosition(position);

			Intent intent = new Intent(TempleListActivity.this,TempleEditActivity.class);
			intent.putExtra("selectedTempleNo", position);
			intent.putExtra("selectedTempleName", templeName);
			startActivity(intent);
		}
	}
}