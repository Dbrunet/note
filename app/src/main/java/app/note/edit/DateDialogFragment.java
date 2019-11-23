package app.note.edit;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Classe que cria a camada para escolher uma data
 */
public class DateDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private EditContract.DateListener mListener;

    //cria um DatePicker com a data de hoje
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    //evento de espera para quando a data for escolhida na camada
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        mListener.setSelectedDate(c.getTime());
    }

    //inicialização da interface de retorno do date
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (EditContract.DateListener) context;
    }
}
