package com.ipca.formulaworld.ui.bets

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.ipca.formulaworld.MainActivity
import com.ipca.formulaworld.R
import com.ipca.formulaworld.ui.home.HomeFragment
import java.math.RoundingMode

class PlaceBets : Fragment() {
    var team: String? = ""
    var odd: String? = ""
    var balance: String?= "1500"
    var titleBalance: String?= "Saldo"
    var titleAmount: String?= "Montante"
    var titleGains: String?= "Ganhos Potenciais"
    var valueBet: Float = 0.2F
    private var mContext: Context? = null
    lateinit var button: Button

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }


    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        team = arguments?.getString("name")
        odd = arguments?.getString("odd")

        val root = inflater.inflate(R.layout.fragment_bets_place, container, false)

        button = root.findViewById<Button>(R.id.buttonBet)

        // Inflate the layout for this fragment
        return root
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Fragment title
        activity?.setTitle(com.ipca.formulaworld.R.string.title_bets)

        // Show back button
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val textViewTeam = view.findViewById<TextView>(R.id.textViewTeam)
        textViewTeam.setText(team)

        val textViewOdd = view.findViewById<TextView>(R.id.textViewOdd)
        textViewOdd.setText(odd)

        val textViewBalance = view.findViewById<TextView>(R.id.textViewBalance)
        textViewBalance.setText(titleBalance)

        val textViewValueBalance = view.findViewById<TextView>(com.ipca.formulaworld.R.id.textViewValueBalance)
        textViewValueBalance.setText(balance + " €")

        val textViewAmount = view.findViewById<TextView>(com.ipca.formulaworld.R.id.textViewAmount)
        textViewAmount.setText(titleAmount)

        val editAmount = view.findViewById<EditText>(com.ipca.formulaworld.R.id.editAmount)
        editAmount.setText("0.00")

        val textViewGains = view.findViewById<TextView>(com.ipca.formulaworld.R.id.textViewGains)
        textViewGains.setText(titleGains)

        val textViewValueGains = view.findViewById<TextView>(com.ipca.formulaworld.R.id.textViewValueGains)

        val handler = Handler(Looper.getMainLooper())

        button.setOnClickListener{
            if (valueBet > balance.toString().toFloat()) {
                Toast.makeText(activity?.baseContext,"Saldo Insufeciente", Toast.LENGTH_LONG).show()
            }
            else
            {
                Toast.makeText(activity?.baseContext,"Aposta efectuada com sucesso", Toast.LENGTH_LONG).show()
                balance = (balance.toString().toFloat() - valueBet).toString()
                handler.postDelayed({
                    activity?.supportFragmentManager?.popBackStack()
//                    val transaction: FragmentTransaction = this.parentFragmentManager.beginTransaction()
//                    val fragmentTwo = HomeFragment()
//                    transaction.replace(R.id.fragment_placeholder, fragmentTwo)
//                    transaction.commit()
                }, 1000)
            }
        }

        editAmount.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                // TODO Auto-generated method stub
                if (s.toString() != "") {
                    valueBet = s.toString().toFloat()
                    val valueTotal = ((valueBet * odd!!.toFloat()).toString())
                    textViewValueGains.setText((valueTotal.toBigDecimal().setScale(2, RoundingMode.UP).toDouble().toString()) + " €")
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable) {

            }
        })


    }
}
