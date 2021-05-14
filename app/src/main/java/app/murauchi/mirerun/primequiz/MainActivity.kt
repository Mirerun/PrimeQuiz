package app.murauchi.mirerun.primequiz

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val QUESTION_COUNT: Int = 10
    }

    var random: Random = Random()
    val questions: IntArray = IntArray(QUESTION_COUNT)
    var point: Int = 0
    var answerCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0 until QUESTION_COUNT) {
            val number = random.nextInt(1000) //問題の数字をランダムで入れる
            Log.d("Number", "Question" + number.toString())
            questions[i] = number //ランダムに出た数字を配列に格納
        }
        //アプリを開いたときからランダムな数字が表示されるように
        point = 0 //得点
        answerCount = 0 //解答数

        textView.text = questions[answerCount].toString() + ""
        textView.setTextColor(Color.BLACK)
    }

    fun maru(v: View) {
        var answer = true //boolean型
        val number = questions[answerCount]

        for (i in 2 until number) { //2以上number未満のi
            if (number % i == 0) { //iで割り切れたら素数じゃない->false
                answer = false
                break //一度素数でないと判定したらその後調べる必要ない
            }
        }
        if (answer) { //if answer==true
            Toast.makeText(this,"正解", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this,"不正解", Toast.LENGTH_SHORT).show()
        }

        if (answer) {
            point++ //+1の意味
            Log.d("maru","正解" + point.toString())
        } else {
            Log.d("maru","不正解")
        }

        answerCount++ //正解数を入れるanswerCountに+1の意味

        if (answerCount == QUESTION_COUNT) { //問題が終わったかどうか
            textView.text = point.toString() + "点" //終わってたら点数表示
            textView.setTextColor(Color.RED)

            point = 0 //次回のために得点と解答数を初期化
            answerCount = 0
        } else {
            textView.text = questions[answerCount].toString() + ""
            textView.setTextColor(Color.BLACK)
        }
    }
}