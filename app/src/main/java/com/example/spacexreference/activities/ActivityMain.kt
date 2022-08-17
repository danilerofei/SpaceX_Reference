package com.example.spacexreference.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacexreference.App
import com.example.spacexreference.LaunchAdapter
import com.example.spacexreference.OnClickLaunchCallback
import com.example.spacexreference.R
import com.example.spacexreference.data.LaunchData
import com.example.spacexreference.models.LaunchViewModel
import com.example.spacexreference.utils.LaunchUtil
import com.example.spacexreference.utils.LaunchViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.launch_item.view.*
import javax.inject.Inject

class ActivityMain : AppCompatActivity() {

    @Inject
    lateinit var launchUtil: LaunchUtil

    private val launchViewModel: LaunchViewModel by lazy {
        ViewModelProvider(
            this,
            LaunchViewModelFactory(launchUtil)
        ).get(LaunchViewModel::class.java)
    }

    private val launchAdapter by lazy {
        LaunchAdapter(this, arrayListOf(), object : OnClickLaunchCallback {
            override fun onClick(launchData: LaunchData, view: View) {
                val intent = Intent(this@ActivityMain, ActivityLaunchInfo::class.java)
                intent.putExtra("data", launchData)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this@ActivityMain,
                    view.iv_launch_icon,
                    "launch_icon"
                )
                startActivity(intent, options.toBundle())
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).appComponent.inject(this)

        items.layoutManager = LinearLayoutManager(this)
        items.adapter = launchAdapter

        launchViewModel.getLaunches().observe(this) {
            pb_loading.visibility = View.INVISIBLE
            if (it.isNullOrEmpty()) {
                Log.e("Size", "Items is null")
            } else {
                launchAdapter.apply {
                    addLaunches(it)
                    notifyDataSetChanged()
                }
            }
        }
    }
}