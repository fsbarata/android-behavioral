package com.fsbarata.behaviors.framework

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BehaviorFragment : Fragment {
	constructor(): super()
	constructor(@LayoutRes contentLayoutId: Int): super(contentLayoutId)

	private val lifecycleBehaviorHelper = LifecycleBehaviorHelper()
	private val fragmentBehaviors = mutableListOf<IFragmentBehavior>()

	val behaviors: List<ILifecycleBehavior> = lifecycleBehaviorHelper.behaviors

	fun addBehavior(behavior: ILifecycleBehavior) {
		if (behavior is IFragmentBehavior) fragmentBehaviors.add(behavior)
		lifecycleBehaviorHelper.addBehavior(behavior)
		lifecycle.addObserver(behavior)
	}

	fun addBehaviors(vararg behaviors: ILifecycleBehavior) {
		behaviors.forEach { addBehavior(it) }
	}

	fun removeBehavior(behavior: ILifecycleBehavior) {
		lifecycle.removeObserver(behavior)
		lifecycleBehaviorHelper.removeBehavior(behavior)
		if (behavior is IFragmentBehavior) fragmentBehaviors.remove(behavior)
	}

	fun removeBehaviors(vararg behaviors: ILifecycleBehavior) {
		behaviors.forEach { removeBehavior(it) }
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		fragmentBehaviors.forEach { it.onViewCreated(view) }
	}

	override fun onDestroyView() {
		fragmentBehaviors.forEach { it.onDestroyView() }
		super.onDestroyView()
	}

	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)
		lifecycleBehaviorHelper.onSaveInstanceState(outState)
	}

	override fun onViewStateRestored(savedInstanceState: Bundle?) {
		super.onViewStateRestored(savedInstanceState)
		if (savedInstanceState != null) {
			lifecycleBehaviorHelper.onRestoreInstanceState(savedInstanceState)
		}
	}

	override fun onAttach(context: Context) {
		super.onAttach(context)
		fragmentBehaviors.forEach { it.onAttach(context) }
	}

	override fun onDetach() {
		fragmentBehaviors.forEach { it.onDetach() }
		super.onDetach()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		lifecycleBehaviorHelper.onCreate(savedInstanceState)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		fragmentBehaviors.forEach { it.onActivityCreated(savedInstanceState) }
	}

	override fun onStart() {
		super.onStart()
		lifecycleBehaviorHelper.onStart()
	}

	override fun onResume() {
		super.onResume()
		lifecycleBehaviorHelper.onResume()
	}

	override fun onPause() {
		lifecycleBehaviorHelper.onPause()
		super.onPause()
	}

	override fun onStop() {
		lifecycleBehaviorHelper.onStop()
		super.onStop()
	}

	override fun onDestroy() {
		lifecycleBehaviorHelper.onDestroy()
		super.onDestroy()
	}

	override fun onLowMemory() {
		super.onLowMemory()
		lifecycleBehaviorHelper.onLowMemory()
	}

	override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
		super.onCreateOptionsMenu(menu, menuInflater)
		lifecycleBehaviorHelper.onCreateOptionsMenu(menu, menuInflater)
	}

	override fun onPrepareOptionsMenu(menu: Menu) {
		super.onPrepareOptionsMenu(menu)
		lifecycleBehaviorHelper.onPrepareOptionsMenu(menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		return lifecycleBehaviorHelper.onOptionsItemSelected(item) ||
				super.onOptionsItemSelected(item)
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		if (!lifecycleBehaviorHelper.onActivityResult(requestCode, resultCode, data))
			super.onActivityResult(requestCode, resultCode, data)
	}

	override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
		if (!lifecycleBehaviorHelper.onRequestPermissionsResult(requestCode, permissions, grantResults))
			super.onRequestPermissionsResult(requestCode, permissions, grantResults)
	}
}
