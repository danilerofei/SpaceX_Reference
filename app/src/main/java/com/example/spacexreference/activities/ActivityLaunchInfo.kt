package com.example.spacexreference.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.spacexreference.App
import com.example.spacexreference.R
import com.example.spacexreference.data.LaunchData
import com.example.spacexreference.models.MissionViewModel
import com.example.spacexreference.utils.LaunchUtil
import com.example.spacexreference.utils.LaunchViewModelFactory
import kotlinx.android.synthetic.main.activity_launch_info.*
import kotlinx.android.synthetic.main.crew_item.view.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ActivityLaunchInfo : AppCompatActivity() {

    @Inject
    lateinit var launchUtil: LaunchUtil

    private val missionViewModel: MissionViewModel by lazy {
        ViewModelProvider(
            this,
            LaunchViewModelFactory(launchUtil)
        ).get(MissionViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_info)

        (application as App).appComponent.inject(this)

        missionViewModel.currentMission.value = intent.getSerializableExtra("data") as? LaunchData

        missionViewModel.currentMission.observe(this) {
            supportActionBar?.title = it.name ?: getString(R.string.app_name)
            val coresCount = it.cores.getOrNull(0)?.flight ?: 0
            tv_launch_core_count.text = getString(R.string.count_repeat_count, coresCount)
            tv_mission_details.text = getString(R.string.mission_details, it.details ?: "")
            val missionStatus = if (it.missionStatus) {
                getString(R.string.success)
            } else {
                getString(R.string.failure)
            }
            tv_mission_status.text = getString(R.string.mission_status, missionStatus)

            if (it.largeLaunchIcon != null) {
                iv_launch_icon.load(it.largeLaunchIcon)
            }

            if (it.dateUtc != null) {
                val format = SimpleDateFormat("HH-mm dd-MM-yyyy", Locale.getDefault())
                val missionDateTime = format.format(it.dateUtc!!) ?: ""
                tv_mission_date_time.text = getString(R.string.mission_date_time, missionDateTime)
            }

            if (it.id != null) {
                missionViewModel.getCrew(it.id!!).observe(this@ActivityLaunchInfo) { crewList ->
                    if (crewList.isNotEmpty()) {
                        for (crew in crewList) {
                            val view = layoutInflater.inflate(R.layout.crew_item, null)
                            view.tv_crew_fio.text = crew.name ?: ""
                            view.tv_crew_agency.text = getString(R.string.agency, crew.agency ?: "")
                            view.tv_crew_status.text = getString(R.string.status, crew.status ?: "")
                            ll_crew.addView(view)
                        }
                    } else {
                        tv_crew.text = getString(R.string.no_crew)
                    }
                }
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}