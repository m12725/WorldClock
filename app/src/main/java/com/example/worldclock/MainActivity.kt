package com.example.worldclock

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //画面のレイアウト指定
        setContentView(R.layout.activity_main)

        // Userのdefault timezoneを取得する
        val timeZone = TimeZone.getDefault()

        // Timezone名を表示するTextView
        val timeZoneView = findViewById<TextView>(R.id.timeZone)
        // Timezone名を表示
        timeZoneView.text = timeZone.displayName

        //　「追加する」ボタンをlayoutから探す
        val addButton = findViewById<Button>(R.id.add)
        //　「追加する」ボタンがタップされたら、timezone選択画面に遷移する
        addButton.setOnClickListener {
            val intent = Intent(this, TimeZoneSelectActivity::class.java)
            //遷移先画面から結果を受け取りたい場合
            startActivityForResult(intent, 1)
        }
    }
}
