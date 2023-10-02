package com.example.blackjack

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.bumptech.glide.Glide

class PlayActivity : AppCompatActivity() {

    private lateinit var imageViewFon2: ImageView
    private lateinit var imageHome: ImageView
    private lateinit var imageRestart: ImageView
    private lateinit var imageCardBack: ImageView
    private lateinit var imageCardFront: ImageView
    private lateinit var imageJack: ImageView
    private lateinit var imageJack2: ImageView
    private lateinit var imageJack3: ImageView
    private lateinit var imageJack4: ImageView
    private lateinit var imageJack5: ImageView
    private lateinit var imageJack6: ImageView
    private lateinit var imageJack7: ImageView
    private lateinit var imageJack8: ImageView
    private lateinit var imageJack9: ImageView
    private lateinit var imageJohn: ImageView
    private lateinit var imageJohn2: ImageView
    private lateinit var imageJohn3: ImageView
    private lateinit var imageJohn4: ImageView
    private lateinit var imageJohn5: ImageView
    private lateinit var imageJohn6: ImageView
    private lateinit var imageJohn7: ImageView
    private lateinit var imageJohn8: ImageView
    private lateinit var imageJohn9: ImageView
    private lateinit var buttonJack: AppCompatButton
    private lateinit var buttonX: AppCompatButton
    private lateinit var buttonJohn: AppCompatButton
    private lateinit var textViewJohn: TextView
    private lateinit var textViewJack: TextView
    private lateinit var textViewScore: TextView
    private lateinit var textViewStavka: TextView
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var jackImageViews: Array<ImageView>
    private lateinit var johnImageViews: Array<ImageView>
    var jackIndex = 0
    var johnIndex = 0

    var score = 0
    var stavka = 0
    var scoreJohn = 0
    var scoreJohnCard = 0
    var scoreJack = 0
    var scoreJackCard = 0
    var allJack = false
    var allJohn = false

    var isClickedJack = false
    var isClickedJohn = false
    var isClickedX = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        imageHome = findViewById(R.id.imageHome)
        imageRestart = findViewById(R.id.imageRestart)
        imageCardBack = findViewById(R.id.imageCardBack)
        imageCardFront = findViewById(R.id.imageCardFront)
        imageJack = findViewById(R.id.imageJack)
        imageJack2 = findViewById(R.id.imageJack2)
        imageJack3 = findViewById(R.id.imageJack3)
        imageJack4 = findViewById(R.id.imageJack4)
        imageJack5 = findViewById(R.id.imageJack5)
        imageJack6 = findViewById(R.id.imageJack6)
        imageJack7 = findViewById(R.id.imageJack7)
        imageJack8 = findViewById(R.id.imageJack8)
        imageJack9 = findViewById(R.id.imageJack9)
        imageJohn = findViewById(R.id.imageJohn)
        imageJohn2 = findViewById(R.id.imageJohn2)
        imageJohn3 = findViewById(R.id.imageJohn3)
        imageJohn4 = findViewById(R.id.imageJohn4)
        imageJohn5 = findViewById(R.id.imageJohn5)
        imageJohn6 = findViewById(R.id.imageJohn6)
        imageJohn7 = findViewById(R.id.imageJohn7)
        imageJohn8 = findViewById(R.id.imageJohn8)
        imageJohn9 = findViewById(R.id.imageJohn9)
        buttonJack = findViewById(R.id.buttonJack)
        buttonX = findViewById(R.id.buttonX)
        buttonJohn = findViewById(R.id.buttonJohn)
        textViewJohn = findViewById(R.id.textViewJohn)
        textViewJack = findViewById(R.id.textViewJack)
        textViewScore = findViewById(R.id.textViewScore)
        textViewStavka = findViewById(R.id.textViewStavka)
        imageViewFon2 = findViewById(R.id.imageViewFon2)

        jackImageViews = arrayOf(
            imageJack,
            imageJack2,
            imageJack3,
            imageJack4,
            imageJack5,
            imageJack6,
            imageJack7,
            imageJack8,
            imageJack9
        )
        johnImageViews = arrayOf(
            imageJohn,
            imageJohn2,
            imageJohn3,
            imageJohn4,
            imageJohn5,
            imageJohn6,
            imageJohn7,
            imageJohn8,
            imageJohn9
        )

        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        score = sharedPreferences.getInt("score", 5)
        textViewScore.text = "Score: " + score.toString()

        Glide.with(this)
            .load("http://135.181.248.237/26/fon2.png")
            .into(imageViewFon2)
        Glide.with(this)
            .load("http://135.181.248.237/26/rubashka.png")
            .into(imageCardBack)
        Glide.with(this)
            .load("http://135.181.248.237/26/rubashka.png")
            .into(imageCardFront)

