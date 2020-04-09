package com.udtt.applegamsung.ui.util

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import com.malibin.memo.ui.dialog.SimpleDialogOnClickListener
import com.udtt.applegamsung.R
import com.udtt.applegamsung.databinding.DialogSimpleBinding
import com.udtt.applegamsung.util.toPx

open class SimpleDialog(context: Context) : Dialog(context), SimpleDialogOnClickListener {

    var message = ""
    var okText = ""
    var cancelText = ""

    private var cancelClickListener: ((view: View) -> Unit)? = null
    private var okClickListener: ((view: View) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DialogSimpleBinding.inflate(layoutInflater).apply {
            clickListener = this@SimpleDialog
            message.text = this@SimpleDialog.message
            btnOk.text = this@SimpleDialog.okText
            btnCancel.text = this@SimpleDialog.cancelText
        }
        setContentView(binding.root)
        window!!.setLayout(WIDTH, WRAP_CONTENT)
        setTransparentWindowBackground()
    }

    override fun onCancelClick(view: View) {
        cancelClickListener?.invoke(view)
        dismiss()
    }

    override fun onOkClick(view: View) {
        okClickListener?.invoke(view)
        dismiss()
    }

    private fun setTransparentWindowBackground() {
        window?.setBackgroundDrawableResource(R.color.transparent)
    }

    fun setSimpleDialogOnClickListener(listener: SimpleDialogOnClickListener) {
        cancelClickListener = listener::onCancelClick
        okClickListener = listener::onOkClick
    }

    fun setCancelClickListener(
        cancelText: String?,
        listener: ((view: View) -> Unit)?
    ): SimpleDialog {
        this.cancelText = cancelText ?: ""
        this.cancelClickListener = listener
        return this
    }

    fun setOkClickListener(okText: String?, listener: ((view: View) -> Unit)?): SimpleDialog {
        this.okText = okText ?: ""
        this.okClickListener = listener
        return this
    }

    companion object {
        private val WIDTH = 330.toPx()
    }
}
