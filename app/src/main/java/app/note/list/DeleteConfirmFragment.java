package app.note.list;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import app.note.R;
import app.note.utils.Constants;

/**
 * Classe que cria a cama de confirmação para deletar
 */
public class DeleteConfirmFragment extends DialogFragment {

    private ListContract.DeleteListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //recebe como parametro o id do lembrete
        final long noteId = getArguments().getLong(Constants.NOTE_ID);

        //instancia do alert para o lembrete
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.are_you_sure);

        //caso positivo
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mListener.setConfirm(true, noteId);
            }
        });

        //caso negativo
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mListener.setConfirm(false, noteId);
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (ListContract.DeleteListener) context;
    }
}
