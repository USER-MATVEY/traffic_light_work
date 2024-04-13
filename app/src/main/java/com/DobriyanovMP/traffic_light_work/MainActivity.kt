package com.DobriyanovMP.traffic_light_work

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.DobriyanovMP.traffic_light_work.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var state: ActivityState

    var light_position = 1
    var light_direction = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        state = savedInstanceState?.getParcelable("activity_state")?: ActivityState(
            currentLight = 1,
            direction = true
        )

        light_position = state.currentLight
        light_direction = state.direction
        ChangeColor()

        binding.changeButton.setOnClickListener { ChangeColor() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("activity_state", state)
    }

    private fun ChangeColor() {
        when (light_position) {
            0 -> LightRed()
            1 -> LightYellow()
            2 -> LightGreen()
        }
        state.currentLight = light_position
        state.direction = light_direction

        if (light_direction){ light_position += 1 }
        else { light_position -= 1 }

        if (light_position >= 2) { light_direction = false }
        if (light_position <= 0) { light_direction = true }
    }

    private fun LightRed() {
        binding.lightRed.setImageResource(R.drawable.red_oval)
        binding.lightYellow.setImageResource(R.drawable.grey_oval)
        binding.lightGreen.setImageResource(R.drawable.grey_oval)

    }

    private fun LightYellow() {
        binding.lightRed.setImageResource(R.drawable.grey_oval)
        binding.lightYellow.setImageResource(R.drawable.yellow_oval)
        binding.lightGreen.setImageResource(R.drawable.grey_oval)
    }

    private fun LightGreen() {
        binding.lightRed.setImageResource(R.drawable.grey_oval)
        binding.lightYellow.setImageResource(R.drawable.grey_oval)
        binding.lightGreen.setImageResource(R.drawable.green_oval)
    }
}