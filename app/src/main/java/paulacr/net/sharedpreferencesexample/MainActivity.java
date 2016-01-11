package paulacr.net.sharedpreferencesexample;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.textoParaSalvar)
    EditText fieldText;
    @Bind(R.id.error)
    TextInputLayout error;
    @Bind(R.id.main_layout)
    View view;
    @Bind(R.id.resultText)
    TextView resultText;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSave) void savePreferences() {
        sharedPreferences = getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(getString(R.string.pref_text), fieldText.getText().toString());
        editor.apply();

        hideKeyboard();
        fieldText.setText("");
        Snackbar.make(view, "Dados salvos com sucesso!!!", Snackbar.LENGTH_LONG).show();
    }

    @OnClick(R.id.btnClean) void clearPreferences() {
        sharedPreferences = getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.remove(getString(R.string.pref_text));
        editor.apply();
        
        hideKeyboard();
        Snackbar.make(view, "Dados salvos com sucesso!!!", Snackbar.LENGTH_LONG).show();
    }

    @OnClick(R.id.btnGetPrefs) void getPreferences() {
        sharedPreferences = getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE);
        String result = sharedPreferences.getString(getString(R.string.pref_text), "");
        resultText.setText("Resultado --> " + result);
    }

    private void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
