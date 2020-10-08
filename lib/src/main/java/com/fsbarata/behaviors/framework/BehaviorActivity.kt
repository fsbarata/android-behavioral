package com.fsbarata.behaviors.framework

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BehaviorActivity : AppCompatActivity {
	constructor() : super()
	constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

	private val lifecycleBehaviorHelper = LifecycleBehaviorHelper()
	private val activityBehaviors = mutableListOf<IActivityBehavior>()

	val behaviors: List<ILifecycleBehavior> = lifecycleBehaviorHelper.behaviors

	fun addBehavior(behavior: ILifecycleBehavior) {
		if (behavior is IActivityBehavior) activityBehaviors.add(behavior)
		lifecycleBehaviorHelper.addBehavior(behavior)
		lifecycle.addObserver(behavior)
	}

	fun addBehaviors(vararg behaviors: ILifecycleBehavior) {
		behaviors.forEach { addBehavior(it) }
	}

	fun removeBehavior(behavior: ILifecycleBehavior) {
		lifecycle.removeObserver(behavior)
		lifecycleBehaviorHelper.removeBehavior(behavior)
		if (behavior is IActivityBehavior) activityBehaviors.remove(behavior)
	}

	fun removeBehaviors(vararg behaviors: ILifecycleBehavior) {
		behaviors.forEach { removeBehavior(it) }
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
		activityBehaviors.forEach { it.onPostCreate(savedInstanceState) }
	}

	override fun onRestoreInstanceState(savedInstanceState: Bundle) {
		super.onRestoreInstanceState(savedInstanceState)
		lifecycleBehaviorHelper.onRestoreInstanceState(savedInstanceState)
	}

	override fun onNewIntent(intent: Intent) {
		super.onNewIntent(intent)
		activityBehaviors.forEach { it.onNewIntent(intent) }
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
		activityBehaviors.find { it.onBackPressed() } ?: super.onBackPressed()
	}

	override fun onSupportNavigateUp(): Boolean {
		return activityBehaviors.any { it.onSupportNavigateUp() } || super.onSupportNavigateUp()
	}

	private fun onContentViewAvailable() {
		activityBehaviors.forEach { it.onContentViewAvailable() }
	}
}
