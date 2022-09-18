package com.sparklead.mycareer.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sparklead.mycareer.R
import com.sparklead.mycareer.models.Questions
import com.sparklead.mycareer.ui.adapters.QuestionsListAdapter


class FAQsFragment : Fragment() {

    private lateinit var adapter :QuestionsListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var questionsArrayList: ArrayList<Questions>

    private lateinit var questions: ArrayList<String>
    private lateinit var answers : ArrayList<String>
    private lateinit var news : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        activity?.window!!.statusBarColor = requireActivity().getColor(R.color.fifth_color)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_faqs,container,false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataInitialize()
        val layoutManager = LinearLayoutManager(activity)
        recyclerView = view.findViewById(R.id.rv_faq_questions)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = QuestionsListAdapter(questionsArrayList)
        recyclerView.adapter = adapter
    }
//
//    private fun getQuestionList():ArrayList<String>{
//        val list = ArrayList<String>()
//
//        for(i in 1..15){
//            list.add("item $i")
//        }
//        return list
//    }

    private fun dataInitialize(){

        questionsArrayList = arrayListOf()

        questions = arrayListOf(
            "In app where my shortlisted career would be shown ?",
            "where i will find Details about different career options ?",
            "How to use your chatbot ? ",
            "To use the chat is it necessary to install Telegram ?",
            "How many trial counsellor sessions would we get?",
            "How to attend counselling sessions ?",
            "Do we get news updates regarding exams and careers ?",
            "As a counsellor how can i use the app ?",
            "Do we get information in multiple language ?",
            "Where we found Details about Exams ?",
            "Where can we read counsellor Blogs?"
        )
        answers = arrayListOf(
            "It would be shown on homepage under Favourite Paths Section. ",
            "Go to explore section and there you will find different career options .",
            "Just click on the bot icon and you will redirect to telegram app and start asking your queries",
            "Yeah, for using our bot feature telegram should be installed .",
            "You will get 1 trial session and after which if you want to attend more sessions you have to pay some minimal amount.",
            "Counselling sessions would be on google meet.",
            "Yes news feature is there on homepage  and you will get the news update there .",
            "You just have to register and login as a counsellor ,fill the required Details and you are ready to go .",
            "Yes currently we support hindi and english as language and in further updates more languages we are going to add.",
            "On home page exam section is there , just click on the exam images and you would be redirected to their official page.",
            "Go to counsellor section and select the counsellor whose blog you want to see .",
        )

        for(i in questions.indices){

            val question = Questions(questions[i],answers[i])
            questionsArrayList.add(question)
        }
    }

}