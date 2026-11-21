package com.example.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private final CalculatorEngine engine = new CalculatorEngine();
    private TextView displayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayText = findViewById(R.id.tvDisplay);
        attachKeyListeners(findViewById(R.id.keypad));
        displayText.setText(engine.getDisplay());
    }

    private void attachKeyListeners(ViewGroup group) {
        for (int i = 0; i < group.getChildCount(); i++) {
            View child = group.getChildAt(i);
            if (child instanceof ViewGroup) {
                attachKeyListeners((ViewGroup) child);
            } else if (child instanceof Button) {
                child.setOnClickListener(v -> {
                    engine.press((String) v.getTag());
                    displayText.setText(engine.getDisplay());
                });
            }
        }
    }
}
