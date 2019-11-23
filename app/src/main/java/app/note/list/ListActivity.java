package app.note.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import app.note.R;
import app.note.data.db.AppDatabase;
import app.note.data.db.entity.Note;
import app.note.edit.EditActivity;
import app.note.utils.Constants;

public class ListActivity extends AppCompatActivity implements ListContract.View,
        ListContract.OnItemClickListener, ListContract.DeleteListener {

    private ListContract.Presenter mPresenter;
    private NoteAdapter mNoteAdapter;

    private TextView mEmptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addNewNote();
            }
        });

        mEmptyTextView = (TextView) findViewById(R.id.emptyTextView);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mNoteAdapter = new NoteAdapter(this);
        recyclerView.setAdapter(mNoteAdapter);

        AppDatabase db = AppDatabase.getInstance(getApplication());
        mPresenter = new ListPresenter(this, db.noteDao());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.populateNote();
    }

    @Override
    public void showAddNote() {
        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);
    }

    @Override
    public void setNotes(List<Note> notes) {
        mEmptyTextView.setVisibility(View.GONE);
        mNoteAdapter.setValues(notes);
    }

    @Override
    public void showEditScreen(long id) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra(Constants.NOTE_ID, id);
        startActivity(intent);
    }

    @Override
    public void showDeleteConfirmDialog(Note note) {
        DeleteConfirmFragment fragment = new DeleteConfirmFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.NOTE_ID, note.id);
        fragment.setArguments(bundle);
        fragment.show(getSupportFragmentManager(), "confirmDialog");
    }

    @Override
    public void showEmptyMessage() {
        mEmptyTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPresenter(ListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void clickItem(Note note) {
        mPresenter.openEditScreen(note);
    }

    @Override
    public void clickLongItem(Note note) {
        mPresenter.openConfirmDeleteDialog(note);
    }

    @Override
    public void setConfirm(boolean confirm, long noteId) {
        if (confirm) {
            mPresenter.delete(noteId);
        }
    }
}
