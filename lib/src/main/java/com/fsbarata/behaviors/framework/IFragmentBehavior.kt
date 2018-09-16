package com.fsbarata.behaviors.framework

import android.content.Context
import android.view.View

interface IFragmentBehavior : ILifecycleBehavior {
	fun onViewCreated(view: View)

	fun onDestroyView()

	fun onAttach(context: Context)

	fun onDetach()
}
