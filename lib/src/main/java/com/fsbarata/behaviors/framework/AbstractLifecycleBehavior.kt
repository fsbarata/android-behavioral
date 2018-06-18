package com.fsbarata.behaviors.framework

import android.os.Bundle

abstract class AbstractLifecycleBehavior : ILifecycleBehavior {
	override fun onBehaviorAttached() {}

	override fun onBehaviorDetached() {}

	override fun onSaveInstanceState(outState: Bundle) {}

	override fun onRestoreInstanceState(savedInstanceState: Bundle) {}
}