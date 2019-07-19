package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.routelist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_fragment_select_rote_type.*
import maystruks08.gmail.com.domain.entity.RouteType
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.toast

class SelectRouteTypeDialog : DialogFragment() {

    private var listener: SelectRouteTypeListener? = null

    private var hikeId: Long? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_fragment_select_rote_type, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hikeId = arguments?.getLong(SELECT_TYPE_DIALOG_HIKE_ID)
        initView()
    }

    private fun initView() {
        btnCreateRote.setOnClickListener {
            when (rgSelectRouteType.checkedRadioButtonId) {
                R.id.rbMainType -> {
                    if (etRouteName.text.isEmpty()) {
                        context?.toast("Введите название маршрута!")
                        return@setOnClickListener
                    }
                    listener?.onTypeSelected(hikeId!!, etRouteName.text.toString(), RouteType.MAIN)
                    this.dismiss()
                }
                R.id.rbSpare -> {
                    if (etRouteName.text.isEmpty()) {
                        context?.toast("Введите название маршрута!")
                        return@setOnClickListener
                    }
                    listener?.onTypeSelected(hikeId!!, etRouteName.text.toString(), RouteType.SPARE)
                    this.dismiss()
                }
                else -> {
                    context?.toast("Укажите тип маршрута!")
                }
            }
        }
        btnCancel.setOnClickListener { this.dismiss() }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (parentFragment is SelectRouteTypeListener) {
            listener = parentFragment as SelectRouteTypeListener
        }
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onDestroy() {
        super.onDestroy()
        listener = null
    }

    companion object {

        private const val SELECT_TYPE_DIALOG_HIKE_ID = "selectTypeDialogHikeId"

        fun getInstance(hikeId: Long): SelectRouteTypeDialog =
            SelectRouteTypeDialog().apply {
                arguments = Bundle().apply {
                    putLong(SELECT_TYPE_DIALOG_HIKE_ID, hikeId)
                }
            }
    }

}