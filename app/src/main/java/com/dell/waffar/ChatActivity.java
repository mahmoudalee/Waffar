package com.dell.waffar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.dell.waffar.bot.ChatAdapter;
import com.dell.waffar.bot.ClickListener;
import com.dell.waffar.bot.Message;
import com.dell.waffar.bot.RecyclerTouchListener;
import com.dell.waffar.bot.SpeakerLabelsDiarization;
import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.AddAudioOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.BaseRecognizeCallback;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.SynthesizeOptions;

import java.io.InputStream;
import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChatAdapter mAdapter;
    private ArrayList messageArrayList;
    private EditText inputMessage;
    private ImageButton btnSend;

    com.ibm.watson.developer_cloud.assistant.v1.model.Context context = null;
    private boolean initialRequest;
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        inputMessage = findViewById(R.id.message);
        btnSend = findViewById(R.id.btn_send);

        recyclerView = findViewById(R.id.recycler_view);

        messageArrayList = new ArrayList<>();
        mAdapter = new ChatAdapter(messageArrayList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        this.inputMessage.setText("");
        this.initialRequest = true;
        sendMessage();

        btnSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(checkInternetConnection()) {
                    sendMessage();
                }
            }
        });

    }

    // Sending a message to Watson Conversation Service
    private void sendMessage() {
        final String inputmessage = this.inputMessage.getText().toString().trim();

        if(inputMessage == null){
            if(!this.initialRequest) {
                Message inputMessage = new Message();
                inputMessage.setMessage(inputmessage);
                inputMessage.setId("1");
                messageArrayList.add(inputMessage);
            }
            else
            {
                Message inputMessage = new Message();
                inputMessage.setMessage(inputmessage);
                inputMessage.setId("100");
                this.initialRequest = false;
            }

            this.inputMessage.setText("");
            mAdapter.notifyDataSetChanged();

            Thread thread = new Thread(new Runnable(){
                public void run() {
                    try {

                        Assistant assistantservice = new Assistant("2018-02-16");
                        //If you like to use USERNAME AND PASSWORD
                        //Your Username: "apikey", password: "<APIKEY_VALUE>"
                        assistantservice.setUsernameAndPassword("apikey", "Vrz5l-ghBw2RVIODF6VH9p_vViw44V7NBKpUFXZG0Oy8");

                        //TODO: Uncomment this line if you want to use API KEY
                        //assistantservice.setApiKey("<API_KEY_VALUE>");

                        //Set endpoint which is the URL. Default value: https://gateway.watsonplatform.net/assistant/api
                        assistantservice.setEndPoint("https://gateway-lon.watsonplatform.net/assistant/api");
                        InputData input = new InputData.Builder(inputmessage).build();
                        //WORKSPACES are now SKILLS
                        MessageOptions options = new MessageOptions.Builder().workspaceId("20a27874-522d-408f-b5c4-00466c4e67a0").input(input).context(context).build();
                        MessageResponse response = assistantservice.message(options).execute();
                        Log.i(TAG, "run: "+response);

                        String outputText = "";
                        int length=response.getOutput().getText().size();
                        Log.i(TAG, "run: "+length);
                        if(length>1) {
                            for (int i = 0; i < length; i++) {
                                outputText += '\n' + response.getOutput().getText().get(i).trim();
                            }
                        }
                        else
                            outputText = response.getOutput().getText().get(0);

                        Log.i(TAG, "run: "+outputText);
                        //Passing Context of last conversation
                        if(response.getContext() !=null)
                        {
                            //context.clear();
                            context = response.getContext();

                        }
                        Message outMessage=new Message();
                        if(response!=null)
                        {
                            if(response.getOutput()!=null && response.getOutput().containsKey("text"))
                            {
                                ArrayList responseList = (ArrayList) response.getOutput().get("text");
                                if(null !=responseList && responseList.size()>0){
                                    outMessage.setMessage(outputText);
                                    outMessage.setId("2");
                                }
                                messageArrayList.add(outMessage);
                            }

                            runOnUiThread(new Runnable() {
                                public void run() {
                                    mAdapter.notifyDataSetChanged();
                                    if (mAdapter.getItemCount() > 1) {
                                        recyclerView.getLayoutManager().smoothScrollToPosition(recyclerView, null, mAdapter.getItemCount()-1);

                                    }

                                }
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();

        }
    }

    /**
     * Check Internet Connection
     * @return
     */
    private boolean checkInternetConnection() {
        // get Connectivity Manager object to check connection
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        // Check for network connections
        if (isConnected){
            return true;
        }
        else {
            Toast.makeText(this, " No Internet Connection available ", Toast.LENGTH_LONG).show();
            return false;
        }

    }

    private void showError(final Exception e) {
        runOnUiThread(new Runnable() {
            @Override public void run() {
                Toast.makeText(ChatActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
    }
}
