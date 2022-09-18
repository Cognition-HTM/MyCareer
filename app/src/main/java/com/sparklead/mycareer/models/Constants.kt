package com.sparklead.mycareer.models

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.webkit.MimeTypeMap

object Constants {
    const val EXTRA_TITLE_EDIT :String = "title"
    const val EXTRA_COUNSELOR_ID : String = "id"
    const val EXTRA_TITLE : String = "title"
    const val EXTRA_IMAGE : String = "image"
    const val EXTRA_ABOUT : String = "about"
    const val EXTRA_COLLEGE:String ="college"
    const val EXTRA_PRE:String ="pre"
    const val EXTRA_CAREER:String ="career"
    const val EXTRA_LINK : String = "link"
    const val EXTRA_PATH : String = "path"
    const val EXTRA_ARRAY : String = "array"

    const val CONTENT : String = "content"
    const val HEADING : String = "heading"
    const val URL : String = "url"
    const val EXAM : String = "exam"
    const val ALL : String = "all"

    const val EXPLORE :String = "explore"
    const val USERS :String = "users"
    const val BLOGS :String = "blogs"
    const val ID :String = "id"
    const val ITEM : String = "item"
    const val SESSIONS : String = "sessions"
    const val COUNSELOR_ID : String ="id"
    const val FAVOURITE : String = "favourite"

    const val EXTRA_USER_DETAILS : String = "extra_user_details"

    const val PICK_IMAGE_REQUEST_CODE = 1
    const val READ_STORAGE_PERMISSION_CODE = 2
    const val NAME: String = "name"
    const val MALE : String = "Male"
    const val FEMALE : String = "Female"
    const val MOBILE : String = "phone"
    const val GENDER : String = "gender"
    const val CURRENT_CLASS : String = "currentClass"
    const val STREAM : String = "stream"
    const val INTERESTS :String = "interests"
    const val IMAGE :String ="image"
    const val ABOUT :String = "about"
    const val PROFILE_COMPLETED :String ="profileCompleted"
    const val USER_PROFILE_IMAGE :String = "user_profile_image"
    const val USER_LOGIN : String = "login"
    const val USER_POPUP : String = "popUp"
    const val USER_RATING :String = "rating"
    const val RESULT : String = "result"

    const val BRANCH : String = "branch"
    const val ACCOUNTING : String = "Accounting"
    const val EXTRA_BRANCH_TITLE : String = "extra_branch_title"
    const val EXTRA_BRANCH_NAME : String = "extra_branch_name"

    //counselor

    const val PICK_IMAGE_REQUEST_CODE_COUNSELOR = 1
    const val READ_STORAGE_PERMISSION_CODE_COUNSELOR = 2
    const val NAME_COUNSELOR: String = "name"
    const val MALE_COUNSELOR : String = "Male"
    const val FEMALE_COUNSELOR : String = "Female"
    const val MOBILE_COUNSELOR : String = "phone"
    const val GENDER_COUNSELOR : String = "gender"
    const val LINKEDIN_COUNSELOR : String = "linkedin"
    const val RESUME_COUNSELOR : String = "resume"
    const val EXPERT_COUNSELOR :String = "fieldExpert"
    const val IMAGE_COUNSELOR :String ="image"
    const val PROFILE_COMPLETED_COUNSELOR :String ="profileCompleted"
    const val USER_PROFILE_IMAGE_COUNSELOR :String = "user_profile_image"

    fun showImageChooser(activity : Activity)
    {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        activity.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }

    fun getFileExtension(activity: Activity, uri: Uri?):String?
    {

        return MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(activity.contentResolver.getType(uri!!))

    }




