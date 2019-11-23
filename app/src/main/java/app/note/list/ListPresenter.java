package app.note.list;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import java.util.List;

import app.note.data.db.dao.NoteDao;
import app.note.data.db.entity.Note;


/**
 * Classe de controle dos dados para a View
 */
public class ListPresenter implements ListContract.Presenter {

    private final ListContract.View mView;
    private final NoteDao noteDao;

    public ListPresenter(ListContract.View view, NoteDao noteDao) {
        this.mView = view;
        this.mView.setPresenter(this);
        this.noteDao = noteDao;
    }

    @Override
    public void start() {
    }

    //chama o método de inserir um novo lembrete (-> EditActivity)
    @Override
    public void addNewNote() {
        mView.showAddNote();
    }

    //metodo que contem um observer, que aguarda ser chamado para retornar os objetos do banco.
    @Override
    public void populateNote() {
        noteDao.findAllNotes().observeForever(new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                //envia os objetos para a view (ListActivity)
                mView.setNotes(notes);
                //caso não tenha nada no banco, chama um metodo na view para mostrar uma mensagem
                //de nada listado na tela
                if (notes == null || notes.size() < 1) {
                    mView.showEmptyMessage();
                }
            }
        });
    }

    //metodo que envia para a view um lembrete
    @Override
    public void openEditScreen(Note note) {
        mView.showEditScreen(note.id);
    }

    //metodo que chama na view o metodo de confirmação de remoção do objeto
    @Override
    public void openConfirmDeleteDialog(Note note) {
        mView.showDeleteConfirmDialog(note);
    }

    //remove uma nota
    @Override
    public void delete(long noteId) {
        Note note = noteDao.findNote(noteId);
        noteDao.deleteNote(note);
    }
}
