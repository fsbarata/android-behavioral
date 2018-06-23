package com.fsbarata.behaviors.sample

import android.os.Bundle
import com.fsbarata.behaviors.ToolbarActionDrawerActivityBehavior
import com.fsbarata.behaviors.framework.BehaviorActivity

class MainActivity : BehaviorActivity() {
	init {
		addBehavior(ToolbarActionDrawerActivityBehavior(
				this,
				R.id.drawer_layout, R.id.toolbar, R.id.nav_view,
				R.string.navigation_drawer_open, R.string.navigation_drawer_close,
				navigationItemSelectedListener = {
					when (it.itemId) {
						R.id.nav_main -> noop()
						R.id.nav_news -> noop()
						R.id.nav_gallery -> noop()
					}
					true
				}))
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}

	private fun noop() {}
}
