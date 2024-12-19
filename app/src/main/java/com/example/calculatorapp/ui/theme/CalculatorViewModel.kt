package com.example.calculatorapp.ui.theme

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

class CalculatorViewModel:ViewModel() {



    private val _equationText=MutableLiveData("")
    val equationText: LiveData<String> =_equationText

    private val _Result=MutableLiveData("0")
    val Result: LiveData<String> =_Result

    fun onbuttonclick(btn:String)
    {

        Log.i("Clicked Button",btn)

        _equationText.value?.let {
            if(btn=="AC")
            {
                _equationText.value=""
                _Result.value="0"
                return

            }

            if(btn=="C")
            {
                if (it.isNotEmpty())
                {
                    _equationText.value=it.substring(0,it.length-1)
                }

                return
            }

            if(btn=="=")
            {
                _equationText.value=_Result.value
                return
            }
            _equationText.value=it+btn

            try {

                _Result.value=calculateresult(_equationText.value.toString())

            }catch (_:Exception){}
        }
    }

    fun calculateresult(equation:String):String{

        val context:Context=Context.enter()

        context.optimizationLevel=-1
        val scriptable:Scriptable=context.initSafeStandardObjects()

        val Finalsresult=context.evaluateString(scriptable,equation,"Javascript",1,null).toString()

        if(Finalsresult.endsWith(".0")){
            return Finalsresult.replace(".0","")
        }
        return Finalsresult
    }


}