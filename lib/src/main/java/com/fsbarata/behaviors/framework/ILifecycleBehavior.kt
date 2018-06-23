package com.fsbarata.behaviors.framework

import android.arch.lifecycle.LifecycleObserver
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

interface ILifecycleBehavior : LifecycleObserver {
	fun onBehaviorAttached()

	fun onBehaviorDetached()

	fun onSaveInstanceState(outState: Bundle)

	fun onRestoreInstanceState(savedInstanceState: Bundle)

	fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater): Boolean

	fun onOptionsItemSelected(item: MenuItem): Boolean
}