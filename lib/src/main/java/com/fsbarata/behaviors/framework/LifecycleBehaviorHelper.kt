package com.fsbarata.behaviors.framework

import android.os.Bundle

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
}
