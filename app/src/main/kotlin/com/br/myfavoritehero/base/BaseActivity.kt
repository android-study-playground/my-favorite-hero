package com.br.myfavoritehero.base

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.br.myfavoritehero.R
import com.br.myfavoritehero.data.models.ErrorResponse

abstract class BaseActivity: AppCompatActivity(){

    fun showError(errors: ErrorResponse?) {

        this.let { ctx ->
            errors?.messageInt?.map { error ->
                errors?.message.add(ctx.getString(error))
            }
        }
        val ers = errors.toString()
        val alertDialog: AlertDialog? = this.let { fragment ->
            val builder = AlertDialog.Builder(fragment)
            builder.setTitle(R.string.error_dialog_title)
            builder.setMessage(ers)
            builder.apply {
                setPositiveButton(
                    R.string.ok
                ) { dialog, _ ->
                    dialog.dismiss()
                }
            }

            builder.create()
        }
        alertDialog?.show()

    }


}