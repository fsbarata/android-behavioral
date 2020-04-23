package com.fsbarata.behaviors.framework

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BehaviorActivity : AppCompatActivity() {
	private val lifecycleBehaviorHelper = LifecycleBehaviorHelper()
	private val behaviors = mutableListOf<IActivityBehavior>()

	fun addBehavior(behavior: ILifecycleBehavior) {
		lifecycleBehaviorHelper.addBehavior(behavior)
		lifecycle.addObserver(behavior)
	}

	fun removeBehavior(behavior: ILifecycleBehavior) {
		lifecycleBehaviorHelper.removeBehavior(behavior)
		lifecycle.removeObserver(behavior)
	}

	fun addBehavior(behavior: IActivityBehavior) {
		behaviors.add(behavior)
		addBehavior(behavior as ILifecycleBehavior)
	}

	fun removeBehavior(behavior: IActivityBehavior) {
		behaviors.remove(behavior)
		removeBehavior(behavior as ILifecycleBehavior)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		lifecycleBehaviorHelper.onCreate(savedInstanceState)
	}

	override fun onStart() {
		super.onStart()
		lifecycleBehaviorHelper.onStart()
	}

	override fun onResume() {
		super.onResume()
		lifecycleBehaviorHelper.onResume()
	}

	override fun onPause() {
		lifecycleBehaviorHelper.onPause()
		super.onPause()
	}

	override fun onStop() {
		lifecycleBehaviorHelper.onStop()
		super.onStop()
	}

	override fun onDestroy() {
		lifecycleBehaviorHelper.onDestroy()
		super.onDestroy()
	}

	override fun onLowMemory() {
		super.onLowMemory()
		lifecycleBehaviorHelper.onLowMemory()
	}

	override fun onPostCreate(savedInstanceState: Bundle?) {
		super.onPostCreate(savedInstanceState)
		behaviors.forEach { it.onPostCreate(savedInstanceState) }
	}

	override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
		super.onRestoreInstanceState(savedInstanceState)
		savedInstanceState?.run {
			lifecycleBehaviorHelper.onRestoreInstanceState(this)
		}
	}

	override fun onNewIntent(intent: Intent) {
		super.onNewIntent(intent)
		behaviors.forEach { it.onNewIntent(intent) }
	}

	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)
		lifecycleBehaviorHelper.onSaveInstanceState(outState)
	}

	override fun setContentView(@LayoutRes layoutRes: Int) {
		super.setContentView(layoutRes)
		onContentViewAvailable()
	}

	override fun setContentView(view: View) {
		super.setContentView(view)
		onContentViewAvailable()
	}

	override fun setContentView(view: View, params: ViewGroup.LayoutParams) {
		super.setContentView(view, params)
		onContentViewAvailable()
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		if (!lifecycleBehaviorHelper.onActivityResult(requestCode, resultCode, data))
			super.onActivityResult(requestCode, resultCode, data)
	}

	override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
		if (!lifecycleBehaviorHelper.onRequestPermissionsResult(requestCode, permissions, grantResults))
			super.onRequestPermissionsResult(requestCode, permissions, grantResults)
	}

	override fun onCreateOptionsMenu(menu: Menu) =
			super.onCreateOptionsMenu(menu).let { superResult ->
				lifecycleBehaviorHelper.onCreateOptionsMenu(menu, menuInflater) || superResult
			}

	override fun onPrepareOptionsMenu(menu: Menu) =
			super.onPrepareOptionsMenu(menu).also {
				lifecycleBehaviorHelper.onPrepareOptionsMenu(menu)
			}

	override fun onOptionsItemSelected(item: MenuItem) =
			lifecycleBehaviorHelper.onOptionsItemSelected(item) || super.onOptionsItemSelected(item)

	override fun onBackPressed() {
		behaviors.find { it.onBackPressed() } ?: super.onBackPressed()
	}

	override fun onSupportNavigateUp(): Boolean {
		return behaviors.any { it.onSupportNavigateUp() } || super.onSupportNavigateUp()
	}

	private fun onContentViewAvailable() {
		behaviors.forEach { it.onContentViewAvailable() }
	}
}
