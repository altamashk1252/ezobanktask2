package website.orgo.task2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var textView2000notes:TextView?=null
    var textView500notes:TextView?=null
    var textView200notes:TextView?=null
    var textView100notes:TextView?=null
    var totalamt:TextView?=null

    var et2000notes:EditText?=null
    var et500notes:EditText?=null
    var et200notes:EditText?=null
    var et100notes:EditText?=null

var depositButton:Button?=null
var withdrawalButton:Button?=null
var withdrawalAmt:EditText?=null
    var available2000notes:Int=0
    var available500notes:Int=0
    var available200notes:Int=0
    var available100notes:Int=0
    var availableTotal:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView2000notes = findViewById(R.id.avl_2000)
        textView500notes = findViewById(R.id.avl_500)
        textView200notes = findViewById(R.id.avl_200)
        textView100notes = findViewById(R.id.avl_100)
        totalamt = findViewById(R.id.total)

        et2000notes = findViewById(R.id.edt_2000)
        et500notes = findViewById(R.id.edt_500)
        et200notes = findViewById(R.id.edt_200)
        et100notes = findViewById(R.id.edt_100)

        depositButton = findViewById(R.id.btndeposite)
        withdrawalAmt = findViewById(R.id.withdrawedittext)
        withdrawalButton = findViewById(R.id.btnwithdraw)
        withdrawalButton!!.setOnClickListener {

            withdrawMoney()

        }


        depositButton!!.setOnClickListener {
            var notes2000=0
            var notes500=0
            var notes200=0
            var notes100=0
            if(et2000notes!!.text.toString().equals("")){
                notes2000=0
            }else{
                notes2000= et2000notes!!.text.toString().toInt()
            }

            if(et500notes!!.text.toString().equals("")){
                notes500=0
            }else{
                notes500= et500notes!!.text.toString().toInt()
            }

            if(et200notes!!.text.toString().equals("")){
notes200=0
            }else{
                notes200= et200notes!!.text.toString().toInt()
            }

            if(et100notes!!.text.toString().equals("")){
notes100=0
            }else{
                notes100= et100notes!!.text.toString().toInt()
            }
            if(notes2000>0||notes500>0||notes200>0||notes100>0){
//                    if(et2000notes!!.text.toString().equals("")){
                        available2000notes=available2000notes+notes2000

//                    }else{
//                        available2000notes=available2000notes+et2000notes!!.text.toString().toInt()
//
//                    }
//                if(et500notes!!.text.toString().equals("")){
                    available500notes=available500notes+notes500

//                }else{
//                    available500notes=available2000notes+et500notes!!.text.toString().toInt()
//
//                }
//                if(et200notes!!.text.toString().equals("")){
                    available200notes=available200notes+notes200

//                }else{
//
//                    available200notes=available2000notes+et200notes!!.text.toString().toInt()
//                }
//                if(et100notes!!.text!!.toString().equals("")){
                    available100notes=available100notes+notes100

//                }else{
//                    available100notes=available2000notes+et100notes!!.text.toString().toInt()
//
//                }
                    availableTotal= (available2000notes*2000)+(available500notes*500)+(available200notes*200)+(available100notes*100)
                    updateUI(available2000notes,available500notes,available200notes,available100notes,availableTotal)

            }else
            {
                Toast.makeText(applicationContext, "Please enter valid number of denominations", Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun withdrawMoney() {
        val withdrawalAmount = withdrawalAmt!!.text.toString().toInt() // Replace with the desired withdrawal amount.
        if (withdrawalAmount <= availableTotal) {
            val note2000ToWithdraw = Math.min(withdrawalAmount / 2000, available2000notes)
            val note500ToWithdraw =
                Math.min((withdrawalAmount - note2000ToWithdraw * 2000) / 500, available500notes)
            val note200ToWithdraw = Math.min(
                (withdrawalAmount - note2000ToWithdraw * 2000 - note500ToWithdraw * 500) / 200,
                available200notes
            )
            val note100ToWithdraw = Math.min(
                (withdrawalAmount - note2000ToWithdraw * 2000 - note500ToWithdraw * 500 - note200ToWithdraw * 200) / 100,
                available100notes
            )
            val totalWithdrawn =
                note2000ToWithdraw * 2000 + note500ToWithdraw * 500 + note200ToWithdraw * 200 + note100ToWithdraw * 100
            if (totalWithdrawn == withdrawalAmount) {
                availableTotal -= totalWithdrawn
                available2000notes -= note2000ToWithdraw
                available500notes -= note500ToWithdraw
                available200notes -= note200ToWithdraw
                available100notes -= note100ToWithdraw
                updateUI(available2000notes,available500notes,available200notes,available100notes,availableTotal)
                Toast.makeText(this, "Withdrawal successful.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Insufficient denominations in the ATM.", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(this, "Insufficient balance.", Toast.LENGTH_SHORT).show()
        }
    }


    private fun updateUI(
        available2000notes: Int,
        available500notes: Int,
        available200notes: Int,
        available100notes: Int,
        total:Int
    ) {
        textView2000notes!!.text=available2000notes.toString()
        textView500notes!!.text=available500notes.toString()
        textView200notes!!.text=available200notes.toString()
        textView100notes!!.text=available100notes.toString()
        totalamt!!.text = total.toString()
        et2000notes!!.text.clear()
        et500notes!!.text.clear()
        et200notes!!.text.clear()
        et100notes!!.text.clear()
        withdrawalAmt!!.text.clear()

    }
}