package com.mcoc.annoncestir

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mcoc.annoncestir.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)

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