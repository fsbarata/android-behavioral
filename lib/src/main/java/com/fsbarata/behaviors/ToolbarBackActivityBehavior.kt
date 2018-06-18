package com.fsbarata.behaviors

import android.support.annotation.IdRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.fsbarata.behaviors.framework.AbstractActivityBehavior

class ToolbarBackActivityBehavior(
		private val activity: AppCompatActivity,
		@param:IdRes private val mToolbarId: Int
) : AbstractActivityBehavior() {
	override fun onContentViewAvailable() {
		activity.apply {
			setSupportActionBar(activity.findViewById(mToolbarId) as Toolbar)
			supportActionBar?.setDisplayShowHomeEnabled(true)
			supportActionBar?.setDisplayHomeAsUpEnabled(true)
		}
	}

	override fun onSupportNavigateUp(): Boolean {
		activity.onBackPressed()
		return true
	}
}
