package com.fsbarata.behaviors.framework

import android.content.Intent
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup

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

	override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
		super.onRestoreInstanceState(savedInstanceState)
		if (savedInstanceState != null) {
			lifecycleBehaviorHelper.onRestoreInstanceState(savedInstanceState)
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
		behaviors.find { it.onActivityResult(requestCode, resultCode, data) }
				?: super.onActivityResult(requestCode, resultCode, data)
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		val superResult = super.onCreateOptionsMenu(menu)
		return lifecycleBehaviorHelper.onCreateOptionsMenu(menu, menuInflater) || superResult
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		return lifecycleBehaviorHelper.onOptionsItemSelected(item) || super.onOptionsItemSelected(item)
	}

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
