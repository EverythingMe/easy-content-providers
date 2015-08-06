package me.everything.providers.android.calllog;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import me.everything.providers.core.AbstractProvider;
import me.everything.providers.core.Data;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class CallsProvider extends AbstractProvider {

	public CallsProvider(Context context) {
		super(context);
	}

	/**
	 * Get all calls.
	 * 
	 * @return List of calls
	 */
	public Data<Call> getCalls() {
        Data<Call> calls = getContentTableData(Call.uri, Call.class);
		return calls;
	}

}
