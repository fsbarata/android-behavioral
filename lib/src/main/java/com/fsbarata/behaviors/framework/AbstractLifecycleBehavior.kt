package com.fsbarata.behaviors.framework

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

abstract class AbstractLifecycleBehavior : ILifecycleBehavior {

	override fun onBehaviorAttached() {}

	override fun onBehaviorDetached() {}

	override fun onCreate(savedInstanceState: Bundle?) {}

	override fun onStart() {}

	override fun onResume() {}

	override fun onPause() {}

	override fun onStop() {}

	override fun onDestroy() {}

	override fun onSaveInstanceState(outState: Bundle) {}

	override fun onRestoreInstanceState(savedInstanceState: Bundle) {}

	override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) = false

	override fun onOptionsItemSelected(item: MenuItem) = false
}