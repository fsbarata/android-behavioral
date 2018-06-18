package com.fsbarata.behaviors.framework

interface IFragmentBehavior : ILifecycleBehavior {
	fun onViewCreated()

	fun onDestroyView()

	fun onAttach()

	fun onDetach()
}