        imageHome.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }
        imageRestart.setOnClickListener {
            recreate()
        }

        buttonJack.setOnClickListener {
            isClickedJack = true
            startJackAnimations()
        }
        buttonJohn.setOnClickListener {
            isClickedJohn = true
            startJackAnimations()
        }
        buttonX.setOnClickListener {
            isClickedX = true
            startJackAnimations()
        }

        showDialog()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        startActivity(Intent(this, MenuActivity::class.java))
        finish()
        return super.onKeyDown(keyCode, event)
    }

    fun startJackAnimations() {
        buttonJack.isClickable = false
        buttonJohn.isClickable = false
        buttonX.isClickable = false
        val currentImageView = jackImageViews[jackIndex]
        performCardAnimation(imageCardBack, getRandomCardImageUrl(), currentImageView) {
            scoreJack += scoreJackCard
            if (scoreJack >= 17) {
                allJack = true
            }
            textViewJack.text = scoreJack.toString()
            if (!allJack) {
                jackIndex++
                if (jackIndex < jackImageViews.size) {
                    startJackAnimations()
                } else {
                    startJohnAnimations()
                }
            } else {
                startJohnAnimations()
            }
        }
    }

    fun startJohnAnimations() {
        val currentImageView = johnImageViews[johnIndex]
        performCardAnimation(imageCardBack, getRandomCardImageUrl(), currentImageView) {
            scoreJohn += scoreJohnCard
            if (scoreJohn >= 17) {
                allJohn = true
            }
            textViewJohn.text = scoreJohn.toString()
            if (!allJohn) {
                johnIndex++
                if (johnIndex < johnImageViews.size) {
                    startJohnAnimations()
                } else {
                }
            } else {
                showDialogResult()
            }
        }
    }


    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.stavka_layout)

        val editTextValue = dialog.findViewById<TextView>(R.id.editTextValue)
        val buttonOk = dialog.findViewById<TextView>(R.id.buttonOk)

        buttonOk.setOnClickListener {
            if (TextUtils.isEmpty(editTextValue.text)) {
                stavka = 0
            } else {
                stavka = editTextValue.text.toString().toInt()
            }
            if (stavka == 0) {

            } else if (stavka <= 50 && stavka > score) {
                stavka = score
                dialog.dismiss()
            } else if (stavka > 50 && stavka <= score) {
                stavka = 50
                dialog.dismiss()
            } else if (stavka > 50 && stavka > score) {
                if (score > 50) {
                    stavka = 50
                    dialog.dismiss()
                } else {
                    stavka = score
                    dialog.dismiss()
                }
            } else {
                dialog.dismiss()
            }
            textViewStavka.text = stavka.toString()
            score -= stavka
            textViewScore.text = "Score: " + score.toString()
        }

        val window = dialog.window
        val layoutParams = WindowManager.LayoutParams()

        layoutParams.gravity = Gravity.CENTER
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = (resources.displayMetrics.heightPixels * 0.4).toInt()
        window?.attributes = layoutParams

        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    fun performCardAnimation(
        imageView: ImageView,
        targetImageUri: String,
        userImage: ImageView,
        onAnimationComplete: () -> Unit
    ) {
        val imageCardLocation = IntArray(2)
        val imageUserLocation = IntArray(2)

        imageView.getLocationOnScreen(imageCardLocation)
        userImage.getLocationOnScreen(imageUserLocation)

        val deltaX = imageUserLocation[0] - imageCardLocation[0]
        val deltaY = imageUserLocation[1] - imageCardLocation[1]

        val translationX = ObjectAnimator.ofFloat(imageView, "translationX", deltaX.toFloat())
        val translationY = ObjectAnimator.ofFloat(imageView, "translationY", deltaY.toFloat())
        val moveCard = AnimatorSet()
        moveCard.playTogether(translationX, translationY)
        moveCard.duration = 1000

        val rotateCard = ObjectAnimator.ofFloat(imageView, "rotationY", 0f, 180f)
        rotateCard.duration = 500
        rotateCard.startDelay = 1000

        val animators = AnimatorSet()
        animators.playSequentially(moveCard, rotateCard)

        animators.addListener(object : Animator.AnimatorListener {

            override fun onAnimationStart(p0: Animator) {
            }

            override fun onAnimationEnd(p0: Animator) {
                imageView.visibility = View.GONE

                Glide.with(imageView.context)
                    .load(targetImageUri)
                    .into(userImage)

                imageView.translationX = 0f
                imageView.translationY = 0f
                imageView.rotationY = 0f
                imageView.visibility = View.VISIBLE

                // Вызываем колбэк после завершения анимации
                onAnimationComplete()
            }

            override fun onAnimationCancel(p0: Animator) {
            }

            override fun onAnimationRepeat(p0: Animator) {
            }
        })

        animators.start()
    }

    fun getRandomCardImageUrl(): String {
        val imageUrls = arrayOf(
            "http://135.181.248.237/24/_6_of_clubs.png",
            "http://135.181.248.237/24/_6_of_diamonds.png",
            "http://135.181.248.237/24/_6_of_hearts.png",
            "http://135.181.248.237/24/_6_of_spades.png",
            "http://135.181.248.237/24/_7_of_clubs.png",
            "http://135.181.248.237/24/_7_of_diamonds.png",
            "http://135.181.248.237/24/_7_of_hearts.png",
            "http://135.181.248.237/24/_7_of_spades.png",
            "http://135.181.248.237/24/_8_of_clubs.png",
            "http://135.181.248.237/24/_8_of_diamonds.png",
            "http://135.181.248.237/24/_8_of_hearts.png",
            "http://135.181.248.237/24/_8_of_spades.png",
            "http://135.181.248.237/24/_9_of_clubs.png",
            "http://135.181.248.237/24/_9_of_diamonds.png",
            "http://135.181.248.237/24/_9_of_hearts.png",
            "http://135.181.248.237/24/_9_of_spades.png",
            "http://135.181.248.237/24/_10_of_clubs.png",
            "http://135.181.248.237/24/_10_of_diamonds.png",
            "http://135.181.248.237/24/_10_of_hearts.png",
            "http://135.181.248.237/24/_10_of_spades.png",
            "http://135.181.248.237/24/jack_of_clubs2.png",
            "http://135.181.248.237/24/jack_of_diamonds2.png",
            "http://135.181.248.237/24/jack_of_hearts2.png",
            "http://135.181.248.237/24/jack_of_spades2.png",
            "http://135.181.248.237/24/queen_of_clubs2.png",
            "http://135.181.248.237/24/queen_of_diamonds2.png",
            "http://135.181.248.237/24/queen_of_hearts2.png",
            "http://135.181.248.237/24/queen_of_spades2.png",
            "http://135.181.248.237/24/king_of_clubs2.png",
            "http://135.181.248.237/24/king_of_diamonds2.png",
            "http://135.181.248.237/24/king_of_hearts2.png",
            "http://135.181.248.237/24/king_of_spades2.png",
            "http://135.181.248.237/24/ace_of_clubs.png",
            "http://135.181.248.237/24/ace_of_diamonds.png",
            "http://135.181.248.237/24/ace_of_hearts.png",
            "http://135.181.248.237/24/ace_of_spades2.png"
        )

        val randomIndex = (0 until imageUrls.size).random()
        scoreJackCard = when {
            imageUrls[randomIndex].contains("jack", ignoreCase = true) -> 2
            imageUrls[randomIndex].contains("queen", ignoreCase = true) -> 3
            imageUrls[randomIndex].contains("king", ignoreCase = true) -> 4
            imageUrls[randomIndex].contains("ace", ignoreCase = true) -> 11
            imageUrls[randomIndex].contains("_6_", ignoreCase = true) -> 6
            imageUrls[randomIndex].contains("_7_", ignoreCase = true) -> 7
            imageUrls[randomIndex].contains("_8_", ignoreCase = true) -> 8
            imageUrls[randomIndex].contains("_9_", ignoreCase = true) -> 9
            imageUrls[randomIndex].contains("_10_", ignoreCase = true) -> 10
            else -> 0
        }
        scoreJohnCard = scoreJackCard
        return imageUrls[randomIndex]
    }

    private fun showDialogResult() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.result_layout)

        val textViewResult = dialog.findViewById<TextView>(R.id.textViewResult)
        val buttonRetry = dialog.findViewById<AppCompatButton>(R.id.buttonRetry)

        if (isClickedJack) {
            if (((scoreJack <= 21) && (scoreJack > scoreJohn)) || ((scoreJack <= 21) && (21 < scoreJohn))) {
                textViewResult.text = "YOU WON!"
                score += stavka*2
                textViewScore.text = "Score: " + score.toString()
                sharedPreferences.edit().putInt("score", score).apply()
            } else {
                textViewResult.text = "YOU'VE LOST!"
                sharedPreferences.edit().putInt("score", score).apply()
            }
        } else if (isClickedJohn) {
            if (((scoreJohn <= 21) && (scoreJack < scoreJohn)) || ((scoreJohn <= 21) && (21 < scoreJack))) {
                textViewResult.text = "YOU WON!"
                score += stavka*2
                textViewScore.text = "Score: " + score.toString()
                sharedPreferences.edit().putInt("score", score).apply()
            } else {
                textViewResult.text = "YOU'VE LOST!"
                sharedPreferences.edit().putInt("score", score).apply()
            }
        } else if (isClickedX) {
            if ((scoreJohn > 21 && scoreJack > 21) || scoreJack == scoreJohn) {
                textViewResult.text = "YOU WON!"
                score += stavka*4
                textViewScore.text = "Score: " + score.toString()
                sharedPreferences.edit().putInt("score", score).apply()
            } else {
                textViewResult.text = "YOU'VE LOST!"
                sharedPreferences.edit().putInt("score", score).apply()
            }
        }

        buttonRetry.setOnClickListener {
            recreate()
        }

        val window = dialog.window
        val layoutParams = WindowManager.LayoutParams()

        layoutParams.gravity = Gravity.CENTER
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = (resources.displayMetrics.heightPixels * 0.4).toInt()
        window?.attributes = layoutParams

        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }
}