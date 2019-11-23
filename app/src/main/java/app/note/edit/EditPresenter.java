package app.note.edit;

import app.note.data.db.dao.NoteDao;
import app.note.data.db.entity.Note;
import app.note.utils.Constants;
import app.note.utils.Util;

public class EditPresenter implements EditContract.Presenter {

    private final EditContract.View view;
    private final NoteDao noteDao;

    public EditPresenter(EditContract.View view, NoteDao noteDao) {
        this.view = view;
        this.view.setPresenter(this);
        this.noteDao = noteDao;
    }

    @Override
    public void start() {

    }

    @Override
    public void save(Note note) {
        long ids = this.noteDao.insertNote(note);
        view.close();
    }

    @Override
    public boolean validate(Note note) {
        view.clearPreErrors();
        if (note.name.isEmpty() || !Util.isValidName(note.name)) {
            view.showErrorMessage(Constants.FIELD_NAME);
            return false;
        }
        if (note.description.isEmpty()) {
            view.showErrorMessage(Constants.FIELD_DESCRIPTION);
            return false;
        }
        if (note.tag.isEmpty()) {
            view.showErrorMessage(Constants.FIELD_TAG);
            return false;
        }
        if (note.color.isEmpty()) {
            view.showErrorMessage(Constants.FIELD_COLOR);
            return false;
        }
        if (note.dateAlert == null) {
            view.showErrorMessage(Constants.FIELD_DATE);
            return false;
        }

        return true;
    }

    @Override
    public void showDateDialog() {
        view.openDateDialog();
    }

    @Override
    public void getNoteAndPopulate(long id) {
        Note note = noteDao.findNote(id);
        if (note != null) {
            view.populate(note);
        }
    }

    @Override
    public void update(Note note) {
        int ids = this.noteDao.updateNote(note);
        view.close();
    }
}
