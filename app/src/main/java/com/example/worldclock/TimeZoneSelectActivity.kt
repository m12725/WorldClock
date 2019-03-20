package com.example.worldclock

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class TimeZoneSelectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // タイムゾーン選択画面のレイアウトを指定する
        setContentView(R.layout.activity_time_zone_select)

        // 最初に「キャンセルされた」結果を返すように設定推しておくと
        // 戻るボタンをタップした時などに対応できる
        setResult(Activity.RESULT_CANCELED)

        // タイムゾーンリストをレイアウトから探す
        val list = findViewById<ListView>(R.id.clockList)
        // タイムゾーン表示用のアダプターを作る
        val adapter = TimeZoneAdapter(this)
        // リストにアダプターをセットする
        list.adapter = adapter

        //リストタップと時の動作
        //ラムダ式で使用しない引数を_で省略できる
        list.setOnItemClickListener { _, _, position, _ ->
            //タップした場所のタイムゾーンをレストからえる
            val timeZone = adapter.getItem(position)

            //遷移元の画面に返す結果
            val result = Intent()
            //ユーザーがタップしたタイムゾーンを設定する
            result.putExtra("timezone", timeZone)
            //「OK」の結果を返す
            setResult(Activity.RESULT_OK, result)

            //この画面を閉じる
            finish()
        }
    }
}
