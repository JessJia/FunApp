package com.smu.funapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.smu.funapp.db.DBHelper;
import com.smu.funapp.thread.ExecuRequest;

public class MainActivity extends ActionBarActivity {
	
	Button btn, btnMessage;
	
	public static Handler handler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			Bundle bundle = msg.getData();
			String title = (String) bundle.get("title");
			String message = (String) bundle.get("message");
			System.out.println("title=="+title+"===Message="+message);
			System.out.println(Thread.currentThread());
			System.out.println(this);
//			Context context = new MainActivity().getApplicationContext();
//			System.out.println(context);
//			Toast.makeText(, title, Toast.LENGTH_LONG).show();
//			new MyNotification(new MainActivity().getApplicationContext(), title, message).sendNotification(MainActivity.class);
			
		};
	};

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn = (Button) findViewById(R.id.btn);
		btnMessage = (Button)findViewById(R.id.btn_message);
		
	}
	
	/**
	 * http 请求
	 * @param view
	 */          
	String url = "http://192.168.0.225:8080/TestServlet/smu/message";
	public void sendMessage(View view) {
		new Thread(new ExecuRequest("来自客户端的请求", url, MainActivity.this)).start();
	}
	
	/**
	 * 数据库操作
	 * @param btn
	 */
	@SuppressLint("NewApi")
	public void onTap(View btn) {
		Toast.makeText(MainActivity.this, "on click", Toast.LENGTH_LONG).show();
		DBHelper helper = new DBHelper(getApplicationContext());
		SQLiteDatabase sqlite = helper.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("insert into bd_customer (code, name, address, sex) values (123, '张三', '北京市朝阳区', 1)");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		Log.i("jss", "==========colum=============");
		Cursor c = sqlite.query("bd_customer", new String[] {"code"}, "", null, "", "", "");
		int i = 0;
		while(c.moveToNext()) {
			Log.i("jss", "count="+c.getColumnIndex("CODE"));
			i = c.getInt(0);
			Log.i("jss", "code type = "+c.getType(0));
			Log.i("jss", "code="+i);
		}
	}

	


}
