

import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.projet.R


class DialogDemoActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity)
        showAlertDialog()
    }

    private fun showAlertDialog() {
        val fm: FragmentManager = supportFragmentManager
        val alertDialog = MyAlertDialogFragment.newInstance("Some title")
        alertDialog.show(fm, "fragment_alert")
    }
    // ...
    // 3. This method is invoked in the activity when the listener is triggered
    // Access the data result passed to the activity here
    fun onFinishEditDialog(inputText: String?) {
        Toast.makeText(this, "Hi, $inputText", Toast.LENGTH_SHORT).show()
    }
}