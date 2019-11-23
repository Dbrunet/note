package app.note.data.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

import app.note.data.db.converter.DateConverter;
import app.note.utils.Util;

/**
 * Entidade Note
 */

//para dar o nome a tabela
@Entity(tableName = "tb_note")
//para dizer que existe um conversor de data
@TypeConverters(DateConverter.class)
public class Note {

    //para o identificador da entidade ter um autoIncremento
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String description;
    public String color;
    public String tag;
    public Date dateAlert;

    //construtor será ignorado pelo banco
    @Ignore
    public Note() {
        this.name = "";
        this.description = "";
        this.dateAlert = null;
        this.color = "";
        this.tag = "";
    }

    public Note(String name, String description, String color, String tag, Date dateAlert) {
        this.name = name;
        this.description = description;
        this.color = color;
        this.tag = tag;
        this.dateAlert = dateAlert;
    }

    @Ignore
    public Note(long id, String name, String description, String color, String tag, Date dateAlert) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.color = color;
        this.tag = tag;
        this.dateAlert = dateAlert;
    }

    //popula o banco de dados inicialmente com 100 linhas no banco
    public synchronized static Note[] populateData() {

        return new Note[]{

                new Note("Local 1", "Descrição 1", String.valueOf(Util.getRandomColor()), "tag 1", new Date()),
                new Note("Local 2", "Descrição 2", String.valueOf(Util.getRandomColor()), "tag 2", new Date()),
                new Note("Local 3", "Descrição 3", String.valueOf(Util.getRandomColor()), "tag 3", new Date()),
                new Note("Local 4", "Descrição 4", String.valueOf(Util.getRandomColor()), "tag 4", new Date()),
                new Note("Local 5", "Descrição 5", String.valueOf(Util.getRandomColor()), "tag 5", new Date()),
                new Note("Local 6", "Descrição 6", String.valueOf(Util.getRandomColor()), "tag 6", new Date()),
                new Note("Local 7", "Descrição 7", String.valueOf(Util.getRandomColor()), "tag 7", new Date()),
                new Note("Local 8", "Descrição 8", String.valueOf(Util.getRandomColor()), "tag 8", new Date()),
                new Note("Local 9", "Descrição 9", String.valueOf(Util.getRandomColor()), "tag 9", new Date()),
                new Note("Local 10", "Descrição 10", String.valueOf(Util.getRandomColor()), "tag 10", new Date()),
                new Note("Local 11", "Descrição 11", String.valueOf(Util.getRandomColor()), "tag 11", new Date()),
                new Note("Local 12", "Descrição 12", String.valueOf(Util.getRandomColor()), "tag 12", new Date()),
                new Note("Local 13", "Descrição 13", String.valueOf(Util.getRandomColor()), "tag 13", new Date()),
                new Note("Local 14", "Descrição 14", String.valueOf(Util.getRandomColor()), "tag 14", new Date()),
                new Note("Local 15", "Descrição 15", String.valueOf(Util.getRandomColor()), "tag 15", new Date()),
                new Note("Local 16", "Descrição 16", String.valueOf(Util.getRandomColor()), "tag 16", new Date()),
                new Note("Local 17", "Descrição 17", String.valueOf(Util.getRandomColor()), "tag 17", new Date()),
                new Note("Local 18", "Descrição 18", String.valueOf(Util.getRandomColor()), "tag 18", new Date()),
                new Note("Local 19", "Descrição 19", String.valueOf(Util.getRandomColor()), "tag 19", new Date()),
                new Note("Local 20", "Descrição 20", String.valueOf(Util.getRandomColor()), "tag 20", new Date()),
                new Note("Local 21", "Descrição 21", String.valueOf(Util.getRandomColor()), "tag 21", new Date()),
                new Note("Local 22", "Descrição 22", String.valueOf(Util.getRandomColor()), "tag 22", new Date()),
                new Note("Local 23", "Descrição 23", String.valueOf(Util.getRandomColor()), "tag 23", new Date()),
                new Note("Local 24", "Descrição 24", String.valueOf(Util.getRandomColor()), "tag 24", new Date()),
                new Note("Local 25", "Descrição 25", String.valueOf(Util.getRandomColor()), "tag 25", new Date()),
                new Note("Local 26", "Descrição 26", String.valueOf(Util.getRandomColor()), "tag 26", new Date()),
                new Note("Local 27", "Descrição 27", String.valueOf(Util.getRandomColor()), "tag 27", new Date()),
                new Note("Local 28", "Descrição 28", String.valueOf(Util.getRandomColor()), "tag 28", new Date()),
                new Note("Local 29", "Descrição 29", String.valueOf(Util.getRandomColor()), "tag 29", new Date()),
                new Note("Local 30", "Descrição 30", String.valueOf(Util.getRandomColor()), "tag 30", new Date()),
                new Note("Local 31", "Descrição 31", String.valueOf(Util.getRandomColor()), "tag 31", new Date()),
                new Note("Local 32", "Descrição 32", String.valueOf(Util.getRandomColor()), "tag 32", new Date()),
                new Note("Local 33", "Descrição 33", String.valueOf(Util.getRandomColor()), "tag 33", new Date()),
                new Note("Local 34", "Descrição 34", String.valueOf(Util.getRandomColor()), "tag 34", new Date()),
                new Note("Local 35", "Descrição 35", String.valueOf(Util.getRandomColor()), "tag 35", new Date()),
                new Note("Local 36", "Descrição 36", String.valueOf(Util.getRandomColor()), "tag 36", new Date()),
                new Note("Local 37", "Descrição 37", String.valueOf(Util.getRandomColor()), "tag 37", new Date()),
                new Note("Local 38", "Descrição 38", String.valueOf(Util.getRandomColor()), "tag 38", new Date()),
                new Note("Local 39", "Descrição 39", String.valueOf(Util.getRandomColor()), "tag 39", new Date()),
                new Note("Local 40", "Descrição 40", String.valueOf(Util.getRandomColor()), "tag 40", new Date()),
                new Note("Local 41", "Descrição 41", String.valueOf(Util.getRandomColor()), "tag 41", new Date()),
                new Note("Local 42", "Descrição 42", String.valueOf(Util.getRandomColor()), "tag 42", new Date()),
                new Note("Local 43", "Descrição 43", String.valueOf(Util.getRandomColor()), "tag 43", new Date()),
                new Note("Local 44", "Descrição 44", String.valueOf(Util.getRandomColor()), "tag 44", new Date()),
                new Note("Local 45", "Descrição 45", String.valueOf(Util.getRandomColor()), "tag 45", new Date()),
                new Note("Local 46", "Descrição 46", String.valueOf(Util.getRandomColor()), "tag 46", new Date()),
                new Note("Local 47", "Descrição 47", String.valueOf(Util.getRandomColor()), "tag 47", new Date()),
                new Note("Local 48", "Descrição 48", String.valueOf(Util.getRandomColor()), "tag 48", new Date()),
                new Note("Local 49", "Descrição 49", String.valueOf(Util.getRandomColor()), "tag 49", new Date()),
                new Note("Local 50", "Descrição 50", String.valueOf(Util.getRandomColor()), "tag 50", new Date()),
                new Note("Local 51", "Descrição 51", String.valueOf(Util.getRandomColor()), "tag 51", new Date()),
                new Note("Local 52", "Descrição 52", String.valueOf(Util.getRandomColor()), "tag 52", new Date()),
                new Note("Local 53", "Descrição 53", String.valueOf(Util.getRandomColor()), "tag 53", new Date()),
                new Note("Local 54", "Descrição 54", String.valueOf(Util.getRandomColor()), "tag 54", new Date()),
                new Note("Local 55", "Descrição 55", String.valueOf(Util.getRandomColor()), "tag 55", new Date()),
                new Note("Local 56", "Descrição 56", String.valueOf(Util.getRandomColor()), "tag 56", new Date()),
                new Note("Local 57", "Descrição 57", String.valueOf(Util.getRandomColor()), "tag 57", new Date()),
                new Note("Local 58", "Descrição 58", String.valueOf(Util.getRandomColor()), "tag 58", new Date()),
                new Note("Local 59", "Descrição 59", String.valueOf(Util.getRandomColor()), "tag 59", new Date()),
                new Note("Local 60", "Descrição 60", String.valueOf(Util.getRandomColor()), "tag 60", new Date()),
                new Note("Local 61", "Descrição 61", String.valueOf(Util.getRandomColor()), "tag 61", new Date()),
                new Note("Local 62", "Descrição 62", String.valueOf(Util.getRandomColor()), "tag 62", new Date()),
                new Note("Local 63", "Descrição 63", String.valueOf(Util.getRandomColor()), "tag 63", new Date()),
                new Note("Local 64", "Descrição 64", String.valueOf(Util.getRandomColor()), "tag 64", new Date()),
                new Note("Local 65", "Descrição 65", String.valueOf(Util.getRandomColor()), "tag 65", new Date()),
                new Note("Local 66", "Descrição 66", String.valueOf(Util.getRandomColor()), "tag 66", new Date()),
                new Note("Local 67", "Descrição 67", String.valueOf(Util.getRandomColor()), "tag 67", new Date()),
                new Note("Local 68", "Descrição 68", String.valueOf(Util.getRandomColor()), "tag 68", new Date()),
                new Note("Local 69", "Descrição 69", String.valueOf(Util.getRandomColor()), "tag 69", new Date()),
                new Note("Local 70", "Descrição 70", String.valueOf(Util.getRandomColor()), "tag 70", new Date()),
                new Note("Local 71", "Descrição 71", String.valueOf(Util.getRandomColor()), "tag 71", new Date()),
                new Note("Local 72", "Descrição 72", String.valueOf(Util.getRandomColor()), "tag 72", new Date()),
                new Note("Local 73", "Descrição 73", String.valueOf(Util.getRandomColor()), "tag 73", new Date()),
                new Note("Local 74", "Descrição 74", String.valueOf(Util.getRandomColor()), "tag 74", new Date()),
                new Note("Local 75", "Descrição 75", String.valueOf(Util.getRandomColor()), "tag 75", new Date()),
                new Note("Local 76", "Descrição 76", String.valueOf(Util.getRandomColor()), "tag 76", new Date()),
                new Note("Local 77", "Descrição 77", String.valueOf(Util.getRandomColor()), "tag 77", new Date()),
                new Note("Local 78", "Descrição 78", String.valueOf(Util.getRandomColor()), "tag 78", new Date()),
                new Note("Local 79", "Descrição 79", String.valueOf(Util.getRandomColor()), "tag 79", new Date()),
                new Note("Local 80", "Descrição 80", String.valueOf(Util.getRandomColor()), "tag 80", new Date()),
                new Note("Local 81", "Descrição 81", String.valueOf(Util.getRandomColor()), "tag 81", new Date()),
                new Note("Local 82", "Descrição 82", String.valueOf(Util.getRandomColor()), "tag 82", new Date()),
                new Note("Local 83", "Descrição 83", String.valueOf(Util.getRandomColor()), "tag 83", new Date()),
                new Note("Local 84", "Descrição 84", String.valueOf(Util.getRandomColor()), "tag 84", new Date()),
                new Note("Local 85", "Descrição 85", String.valueOf(Util.getRandomColor()), "tag 85", new Date()),
                new Note("Local 86", "Descrição 86", String.valueOf(Util.getRandomColor()), "tag 86", new Date()),
                new Note("Local 87", "Descrição 87", String.valueOf(Util.getRandomColor()), "tag 87", new Date()),
                new Note("Local 88", "Descrição 88", String.valueOf(Util.getRandomColor()), "tag 88", new Date()),
                new Note("Local 89", "Descrição 89", String.valueOf(Util.getRandomColor()), "tag 89", new Date()),
                new Note("Local 90", "Descrição 90", String.valueOf(Util.getRandomColor()), "tag 90", new Date()),
                new Note("Local 91", "Descrição 91", String.valueOf(Util.getRandomColor()), "tag 91", new Date()),
                new Note("Local 92", "Descrição 92", String.valueOf(Util.getRandomColor()), "tag 92", new Date()),
                new Note("Local 93", "Descrição 93", String.valueOf(Util.getRandomColor()), "tag 93", new Date()),
                new Note("Local 94", "Descrição 94", String.valueOf(Util.getRandomColor()), "tag 94", new Date()),
                new Note("Local 95", "Descrição 95", String.valueOf(Util.getRandomColor()), "tag 95", new Date()),
                new Note("Local 96", "Descrição 96", String.valueOf(Util.getRandomColor()), "tag 96", new Date()),
                new Note("Local 97", "Descrição 97", String.valueOf(Util.getRandomColor()), "tag 97", new Date()),
                new Note("Local 98", "Descrição 98", String.valueOf(Util.getRandomColor()), "tag 98", new Date()),
                new Note("Local 99", "Descrição 99", String.valueOf(Util.getRandomColor()), "tag 99", new Date()),
                new Note("Local 100", "Descrição 100", String.valueOf(Util.getRandomColor()), "tag 100", new Date()),


        };
    }
}
