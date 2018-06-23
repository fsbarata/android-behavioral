package com.fsbarata.behaviors

import android.app.Activity
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.fsbarata.behaviors.framework.AbstractActivityBehavior

class ActionDrawerActivityBehavior(
		private val activity: Activity,
		@IdRes private val drawerLayoutId: Int,
		@IdRes private val toolbarId: Int,
		@IdRes private val navigationViewId: Int,
		@StringRes private val navigationDrawerOpenDescRes: Int = 0,
		@StringRes private val navigationDrawerCloseDescRes: Int = 0,
		private val navigationItemSelectedListener: (MenuItem) -> Boolean
) : AbstractActivityBehavior() {
	lateinit var drawer:DrawerLayout
	lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

	override fun onContentViewAvailable() {
		drawer = activity.findViewById(drawerLayoutId)

		actionBarDrawerToggle = ActionBarDrawerToggle(
				activity,
				drawer,
				activity.findViewById(toolbarId),
				navigationDrawerOpenDescRes,
				navigationDrawerCloseDescRes)

		drawer.addDrawerListener(actionBarDrawerToggle)
		activity.findViewById<NavigationView>(navigationViewId).setNavigationItemSelectedListener {
			navigationItemSelectedListener(it).also {
				drawer.closeDrawer(GravityCompat.START)
			}
		}
	}

	override fun onPostCreate(savedInstanceState: Bundle?) {
		// Sync the toggle state after onRestoreInstanceState has occurred.
		actionBarDrawerToggle.syncState()
	}

	override fun onBackPressed() =
			drawer.isDrawerOpen(GravityCompat.START).also {
				if (it) drawer.closeDrawer(GravityCompat.START)
			}
}