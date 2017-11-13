package com.project.akejufatai.craze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        final LevelStorage levelStorage = new LevelStorage(this);

        Button playButton = (Button) findViewById(R.id.play_button);
        TextView levelTextView = (TextView) findViewById(R.id.level_text_view);
        levelTextView.setText("LEVEL #" + levelStorage.getValue());
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                levelStorage.updateLevel(1);

                Intent intent = new Intent(PlayActivity.this,GameActivity.class);
                startActivity(intent);
            }
        });
    }


}
