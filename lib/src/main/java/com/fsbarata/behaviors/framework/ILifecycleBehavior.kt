package com.fsbarata.behaviors.framework

import android.arch.lifecycle.LifecycleObserver
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

interface ILifecycleBehavior : LifecycleObserver {
	fun onBehaviorAttached()

	fun onBehaviorDetached()

	fun onCreate(savedInstanceState: Bundle?)

	fun onStart()

	fun onResume()

	fun onPause()

	fun onStop()

	fun onDestroy()

	fun onSaveInstanceState(outState: Bundle)

	fun onRestoreInstanceState(savedInstanceState: Bundle)

	fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater): Boolean

	fun onOptionsItemSelected(item: MenuItem): Boolean

	fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean

	fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray): Boolean
}
