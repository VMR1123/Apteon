package com.example.digiitplay.OperatorOverload;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.digiitplay.R;

import java.util.Random;

public class OperatorOverloadGameActivity extends AppCompatActivity {
    private long pressedTime;
    int right = 0, wrong = 0, rightEasy = 0, rightMed = 0, rightHard = 0, rightHardPlus = 0, wrongEasy = 0, wrongMed = 0, wrongHard = 0, wrongHardPlus = 0;
    public int temp;
    public CountDownTimer yourCountDownTimer;

    public TextView timer, textView1, countDown, num1, num2, num3, one, two, three, four, five, six, seven, eight, nine, clear, ans, check, plus, multiplicator, equalto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator_overload_game);

        timer = findViewById(R.id.timer);
        textView1 = findViewById(R.id.textView12);
        countDown = findViewById(R.id.timer_321);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);

        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);

        clear = findViewById(R.id.clear);
        plus = findViewById(R.id.textView);
        multiplicator = findViewById(R.id.textView2);
        equalto = findViewById(R.id.textView3);

        ans = findViewById(R.id.answer);

        check = findViewById(R.id.check);

        Bundle i = getIntent().getExtras();
        int mode = i.getInt("mode");
        temp = mode;

        makeINVISIBLE();

        if (mode < 9 && mode > 4) {
            yourCountDownTimer = new CountDownTimer(4000, 1000) {

                public void onTick(long millisUntilFinished) {
                    if (millisUntilFinished > 1000)
                        countDown.setText(String.valueOf(millisUntilFinished / 1000));
                    else
                        countDown.setText("Go!!!");
                }

                public void onFinish() {
                    countDown.setVisibility(View.INVISIBLE);

                    makeVISIBLE();

                    show(mode);
                    yourCountDownTimer = new CountDownTimer(46000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            timer.setText(String.valueOf(millisUntilFinished / 1000));
                        }

                        public void onFinish() {
                            textView1.setText(R.string.timeUp);
                            Intent i2 = new Intent(getApplicationContext(), OperatorOverloadScoreActivity.class);

                            i2.putExtra("correct", String.valueOf(right));
                            i2.putExtra("incorrect", String.valueOf(wrong));
                            i2.putExtra("mode", String.valueOf(mode));

                            startActivity(i2);
                            finish();
                        }

                    }.start();
                }
            }.start();

        } else if (mode == 9) {

            yourCountDownTimer = new CountDownTimer(4000, 1000) {

                public void onTick(long millisUntilFinished) {
                    if (millisUntilFinished > 1000)
                        countDown.setText(String.valueOf(millisUntilFinished / 1000));
                    else
                        countDown.setText("Go!!!");
                }

                public void onFinish() {
                    countDown.setVisibility(View.INVISIBLE);

                    makeVISIBLE();

                    textView1.setText("Easy");
                    show(5);
                    yourCountDownTimer = new CountDownTimer(31000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            timer.setText(String.valueOf(millisUntilFinished / 1000));
                        }

                        public void onFinish() {
                            textView1.setText("Moderate");
                            show(6);
                            yourCountDownTimer = new CountDownTimer(31000, 1000) {
                                public void onTick(long millisUntilFinished) {
                                    timer.setText(String.valueOf(millisUntilFinished / 1000));
                                }

                                public void onFinish() {
                                    textView1.setText("Hard");
                                    show(7);
                                    yourCountDownTimer = new CountDownTimer(31000, 1000) {
                                        public void onTick(long millisUntilFinished) {
                                            timer.setText(String.valueOf(millisUntilFinished / 1000));
                                        }

                                        public void onFinish() {
                                            textView1.setText("Hard +");
                                            show(8);
                                            yourCountDownTimer = new CountDownTimer(31000, 1000) {
                                                public void onTick(long millisUntilFinished) {
                                                    timer.setText(String.valueOf(millisUntilFinished / 1000));
                                                }

                                                public void onFinish() {
                                                    textView1.setText(R.string.timeUp);
                                                    Intent i2 = new Intent(getApplicationContext(), OperatorOverloadScoreActivity2.class);

                                                    i2.putExtra("mode", String.valueOf(mode));
                                                    i2.putExtra("correctEasy", String.valueOf(rightEasy));
                                                    i2.putExtra("correctMed", String.valueOf(rightMed));
                                                    i2.putExtra("correctHard", String.valueOf(rightHard));
                                                    i2.putExtra("correctHardPlus", String.valueOf(rightHardPlus));
                                                    i2.putExtra("incorrectEasy", String.valueOf(wrongEasy));
                                                    i2.putExtra("incorrectMed", String.valueOf(wrongMed));
                                                    i2.putExtra("incorrectHard", String.valueOf(wrongHard));
                                                    i2.putExtra("incorrectHardPlus", String.valueOf(wrongHardPlus));

                                                    startActivity(i2);
                                                    finish();
                                                }
                                            }.start();
                                        }
                                    }.start();
                                }
                            }.start();
                        }
                    }.start();
                }

            }.start();

        } else if (mode < 5) {
            makeVISIBLE();
            timer.setVisibility(View.INVISIBLE);
            show(mode);
        }
    }

    public void makeINVISIBLE() {
        timer.setVisibility(View.INVISIBLE);
        textView1.setVisibility(View.INVISIBLE);

        num1.setVisibility(View.INVISIBLE);
        num2.setVisibility(View.INVISIBLE);
        num3.setVisibility(View.INVISIBLE);

        one.setVisibility(View.INVISIBLE);
        two.setVisibility(View.INVISIBLE);
        three.setVisibility(View.INVISIBLE);
        four.setVisibility(View.INVISIBLE);
        five.setVisibility(View.INVISIBLE);
        six.setVisibility(View.INVISIBLE);
        seven.setVisibility(View.INVISIBLE);
        eight.setVisibility(View.INVISIBLE);
        nine.setVisibility(View.INVISIBLE);
        clear.setVisibility(View.INVISIBLE);
        ans.setVisibility(View.INVISIBLE);
        check.setVisibility(View.INVISIBLE);
        plus.setVisibility(View.INVISIBLE);
        equalto.setVisibility(View.INVISIBLE);
        multiplicator.setVisibility(View.INVISIBLE);
    }

    public void makeVISIBLE() {
        timer.setVisibility(View.VISIBLE);
        textView1.setVisibility(View.VISIBLE);

        num1.setVisibility(View.VISIBLE);
        num2.setVisibility(View.VISIBLE);
        num3.setVisibility(View.VISIBLE);

        one.setVisibility(View.VISIBLE);
        two.setVisibility(View.VISIBLE);
        three.setVisibility(View.VISIBLE);
        four.setVisibility(View.VISIBLE);
        five.setVisibility(View.VISIBLE);
        six.setVisibility(View.VISIBLE);
        seven.setVisibility(View.VISIBLE);
        eight.setVisibility(View.VISIBLE);
        clear.setVisibility(View.VISIBLE);
        ans.setVisibility(View.VISIBLE);
        check.setVisibility(View.VISIBLE);

        plus.setVisibility(View.VISIBLE);
        equalto.setVisibility(View.VISIBLE);
        multiplicator.setVisibility(View.VISIBLE);
    }

    public void number(String n, int mode) {


        String n1, n2, n3;

        n1 = num1.getText().toString();
        n2 = num2.getText().toString();
        n3 = num3.getText().toString();

        if ((mode == 1 || mode == 5) && n3.isEmpty())
            num3.setText(n);
        else if ((mode == 2 || mode == 6) && n2.isEmpty())
            num2.setText(n);
        else if (mode == 3 || mode == 7) {
            if (n1.isEmpty())
                num1.setText(n);
            else if (n2.isEmpty())
                num2.setText(n);
        } else {
            if (n1.isEmpty())
                num1.setText(n);
            else if (n2.isEmpty())
                num2.setText(n);
            else if (n3.isEmpty())
                num3.setText(n);
        }
    }

    public void show(int mode) {

        one.setVisibility(View.VISIBLE);
        two.setVisibility(View.VISIBLE);
        three.setVisibility(View.VISIBLE);
        four.setVisibility(View.VISIBLE);
        five.setVisibility(View.VISIBLE);
        six.setVisibility(View.VISIBLE);
        seven.setVisibility(View.VISIBLE);
        eight.setVisibility(View.VISIBLE);
        nine.setVisibility(View.VISIBLE);

        num1.setText("");
        num2.setText("");
        num3.setText("");

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number("1", mode);
                one.setVisibility(View.INVISIBLE);
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number("2", mode);
                two.setVisibility(View.INVISIBLE);
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number("3", mode);
                three.setVisibility(View.INVISIBLE);
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number("4", mode);
                four.setVisibility(View.INVISIBLE);
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number("5", mode);
                five.setVisibility(View.INVISIBLE);
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number("6", mode);
                six.setVisibility(View.INVISIBLE);
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number("7", mode);
                seven.setVisibility(View.INVISIBLE);
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number("8", mode);
                eight.setVisibility(View.INVISIBLE);
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number("9", mode);
                nine.setVisibility(View.INVISIBLE);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mode == 1 || mode == 5)
                    num3.setText(null);
                else if (mode == 2 || mode == 6)
                    num2.setText(null);
                else if (mode == 3 || mode == 7) {
                    num1.setText(null);
                    num2.setText(null);
                } else {
                    num1.setText(null);
                    num2.setText(null);
                    num3.setText(null);
                }

                one.setVisibility(View.VISIBLE);
                two.setVisibility(View.VISIBLE);
                three.setVisibility(View.VISIBLE);
                four.setVisibility(View.VISIBLE);
                five.setVisibility(View.VISIBLE);
                six.setVisibility(View.VISIBLE);
                seven.setVisibility(View.VISIBLE);
                eight.setVisibility(View.VISIBLE);
                nine.setVisibility(View.VISIBLE);
            }
        });

        Random random = new Random();

        int n1, n2, n3, max1 = 9, min1 = 2, max2 = 9, min2 = 1;

        int answer;

        n1 = random.nextInt((max1 - min1) + 1) + min1;

        do {
            n2 = random.nextInt((max1 - min1) + 1) + min1;
        } while (n1 == n2);

        do {
            n3 = random.nextInt((max2 - min2) + 1) + min2;
        } while (n3 == n1 || n3 == n2);

        answer = n1 * n2 + n3;

        ans.setText(Integer.toString(answer));

        if (mode == 1 || mode == 5) {
            num1.setText(Integer.toString(n1));
            num2.setText(Integer.toString(n2));
        } else if (mode == 2 || mode == 6) {
            num1.setText(Integer.toString(n1));
            num3.setText(Integer.toString(n3));
        } else if (mode == 3 || mode == 7) {
            num3.setText(Integer.toString(n3));
        }

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int number1 = Integer.parseInt(num1.getText().toString());
                    int number2 = Integer.parseInt(num2.getText().toString());
                    int number3 = Integer.parseInt(num3.getText().toString());

                    if (number1 * number2 + number3 != answer) {
                        Toast.makeText(getApplicationContext(), "Wrong Answer!!!", Toast.LENGTH_SHORT).show();

                        if (mode == 5 && temp == 9)
                            wrongEasy++;
                        else if (mode == 6 && temp == 9)
                            wrongMed++;
                        else if (mode == 7 && temp == 9)
                            wrongHard++;
                        else if (mode == 8 && temp == 9)
                            wrongHardPlus++;
                        else
                            wrong++;
                        show(mode);
                    } else if (number1 * number2 + number3 == answer) {
                        Toast.makeText(getApplicationContext(), "Correct Answer!!!", Toast.LENGTH_SHORT).show();

                        if (mode == 5 && temp == 9)
                            rightEasy++;
                        else if (mode == 6 && temp == 9)
                            rightMed++;
                        else if (mode == 7 && temp == 9)
                            rightHard++;
                        else if (mode == 8 && temp == 9)
                            rightHardPlus++;
                        else
                            right++;
                        show(mode);
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(OperatorOverloadGameActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                    show(mode);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (temp > 4) {
            yourCountDownTimer.cancel();
            if (pressedTime + 2000 > System.currentTimeMillis()) {
                super.onBackPressed();
                finish();
            } else {
                Toast.makeText(getBaseContext(), "Press back again to Exit! Current Round Progress Will be Discarded!!! ", Toast.LENGTH_SHORT).show();
            }
            pressedTime = System.currentTimeMillis();
        } else
            finish();
    }
}