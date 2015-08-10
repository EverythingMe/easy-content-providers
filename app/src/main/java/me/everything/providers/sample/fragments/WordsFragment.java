package me.everything.providers.sample.fragments;

import android.widget.TextView;

import me.everything.providers.android.dictionary.DictionaryProvider;
import me.everything.providers.android.dictionary.Word;
import me.everything.providers.core.Data;
import me.everything.providers.sample.base.GetCursorTask;
import me.everything.providers.sample.base.RecycleViewCursorFragment;

/**
 * Created by sromku
 */
public class WordsFragment extends RecycleViewCursorFragment<Word> {

    @Override
    protected String getTitle() {
        return "Words";
    }

    @Override
    protected void bindEntity(Word word, TextView title, TextView details) {
        title.setText(word.word);
        details.setText(word.locale);
    }

    @Override
    protected GetCursorTask.DataFetcher<Word> getFetcher() {
        return new GetCursorTask.DataFetcher<Word>() {
            @Override
            public Data<Word> getData() {
                DictionaryProvider provider = new DictionaryProvider(getApplicationContext());
                return provider.getWords();
            }
        };
    }


}
