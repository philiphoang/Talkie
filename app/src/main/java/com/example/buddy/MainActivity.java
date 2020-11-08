package com.example.buddy;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.codyzen.spriterunner.SpriteView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    private TextToSpeech tts;
    private EditText editText;
    private FloatingActionButton sendBtn;
    private ImageView mVoiceBtn;

    private String ip = "10.0.0.227";
    private InetAddress endPoint;

    private Thread worker;

    private final int USER = 0;
    private final int BOT = 1;

    private String[] MOOD_HAPPY = {"good", "great", "wonderful", "amazing", "happy", "excited", "glad"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edittext_chatbox);
        sendBtn = findViewById(R.id.send_button);
        mVoiceBtn = findViewById(R.id.talkie_neutral);

        //Initialize Text to speech
        tts = new TextToSpeech(getApplication(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR)
                    tts.setLanguage(Locale.UK);
            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendMessage();
            }
        });

        mVoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });

    }

    private void runThread() {
        worker = new Thread(new Runnable(){
            public void run() {
                try {
                    endPoint = InetAddress.getLocalHost();
                    ip = endPoint.getHostAddress();
                    Log.d("IPADDRESS", ip);
                } catch(UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void speak() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi, speak something");

        try {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
        } catch (Exception e) {
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_SPEECH_INPUT:
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    editText.setText(result.get(0));
                }
                break;
        }
    }

    private void sendMessage() {
        String msg = editText.getText().toString();
        UserMessage userMessage = null;

        OkHttpClient okHttpClient = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + ip + ":5005/webhooks/rest/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        if (msg.trim().isEmpty()) {
            Toast.makeText(MainActivity.this, "Please enter your query!", Toast.LENGTH_LONG).show();
        }
        else {
            Log.e("Msg", "message: $msg");
            showTextView(msg, USER);
            editText.setText("");
            userMessage = new UserMessage("User", msg);
        }

        Toast.makeText(MainActivity.this, ""+userMessage.getMessage()
                ,Toast.LENGTH_LONG).show();
        MessageSender messageSender = retrofit.create(MessageSender.class);
        Call<List<BotResponse>> response = messageSender.sendMessage(userMessage);
        response.enqueue(new Callback<List<BotResponse>>() {
            @Override
            public void onResponse(Call<List<BotResponse>> call, Response<List<BotResponse>> response) {
                if (response.body() == null || response.body().size() == 0)
                    showTextView("Sorry, did not quite understand", BOT);
                else {
                    BotResponse botResponse = response.body().get(0);
                    showTextView(botResponse.getText(), BOT);

                    try {
                        Thread.sleep(1200);

                        //DOES NOT WORK
                        if (stringContainsStringFromList(botResponse.getText(), MOOD_HAPPY)) {
                            findViewById(R.id.talkie_neutral).setVisibility(View.GONE);
                            findViewById(R.id.talkie_smile).setVisibility(View.VISIBLE);
                            Log.d("IMAGE", "CHAGNED IMAGE");
                        }

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            tts.speak(botResponse.getText(), TextToSpeech.QUEUE_FLUSH, null, null);
                        }
                        else {
                            tts.speak(botResponse.getText(), TextToSpeech.QUEUE_FLUSH, null);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }

            @Override
            public void onFailure(Call<List<BotResponse>> call, Throwable t) {
                showTextView("Waiting for message", BOT);
                Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showTextView(String message, int type) {
        FrameLayout frameLayout;

        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);

        switch (type) {
            case USER:
                frameLayout = getUserLayout();
                break;
            case BOT:
                frameLayout = getBotLayout();
                break;
            default:
                frameLayout = getBotLayout();
                break;
        }

        frameLayout.setFocusableInTouchMode(true);
        TextView tv = frameLayout.findViewById(R.id.chat_msg);
        tv.setText(message);
        frameLayout.requestFocus();
        editText.requestFocus();
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aa", Locale.ENGLISH);
        String time = dateFormat.format(date);
        TextView timeTextView = frameLayout.findViewById(R.id.message_time);
        timeTextView.setText(time.toString());
    }

    FrameLayout getUserLayout() {
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        return (FrameLayout) inflater.inflate(R.layout.user_message_box, null);
    }

    FrameLayout getBotLayout() {
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        return (FrameLayout) inflater.inflate(R.layout.bot_message_box, null);
    }

    public static boolean stringContainsStringFromList(String str, String[] list) {
        return (Arrays.asList(list).contains(str.toLowerCase()));
    }
}