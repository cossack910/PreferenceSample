package local.hal.st42.android.preferencesample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "PSPrefsFile";

    private static final int FONT_TYPE_DEFAULT = 0;

    private static final int FONT_TYPE_SERIF = 1;

    private static final int FONT_TYPE_SANS_SERIF = 2;

    private static final int FONT_TYPE_MONOSPACE = 3;

    private Typeface _fontType;

    private int _fontStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        _fontStyle = settings.getInt("fontStyle", Typeface.NORMAL);
        int fontTypeCode = settings.getInt("fontType", FONT_TYPE_DEFAULT);
        _fontType = fontTypeInt2FontType(fontTypeCode);
        TextView tvSpeech = findViewById(R.id.tvSpeech);
        tvSpeech.setTypeface(_fontType, _fontStyle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.menuFonttypeSerief:
                editor.putInt("fontType", FONT_TYPE_SERIF);
                _fontType = Typeface.SERIF;
                break;
            case R.id.menuFonttypeSanserief:
                editor.putInt("fontType", FONT_TYPE_SANS_SERIF);
                _fontType = Typeface.SANS_SERIF;
                break;
            case R.id.menuFonttypeMonospace:
                editor.putInt("fontType", FONT_TYPE_MONOSPACE);
                _fontType = Typeface.MONOSPACE;
                break;
            case R.id.menuFontstyleNormal:
                editor.putInt("fontStyle", Typeface.NORMAL);
                _fontStyle = Typeface.NORMAL;
                break;
            case R.id.menuFontstyleItalic:
                editor.putInt("fontStyle", Typeface.ITALIC);
                _fontStyle = Typeface.ITALIC;
                break;
            case R.id.menuFontstyleBold:
                editor.putInt("fontStyle", Typeface.BOLD);
                _fontStyle = Typeface.BOLD;
                break;
            case R.id.menuFontstyleBolditalic:
                editor.putInt("fontStyle", Typeface.BOLD_ITALIC);
                _fontStyle = Typeface.BOLD_ITALIC;
                break;
            case R.id.menuReset:
                _fontType = Typeface.DEFAULT;
                editor.putInt("fontType", FONT_TYPE_DEFAULT);
                _fontStyle = Typeface.NORMAL;
                editor.putInt("fontStyle", Typeface.NORMAL);
                break;
        }
        editor.commit();

        TextView tvSpeech = findViewById(R.id.tvSpeech);
        tvSpeech.setTypeface(_fontType, _fontStyle);
        return super.onOptionsItemSelected(item);
    }

    private Typeface fontTypeInt2FontType(int fontTypeInt) {
        Typeface fontType = Typeface.DEFAULT;
        switch(fontTypeInt) {
            case FONT_TYPE_SERIF:
                fontType = Typeface.SERIF;
                break;
            case FONT_TYPE_SANS_SERIF:
                fontType = Typeface.SANS_SERIF;
                break;
            case FONT_TYPE_MONOSPACE:
                fontType = Typeface.MONOSPACE;
                break;
        }
        return fontType;
    }
}
