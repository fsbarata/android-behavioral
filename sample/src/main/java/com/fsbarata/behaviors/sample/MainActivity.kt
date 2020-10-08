package com.fsbarata.behaviors.sample

import android.os.Bundle
import android.widget.Toast
import com.fsbarata.behaviors.MenuBehavior
import com.fsbarata.behaviors.ToolbarActionDrawerActivityBehavior
import com.fsbarata.behaviors.framework.BehaviorActivity

class MainActivity : BehaviorActivity(R.layout.activity_main) {
	init {
		addBehaviors(ToolbarActionDrawerActivityBehavior(
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
				}
		), MenuBehavior(
				this,
				R.menu.sample_activity_menu,
				R.id.add to { menuItem ->
					Toast.makeText(this, "Clicked $menuItem", Toast.LENGTH_SHORT).show()
				}
		))
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		supportFragmentManager.beginTransaction()
				.replace(R.id.fragment_container, MainFrag())
				.commit()
	}

	private fun noop() {}
}
