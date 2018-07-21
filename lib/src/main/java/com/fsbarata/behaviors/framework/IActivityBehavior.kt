package com.fsbarata.behaviors.framework


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

interface IActivityBehavior : ILifecycleBehavior {
	fun onContentViewAvailable()

	fun onPostCreate(savedInstanceState: Bundle?)

	fun onSupportNavigateUp(): Boolean

	fun onBackPressed(): Boolean

	fun onNewIntent(intent: Intent)
}
