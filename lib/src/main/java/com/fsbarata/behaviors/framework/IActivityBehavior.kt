package com.fsbarata.behaviors.framework


import android.content.Intent
import android.view.Menu
import android.view.MenuItem

interface IActivityBehavior : ILifecycleBehavior {
	fun onContentViewAvailable()

	fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean

	fun onSupportNavigateUp(): Boolean

	fun onBackPressed(): Boolean

	fun onNewIntent(intent: Intent)
}
