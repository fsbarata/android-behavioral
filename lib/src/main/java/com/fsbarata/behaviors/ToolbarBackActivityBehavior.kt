package com.fsbarata.behaviors

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
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
