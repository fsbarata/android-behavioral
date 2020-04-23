package com.fsbarata.behaviors

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.fsbarata.behaviors.framework.AbstractActivityBehavior
import com.google.android.material.navigation.NavigationView

class ToolbarActionDrawerActivityBehavior(
		private val activity: AppCompatActivity,
		@IdRes private val drawerLayoutId: Int,
		@IdRes private val toolbarId: Int,
		@IdRes private val navigationViewId: Int,
		@StringRes private val navigationDrawerOpenDescRes: Int = 0,
		@StringRes private val navigationDrawerCloseDescRes: Int = 0,
		private val gravity: Int = GravityCompat.START,
		private val navigationItemSelectedListener: (MenuItem) -> Boolean = { false }
) : AbstractActivityBehavior() {
	lateinit var drawer: DrawerLayout
	lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

	override fun onContentViewAvailable() {
		drawer = activity.findViewById(drawerLayoutId)

		val toolbar = activity.findViewById<Toolbar>(toolbarId)
		activity.setSupportActionBar(toolbar)

		actionBarDrawerToggle = ActionBarDrawerToggle(
				activity,
				drawer,
				toolbar,
				navigationDrawerOpenDescRes,
				navigationDrawerCloseDescRes)

		drawer.addDrawerListener(actionBarDrawerToggle)
		activity.findViewById<NavigationView>(navigationViewId).setNavigationItemSelectedListener {
			navigationItemSelectedListener(it)
		}
	}

	override fun onPostCreate(savedInstanceState: Bundle?) {
		// Sync the toggle state after onRestoreInstanceState has occurred.
		actionBarDrawerToggle.syncState()
	}

	override fun onBackPressed() =
			drawer.isDrawerOpen(gravity).also {
				if (it) drawer.closeDrawer(gravity)
			}
}