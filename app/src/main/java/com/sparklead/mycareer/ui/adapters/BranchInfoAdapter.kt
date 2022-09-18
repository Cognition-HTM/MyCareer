package com.sparklead.mycareer.ui.adapters

import android.content.ContentValues.TAG
import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.sparklead.mycareer.R
import kotlinx.android.synthetic.main.item_list_details_info.view.*
import java.util.*

class BranchInfoAdapter(
    private val context: Context,
    private val des : String,
    private val skills:String
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    lateinit var tts: TextToSpeech

    var isVisible1 = false
    var isVisible2 = false
    var isVisible3 = false
    var isVisible4 = false

    var isVisible1_1 = false
    var isVisible2_1 = false
    var isVisible3_1 = false
    var isVisible4_1 = false

    var translate1 = false
    var translate2 = false
    var translate3 = false
    var translate4 = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_list_details_info,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val model = items[position]
        if(holder is MyViewHolder){

            //overview
            holder.itemView.tv_details_info1.visibility = if(isVisible1) View.VISIBLE else View.GONE
            holder.itemView.ll_line1.visibility = if(isVisible1) View.VISIBLE else View.GONE
            holder.itemView.iv_visible_arrow1.setOnClickListener {
                isVisible1 = !isVisible1
                notifyItemChanged(position)
            }
            if(isVisible1){
                holder.itemView.iv_visible_arrow1.setImageResource(R.drawable.up_arrow)
            }
            else{
                holder.itemView.iv_visible_arrow1.setImageResource(R.drawable.down_arrow)
            }
            holder.itemView.tv_details_info1.text = des.replace("\\n", "\n")

            holder.itemView.btn_speak1.visibility = if(!isVisible1_1) View.VISIBLE else View.GONE
            holder.itemView.btn_mute1.visibility = if(isVisible1_1) View.VISIBLE else View.GONE
            holder.itemView.btn_speak1.setOnClickListener {
                isVisible1_1 =! isVisible1_1
                notifyItemChanged(position)
                //implement speak option..
                tts = TextToSpeech(context,TextToSpeech.OnInitListener {
                    if( it ==TextToSpeech.SUCCESS){
                        tts.language = Locale.US
                        tts.setSpeechRate(1.0f)
                        tts.speak(des.toString() ,TextToSpeech.QUEUE_ADD,null)
                    }
                })
            }
            holder.itemView.btn_mute1.setOnClickListener {
                isVisible1_1 =! isVisible1_1
                notifyItemChanged(position)
//                if(tts.isSpeaking)
//                {
                    tts.stop()
                    tts.shutdown()
//                }
            }

//            val languages = context.getString(R.array.languages)
//            val arrayAdapters = ArrayAdapter(context,R.layout.dropdown_list,languages)

            holder.itemView.iv_translate1.setOnClickListener {
                translate1=!translate1

                if(translate1) {
                    val options = TranslatorOptions.Builder()
                        .setSourceLanguage(TranslateLanguage.ENGLISH)
                        .setTargetLanguage(TranslateLanguage.KANNADA)
                        .build()

                    val englishHindiTranslator = Translation.getClient(options)

                    englishHindiTranslator.downloadModelIfNeeded()
                        .addOnSuccessListener {
                            Log.e(TAG, "Download Successful")
                        }
                        .addOnFailureListener {
                            Log.e(TAG, "Download Error: " + it.printStackTrace().toString())
                        }


                        .addOnSuccessListener {
                            Log.e(TAG, "Download Successful")
                            //Translates the given input from the source language into the target language.
                            englishHindiTranslator.translate(des.toString())
                                .addOnSuccessListener {
                                    //Translation successful
                                    holder.itemView.tv_details_info1.text = it
                                }
                                .addOnFailureListener {
                                    //Error
                                    Log.e(TAG, "Error: " + it.localizedMessage)
                                }
                        }
                        .addOnFailureListener {
                            Log.e(TAG, "Download Error: " + it.localizedMessage)
                        }
                }
                else{
                    holder.itemView.tv_details_info1.text = des.replace("\\n", "\n")
                }
            }



            //path
            holder.itemView.tv_details_info2.visibility = if(isVisible2) View.VISIBLE else View.GONE
            holder.itemView.ll_line2.visibility = if(isVisible2) View.VISIBLE else View.GONE
            holder.itemView.iv_visible_arrow2.setOnClickListener {
                isVisible2 = !isVisible2
                notifyItemChanged(position)
            }
            if(isVisible2){
                holder.itemView.iv_visible_arrow2.setImageResource(R.drawable.up_arrow)
            }
            else{
                holder.itemView.iv_visible_arrow2.setImageResource(R.drawable.down_arrow)
            }
            holder.itemView.tv_details_info2.text = skills.replace("\\n", "\n")

            holder.itemView.btn_speak2.visibility = if(!isVisible2_1) View.VISIBLE else View.GONE
            holder.itemView.btn_mute2.visibility = if(isVisible2_1) View.VISIBLE else View.GONE
            holder.itemView.btn_speak2.setOnClickListener {
                isVisible2_1 =! isVisible2_1
                notifyItemChanged(position)
                //implement speak option..
                tts = TextToSpeech(context,TextToSpeech.OnInitListener {
                    if( it ==TextToSpeech.SUCCESS){
                        tts.language = Locale.US
                        tts.setSpeechRate(1.0f)
                        tts.speak(skills.toString() ,TextToSpeech.QUEUE_ADD,null)

                    }
                })
            }
            holder.itemView.btn_mute2.setOnClickListener {
                isVisible2_1 =! isVisible2_1
                notifyItemChanged(position)
//                if(tts.isSpeaking)
//                {
                tts.stop()
                tts.shutdown()
//                }
            }
            holder.itemView.iv_translate2.setOnClickListener {
                translate2=!translate2

                if(translate2) {
                    val options = TranslatorOptions.Builder()
                        .setSourceLanguage(TranslateLanguage.ENGLISH)
                        .setTargetLanguage(TranslateLanguage.KANNADA)
                        .build()

                    val englishHindiTranslator = Translation.getClient(options)

                    englishHindiTranslator.downloadModelIfNeeded()
                        .addOnSuccessListener {
                            Log.e(TAG, "Download Successful")
                        }
                        .addOnFailureListener {
                            Log.e(TAG, "Download Error: " + it.printStackTrace().toString())
                        }


                        .addOnSuccessListener {
                            Log.e(TAG, "Download Successful")
                            //Translates the given input from the source language into the target language.
                            englishHindiTranslator.translate(skills)
                                .addOnSuccessListener {
                                    //Translation successful
                                    holder.itemView.tv_details_info2.text = it
                                }
                                .addOnFailureListener {
                                    //Error
                                    Log.e(TAG, "Error: " + it.localizedMessage)
                                }
                        }
                        .addOnFailureListener {
                            Log.e(TAG, "Download Error: " + it.localizedMessage)
                        }
                }
                else{
                    holder.itemView.tv_details_info2.text = skills.replace("\\n", "\n")
                }
            }



            //responsibility
//
//            holder.itemView.tv_details_info3.visibility = if(isVisible3) View.VISIBLE else View.GONE
//            holder.itemView.ll_line3.visibility = if(isVisible3) View.VISIBLE else View.GONE
//            holder.itemView.iv_visible_arrow3.setOnClickListener {
//                isVisible3 = !isVisible3
//                notifyItemChanged(position)
//            }
//            if(isVisible3){
//                holder.itemView.iv_visible_arrow3.setImageResource(R.drawable.up_arrow)
//            }
//            else{
//                holder.itemView.iv_visible_arrow3.setImageResource(R.drawable.down_arrow)
//            }
//            holder.itemView.tv_details_info3.text = responsibilities.replace("\\n", "\n")
//
//            holder.itemView.btn_speak3.visibility = if(!isVisible3_1) View.VISIBLE else View.GONE
//            holder.itemView.btn_mute3.visibility = if(isVisible3_1) View.VISIBLE else View.GONE
//            holder.itemView.btn_speak3.setOnClickListener {
//                isVisible3_1 =! isVisible3_1
//                notifyItemChanged(position)
//                //implement speak option..
//                tts = TextToSpeech(context,TextToSpeech.OnInitListener {
//                    if( it ==TextToSpeech.SUCCESS){
//                        tts.language = Locale.US
//                        tts.setSpeechRate(1.0f)
//                        tts.speak(responsibilities.toString() ,TextToSpeech.QUEUE_ADD,null)
//
//                    }
//                })
//            }
//            holder.itemView.btn_mute3.setOnClickListener {
//                isVisible3_1 =! isVisible3_1
//                notifyItemChanged(position)
////                if(tts.isSpeaking)
////                {
//                tts.stop()
//                tts.shutdown()
////                }
//            }
//            holder.itemView.iv_translate3.setOnClickListener {
//                translate3=!translate3
//
//                if(translate3) {
//                    val options = TranslatorOptions.Builder()
//                        .setSourceLanguage(TranslateLanguage.ENGLISH)
//                        .setTargetLanguage(TranslateLanguage.KANNADA)
//                        .build()
//
//                    val englishHindiTranslator = Translation.getClient(options)
//
//                    englishHindiTranslator.downloadModelIfNeeded()
//                        .addOnSuccessListener {
//                            Log.e(TAG, "Download Successful")
//                        }
//                        .addOnFailureListener {
//                            Log.e(TAG, "Download Error: " + it.printStackTrace().toString())
//                        }
//
//
//                        .addOnSuccessListener {
//                            Log.e(TAG, "Download Successful")
//                            //Translates the given input from the source language into the target language.
//                            englishHindiTranslator.translate(responsibilities)
//                                .addOnSuccessListener {
//                                    //Translation successful
//                                    holder.itemView.tv_details_info3.text = it
//                                }
//                                .addOnFailureListener {
//                                    //Error
//                                    Log.e(TAG, "Error: " + it.localizedMessage)
//                                }
//                        }
//                        .addOnFailureListener {
//                            Log.e(TAG, "Download Error: " + it.localizedMessage)
//                        }
//                }
//                else{
//                    holder.itemView.tv_details_info3.text = responsibilities.replace("\\n","\n")
//                }
//            }
//
//
//
//
//            //skills
//
//            holder.itemView.tv_details_info4.visibility = if(isVisible4) View.VISIBLE else View.GONE
//            holder.itemView.ll_line4.visibility = if(isVisible4) View.VISIBLE else View.GONE
//            holder.itemView.iv_visible_arrow4.setOnClickListener {
//                isVisible4 = !isVisible4
//                notifyItemChanged(position)
//            }
//            if(isVisible4){
//                holder.itemView.iv_visible_arrow4.setImageResource(R.drawable.up_arrow)
//            }
//            else{
//                holder.itemView.iv_visible_arrow4.setImageResource(R.drawable.down_arrow)
//            }
//            holder.itemView.tv_details_info4.text = skills.replace("\\n", "\n")
//            holder.itemView.btn_speak4.visibility = if(!isVisible4_1) View.VISIBLE else View.GONE
//            holder.itemView.btn_mute4.visibility = if(isVisible4_1) View.VISIBLE else View.GONE
//            holder.itemView.btn_speak4.setOnClickListener {
//                isVisible4_1 =! isVisible4_1
//                notifyItemChanged(position)
//                //implement speak option..
//                tts = TextToSpeech(context,TextToSpeech.OnInitListener {
//                    if( it ==TextToSpeech.SUCCESS){
//                        tts.language = Locale.US
//                        tts.setSpeechRate(1.0f)
//                        tts.speak(skills.toString() ,TextToSpeech.QUEUE_ADD,null)
//
//                    }
//                })
//            }
//            holder.itemView.btn_mute4.setOnClickListener {
//                isVisible4_1 =! isVisible4_1
//                notifyItemChanged(position)
////                if(tts.isSpeaking)
////                {
//                tts.stop()
//                tts.shutdown()
////                }
//            }
//            holder.itemView.iv_translate4.setOnClickListener {
//                translate4=!translate4
//
//                if(translate4) {
//                    val options = TranslatorOptions.Builder()
//                        .setSourceLanguage(TranslateLanguage.ENGLISH)
//                        .setTargetLanguage(TranslateLanguage.KANNADA)
//                        .build()
//
//                    val englishHindiTranslator = Translation.getClient(options)
//
//                    englishHindiTranslator.downloadModelIfNeeded()
//                        .addOnSuccessListener {
//                            Log.e(TAG, "Download Successful")
//                        }
//                        .addOnFailureListener {
//                            Log.e(TAG, "Download Error: " + it.printStackTrace().toString())
//                        }
//
//
//                        .addOnSuccessListener {
//                            Log.e(TAG, "Download Successful")
//                            //Translates the given input from the source language into the target language.
//                            englishHindiTranslator.translate(skills)
//                                .addOnSuccessListener {
//                                    //Translation successful
//                                    holder.itemView.tv_details_info4.text = it
//                                }
//                                .addOnFailureListener {
//                                    //Error
//                                    Log.e(TAG, "Error: " + it.localizedMessage)
//                                }
//                        }
//                        .addOnFailureListener {
//                            Log.e(TAG, "Download Error: " + it.localizedMessage)
//                        }
//                }
//                else{
//                    holder.itemView.tv_details_info4.text = overview.replace("\\n", "\n")
//                }
//            }
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

    private class MyViewHolder(view: View): RecyclerView.ViewHolder(view)

}