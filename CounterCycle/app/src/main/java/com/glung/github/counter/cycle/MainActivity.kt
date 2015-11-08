package com.glung.github.counter.cycle

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        runCycle(
                { element -> main(this, element) },
                makeActivityDriver(this)
        )
    }
}
