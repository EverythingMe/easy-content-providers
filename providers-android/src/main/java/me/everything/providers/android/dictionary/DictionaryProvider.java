package me.everything.providers.android.dictionary;

import android.content.Context;

import me.everything.providers.core.AbstractProvider;
import me.everything.providers.core.Data;

/**
 * Created by sromku
 */
public class DictionaryProvider extends AbstractProvider {

    public DictionaryProvider(Context context) {
        super(context);
    }

    public Data<Word> getWords() {
        Data<Word> words = getContentTableData(Word.uri, Word.class);
        return words;
    }
}
