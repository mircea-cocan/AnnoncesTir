package com.mcoc.annoncestir

import android.os.Bundle
import android.widget.RelativeLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import com.mcoc.annoncestir.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : MainActivityBinding
    private val viewModel: ShootsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        replaceFragment(DeclareShootFragment())

        binding.newShootsSerieBtn.setOnClickListener() {
            viewModel.shootsModel.Ini()
            findViewById<CountShootsView>(R.id.count_shoots_view)?.invalidate()
        }
        binding.fragmentDeclareBtn.setOnClickListener() {
            replaceFragment(DeclareShootFragment())
        }

        binding.fragmentCountBtn.setOnClickListener() {
            replaceFragment((CountShootsFragment()))
        }
    }

    private fun replaceFragment(fragment:Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()


    }
}