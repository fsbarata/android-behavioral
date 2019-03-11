package com.fsbarata.behaviors

import android.app.Activity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.annotation.MenuRes
import com.fsbarata.behaviors.framework.AbstractLifecycleBehavior

open class MenuBehavior(
		private val activity: () -> Activity?,
		@param:MenuRes private val menuRes: Int,
		vararg idClickListeners: Pair<Int, (MenuItem) -> Unit>
) : AbstractLifecycleBehavior() {
	private val itemClickListenerMap = idClickListeners.toMap()

	override fun onBehaviorAttached() {
		val activity = activity() ?: return
		if (activity.window != null) {
			activity.invalidateOptionsMenu()
		}
	}

	override fun onBehaviorDetached() {
		val activity = activity() ?: return
		if (activity.window != null) {
			activity.invalidateOptionsMenu()
		}
	}

	override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater): Boolean {
		menuInflater.inflate(menuRes, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem) =
			itemClickListenerMap[item.itemId]?.also { it(item) } != null
}
