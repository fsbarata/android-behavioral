package com.fsbarata.behaviors

import android.app.Activity
import android.support.annotation.IdRes
import android.support.annotation.MenuRes
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.fsbarata.behaviors.framework.AbstractLifecycleBehavior

open class MenuBehavior(
		private val activity: Activity?,
		@param:MenuRes private val menuRes: Int,
		vararg private val itemsDetails: MenuItemDetails
) : AbstractLifecycleBehavior() {

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
		itemsDetails.forEach {
			menu.findItem(it.id)?.apply { it.title?.let { title = it } }
		}
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		return itemsDetails.find { it.id == item.itemId }
				?.run { onClickListener(item) } != null
	}

	data class MenuItemDetails(
			@IdRes val id: Int,
			val title: String? = null,
			val onClickListener: (MenuItem) -> Unit
	)
}
