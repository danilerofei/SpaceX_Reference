package com.example.spacexreference

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.spacexreference.data.LaunchData
import kotlinx.android.synthetic.main.launch_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class LaunchAdapter(
    private val context: Context,
    private val items: ArrayList<LaunchData>,
    private val onClickLaunchCallback: OnClickLaunchCallback
) : RecyclerView.Adapter<LaunchAdapter.ViewHolder>() {

    private val inflater by lazy {
        LayoutInflater.from(context)
    }

    private val format by lazy {
        SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(launchData: LaunchData) {
            view.tv_launch_name.text = launchData.name ?: ""
            view.tv_launch_core_count.text = context.getString(
                R.string.count_repeat_count,
                launchData.cores.firstOrNull()?.flight ?: 0L
            )

            val missionStatus = if (launchData.missionStatus) {
                context.getString(R.string.success)
            } else {
                context.getString(R.string.failure)
            }
            view.tv_mission_status.text = context.getString(R.string.mission_status, missionStatus)
            if (launchData.largeLaunchIcon != null) {
                view.iv_launch_icon.load(launchData.largeLaunchIcon)
            }

            val launchDate = if (launchData.dateUtc != null) {
                format.format(launchData.dateUtc!!) ?: ""
            } else {
                ""
            }
            view.tv_launch_date.text = context.getString(R.string.launch_date, launchDate)

            view.launch_item_content.setOnClickListener {
                onClickLaunchCallback.onClick(launchData, view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.launch_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    fun addLaunches(items: List<LaunchData>) {
        this.items.apply {
            clear()
            addAll(items)
        }
    }
}