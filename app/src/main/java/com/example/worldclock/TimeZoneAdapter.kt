package com.example.worldclock

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextClock
import android.widget.TextView
import java.util.*


// タイムゾーンのリスト表示用アダプター
class TimeZoneAdapter(private val context: Context,
                      private val timeZones: Array<String> = TimeZone.getAvailableIDs())
    : BaseAdapter() {

    private val inflater = LayoutInflater.from(context)

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?):
            View {
        // convertViewがある場合はそれを使い、ない場合は新しく作る
        val view = convertView ?: createView(parent)

        // 表示する値をセットする
        // positionから、表示すべきタイムゾーンのIDを得る
        val timeZoneID = getItem(position)
        // timezoneIDから、timezoneを得る
        val timeZone = TimeZone.getTimeZone(timeZoneID)

        // タグからViewHolderを取得
        val viewHolder = view.tag as ViewHolder

        // タイムゾーン名をセット
        viewHolder.name.text = "${timeZone.displayName}(${timeZone.id})"

        //タイムゾーンをセット
        viewHolder.clock.timeZone = timeZone.id

        return view
    }

    // リストの位置に対応したtimezone IDを返す
    override fun getItem(position: Int) = timeZones[position]

    override fun getItemId(position: Int) = position.toLong()

    // リストの表示する件数を返す
    override fun getCount() = timeZones.size

    // 各行のViewへの参照を持っておくことで、
    // 毎回findViewByIdをすることを避ける
    private class ViewHolder(view: View) {
        val name = view.findViewById<TextView>(R.id.timeZone)
        val clock = view.findViewById<TextClock>(R.id.clock)
    }

    private fun createView(parent: ViewGroup?) : View {
        val view = inflater.inflate(R.layout.list_time_zone_row, parent, false)
        view.tag = ViewHolder(view)
        return view
    }
}