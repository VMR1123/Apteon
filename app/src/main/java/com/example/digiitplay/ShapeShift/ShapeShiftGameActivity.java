package com.example.digiitplay.ShapeShift;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.digiitplay.R;

import java.util.ArrayList;
import java.util.Collections;

public class ShapeShiftGameActivity extends AppCompatActivity {

    private long pressedTime;
    Button one, two, three, four, check, clear;
    public TextView num1, num2, num3, num4, num5, num6, num7, num8, countDown, timer;
    ImageView iv1, iv2, iv3, iv4;
    ImageView imageView[] = new ImageView[4];
    LinearLayout ll1, ll2, ll3, ll4, ll5;

    public CountDownTimer yourCountDownTimer;
    View line, arrow, arrow2;

    Integer imageArray[] = {R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4};
    ArrayList<Integer> integerlist1 = new ArrayList<>(4);
    ArrayList<Integer> integerlist2 = new ArrayList<>(4);
    int it1, it2, it3, it4, mode, temp;
    int right = 0, wrong = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle intent = getIntent().getExtras();
        mode = intent.getInt("mode");

        if (mode == 1 || mode == 3)
            setContentView(R.layout.activity_normal_shape_shift);
        else if (mode == 2 || mode == 4) {
            setContentView(R.layout.activity_challenging_shape_shift);
            num5 = findViewById(R.id.num5);
            num6 = findViewById(R.id.num6);
            num7 = findViewById(R.id.num7);
            num8 = findViewById(R.id.num8);
            ll5 = findViewById(R.id.ll5);
            arrow2 = findViewById(R.id.imageView10);
        }

        temp = mode;

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        num4 = findViewById(R.id.num4);

