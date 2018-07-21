package com.fsbarata.behaviors.framework

import android.content.Intent
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

	fun onCreate(savedInstanceState: Bundle?) {
		behaviors.forEach { it.onCreate(savedInstanceState) }
	}

	fun onStart() {
		behaviors.forEach { it.onStart() }
	}

	fun onResume() {
		behaviors.forEach { it.onResume() }
	}

	fun onPause() {
		behaviors.forEach { it.onPause() }
	}

	fun onStop() {
		behaviors.forEach { it.onStop() }
	}

	fun onDestroy() {
		behaviors.forEach { it.onDestroy() }
	}

	fun onSaveInstanceState(outState: Bundle) {
		behaviors.forEach { it.onSaveInstanceState(outState) }
	}

	fun onRestoreInstanceState(savedInstanceState: Bundle) {
		behaviors.forEach { it.onRestoreInstanceState(savedInstanceState) }
	}

	fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater): Boolean {
		return behaviors.filter { it.onCreateOptionsMenu(menu, menuInflater) }.any()
	}

	fun onOptionsItemSelected(item: MenuItem): Boolean {
		return behaviors.any { it.onOptionsItemSelected(item) }
	}

	fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) =
			behaviors.any { it.onActivityResult(requestCode, resultCode, data) }
}
