package com.fsbarata.behaviors.framework

import android.arch.lifecycle.LifecycleObserver
import android.os.Bundle

interface ILifecycleBehavior : LifecycleObserver {
	fun onBehaviorAttached()

	fun onBehaviorDetached()

	fun onSaveInstanceState(outState: Bundle)

	fun onRestoreInstanceState(savedInstanceState: Bundle)
}
