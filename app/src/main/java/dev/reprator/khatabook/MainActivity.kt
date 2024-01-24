package dev.reprator.khatabook

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    companion object {
        private val clientId = "8326d62006860371745e"
        private val clientSecret = "5a80298e6584c0ed210d340beb2bae727898eb6b"
        private val redirectUrl = "dev.reprator.khatabook://auth/oauth2callback"
    }

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        findViewById<View>(R.id.loginGithub).setOnClickListener {
            loginGithub()
        }
    }


    private fun loginGithub () {
        val intent = CustomTabsIntent.Builder().build()
        val uri = Uri.parse("https://github.com/login/oauth/authorize?client_id=$clientId&scope=repo&redirect_url=$redirectUrl")
        intent.launchUrl(this, uri)
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val code = intent?.data?.getQueryParameter("code") ?: return
        viewModel.makeGitHubRequest(clientId, clientSecret, code)
    }

}