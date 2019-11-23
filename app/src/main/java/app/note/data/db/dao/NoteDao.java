package app.note.data.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


import app.note.data.db.entity.Note;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Classe Dao da entidade Note
 */
@Dao
public interface NoteDao {

    //retorna todas os lembretes ordenado pelo nome
    @Query("SELECT * FROM tb_note ORDER BY name ASC")
    LiveData<List<Note>> findAllNotes();

    //retorna todos os lembretes
    @Query("SELECT * FROM tb_note")
    List<Note> getAllChannels();

    //retorna um lembrete especifico pelo id: String
    @Query("SELECT * FROM tb_note WHERE id=:id")
    Note findNoteById(String id);

    //retorna um lembrete especifico pelo id: long
    @Query("SELECT * FROM tb_note WHERE id=:id")
    Note findNote(long id);

    //insere um lembrete, ignora conflitos
    @Insert(onConflict = IGNORE)
    long insertNote(Note note);

    //insere lembretes.
    @Insert
    void insertNotes(Note... note);

    //atualiza um lembrete
    @Update
    int updateNote(Note note);

    //atualiza varios lembretes
    @Update
    void updateNote(List<Note> notes);

    //remove um lembrete
    @Delete
    void deleteNote(Note note);

    //remove todos os lembretes
    @Query("DELETE FROM tb_note")
    void deleteAll();
}
