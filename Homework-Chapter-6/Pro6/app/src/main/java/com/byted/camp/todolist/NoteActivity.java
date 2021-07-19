package com.byted.camp.todolist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.byted.camp.todolist.beans.Priority;
import com.byted.camp.todolist.beans.State;
import com.byted.camp.todolist.db.TodoContract.TodoNote;
import com.byted.camp.todolist.db.TodoDbHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class NoteActivity extends AppCompatActivity {

    private static final String KEY_DRAFT = "note_draft";

    private EditText editText;
    private Button addBtn;
    private RadioGroup radioGroup;
    private AppCompatRadioButton lowRadio;

    private TodoDbHelper dbHelper;
    private SQLiteDatabase database;

    // 读写文件方式存储草稿
    private String dFileName = null;
    private String dContent = "";
    private boolean dflag = true;

    // SharedPreferences方式存储草稿
    // private Context context = this;
    // private SharedPreferences sp = context.getSharedPreferences("usersettings", Context.MODE_PRIVATE);
    // private SharedPreferences.Editor editor = sp.edit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        setTitle(R.string.take_a_note);

        dbHelper = new TodoDbHelper(this);
        database = dbHelper.getWritableDatabase();

        dFileName =  getFilesDir().getAbsolutePath() + File.separator + "draft.txt";
        int ld = loadDraft();

        editText = findViewById(R.id.edit_text);
        editText.setFocusable(true);
        editText.requestFocus();
        if (ld == 1) editText.setText(dContent);

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.showSoftInput(editText, 0);
        }
        radioGroup = findViewById(R.id.radio_group);
        lowRadio = findViewById(R.id.btn_low);
        lowRadio.setChecked(true);

        // SP方式存储草稿
        // editText.setText(sp.getString("content", ""));

        addBtn = findViewById(R.id.btn_add);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence content = editText.getText();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(NoteActivity.this,
                            "No content to add", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean succeed = saveNote2Database(content.toString().trim(),
                        getSelectedPriority());
                if (succeed) {
                    Toast.makeText(NoteActivity.this,
                            "Note added", Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_OK);
                } else {
                    Toast.makeText(NoteActivity.this,
                            "Error", Toast.LENGTH_SHORT).show();
                }
                editText.setText("");
                dflag = false;
                finish();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        storeDraft(editText.getText().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
        database = null;
        dbHelper.close();
        dbHelper = null;
        // editor.putString("content", editText.getText().toString());
        storeDraft(editText.getText().toString());
        if (dflag) Toast.makeText(NoteActivity.this,"store draft",Toast.LENGTH_SHORT).show();
    }

    private boolean saveNote2Database(String content, Priority priority) {
        // TODO: 2021/7/19 8. 这里插入数据库
        if (database == null || TextUtils.isEmpty(content)) {
            return false;
        }
        ContentValues values = new ContentValues();
        values.put(TodoNote.CONTENT, content);
        values.put(TodoNote.STATE, State.TODO.intValue);
        values.put(TodoNote.DATE, System.currentTimeMillis());
        values.put(TodoNote.PRIORITY, priority.intValue);
        long rowId = database.insert(TodoNote.TABLE_NAME, null, values);
        return rowId != -1;
    }

    private Priority getSelectedPriority() {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.btn_high:
                return Priority.High;
            case R.id.btn_medium:
                return Priority.Medium;
            default:
                return Priority.Low;
        }
    }

    // 读写文件方式的存储草稿，每次发表后清除
    private int loadDraft(){
        int flag;
        File file = new File(dFileName);
        if (!file.exists()) {
            return 0;
        }
        try {
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            final StringBuffer sb = new StringBuffer();
            while (inputStream.read(bytes) != -1) {
                sb.append(new String(bytes));
            }
            dContent = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }

    private int storeDraft(String tem){

        File file = new File(dFileName);
        int flag;
        if (!file.exists()) {
            try {
                boolean isSuccess = file.createNewFile();
                if (!isSuccess) {
                    throw new IOException("create exception error.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(tem.getBytes());
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }

}
