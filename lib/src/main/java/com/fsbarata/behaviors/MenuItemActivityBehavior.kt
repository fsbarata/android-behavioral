package com.fsbarata.behaviors

import android.support.annotation.IdRes
import android.support.annotation.MenuRes
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.fsbarata.behaviors.framework.AbstractActivityBehavior

open class MenuItemActivityBehavior(
		private val activity: AppCompatActivity,
		@param:MenuRes private val menuRes: Int,
		@param:IdRes private val itemId: Int,
		private val title: String? = null,
		private val onClickListener: () -> Unit
) : AbstractActivityBehavior() {

	override fun onBehaviorAttached() {
		if (activity.window != null) {
			activity.invalidateOptionsMenu()
		}
	}

	override fun onBehaviorDetached() {
		if (activity.window != null) {
			activity.invalidateOptionsMenu()
		}
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		activity.menuInflater.inflate(menuRes, menu)
		title?.let { menu.findItem(itemId)?.title = it }
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		if (item.itemId == itemId) {
			onClickListener()
			return true
		}

		return false
	}
}
