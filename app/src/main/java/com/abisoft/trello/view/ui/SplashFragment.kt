package com.abisoft.trello.view.ui

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.abisoft.trello.R

class SplashFragment : Fragment() {
    val timer = object : CountDownTimer(4000, 1000) {

        override fun onTick(millisUntilFinished: Long) {

        }

        override fun onFinish() {
            findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        timer.start()
    }
}