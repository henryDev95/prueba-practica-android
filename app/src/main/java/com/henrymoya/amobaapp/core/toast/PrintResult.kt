package com.henrymoya.amobaapp.core.toast

import android.content.Context
import android.view.Gravity
import com.shashank.sony.fancytoastlib.FancyToast

object PrintResult {

    fun errorResult(context: Context, message : String) { // metodo para informar el error
        FancyToast.makeText(
            context,
            message,
            FancyToast.LENGTH_SHORT,
            FancyToast.WARNING,
            false
        ).show()
    }


    fun successResult(context: Context, message:String) {
        val toast = FancyToast.makeText(
            context,
            message,
            FancyToast.LENGTH_SHORT,
            FancyToast.SUCCESS,
            false
        )
        toast.setGravity(Gravity.BOTTOM, 0, 0)
        toast.show()
    }

    fun cancelResult(context: Context, message:String) {
        val toast = FancyToast.makeText(
            context,
            message,
            FancyToast.LENGTH_LONG,
            FancyToast.WARNING,
            false
        )
        toast.setGravity(Gravity.BOTTOM, 0, 0)
        toast.show()
    }
}