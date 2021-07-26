package com.philimonnag.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.content.res.TypedArrayUtils.getText
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException


class MainActivity : AppCompatActivity() {
    var lastNumericValue:Boolean=false
    var desimalValue:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onDigit(view:View){
      inputTv.append((view as Button).text)
        lastNumericValue=true
        desimalValue=false
    }
    fun onClear(view: View){
        inputTv.text = ""
        desimalValue=false
        lastNumericValue=false
    }
    fun onOperator(view: View){
        if(lastNumericValue&& !isOperatorAdded(inputTv.text.toString()))
        inputTv.append((view as Button).text)
        desimalValue=false
        lastNumericValue=false
    }
    fun onEqual(view: View){
        if(lastNumericValue){
            var prefix=""
            var inputText=inputTv.text
            try {
                if (inputText.startsWith("-")){
                     prefix="-"
                     inputText=inputText.substring(1)
                 }
                if(inputText.contains("-")){
                    val value=inputTv.text.split("-")
                    var one=value[0]
                    val two=value[1]
                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }
                    val result=one.toDouble()-two.toDouble()
                    inputTv.text=result.toString()
                }else if(inputText.contains("+")){
                    val value=inputTv.text.split("+")
                    var one=value[0]
                    val two=value[1]
                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }
                    val result=one.toDouble()+two.toDouble()
                    inputTv.text=result.toString()
                }else if(inputText.contains("/")){
                    val value=inputTv.text.split("/")
                    var one=value[0]
                    val two=value[1]
                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }
                    val result=one.toDouble()/two.toDouble()
                    inputTv.text=result.toString()
                }else if(inputText.contains("*")){
                    val value=inputTv.text.split("*")
                    var one=value[0]
                    val two=value[1]
                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }
                    val result=one.toDouble()*two.toDouble()
                    inputTv.text=result.toString()
                }
            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }

    }
    fun onDecimal(view: View){
        if(!desimalValue){
            desimalValue =true
            inputTv.append((view as Button).text)
        }

    }
    private fun isOperatorAdded(value:String):Boolean{
        return if(value.startsWith("-")){
            false
        }else
        {
            value.contains("-")||value.contains("+")||
                    value.contains("/")||value.contains("*")
        }
    }
}