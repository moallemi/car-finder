package me.moallemi.carfinder.ui.cartype.browse.maintype

import android.os.Bundle

data class MainTypeBrowseFragmentArgs(val manufacturer: String) {
    fun toBundle(): Bundle {
        val result = Bundle()
        result.putString("manufacturer", this.manufacturer)
        return result
    }

    companion object {
        @JvmStatic
        fun fromBundle(bundle: Bundle): MainTypeBrowseFragmentArgs {
            bundle.classLoader = MainTypeBrowseFragmentArgs::class.java.classLoader
            val manufacturer: String?
            if (bundle.containsKey("manufacturer")) {
                manufacturer = bundle.getString("manufacturer")
                if (manufacturer == null) {
                    throw IllegalArgumentException(
                        "Argument \"manufacturer\" is marked as non-null but was passed a null value."
                    )
                }
            } else {
                throw IllegalArgumentException(
                    "Required argument \"manufacturer\" is missing and does not have a defaultValue"
                )
            }
            return MainTypeBrowseFragmentArgs(manufacturer)
        }
    }
}