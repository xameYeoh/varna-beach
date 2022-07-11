package com.getman.varnabeach.util

import android.content.Context
import android.widget.Toast
import javax.inject.Inject

class ErrorToastDisplayer @Inject constructor(
    private val context: Context
) : ErrorHandler {
    override fun displayError() = Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
}