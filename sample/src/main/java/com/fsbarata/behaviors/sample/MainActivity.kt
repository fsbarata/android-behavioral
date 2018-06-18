package com.fsbarata.behaviors.sample

import android.os.Bundle
import android.app.Activity
import com.fsbarata.behaviors.framework.BehaviorActivity

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BehaviorActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}

}
