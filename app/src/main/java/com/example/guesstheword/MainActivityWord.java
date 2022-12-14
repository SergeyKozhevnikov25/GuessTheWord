package com.example.guesstheword;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class MainActivityWord extends AppCompatActivity {

    private final ArrayList<Word> gameArrayWord = new ArrayList<>();
    private TextView tvWord;
    private String answerWord;
    private TextView tvCountDownWord;
    private int rightAnswerCountWord;
    private final int[] buttonArrayWord = new int[]{R.id.var1, R.id.var2, R.id.var3, R.id.var4};
    private Dialog dialogWord;
    private Handler handlerWord;
    private Runnable runnableWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_word);
        tvWord = findViewById(R.id.tv_word);
        tvCountDownWord = findViewById(R.id.tv_count_down_word);
        handlerWord = new Handler();
        runnableWord = this::setTaskWord;
        startGameWord();
    }

    private void startGameWord() {
        fillArrayWord();
        rightAnswerCountWord = 0;
        Collections.shuffle(gameArrayWord);
        final int[] time = {60};
        CountDownTimer countDownTimerWord = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time[0]--;
                tvCountDownWord.setText("Осталось времени: " + time[0] + " с");
            }

            @Override
            public void onFinish() {
                endGameWord();
            }
        };
        countDownTimerWord.start();
        setTaskWord();
    }

    private void setTaskWord() {
        if (gameArrayWord.size() < 4) {
            endGameWord();
        }
        ArrayList<String> answers = new ArrayList<>();
        setEnabledButtonWord(true);
        for (int value : buttonArrayWord) {
            TextView tv = findViewById(value);
            tv.setBackgroundResource(R.drawable.button_word);
        }
        Random randomWord = new Random();
        Collections.shuffle(gameArrayWord);
        int rndWord = randomWord.nextInt(gameArrayWord.size());
        tvWord.setText(gameArrayWord.get(rndWord).getWord());
        for (int i = 0; i < buttonArrayWord.length; i++) {
            TextView tv = findViewById(buttonArrayWord[i]);
            answers.add(gameArrayWord.get(i).getName());
            tv.setText(gameArrayWord.get(i).getName());
        }
        if (!answers.contains(gameArrayWord.get(rndWord).getName())) {
            TextView tv = findViewById(buttonArrayWord[randomWord.nextInt(4)]);
            tv.setText(gameArrayWord.get(rndWord).getName());
        }
        answerWord = gameArrayWord.get(rndWord).getName();
    }

    private void endGameWord() {
        setEnabledButtonWord(false);
        gameArrayWord.clear();
        dialogWord = new Dialog(this);
        dialogWord.setContentView(R.layout.dialog_end_word);
        TextView scoreWord = dialogWord.findViewById(R.id.tv_score_word);
        scoreWord.setText(String.format("Ваш счёт: %d", rightAnswerCountWord));
        dialogWord.show();
    }


    private void setEnabledButtonWord(boolean b) {
        for (int value : buttonArrayWord) {
            TextView tv = findViewById(value);
            tv.setEnabled(b);
        }
    }

    public void onClickRestartWord(View view) {
        dialogWord.dismiss();
        startGameWord();
    }

    public void onClickExitWord(View view) {
        dialogWord.dismiss();
        finish();
    }

    public void onClickAnswerWord(View view) {
        TextView tv = (TextView) view;
        if (tv.getText().toString().equals(answerWord)) {
            setEnabledButtonWord(false);
            tv.setBackgroundResource(R.drawable.button_true_green_word);
            rightAnswerCountWord++;
            Iterator<Word> iterator = gameArrayWord.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getName().equals(answerWord)) {
                    iterator.remove();
                }
            }
        } else {
            for (int value : buttonArrayWord) {
                TextView textView = findViewById(value);
                if (textView.getText().toString().equals(answerWord)) {
                    textView.setBackgroundResource(R.drawable.button_true_green_word);
                }
            }
            setEnabledButtonWord(false);
            tv.setBackgroundResource(R.drawable.button_false_red_word);
        }
        handlerWord.postDelayed(runnableWord, 700);
    }

    private void fillArrayWord() {
        gameArrayWord.add(new Word("abolish", "Отменять"));
        gameArrayWord.add(new Word("addiction", "зависимость"));
        gameArrayWord.add(new Word("amateur", "любитель"));
        gameArrayWord.add(new Word("ambassador", "посол"));
        gameArrayWord.add(new Word("anger", "Злость"));
        gameArrayWord.add(new Word("approve", "одобрять"));
        gameArrayWord.add(new Word("apron", "фартук"));
        gameArrayWord.add(new Word("arrange", "организовывать"));
        gameArrayWord.add(new Word("arrogant", "высокомерный"));
        gameArrayWord.add(new Word("boast", "Хвастаться"));
        gameArrayWord.add(new Word("bodyguard", "телохранитель"));
        gameArrayWord.add(new Word("canteen", "столовая"));
        gameArrayWord.add(new Word("celebrity", "знаменитость"));
        gameArrayWord.add(new Word("childhood", "детство"));
        gameArrayWord.add(new Word("civilian", "штатский"));
        gameArrayWord.add(new Word("collapse", "разрушаться"));
        gameArrayWord.add(new Word("commission", "комиссия"));
        gameArrayWord.add(new Word("confidence", "уверенность"));
        gameArrayWord.add(new Word("contemptuous", "презрительный"));
        gameArrayWord.add(new Word("convenient", "удобный"));
        gameArrayWord.add(new Word("correspondence", "переписка"));
        gameArrayWord.add(new Word("courage", "смелость"));
        gameArrayWord.add(new Word("crawl", "ползать"));
        gameArrayWord.add(new Word("creative", "творческий"));
        gameArrayWord.add(new Word("dedication", "верность"));
        gameArrayWord.add(new Word("deliver", "доставлять"));
        gameArrayWord.add(new Word("depth", "глубина"));
        gameArrayWord.add(new Word("descend", "спускаться"));
        gameArrayWord.add(new Word("destination", "назначение"));
        gameArrayWord.add(new Word("deteriorate", "ухудшать"));
        gameArrayWord.add(new Word("disappointment", "разочарование"));
        gameArrayWord.add(new Word("dismiss", "отпускать"));
        gameArrayWord.add(new Word("dissolve", "разрушать"));
        gameArrayWord.add(new Word("distribute", "распространять"));
        gameArrayWord.add(new Word("district", "район"));
        gameArrayWord.add(new Word("elaborate", "разрабатывать"));
        gameArrayWord.add(new Word("embarrassment", "затруднение"));
        gameArrayWord.add(new Word("entourage", "окружение"));
        gameArrayWord.add(new Word("evidence", "доказательство"));
        gameArrayWord.add(new Word("extinction", "вымирание"));
        gameArrayWord.add(new Word("famine", "голод"));
        gameArrayWord.add(new Word("flood", "потоп"));
        gameArrayWord.add(new Word("generosity", "щедрость"));
        gameArrayWord.add(new Word("gluttony", "обжорство"));
        gameArrayWord.add(new Word("hiccup", "икота"));
        gameArrayWord.add(new Word("honesty", "честность"));
        gameArrayWord.add(new Word("household", "быт"));
        gameArrayWord.add(new Word("humanity", "человечество"));
        gameArrayWord.add(new Word("humiliate", "унижать"));
        gameArrayWord.add(new Word("interpret", "толковать"));
        gameArrayWord.add(new Word("investigate", "исследовать"));
        gameArrayWord.add(new Word("justice", "справедливость"));
        gameArrayWord.add(new Word("kindness", "доброта"));
        gameArrayWord.add(new Word("knowledge", "знание"));
        gameArrayWord.add(new Word("landlord", "землевладелец"));
        gameArrayWord.add(new Word("liberty", "свобода"));
        gameArrayWord.add(new Word("maintain", "поддерживать"));
        gameArrayWord.add(new Word("mature", "зрелый"));
        gameArrayWord.add(new Word("mirror", "зеркало"));
        gameArrayWord.add(new Word("naughty", "непослушный"));
        gameArrayWord.add(new Word("patience", "терпение"));
        gameArrayWord.add(new Word("persuade", "убеждать"));
        gameArrayWord.add(new Word("petrol", "бензин"));
        gameArrayWord.add(new Word("pleasure", "удовольствие"));
        gameArrayWord.add(new Word("prejudice", "предубеждение"));
        gameArrayWord.add(new Word("prescription", "рекомендация"));
        gameArrayWord.add(new Word("profit", "выгода"));
        gameArrayWord.add(new Word("promotion", "продвижение"));
        gameArrayWord.add(new Word("prosecutor", "прокурор"));
        gameArrayWord.add(new Word("quarrel", "ссора"));
        gameArrayWord.add(new Word("referee", "судья"));
        gameArrayWord.add(new Word("reference book", "справочник"));
        gameArrayWord.add(new Word("rehearsal", "репетиция"));
        gameArrayWord.add(new Word("remarkable", "значительный"));
        gameArrayWord.add(new Word("resentment", "негодование"));
        gameArrayWord.add(new Word("ruthless", "Безжалостный"));
        gameArrayWord.add(new Word("satchel", "сумка"));
        gameArrayWord.add(new Word("spokesman", "представитель"));
        gameArrayWord.add(new Word("squeeze", "Сжимать"));
        gameArrayWord.add(new Word("stationary", "неизменный"));
        gameArrayWord.add(new Word("sufficient", "достаточный"));
        gameArrayWord.add(new Word("superstition", "суеверие"));
        gameArrayWord.add(new Word("surgeon", "хирург"));
        gameArrayWord.add(new Word("surveyor", "землемер"));
        gameArrayWord.add(new Word("suspect", "подозревать"));
        gameArrayWord.add(new Word("vain", "тщеславный"));
        gameArrayWord.add(new Word("valuable", "ценный"));
        gameArrayWord.add(new Word("whistle", "свистеть"));
        gameArrayWord.add(new Word("withdraw", "извлекать"));
    }
}