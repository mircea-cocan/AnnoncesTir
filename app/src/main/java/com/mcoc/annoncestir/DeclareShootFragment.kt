package com.mcoc.annoncestir

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels


class DeclareShootFragment:Fragment(R.layout.declare_shoot_fragment) {
    private val viewModel: ShootsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var shootView = view.findViewById<DeclareShootView>(R.id.declare_shoot_view)

        shootView.shootsModel = viewModel.shootsModel
    }
}