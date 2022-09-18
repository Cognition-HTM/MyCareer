package com.sparklead.mycareer.ui.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.sparklead.mycareer.R
import com.sparklead.mycareer.models.Constants
import com.sparklead.mycareer.models.QuizQuestions
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.bottom_sheet_dialog.*
import java.util.*
import kotlin.collections.ArrayList

class QuizActivity : AppCompatActivity() , View.OnClickListener {

    private var mCurrentPosition : Int =1
    private var mQuestionsList :ArrayList<QuizQuestions>?= null
    private var mSelectedOptionPosition : Int =1
    private lateinit var mUserName :String
    private var  a : Array<Int> = Array(10) { 0 }
    private lateinit var question : QuizQuestions


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        mQuestionsList = Constants.getQuestions()

        setQuestion()

        mUserName = intent.getStringExtra(Constants.NAME).toString()

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        tv_option_five.setOnClickListener(this)
        tv_option_six.setOnClickListener(this)
        tv_option_seven.setOnClickListener(this)
        tv_option_eight.setOnClickListener(this)
        tv_option_nine.setOnClickListener(this)
        btn_quiz_submit.setOnClickListener(this)
    }


    private fun setQuestion(){


        question = mQuestionsList!![mCurrentPosition-1]

        defaultOptionsView()

        if(mCurrentPosition==mQuestionsList!!.size){
            btn_quiz_submit.text ="Finish"
        }
        else
        {
            btn_quiz_submit.text = "Next"
        }

        progressbar.progress =mCurrentPosition
        tv_progress.text = "$mCurrentPosition"+ "/" + progressbar.max

        tv_question.text = question.question
        tv_option_one.text = question.one.option
        tv_option_two.text =question.two.option
        tv_option_three.text = question.three.option
        tv_option_four.text = question.four.option
        tv_option_five.text = question.five.option
        tv_option_six.text = question.six.option
        tv_option_seven.text = question.seven.option
        tv_option_eight.text = question.eight.option
        tv_option_nine.text = question.nine.option

    }


    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.tv_option_one->{
                selectedOption(tv_option_one ,question.one.index )
            }
            R.id.tv_option_two->{
                selectedOption(tv_option_two,question.two.index)
            }
            R.id.tv_option_three->{
                selectedOption(tv_option_three,question.three.index)
            }
            R.id.tv_option_four->{
                selectedOption(tv_option_four,question.four.index)
            }
            R.id.tv_option_five->{
                selectedOption(tv_option_five,question.five.index)
            }
            R.id.tv_option_six->{
                selectedOption(tv_option_six,question.six.index)
            }
            R.id.tv_option_seven->{
                selectedOption(tv_option_seven,question.seven.index)
            }
            R.id.tv_option_eight->{
                selectedOption(tv_option_eight,question.eight.index)
            }
            R.id.tv_option_nine->{
                selectedOption(tv_option_nine,question.nine.index)
            }

            R.id.btn_quiz_submit->{

                mCurrentPosition++

                a[mSelectedOptionPosition]++

                println(mSelectedOptionPosition.toString()+"\n\n\n\n\n\n\n\n"+ a.contentToString())


                val maxIdx = a.indices.maxBy { a[it] } ?: -1
                println(maxIdx)


                when{
                        mCurrentPosition <=mQuestionsList!!.size->{
                            setQuestion()
                        }
                        else->
                        {




                            val intent = Intent(this , QuizResultActivity::class.java)
                            intent.putExtra(Constants.NAME,mUserName)
                            intent.putExtra(Constants.ABOUT,maxIdx.toString())
//                            intent.putExtra(Constants.Total_Question,mQuestionslist!!.size)
                            startActivity(intent)
                            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
                            finish()
                        }
                    }
//                }
//                else
//                {
//                    val question = mQuestionsList!![mCurrentPosition-1]
//
//
//                    if(mCurrentPosition == mQuestionsList!!.size){
//                        btn_quiz_submit.text ="Finish"
//                    }
//                    else
//                    {
//                        btn_quiz_submit.text ="Ok"
//                    }
                    mSelectedOptionPosition=0
//                }
            }
        }
    }

    private fun selectedOption(tv: TextView, selectedOptionNum:Int){

        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#FFFFFF"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)

        tv.elevation = 30f

        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_background
        )
    }

    private fun defaultOptionsView(){

        val options = ArrayList<TextView>()

        options.add(0,tv_option_one)
        options.add(1,tv_option_two)
        options.add(2,tv_option_three)
        options.add(3,tv_option_four)
        options.add(4,tv_option_five)
        options.add(5,tv_option_six)
        options.add(6,tv_option_seven)
        options.add(7,tv_option_eight)
        options.add(8,tv_option_nine)

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT

            option.elevation = 10f

            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_background
            )
        }

    }
}