    fun getQuestions():ArrayList<QuizQuestions>{

        val questionList = ArrayList<QuizQuestions>()
        val que1 = QuizQuestions(1,"What was your favorite subject in school? ",
            QuizValues("Music", 1),
            QuizValues("Art",2),
            QuizValues("Psychology", 3),
            QuizValues("Reading",4),
            QuizValues("Zoology",5),
            QuizValues("Speech and Debate",6),
            QuizValues("Sports and dance",7),
            QuizValues("Math",8),
            QuizValues("I didn't like School",9)
        )

        questionList.add(que1)

        val que2 = QuizQuestions(2,"During your free time, you like to:",
            QuizValues("Spend time alone", 9),
            QuizValues("Read",4),
            QuizValues("Go to parties",3),
            QuizValues("Participate in sports", 7),
            QuizValues("Listen to music", 1),
            QuizValues("Camp, hike or garden",5),
            QuizValues("Draw and paint",2),
            QuizValues("Solve Math equations",8),
            QuizValues("Read self help books",6)

        )

        questionList.add(que2)

        val que3 = QuizQuestions(3,"Which type of television programs do you usually watch?",
            QuizValues("Sports broadcasts",7),
            QuizValues("Documentaries",4),
            QuizValues("Musicals",1),
            QuizValues("Science programs",8),
            QuizValues("Nature programs",5),
            QuizValues("Design and style programs",2),
            QuizValues("Adaptations of classic books",6),
            QuizValues("Talk shows",3),
            QuizValues("Social commentary documentaries",9)

        )

        questionList.add(que3)

        val que4 = QuizQuestions(4,"It's your day off and it happens to be a beautiful summer day. You are most likely to:This question is required. ",
            QuizValues("Go to an art museum",2),
            QuizValues("Review your household",4),
            QuizValues("Enjoy a quiet day all to yourself",9),
            QuizValues("Hang out with your friends at all the mall",3),
            QuizValues("Attend a local concert",1),
            QuizValues("Invite your friends out for a game of soccer",7),
            QuizValues("Head to the nearest trail for a day hike",5),
            QuizValues("Curl up with a good book",8),
            QuizValues("Journal and mediate",6)

        )

        questionList.add(que4)

        val que5 = QuizQuestions(5,"When you are trying to come up with ideas for a new project, you are most likely to find inspiration by:This question is required. ",
            QuizValues("Reading article that are related to your topic",8),
            QuizValues("Reflecting on the project quietly by yourself",9),
            QuizValues("Creating a chart listing different alternatives and assigning each a score based on various factors",6),
            QuizValues("Working in your garden",5),
            QuizValues("Going for a jog through the neighborhood",7),
            QuizValues("Discussing your options with other people",3),
            QuizValues("Making a mind map exploring your different options",2),
            QuizValues("Listening to your favorite songs",1),
            QuizValues("Rereading classics and finding inspiration in history",4)
        )

        questionList.add(que5)

        val que6 = QuizQuestions(6,"At a party, you're most likely to:This question is required. ",
            QuizValues("Challenge someone to a game of darts",7),
            QuizValues("Chat with as many people as possible",9),
            QuizValues("Spending the evening calculating how much the party cost",8),
            QuizValues("Keep to yourself and observe other people",3),
            QuizValues("Notice the architecture of the host's home",2),
            QuizValues("Takes a stroll through the host's garden",5),
            QuizValues("Get into a discussion about your favourite author",6),
            QuizValues("Browse through the host's music collection",1),
            QuizValues("Wax philosophical to other guests",4)

        )

        questionList.add(que6)

        val que7 = QuizQuestions(7,"You're trying to choose a board game to play with friends. Which of the following activities would you excel at most?This question is required.",
            QuizValues("Solving riddles",2),
            QuizValues("Physical tasks such as tossing a ball into a basket",7),
            QuizValues("Leading a group",3),
            QuizValues("Drawing clues to help team members guess the answer",9),
            QuizValues("Identifying plants and animals",5),
            QuizValues("Math questions",8),
            QuizValues("Remember song lyrics",1),
            QuizValues("Identifying who said as a famous quotation",6),
            QuizValues("Looking into my crystal ball",4)

        )

        questionList.add(que7)

        val que8 = QuizQuestions(8,"You have a big test tomorrow and need to review the material. What study method do you use?This question is required. ",
            QuizValues("Sorting concepts into different categories to make them easier to remember",2),
            QuizValues("Get together with classmates to study group sessions",3),
            QuizValues("Try to gain hands on experience",7),
            QuizValues("Focus on understanding the reasoning and logic behind the material",8),
            QuizValues("Make up to help memorize different concepts",1),
            QuizValues("Read over your notes and assigned readings",6),
            QuizValues("Read the material outside the park",5),
            QuizValues("Lock yourself in the room to study quietly with no distractions",9),
            QuizValues("Try to get the general gist of the materials and wing it",4)

        )

        questionList.add(que8)

        return questionList
    }


}