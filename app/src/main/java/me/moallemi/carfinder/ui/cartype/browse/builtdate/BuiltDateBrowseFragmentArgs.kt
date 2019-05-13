package me.moallemi.carfinder.ui.cartype.browse.builtdate

import android.os.Bundle

data class BuiltDateBrowseFragmentArgs(val manufacturer: String, val mainType: String) {
    fun toBundle(): Bundle {
        val result = Bundle()
        result.putString("manufacturer", manufacturer)
        result.putString("mainType", mainType)
        return result
    }

    companion object {
        @JvmStatic
        fun fromBundle(bundle: Bundle): BuiltDateBrowseFragmentArgs {
            bundle.classLoader = BuiltDateBrowseFragmentArgs::class.java.classLoader
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
            val mainType: String?
            if (bundle.containsKey("mainType")) {
                mainType = bundle.getString("mainType")
                if (mainType == null) {
                    throw IllegalArgumentException(
                        "Argument \"mainType\" is marked as non-null but was passed a null value."
                    )
                }
            } else {
                throw IllegalArgumentException(
                    "Required argument \"mainType\" is missing and does not have a defaultValue"
                )
            }
            return BuiltDateBrowseFragmentArgs(manufacturer, mainType)
        }
    }
}