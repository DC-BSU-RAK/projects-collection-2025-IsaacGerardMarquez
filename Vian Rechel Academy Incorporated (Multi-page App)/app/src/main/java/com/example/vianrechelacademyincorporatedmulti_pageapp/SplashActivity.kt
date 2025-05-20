import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.vianrechelacademyincorporatedmulti_pageapp.OnboardingOneActivity
import com.example.vianrechelacademyincorporatedmulti_pageapp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // This delays the splash for 3 seconds then go to OnboardingOneActivity.
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, OnboardingOneActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // This is the time for the splash screen.
    }
}
