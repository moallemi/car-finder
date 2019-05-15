package me.moallemi.carfinder.ui.summary

import android.os.Bundle

data class SummaryFragmentArgs(val mode: SummaryFragment.Mode) {
    fun toBundle(): Bundle {
        val result = Bundle()
        result.putInt("mode", this.mode.ordinal)
        return result
    }

    companion object {
        @JvmStatic
        fun fromBundle(bundle: Bundle): SummaryFragmentArgs {
            bundle.classLoader = SummaryFragmentArgs::class.java.classLoader
            val mode: Int?
            if (bundle.containsKey("mode")) {
                mode = bundle.getInt("mode")
            } else {
                throw IllegalArgumentException(
                    "Required argument \"mode\" is missing and does not have a defaultValue"
                )
            }
            return SummaryFragmentArgs(SummaryFragment.Mode.values()[mode])
        }
    }
}