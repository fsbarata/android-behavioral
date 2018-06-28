package com.fsbarata.behaviors

import android.app.Activity
import android.support.annotation.MenuRes
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.fsbarata.behaviors.framework.AbstractLifecycleBehavior

open class MenuBehavior(
		private val activity: Activity?,
		@param:MenuRes private val menuRes: Int,
		vararg idClickListeners: Pair<Int, (MenuItem) -> Unit>
) : AbstractLifecycleBehavior() {
	private val itemClickListenerMap = idClickListeners.toMap()

	override fun onBehaviorAttached() {
		if (activity?.window != null) {
			activity.invalidateOptionsMenu()
		}
	}

	override fun onBehaviorDetached() {
		if (activity?.window != null) {
			activity.invalidateOptionsMenu()
		}
	}

	override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater): Boolean {
		menuInflater.inflate(menuRes, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		return itemClickListenerMap[item.itemId]?.also { it(item) } != null
	}
}
