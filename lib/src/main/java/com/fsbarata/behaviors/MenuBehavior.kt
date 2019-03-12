package com.fsbarata.behaviors

import android.app.Activity
import android.support.annotation.MenuRes
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.fsbarata.behaviors.framework.AbstractLifecycleBehavior

class MenuBehavior private constructor(
		private val activity: Activity?,
		private val fragment: Fragment?,
		@param:MenuRes private val menuRes: Int,
		vararg idClickListeners: Pair<Int, (MenuItem) -> Unit>
) : AbstractLifecycleBehavior() {
	private val itemClickListenerMap = idClickListeners.toMap()

	constructor(
			activity: Activity,
			@MenuRes menuRes: Int,
			vararg idClickListeners: Pair<Int, (MenuItem) -> Unit>
	): this(activity, null, menuRes, *idClickListeners)

	constructor(
			fragment: Fragment,
			@MenuRes menuRes: Int,
			vararg idClickListeners: Pair<Int, (MenuItem) -> Unit>
	): this(null, fragment, menuRes, *idClickListeners)

	override fun onBehaviorAttached() {
		val activity = activity ?: fragment?.activity ?: return
		if (activity.window != null) {
			activity.invalidateOptionsMenu()
		}
	}

	override fun onBehaviorDetached() {
		val activity = activity ?: fragment?.activity ?: return
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