        iv1 = findViewById(R.id.imageView5);
        iv2 = findViewById(R.id.imageView6);
        iv3 = findViewById(R.id.imageView7);
        iv4 = findViewById(R.id.imageView8);

//        ImageView imageView[] = {iv1, iv2, iv3, iv4};
        imageView[0] = iv1;
        imageView[1] = iv2;
        imageView[2] = iv3;
        imageView[3] = iv4;

        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);

        check = findViewById(R.id.check);
        clear = findViewById(R.id.clear);

        line = findViewById(R.id.vdivider1);
        arrow = findViewById(R.id.imageView9);

        integerlist1.add(0);
        integerlist1.add(1);
        integerlist1.add(2);
        integerlist1.add(3);

        integerlist2.add(0);
        integerlist2.add(1);
        integerlist2.add(2);
        integerlist2.add(3);

        ll1 = findViewById(R.id.ll1);
        ll2 = findViewById(R.id.ll2);
        ll3 = findViewById(R.id.ll3);
        ll4 = findViewById(R.id.ll4);

        timer = findViewById(R.id.timer);
        countDown = findViewById(R.id.countdown);

        makeINVISIBLE();

        if (mode == 3) {
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

                    modeWiseSelection(mode);
                    yourCountDownTimer = new CountDownTimer(61000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            timer.setText(String.valueOf(millisUntilFinished / 1000));
                        }

                        public void onFinish() {
                            timer.setText(R.string.timeUp);
                            Intent i2 = new Intent(getApplicationContext(), ShapeShiftScoreActivity.class);

                            i2.putExtra("correct", String.valueOf(right));
                            i2.putExtra("incorrect", String.valueOf(wrong));
                            i2.putExtra("mode", String.valueOf(mode));

                            startActivity(i2);
                            finish();
                        }

                    }.start();
                }
            }.start();
        } else if (mode == 4) {
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

                    modeWiseSelection(mode);
                    yourCountDownTimer = new CountDownTimer(121000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            timer.setText(String.valueOf(millisUntilFinished / 1000));
                        }

                        public void onFinish() {
                            timer.setText(R.string.timeUp);
                            Intent i2 = new Intent(getApplicationContext(), ShapeShiftScoreActivity.class);

                            i2.putExtra("correct", String.valueOf(right));
                            i2.putExtra("incorrect", String.valueOf(wrong));
                            i2.putExtra("mode", String.valueOf(mode));

                            startActivity(i2);
                            finish();
                        }

                    }.start();
                }
            }.start();
        } else if (mode < 5) {
            makeVISIBLE();
            timer.setVisibility(View.INVISIBLE);
            modeWiseSelection(mode);
        }
    }

    public void makeINVISIBLE() {
        timer.setVisibility(View.INVISIBLE);
        line.setVisibility(View.INVISIBLE);
        arrow.setVisibility(View.INVISIBLE);
        if (mode == 4) {
            arrow2.setVisibility(View.INVISIBLE);
            ll5.setVisibility(View.INVISIBLE);
        }

        ll1.setVisibility(View.INVISIBLE);
        ll2.setVisibility(View.INVISIBLE);
        ll3.setVisibility(View.INVISIBLE);
        ll4.setVisibility(View.INVISIBLE);

        clear.setVisibility(View.INVISIBLE);
        check.setVisibility(View.INVISIBLE);
    }

    public void makeVISIBLE() {
        countDown.setVisibility(View.INVISIBLE);
        timer.setVisibility(View.VISIBLE);
        line.setVisibility(View.VISIBLE);
        arrow.setVisibility(View.VISIBLE);

        if (mode == 4) {
            arrow2.setVisibility(View.VISIBLE);
            ll5.setVisibility(View.VISIBLE);
        }

        ll1.setVisibility(View.VISIBLE);
        ll2.setVisibility(View.VISIBLE);
        ll3.setVisibility(View.VISIBLE);
        ll4.setVisibility(View.VISIBLE);

        clear.setVisibility(View.VISIBLE);
        check.setVisibility(View.VISIBLE);
    }

    public void modeWiseSelection(int mode) {
        if (mode == 1 || mode == 3) {
            Collections.shuffle(integerlist1);

            //set shuffled images to button[] array
            for (int i = 0; i < 4; i++) {
                imageView[i].setBackgroundResource(imageArray[integerlist1.get(i)]);
            }

            one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    number("1", mode);
                }
            });

            two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    number("2", mode);
                }
            });

            three.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    number("3", mode);
                }
            });

            four.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    number("4", mode);
                }
            });

            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {

                        it1 = Integer.parseInt(num1.getText().toString()) - 1;
                        it2 = Integer.parseInt(num2.getText().toString()) - 1;
                        it3 = Integer.parseInt(num3.getText().toString()) - 1;
                        it4 = Integer.parseInt(num4.getText().toString()) - 1;

                    } catch (Exception e) {
                        Toast.makeText(ShapeShiftGameActivity.this, "Please enter numbers from 1 to 4 only!", Toast.LENGTH_SHORT).show();
                    }
                    int counter = 0;

                    int[] in = {it1, it2, it3, it4};

                    for (int i = 0; i < 4; i++) {
                        if (in[i] != integerlist1.get(i)) {
                            Toast.makeText(ShapeShiftGameActivity.this, "Wrong answer", Toast.LENGTH_SHORT).show();
                            wrong++;
                            break;
                        } else {
                            counter++;
                        }
                    }

                    if (counter == 4) {
                        Toast.makeText(ShapeShiftGameActivity.this, "Correct answer", Toast.LENGTH_SHORT).show();
                        right++;
                    }
                    num1.setText(null);
                    num2.setText(null);
                    num3.setText(null);
                    num4.setText(null);
                    modeWiseSelection(mode);
                }
            });

            clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    num1.setText(null);
                    num2.setText(null);
                    num3.setText(null);
                    num4.setText(null);
                }
            });
        } else if (mode == 2 || mode == 4) {
            Collections.shuffle(integerlist1);

            num1.setText(String.valueOf(integerlist1.get(0) + 1));
            num2.setText(String.valueOf(integerlist1.get(1) + 1));
            num3.setText(String.valueOf(integerlist1.get(2) + 1));
            num4.setText(String.valueOf(integerlist1.get(3) + 1));


            Collections.shuffle(integerlist2);
            //set shuffled images to button[] array
            for (int i = 0; i < 4; i++) {
                imageView[i].setBackgroundResource(imageArray[integerlist1.get(integerlist2.get(i))]);
            }

            one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    number("1", mode);
                }
            });

            two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    number("2", mode);
                }
            });

            three.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    number("3", mode);
                }
            });

            four.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    number("4", mode);
                }
            });

            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {

                        it1 = Integer.parseInt(num5.getText().toString()) - 1;
                        it2 = Integer.parseInt(num6.getText().toString()) - 1;
                        it3 = Integer.parseInt(num7.getText().toString()) - 1;
                        it4 = Integer.parseInt(num8.getText().toString()) - 1;

                    } catch (Exception e) {
                        Toast.makeText(ShapeShiftGameActivity.this, "Please enter numbers from 1 to 4 only!", Toast.LENGTH_SHORT).show();
                    }
                    int counter = 0;

                    int[] in = {it1, it2, it3, it4};

                    for (int i = 0; i < 4; i++) {
                        if (in[i] != integerlist2.get(i)) {
                            Toast.makeText(ShapeShiftGameActivity.this, "Wrong answer", Toast.LENGTH_SHORT).show();
                            wrong++;
                            break;
                        } else {
                            counter++;
                        }
                    }

                    if (counter == 4) {
                        right++;
                        Toast.makeText(ShapeShiftGameActivity.this, "Correct answer", Toast.LENGTH_SHORT).show();
                    }
                    num5.setText(null);
                    num6.setText(null);
                    num7.setText(null);
                    num8.setText(null);
                    modeWiseSelection(mode);
                }
            });

            clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    num5.setText(null);
                    num6.setText(null);
                    num7.setText(null);
                    num8.setText(null);
                }
            });
        }

    }

    public void number(String n, int mode) {

        String n1, n2, n3, n4;

        if (mode == 1 || mode == 3) {
            n1 = num1.getText().toString();
            n2 = num2.getText().toString();
            n3 = num3.getText().toString();
            n4 = num4.getText().toString();
            if (n1.isEmpty())
                num1.setText(n);
            else if (n2.isEmpty())
                num2.setText(n);
            else if (n3.isEmpty())
                num3.setText(n);
            else if (n4.isEmpty())
                num4.setText(n);
        } else {
            n1 = num5.getText().toString();
            n2 = num6.getText().toString();
            n3 = num7.getText().toString();
            n4 = num8.getText().toString();
            if (n1.isEmpty())
                num5.setText(n);
            else if (n2.isEmpty())
                num6.setText(n);
            else if (n3.isEmpty())
                num7.setText(n);
            else if (n4.isEmpty())
                num8.setText(n);
        }
    }

    @Override
    public void onBackPressed() {

        if (temp > 2) {
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