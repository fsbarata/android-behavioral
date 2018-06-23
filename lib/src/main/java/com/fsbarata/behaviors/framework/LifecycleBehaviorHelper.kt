package com.fsbarata.behaviors.framework

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

class LifecycleBehaviorHelper {
	private val behaviors = mutableListOf<ILifecycleBehavior>()

	fun addBehavior(behavior: ILifecycleBehavior) {
		behaviors.add(behavior)
		behavior.onBehaviorAttached()
	}

	fun removeBehavior(behavior: ILifecycleBehavior) {
		behaviors.remove(behavior)
		behavior.onBehaviorDetached()
	}

	fun onSaveInstanceState(outState: Bundle) {
		behaviors.forEach { it.onSaveInstanceState(outState) }
	}

	fun onRestoreInstanceState(savedInstanceState: Bundle) {
		behaviors.forEach { it.onRestoreInstanceState(savedInstanceState) }
	}

	fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater): Boolean {
		return behaviors.map { it.onCreateOptionsMenu(menu, menuInflater) }.any()
	}

	fun onOptionsItemSelected(item: MenuItem): Boolean {
		return behaviors.any { it.onOptionsItemSelected(item) }
	}
}
