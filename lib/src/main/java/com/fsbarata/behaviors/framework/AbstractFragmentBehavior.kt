package com.fsbarata.behaviors.framework

import android.content.Context
import android.os.Bundle
import android.view.View

abstract class AbstractFragmentBehavior : AbstractLifecycleBehavior(), IFragmentBehavior {
	override fun onActivityCreated(savedInstanceState: Bundle?) {}

	override fun onViewCreated(view: View) {}

	override fun onDestroyView() {}

	override fun onAttach(context: Context) {}

	override fun onDetach() {}
}