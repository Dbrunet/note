package app.note.edit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

import app.note.R;
import app.note.data.db.AppDatabase;
import app.note.data.db.entity.Note;
import app.note.utils.Constants;
import app.note.utils.Util;

public class EditActivity extends AppCompatActivity implements EditContract.View, EditContract.DateListener {

    private EditContract.Presenter mPresenter;

    //xml
    private EditText mNameEditText;
    //xml
    private EditText mDescriptionEditText;
    //xml
    private EditText mColorEditText;
    //xml
    private EditText mDateCreateEditText;
    //xml
    private EditText mTagEditText;

    //xml
    private TextInputLayout mNameTextInputLayout;
    //xml
    private TextInputLayout mDescriptionInputLayout;
    //xml
    private TextInputLayout mTagInputLayout;
    //xml
    private TextInputLayout mDateCreateInputLayout;
    //xml
    private TextInputLayout mColorTextInputLayout;

    //xml
    private FloatingActionButton mFab;

    private Note note;
    private boolean mEditMode = false;

    /**
     * Método que inicializa as views da tela
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //o xml em que estão os componentes da minha interface
        setContentView(R.layout.activity_edit);

        //cria um objeto note
        note = new Note();

        //verifica se é um novo lembrete ou update
        checkMode();

        //recupera a instancia do banco de dados
        AppDatabase db = AppDatabase.getInstance(getApplication());
        //cria o objeto presenter
        mPresenter = new EditPresenter(this, db.noteDao());

        //inicializa as views
        initViews();
    }

    private void checkMode() {
        //verifica se esta chegando algum atributo da tela anterior
        if (getIntent().getExtras() != null) {
            //caso estiver chegando a tela é de editar
            getSupportActionBar().setTitle("Editar Lembrete");
            //pega o id do lembrete que esta sendo passado pela tela anterior
            note.id = getIntent().getLongExtra(Constants.NOTE_ID, 0);
            //modo edição recebe true
            mEditMode = true;

        }else{
            //caso não estiver chegando a tela é de novo cadastro de lembrete
            getSupportActionBar().setTitle("Novo Lembrete");
        }
    }

    /**
     * Método chamado após a inicialização das views
     */
    @Override
    protected void onStart() {
        super.onStart();
        //verifica se a tela é de edição
        if (mEditMode) {
            //caso for edição chama do presenter para buscar o lembrete no banco
            mPresenter.getNoteAndPopulate(note.id);
        }
    }

    /**
     * Inicializa as views da tela, eles se encontram no xml
     *
     */
    private void initViews() {
        //campo de texto editável
        mNameEditText = (EditText) findViewById(R.id.nameEditText);
        //campo de texto editável
        mDescriptionEditText = (EditText) findViewById(R.id.descriptionEditText);
        //campo de texto editável
        mTagEditText = (EditText) findViewById(R.id.tagEditText);
        //campo de texto editável
        mColorEditText = (EditText) findViewById(R.id.tagEditText);
        //campo de texto editável
        mDateCreateEditText = (EditText) findViewById(R.id.dateAlertEditText);

        //container do campo de texto editável
        mNameTextInputLayout = (TextInputLayout) findViewById(R.id.nameTextInputLayout);
        //container do campo de texto editável
        mDescriptionInputLayout = (TextInputLayout) findViewById(R.id.descriptionTextInputLayout);
        //container do campo de texto editável
        mTagInputLayout = (TextInputLayout) findViewById(R.id.tagTextInputLayout);
        //container do campo de texto editável
        mDateCreateInputLayout = (TextInputLayout) findViewById(R.id.dateAlertTextInputLayout);
        //container do campo de texto editável
        mColorTextInputLayout = (TextInputLayout) findViewById(R.id.colorTextInputLayout);

        //evento de clique do campo de data
        mDateCreateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chama a camada de data
                mPresenter.showDateDialog();
            }
        });

        //botão de confirmação de cadastro e para edição
        //inicialização do botão floating
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        //verifica se é cadastro ou edição para trocar o icone
        mFab.setImageResource(mEditMode ? R.drawable.ic_refresh : R.drawable.ic_done);
        //evento de click do botão
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //pega o nome do campo de texto
                note.name = mNameEditText.getText().toString();
                //pega a descrição do campo de texto
                note.description = mDescriptionEditText.getText().toString();
                //pega a color criada randomicamente e caso não for nula
                if (TextUtils.isEmpty(note.color)) note.color = String.valueOf(Util.getRandomColor());
                //pega a tag do campo de texto
                note.tag = mTagEditText.getText().toString();
                //valida os campos antes de salvar no banco
                boolean valid = mPresenter.validate(note);

                //caso não valido o formulário não salva
                if (!valid) return;

                //verifica se é update ou save
                if (mEditMode) {
                    mPresenter.update(note);
                } else {
                    mPresenter.save(note);
                }
            }
        });
    }

    //recebendo a interface que
    @Override
    public void setPresenter(EditContract.Presenter presenter) {
        mPresenter = presenter;
    }

    /**
     * Valida os campos caso não forem preenchidos
     * @param field
     */
    @Override
    public void showErrorMessage(int field) {
        if (field == Constants.FIELD_NAME) {
            mNameTextInputLayout.setError(getString(R.string.invalid_name));
        } else if (field == Constants.FIELD_DESCRIPTION) {
            mDescriptionInputLayout.setError(getString(R.string.invalid_description));
        } else if (field == Constants.FIELD_TAG) {
            mTagInputLayout.setError(getString(R.string.invalid_tag));
        } else if (field == Constants.FIELD_DATE) {
            mDateCreateInputLayout.setError(getString(R.string.invalid_data));
        }
    }

    /**
     * Limpa os campos de erro
     */
    @Override
    public void clearPreErrors() {
        mNameTextInputLayout.setErrorEnabled(false);
        mDescriptionInputLayout.setErrorEnabled(false);
        mTagInputLayout.setErrorEnabled(false);
        mColorTextInputLayout.setErrorEnabled(false);
        mDateCreateInputLayout.setErrorEnabled(false);
    }

    /**
     * Metodo para a chamada da camada de escolha do date
     */
    @Override
    public void openDateDialog() {
        DateDialogFragment fragment = new DateDialogFragment();
        fragment.show(getSupportFragmentManager(), "datePicker");
    }

    //finaliza a tela
    @Override
    public void close() {
        finish();
    }

    //retorna a nota do banco
    @Override
    public void populate(Note note) {
        this.note = note;
        mNameEditText.setText(note.name);
        mDescriptionEditText.setText(note.description);
        mColorEditText.setText(note.color);
        mDateCreateEditText.setText(Util.format(note.dateAlert));
        mTagEditText.setText(note.tag);
    }

    //recebe o date escolhido da camada do datePicker
    @Override
    public void setSelectedDate(Date date) {
        note.dateAlert = date;
        //formada a data e insere no campo de data
        mDateCreateEditText.setText(Util.format(date));
    }
}
