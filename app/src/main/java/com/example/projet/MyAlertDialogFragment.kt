
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface

import android.os.Bundle
import androidx.fragment.app.DialogFragment


internal class MyAlertDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title: String? = getArguments()?.getString("title")
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(getActivity())
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage("Are you sure?")
        alertDialogBuilder.setPositiveButton("OK",
            DialogInterface.OnClickListener { dialog, which ->
                // on success
            })

        return alertDialogBuilder.create()
    }

    companion object {
        fun newInstance(title: String?): MyAlertDialogFragment {
            val frag = MyAlertDialogFragment()
            val args = Bundle()
            args.putString("title", title)
            frag.setArguments(args)
            return frag
        }
    }
}