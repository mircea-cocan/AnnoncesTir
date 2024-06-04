package com.mcoc.annoncestir

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class CountShootsFragment:Fragment(R.layout.count_shoots_fragment) {
    private val viewModel: ShootsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var shootView = view.findViewById<CountShootsView>(R.id.count_shoots_view)

        shootView.shootsModel = viewModel.shootsModel
    }

}