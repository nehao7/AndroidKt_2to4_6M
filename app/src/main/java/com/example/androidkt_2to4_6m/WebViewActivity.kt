package com.example.androidkt_2to4_6m

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidkt_2to4_6m.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    lateinit var binding:ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val webSettings: WebSettings = binding.webView.settings
        binding.webView.getSettings().setSupportMultipleWindows(true);
        webSettings.javaScriptEnabled = true

        binding.webView.webViewClient = WebViewClient()
        // this will load the url of the website
        binding.webView.loadUrl("https://blog.logrocket.com/customize-androids-back-button-navigation-webview/")

        // if you want to enable zoom feature
        binding.webView.settings.setSupportZoom(true)
    }

    override fun onBackPressed() {
        // if your webview can go back it will go back
        if (binding.webView.canGoBack()){
            binding.webView.goBack()
        }
//         if your webview cannot go back
        // it will exit the application
        else{
            super.onBackPressed()
        }
    }
}