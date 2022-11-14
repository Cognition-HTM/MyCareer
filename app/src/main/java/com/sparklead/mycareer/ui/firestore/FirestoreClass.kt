package com.sparklead.mycareer.ui.firestore

import android.app.Activity
import android.net.Uri
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.sparklead.mycareer.models.*
import com.sparklead.mycareer.ui.activities.*
import com.sparklead.mycareer.ui.fragments.CounselorFragment
import com.sparklead.mycareer.ui.fragments.ExploreFragment
import com.sparklead.mycareer.ui.fragments.HomeFragment

class FirestoreClass {

    private val mFirestore = FirebaseFirestore.getInstance()
    private val accounting = "Accounting"

    fun registerUser(activity: SignUpActivity, userInfo: User) {

        //the "users" is a collection name.
        mFirestore.collection(Constants.USERS)
            //Document Id for users fields.
            .document(userInfo.id)
            //here the userInfo are field and the setOption is set to merge.
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                //Here call a function of base activity for transferring the result to it.
                activity.userRegistrationSuccess()
            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while registering the user_id.",
                    e
                )
            }
    }

     private fun getCurrentUserId(): String {

        //An Instance of currentUser using FirebaseAuth
        val currentUser = FirebaseAuth.getInstance().currentUser

        //A variable to assign the currentUserId if it is not null or else it will be blank.
        var currentUserId = ""
        if (currentUser != null) {
            currentUserId = currentUser.uid
        }

        return currentUserId

    }
    fun getCurrentUserIdNo(activity: SplashScreenActivity): String {

        //An Instance of currentUser using FirebaseAuth
        val currentUser = FirebaseAuth.getInstance().currentUser

        //A variable to assign the currentUserId if it is not null or else it will be blank.
        var currentUserId = ""
        if (currentUser != null) {
            currentUserId = currentUser.uid
        }
        return currentUserId

    }


    fun getUserDetails(activity: Activity) {
        //Here we pass the collection name from which we wants the data.
        mFirestore.collection(Constants.USERS)
            // the document id to get the field of User.
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->
                Log.i(activity.javaClass.simpleName, document.toString())

                //Here we have received the document snapshot which is converted into the User data model object.
                val user = document.toObject(User::class.java)!!

//                val sharedPreferences = activity.getSharedPreferences(
//                    Constants.CAGUI_PREFERENCES,
//                    Context.MODE_PRIVATE
//                )
//
//                val editor : SharedPreferences.Editor = sharedPreferences.edit()
////                key : logged_in_success :Aditya Gupta
//                editor.putString(Constants.LOGGED_IN_USERNAME," ${user.name}")
//                editor.apply()
////                //end
                when (activity) {
                    is SignInActivity -> {
                        //call a function of base activity for transferring the result to it
                        activity.userLoggedInSuccess(user)

                    }
                    is SettingActivity -> {
                        activity.userDetailsSuccess(user)

                    }
                    is CounselorDashboardActivity -> {
                        activity.userDetailsSuccess(user)

                    }
                    is SplashScreenActivity ->{
                        activity.userDetailsSuccess(user)
                    }
                    is CounselorDetailsActivity ->{
                        activity.userDetailsSuccess(user)
                    }
                }

            }
        //end
    }

    fun updateUserProfileData(activity: Activity, userHashMap: HashMap<String, Any>) {
        mFirestore.collection(Constants.USERS)
            .document(getCurrentUserId())
            .update(userHashMap)
            .addOnCompleteListener {

                println("Yes profile updated")
                when (activity) {
                    is ProfileActivity -> {
                        activity.userProfileUpdateSuccess()
                    }

                    is CounselorProfileActivity -> {
                        activity.userProfileUpdateSuccess()

                    }
                    is CounselorEditActivity -> {
                        activity.userUpdateSuccess()
                    }
                    is SignInActivity ->{
                        activity.userUpdateSuccess()
                    }
                    is SettingActivity -> {
                        activity.userUpdateSuccess()
                    }
                    is CounselorDetailsActivity ->{
                        activity.hideProgressDialog()
                    }
                    is QuizResultActivity -> {
//                        activity.hideProgressDialog()
                    }
                }
            }
            .addOnFailureListener { e ->
                when (activity) {
                    is ProfileActivity -> {
                        activity.hideProgressDialog()
                    }
                }
                Log.e(
                    activity.javaClass.simpleName, "Error while updating the user_id Details", e
                )
            }
    }


    fun uploadImageToCloudStorage(activity: Activity, imageFileURI: Uri?, imageType: String) {

        //getting the storage reference
        val sRef: StorageReference = FirebaseStorage.getInstance().reference.child(
            imageType + System.currentTimeMillis() + "."
                    + Constants.getFileExtension(
                activity,
                imageFileURI
            )
        )

        //adding the file to reference
        sRef.putFile(imageFileURI!!)
            .addOnSuccessListener { taskSnapshot ->
                // The image upload is success
                Log.e(
                    "Firebase Image URL",
                    taskSnapshot.metadata!!.reference!!.downloadUrl.toString()
                )

                // Get the downloadable url from the task snapshot
                taskSnapshot.metadata!!.reference!!.downloadUrl
                    .addOnSuccessListener { uri ->
                        Log.e("Downloadable Image URL", uri.toString())

                        // TODO Step 8: Pass the success result to base class.
                        // START
                        // Here call a function of base activity for transferring the result to it.
                        when (activity) {
                            is ProfileActivity -> {
                                activity.imageUploadSuccess(uri.toString())
                            }
                            is CounselorProfileActivity -> {
                                activity.imageUploadSuccess(uri.toString())
                            }
                        }
                        // END
                    }
            }
            .addOnFailureListener { exception ->

                // Hide the progress dialog if there is any error. And print the error in log.
                when (activity) {
                    is ProfileActivity -> {
                        activity.hideProgressDialog()
                    }
                }
                Log.e(
                    "ABCD",
                    exception.message,
                    exception
                )
            }
    }

    fun getUserDetailsForFragment(fragment: Fragment) {

        //Here we pass the collection name from which we wants the data.
        mFirestore.collection(Constants.USERS)

            // the document id to get the field of User.
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->

                //Here we have received the document snapshot which is converted into the User data model object.
                val user = document.toObject(User::class.java)!!

//                val sharedPreferences = activity.getSharedPreferences(
//                    Constants.CAGUI_PREFERENCES,
//                    Context.MODE_PRIVATE
//                )
//
//                val editor : SharedPreferences.Editor = sharedPreferences.edit()
////                key : logged_in_success :Aditya Gupta
//                editor.putString(Constants.LOGGED_IN_USERNAME," ${user.name}")
//                editor.apply()
////                //end
                when (fragment) {
                    is HomeFragment -> {
                        //call a function of base activity for transferring the result to it
                        fragment.userDetailsSuccess(user)

                    }

                }

            }
        //end
    }

    fun getExploreList(fragment: ExploreFragment){
        mFirestore.collection(Constants.EXPLORE)
            .document("ag9yl8ZFOdFSiI6hZHNx")
            .get()
            .addOnSuccessListener { document ->

                val exploreList : ArrayList<Explore> = ArrayList()

                val explore = document.toObject(ExploreMain::class.java)!!

                for (i in explore.item) {
                    exploreList.add(i)
                }

                fragment.getExploreSuccess(exploreList)

            }
            .addOnFailureListener {
                Log.e("Explore Fragment" , "Failed to getting data from firebase")
            }
    }

    fun getCounselorDetailsForFragment(fragment: Fragment) {
        mFirestore.collection(Constants.USERS)
            .get()
            .addOnSuccessListener { document ->

                val counselorList: ArrayList<User> = ArrayList()

                for (i in document.documents) {
                    val counselor = i.toObject(User::class.java)

                    if (counselor!!.userType == "Counselor") {
                        counselor.id = i.id
                        counselorList.add(counselor)
                    }
                }

//                val user = document.toObjects(User::class.java)
//
                when (fragment) {
                    is CounselorFragment -> {
                        fragment.getCounselorDetailsSuccess(counselorList)
                    }
                }
            }
            .addOnFailureListener {

            }
    }

    fun updateBlogs(activity: CounselorEditActivity, blogs: HashMap<String, Any>) {
        mFirestore.collection(Constants.BLOGS)
            .document(getCurrentUserId())
            .set(blogs)
            .addOnSuccessListener {

                activity.blogsUpdatedSuccessfully()

            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while uploading the blogs.",
                    e
                )
            }
    }

    fun getBlogsList(activity: CounselorEditActivity, id: String) {
        mFirestore.collection(Constants.BLOGS)
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->

                val blogsList: ArrayList<PreviousBlogs> = ArrayList()

//                println("Yes 1st")
//                println(document.data.toString())
                if (document.data == null) {
                    activity.newUserBlogs()
                } else {

                    val blogs = document.toObject(Blogs::class.java)!!

                    for (i in blogs.item) {
                        blogsList.add(i);
                    }
                    println(blogsList)


                    activity.successfullyBlogs(blogsList)
                }

            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while getting the blogs.",
                    e
                )
            }

    }

    fun getPreviousBlogsList(activity: Activity) {
        mFirestore.collection(Constants.BLOGS)
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->

                val blogsList: ArrayList<PreviousBlogs> = ArrayList()

//                println("Yes 1st")
//                println(document.data.toString())
                if (document.data == null) {

                    when (activity) {
                        is CounselorDashboardActivity -> {
                            activity.noBlogsAvailable()
                        }
                        is CounselorDetailsActivity -> {
                            activity.noBlogsAvailable()
                        }
                    }

                } else {

                    val blogs = document.toObject(Blogs::class.java)!!

                    for (i in blogs.item) {
                        blogsList.add(i);
                    }
                    println(blogsList)

                    when (activity) {
                        is CounselorDashboardActivity -> {
                            activity.getBlogsDetailsSuccess(blogsList)
                        }
                        is CounselorDetailsActivity -> {
                            activity.getBlogsDetailsSuccess(blogsList)
                        }
                    }
                }


            }
            .addOnFailureListener { e ->
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while getting the blogs.",
                    e
                )
            }
    }

    fun updateSessions(activity: CounselorEditActivity, sessions: HashMap<String, Any>) {
        mFirestore.collection(Constants.SESSIONS)
            .document(getCurrentUserId())
            .set(sessions)
            .addOnSuccessListener {
                activity.updateSessionsSuccess()
            }
            .addOnFailureListener { e ->
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while uploading the sessions.",
                    e
                )
            }

    }

    fun getSessionsList(activity: CounselorEditActivity) {
        mFirestore.collection(Constants.SESSIONS)
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->

                val sessionsList: ArrayList<PreviousSessions> = ArrayList()

//                println("Yes 1st")
//                println(document.data.toString())
                if (document.data == null) {
                    activity.newUserSessions()
                } else {

                    val sessions = document.toObject(Sessions::class.java)!!

                    for (i in sessions.item) {
                        sessionsList.add(i);
                    }


                    activity.successfullySessions(sessionsList)
                }

            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while getting the blogs.",
                    e
                )
            }
    }

    fun getPreviousSessionsList(activity: Activity) {
        mFirestore.collection(Constants.SESSIONS)
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->

                val sessionsList: ArrayList<PreviousSessions> = ArrayList()

                if (document.data == null) {

                    when (activity) {
                        is CounselorDashboardActivity -> {
                            activity.noSessionsAvailable()
                        }
                    }

                } else {

                    val sessions = document.toObject(Sessions::class.java)!!

                    for (i in sessions.item) {
                        sessionsList.add(i)
                    }

                    when (activity) {
                        is CounselorDashboardActivity -> {
                            activity.getSessionsDetailsSuccess(sessionsList)
                        }
                    }
                }


            }
            .addOnFailureListener { e ->
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while getting the blogs.",
                    e
                )
            }
    }

    fun getDetailsPreviousBlogsList(activity: Activity, id: String) {
        mFirestore.collection(Constants.BLOGS)
            .document(id)
            .get()
            .addOnSuccessListener { document ->

                val blogsList: ArrayList<PreviousBlogs> = ArrayList()

//                println("Yes 1st")
//                println(document.data.toString())
                if (document.data == null) {

                    when (activity) {
                        is CounselorDetailsActivity -> {
                            activity.noBlogsAvailable()
                        }
                    }

                } else {

                    val blogs = document.toObject(Blogs::class.java)!!

                    for (i in blogs.item) {
                        blogsList.add(i);
                    }

                    when (activity) {
                        is CounselorDetailsActivity -> {
                            activity.getBlogsDetailsSuccess(blogsList)
                        }
                    }
                }
            }
            .addOnFailureListener { e ->
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while getting the blogs.",
                    e
                )
            }
    }

    fun getCounselorDetails(activity: Activity, id: String) {
        mFirestore.collection(Constants.USERS)
            .document(id)
            .get()
            .addOnSuccessListener { document ->

                val counselor = document.toObject(User::class.java)!!

                when (activity) {
                    is CounselorDetailsActivity -> {
                        activity.counselorDetailsSuccess(counselor)
                    }
                }
            }
            .addOnFailureListener { e ->
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while getting the Counselor Details.",
                    e
                )
            }

    }

    fun getSessionsCurrentList(activity: Activity, id: String) {
        mFirestore.collection(Constants.SESSIONS)
            .document(id)
            .get()
            .addOnSuccessListener { document ->

                val sessionsList: ArrayList<PreviousSessions> = ArrayList()

                if (document.data == null) {
                    when (activity) {
                        is CounselorDetailsActivity -> {
                            activity.noSessionsAvailable()
                        }
                    }

                } else {

                    val sessions = document.toObject(Sessions::class.java)!!

                    for (i in sessions.item) {
                        sessionsList.add(i)
                    }

                    when (activity) {
                        is CounselorDetailsActivity -> {
                            activity.getSessionsDetailsSuccess(sessionsList)
                        }
                    }
                }
            }
            .addOnFailureListener { e ->
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while getting the blogs.",
                    e
                )
            }
    }

    fun updateFavourite(activity: Activity,favourite : HashMap<String,Any>) {
        mFirestore.collection(Constants.FAVOURITE)
            .document(getCurrentUserId())
            .set(favourite)
            .addOnSuccessListener {

                when (activity) {
                    is BranchInfoActivity -> {
                        activity.updateFavouriteSuccess()
                    }

                }

            }
            .addOnFailureListener { e ->

                when (activity) {
                    is BranchInfoActivity -> {
                        activity.hideProgressDialog()
                        Log.e(
                            activity.javaClass.simpleName,
                            "Error while uploading the blogs.",
                            e
                        )
                    }

                }
            }
    }

    fun getFavouriteList(activity: BranchInfoActivity) {
        mFirestore.collection(Constants.FAVOURITE)
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->

                val favouriteList: ArrayList<Favourite> = ArrayList()

//                println("Yes 1st")
//                println(document.data.toString())

                if (document.data == null) {
                    activity.newUserFavoriteSuccess()
                } else {

                    val favourite = document.toObject(FavoriteMain::class.java)!!

                    for (i in favourite.item) {
                        favouriteList.add(i);
                    }


                    activity.successfullyFavourite(favouriteList)
                }

            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while getting the blogs.",
                    e
                )
            }

    }

    fun getVisibleFavouriteList(activity: BranchInfoActivity){
        mFirestore.collection(Constants.FAVOURITE)
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->

                val favouriteList: ArrayList<Favourite> = ArrayList()

//                println("Yes 1st")
//                println(document.data.toString())
                if (document.data == null) {
                    activity.firstUserFavorite()
                } else {

                    val favourite = document.toObject(FavoriteMain::class.java)!!

                    for (i in favourite.item) {
                        favouriteList.add(i);
                    }

                    activity.visibleSuccess(favouriteList)
                }

            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while getting the blogs.",
                    e
                )
            }
    }

    fun deleteListCheckFavorite(activity: BranchInfoActivity){
        mFirestore.collection(Constants.FAVOURITE)
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->

                val favouriteList: ArrayList<Favourite> = ArrayList()

//                println("Yes 1st")
//                println(document.data.toString())
                if (document.data == null) {
                    activity.deleteListCheckSuccessNew()
                } else {

                    val favourite = document.toObject(FavoriteMain::class.java)!!

                    for (i in favourite.item) {
                        favouriteList.add(i);
                    }
                    activity.deleteListCheckSuccess(favouriteList)
                }

            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while getting the blogs.",
                    e
                )
            }
    }

    fun deleteFavourite(activity: BranchInfoActivity){
        mFirestore.collection(Constants.FAVOURITE)
            .document(getCurrentUserId())
            .delete()
            .addOnSuccessListener {
                activity.onBackPressed()
            }
    }

    fun getFavouriteListFragment(fragment: HomeFragment){
        mFirestore.collection(Constants.FAVOURITE)
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->

                val favouriteList: ArrayList<Favourite> = ArrayList()

//                println("Yes 1st")
//                println(document.data.toString())
                if (document.data == null) {
                    fragment.hideProgressDialog()
                } else {

                    val favourite = document.toObject(FavoriteMain::class.java)!!

                    for (i in favourite.item) {
                        favouriteList.add(i);
                    }

                    fragment.getFavouriteListSuccess(favouriteList)
                }

            }
            .addOnFailureListener { e ->
                fragment.hideProgressDialog()
                Log.e(
                    fragment.javaClass.simpleName,
                    "Error while getting the blogs.",
                    e
                )
            }
    }

    fun updateCounselorData(activity: Activity,counselorHashMap: HashMap<String, Any>,id:String){
        mFirestore.collection(Constants.USERS)
            .document(id)
            .update(counselorHashMap)
            .addOnCompleteListener {

                when (activity) {
                    is CounselorDetailsActivity ->{
                    }
                }
            }
            .addOnFailureListener { e ->
                when (activity) {
                    is ProfileActivity -> {
                        activity.hideProgressDialog()
                    }
                }
                Log.e(
                    activity.javaClass.simpleName, "Error while updating the user_id Details", e
                )
            }
    }
}
