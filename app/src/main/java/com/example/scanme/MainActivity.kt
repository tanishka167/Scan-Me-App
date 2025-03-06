package com.example.scanme

import android.app.ComponentCaller
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

class MainActivity : AppCompatActivity() {

    lateinit var result: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        val camera=findViewById<ImageView>(R.id.btnCamera)
        val erase=findViewById<ImageView>(R.id.btnErase)
        val copy=findViewById<ImageView>(R.id.btnCopy)

        result= findViewById(R.id.resultTV)

        camera.setOnClickListener{
//            open up the camera and store the image
//            on clicked image we will run ML Algo to extract text out of it

            val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if(intent.resolveActivity(packageManager)!=null){
//                Receive image and send it for text extraction
                startActivityForResult(intent,123)

            }
            else{
//                something went wrong
                Toast.makeText(this,"Opps something went wrong",Toast.LENGTH_SHORT).show()
            }
        }

        erase.setOnClickListener{
            result.setText("")
        }

        copy.setOnClickListener{
            val clipBoard=getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip= ClipData.newPlainText("label",result.text.toString())
            clipBoard.setPrimaryClip(clip)
            Toast.makeText(this,"Copied to ClipBoard",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 123 && resultCode == RESULT_OK) {
            val extras = data?.extras
            val bitmap = extras?.get("data") as? Bitmap
            if (bitmap != null) {
                detectTextUsingML(bitmap)
            } else {
                Toast.makeText(this, "Failed to capture image", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun detectTextUsingML(bitmap: Bitmap) {
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
        val image = InputImage.fromBitmap(bitmap, 0)

        recognizer.process(image)
            .addOnSuccessListener { visionText ->
                // Display extracted text in the EditText
                result.setText(visionText.text)
            }
            .addOnFailureListener { e ->
                // Show error message if text extraction fails
                Toast.makeText(this, "Failed to extract text: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}