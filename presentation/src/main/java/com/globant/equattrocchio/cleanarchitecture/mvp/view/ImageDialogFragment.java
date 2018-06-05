package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.data.response.Image;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageDialogFragment extends DialogFragment {

    @BindView(R.id.image_detail)
    ImageView imageDetail;
    @BindView(R.id.text_id)
    TextView textId;
    @BindView(R.id.text_url)
    TextView textUrl;
    @BindView(R.id.text_large_url)
    TextView textLargeUrl;
    @BindView(R.id.text_source_id)
    TextView textSourceId;
    @BindView(R.id.text_copyright)
    TextView textCopyright;
    @BindView(R.id.text_site)
    TextView textSite;

    private Gson gson = new Gson();
    private Image image;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        image = gson.fromJson(getArguments().getString("image"), Image.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_fragment_image, null);
        ButterKnife.bind(this, view);

        Glide.with(view).load(image.getUrl()).into(imageDetail);
        textId.setText(image.getId().toString());
        textUrl.setText(image.getUrl());
        textLargeUrl.setText(image.getLargeUrl());
        textSourceId.setText(image.getSourceId() != null ? image.getSourceId().toString() : "");
        textCopyright.setText(image.getCopyright());
        textSite.setText(image.getSite());

        builder.setView(view)
                .setPositiveButton(R.string.back, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        return builder.create();
    }

}
