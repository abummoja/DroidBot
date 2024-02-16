package tts.abu.bot.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import tts.abu.bot.R;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import java.util.*;
import android.os.*;
import android.speech.*;
import android.text.format.*;
import android.content.*;
import android.net.*;
import android.view.View.OnClickListener;
import android.view.*;
import android.view.animation.*;
import android.media.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity
{

	private TextToSpeech atts;
	private SpeechRecognizer asr;
	private Button spk;
	private ImageView im;
	private Animation anim;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		im = findViewById(R.id.imag);
		anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.reverse_pbar_rotor);
		
		im.startAnimation(anim);
		spk = findViewById(R.id.buttn);
		spk.setOnClickListener(new OnClickListener(){
			public void onClick(View p1){
				Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
				i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
						   RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
				i.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
				asr.startListening(i);
				
				
			}
		});
		
		initializeTextToSpeech();
		initializeSpeechRecognizer();
    }

	private void initializeSpeechRecognizer()
	{
		if (SpeechRecognizer.isRecognitionAvailable(this))
		{
			asr = SpeechRecognizer.createSpeechRecognizer(this);
			asr.setRecognitionListener(new RecognitionListener() {

					@Override
					public void onReadyForSpeech(Bundle p1)
					{
						// TODO: Implement this method
					}

					@Override
					public void onBeginningOfSpeech()
					{
						// TODO: Implement this method
					}

					@Override
					public void onRmsChanged(float p1)
					{
						// TODO: Implement this method
					}

					@Override
					public void onBufferReceived(byte[] p1)
					{
						// TODO: Implement this method
					}

					@Override
					public void onEndOfSpeech()
					{
						// TODO: Implement this method
					}

					@Override
					public void onError(int p1)
					{
						// TODO: Implement this method
					}

					@Override
					public void onResults(Bundle bundle)
					{
						List<String> results = bundle.getStringArrayList(
							SpeechRecognizer.RESULTS_RECOGNITION
						);
						processResult(results.get(0));
						// TODO: Implement this method
					}
					@Override
					public void onPartialResults(Bundle p1)
					{
						// TODO: Implement this method
					}

					@Override
					public void onEvent(int p1, Bundle p2)
					{
						// TODO: Implement this method
					}


				});
		}
		// TODO: Implement this method
	}
	private void processResult(String command)
	{
		command = command.toLowerCase();
		
		
		if (command.indexOf("what") != -1)
		{
			if (command.indexOf("your name") != -1)
			{
				speak("I am Abummoja.");
			}
			if (command.indexOf("time") != -1)
			{
				Date now = new Date();
				String time = DateUtils.formatDateTime(this, now.getTime(),
													   DateUtils.FORMAT_SHOW_TIME);
				speak("The time is" + time);
			}
			if (command.indexOf("love") != -1)
			{
				speak("it is a good feeling or expression of affection and is recommended to you by abummoja");
			}
		}
		else if (command.indexOf("open") != -1)
		{
			if (command.indexOf("browser") != -1)
			{
				speak("initializing internet access");
				Intent intent = new Intent(Intent.ACTION_VIEW,
										   Uri.parse("https://www.google.com/search?q=abummoja&oq=abu&aqs=chrome.0.69i59j69i57.3186j0j4&client=ms-android-alcatel&sourceid=chrome-mobile&ie=UTF-8"));
				startActivity(intent);
			}
			if (command.indexOf("gallery") != -1)
			{
				speak("opening gallery");
			}
		}
		else if(command.indexOf("hi") != -1)
		{
			speak("hello");
		}
		else if(command.indexOf("hello") != -1)
		{
			speak("hi, nice to hear from you");
		}
		// TODO: Implement this method
	}
	private void initializeTextToSpeech()
	{ 
		atts = new TextToSpeech(this, new  TextToSpeech.OnInitListener(){
				@Override
				public void onInit(int i)
				{
					if (atts.getEngines().size() == 0)
					{
						finish();
					}
					else
					{
						atts.setLanguage(Locale.UK);
						speak("hello, i am win tel at your service.");
					}
				}
			});
		// TODO: Implement this method
	}
	private void speak(String message)
	{
		if (Build.VERSION.SDK_INT >= 21)
		{
			atts.speak(message, TextToSpeech.QUEUE_FLUSH, null, null);
		}
		else
		{
			atts.speak(message, TextToSpeech.QUEUE_FLUSH, null);
		}
		// TODO: Implement this method
	}
	@Override
	protected void onPause()
	{
		// TODO: Implement this method
		super.onPause();
		atts.shutdown();
	}

}
