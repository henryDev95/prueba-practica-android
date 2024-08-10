package com.henrymoya.amobaapp.core.sesion

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

class SessionManagement(context: Context) {

    var prefer: SharedPreferences
    var editor: SharedPreferences.Editor
    var context: Context

    init {
        this.context = context
        this.prefer = context.getSharedPreferences(Constantes.NAME_PROJECT, 0)
        this.editor = prefer.edit();
        this.editor.commit();
    }

    fun setToken(token: String) {
        editor = prefer.edit();
        editor.putString(Constantes.NAME_TOKEN, token);
        editor.commit();
    }

    fun getToken(): String {
        return prefer.getString(Constantes.NAME_TOKEN, "")!!
    }

    fun logoutUser() {
        editor.clear();
        editor.commit();
    }

}