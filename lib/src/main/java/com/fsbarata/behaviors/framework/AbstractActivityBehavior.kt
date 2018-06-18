package com.fsbarata.behaviors.framework

import android.content.Intent
import android.view.Menu
import android.view.MenuItem

abstract class AbstractActivityBehavior : AbstractLifecycleBehavior(), IActivityBehavior {
	override fun onContentViewAvailable() {}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) = false

	override fun onCreateOptionsMenu(menu: Menu) = false

	override fun onOptionsItemSelected(item: MenuItem) = false

	override fun onSupportNavigateUp() = false

	override fun onBackPressed() = false

	override fun onNewIntent(intent: Intent) {}
}