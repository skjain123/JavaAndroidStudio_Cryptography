package com.example.cryptography;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RadioButton RBScytale;
    RadioButton RBSCaesar;
    RadioButton RBVigenere;

    EditText inText;
    EditText keyText;

    TextView outText;

    Button enB;
    Button deB;

    boolean ss = false;
    boolean cs = false;
    boolean vs = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*-------------*/

        RBScytale = this.findViewById(R.id.RB_Scytale);
        RBSCaesar = this.findViewById(R.id.RB_Caesar);
        RadioButton RBVigenere = this.findViewById(R.id.RB_Vigenere);
        inText = this.findViewById(R.id.input_textView);
        EditText keyText = this.findViewById(R.id.key_textView);
        TextView outText = this.findViewById(R.id.output_text);
        enB = this.findViewById(R.id.en_Button);
        deB = this.findViewById(R.id.de_Button);

        RBScytale.setOnClickListener(this);
        RBSCaesar.setOnClickListener(this);
        RBVigenere.setOnClickListener(this);
        enB.setOnClickListener(this);
        deB.setOnClickListener(this);

    }

    @Override
    public void onClick (View v) {
        if (v.getId() == RBScytale.getId()) {
            RBScytale.toggle();
            ss = !ss;
        } else if (v.getId() == RBSCaesar.getId()) {
            RBSCaesar.toggle();
            cs = !cs;
        } else if (v.getId() == RBVigenere.getId()) {
            RBVigenere.toggle();
            vs = !vs;
        } else if (v.getId() == enB.getId()) {
            String input = inText.getText().toString();
            String key = keyText.getText().toString();

            key = key.replaceAll("[^a-zA-Z0-9_-]", "");
            key = key.replaceAll("\\s", "");

            if (!input.isEmpty() && !key.isEmpty()) {
                if (ss) {
                    outText.setText(en_Scytale(input, Integer.parseInt(key)));
                } else if (cs) {
                    outText.setText(en_Caesar(input, Integer.parseInt(key)));
                } else if (vs) {
                    key = key.replaceAll("[0-9]+", "");
                    if (!key.replaceAll("[0-9]+", "").isEmpty()) {
                        key = key.replaceAll("[0-9]+", "");
                        outText.setText(en_Vigenere(input, key));
                    }
                }
            }
        } else if (v.getId() == deB.getId()) {
            //sdf
        }
    }


    //CHIPHERS // DE = decrypt // EN = ENCRYPT

    public String en_Vigenere(String input, String key) {
        String output = "";
        int[] outKey = new int[key.length()];

        key = key.toUpperCase();

        keyChecker(key);

        for (int i = 0; i < key.length(); i++) {
            outKey[i] = (key.charAt(i) - 'A');
        }

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) >= 'A' && input.charAt(i) <= 'Z' && input.charAt(i) + outKey[i] > 'Z') { //upper
                output += (char) ('A' - 1 + Math.abs('Z' - (input.charAt(i) + outKey[i])));
            } else if (input.charAt(i) >= 'a' && input.charAt(i) <= 'z' && input.charAt(i) + outKey[i] > 'z') { //lower
                output += (char) ('a' - 1 + Math.abs('z' - (input.charAt(i) + outKey[i])));
            } else {
                output += (char) (input.charAt(i) + outKey[i]);
            }
        }

        return output;
    }

    public String de_Vigenere(String input, String key) {
        String output = "";

        int[] outKey = new int[key.length()];

        key = key.toUpperCase();

        keyChecker(key);

        for (int i = 0; i < key.length(); i++) {
            outKey[i] = (key.charAt(i) - 'A');
        }

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) >= 'A' && input.charAt(i) <= 'Z' && input.charAt(i) - outKey[i] < 'A') { //upper
                output += (char) (1 + 'Z' - ('A' - (input.charAt(i) - outKey[i])));
            } else if (input.charAt(i) >= 'a' && input.charAt(i) <= 'z' && input.charAt(i) - outKey[i] < 'a') { //lower
                output += (char) (1 + 'z' - ('a' - (input.charAt(i) - outKey[i])));
            }else {
                output += (char) (input.charAt(i) - outKey[i]);
            }
        }

        return output;
    }

    private void keyChecker(String key) {
        for (int i = 0; i < key.length(); i++) {
            if (key.charAt(i) >= 'A' && key.charAt(i) <= 'Z') {
                continue;
            } else if (key.charAt(i) >= 'a' && key.charAt(i) <= 'z') {
                continue;
            } else {
                System.out.println("Please enter a key without special characters!");
                System.exit(0);
            }
        }
    }



    private String en_Caesar(String input, int key) {
        String output = "";

        while (key > 26) {
            key = key - 26;
        }

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) >= 'A' && input.charAt(i) <= 'Z' && input.charAt(i) + key > 'Z') { //upper
                output += (char) ('A' - 1 + Math.abs('Z' - (input.charAt(i) + key)));
            } else if (input.charAt(i) >= 'a' && input.charAt(i) <= 'z' && input.charAt(i) + key > 'z') { //lower
                output += (char) ('a' - 1 + Math.abs('z' - (input.charAt(i) + key)));
            } else {
                output += (char) (input.charAt(i) + key);
            }
        }

        return output;
    }

    private String de_Caesar(String input, int key) {
        String output = "";

        while (key > 26) {
            key = key - 26;
        }

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) >= 'A' && input.charAt(i) <= 'Z' && input.charAt(i) - key < 'A') { //upper
                output += (char) (1 + 'Z' - ('A' - (input.charAt(i) - key)));
            } else if (input.charAt(i) >= 'a' && input.charAt(i) <= 'z' && input.charAt(i) - key < 'a') { //lower
                output += (char) (1 + 'z' - ('a' - (input.charAt(i) - key)));
            }/* else {
				output += (char) (input.charAt(i) - key);
			}*/
        }

        return output;
    }

    private String en_Scytale(String input, int row) {
        String output = "";

        int col = Math.round((input.length() / row) + 1);
        int diff = row - ((row * col) - input.length());
        int strI = 0;

        char[][] t = new char[row][col];

        System.out.println("info: " + row + "," + col + "," + input.length());

        for (int i = 0; i < t.length; i++)
        {
            for (int j = 0; j < t[0].length; j++)
            {
                if (j == col - 1 && diff <= 0 || strI >= input.length()) {
                    t[i][j] = '@';
                } else {
                    t[i][j] = input.charAt(strI);
                    strI++;
                }
            }
            diff--;
        }

        for (int i = 0; i < t[0].length; i++)
        {
            for (int j = 0; j < t.length; j++)
            {
                if (t[j][i] != '@') {
                    output += t[j][i];
                }
            }
        }

        return output;
    }

    private String de_Scytale(String input, int row) {
        String output = "";

        int col = Math.round((input.length() / row) + 1);
        int diff = row - ((row * col) - input.length());
        int strI = 0;

        String ps = "";

        for (int i = 0; i < row * col; i++)
        {
            if (strI >= input.length()) {
                ps += '@';
            } else {
                ps += input.charAt(strI);
                strI++;
            }
        }

        strI = 0;

        char[][] t = new char[row][col];

        for (int i = 0; i < t[0].length; i++)
        {
            for (int j = 0; j < t.length; j++)
            {
                t[j][i] = ps.charAt(strI);
                strI++;
            }
        }

        for (int i = 0; i < t.length; i++)
        {
            for (int j = 0; j < t[0].length; j++)
            {
                if (t[i][j] != '@') {
                    output += t[i][j];
                }
            }
        }

        return output;
    }
}
