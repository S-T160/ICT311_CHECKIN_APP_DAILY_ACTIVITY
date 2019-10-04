package com.example.daily_activity;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;


import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class ActivityFragment extends Fragment {
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_PHOTO = 3;
    private static final String argument_daily_id = "daily_id";
    private Daily_Activity UDaily_Activity;
    private EditText Utitle;
    private EditText Uplace;
    private EditText Udetail;
    private Button Udate;
    private Button Usave;
    private Button Ucancel;
    private Button Umap_button;
    private ImageButton UPhotoButton;
    private ImageView UPhotoView;
    private File Upicturefolder;


    public static ActivityFragment newInstance(UUID dailyid) {
        Bundle args = new Bundle();
        args.putSerializable(argument_daily_id, dailyid);
        ActivityFragment fragment = new ActivityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID dailyid = (UUID) getArguments().getSerializable(argument_daily_id);
        UDaily_Activity = Routines.get(getActivity()).getdailylist(dailyid);
        Upicturefolder = Routines.get(getActivity()).getPicturefolder(UDaily_Activity);
    }

    @Override
    public void onPause() {
        super.onPause();
        Routines.get(getActivity()).update_dailyactivity(UDaily_Activity);

    }

    @Override
    public void onActivityResult(int askcode, int idcode, Intent datas) {
        if ((idcode) != Activity.RESULT_OK) {
            return;
        }
        if (idcode == REQUEST_DATE) {
            Date dates = (Date) datas.getSerializableExtra(datepicking_calender_fragment.EXTRA_DATE);
            UDaily_Activity.setUdate(dates);
            updateDate();
        } else if (askcode == REQUEST_PHOTO) {
        Uri uri =
                FileProvider.getUriForFile(getActivity(),
                        "com.example.daily_activity.fileprovider",
                        Upicturefolder);
        getActivity().revokeUriPermission(uri,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        updatePICTUREView();

    }}

    private void updateDate() {
        Udate.setText(UDaily_Activity.getUdate().toString());
    }
    private String getCrimeReport() {
        String dateFormat = "EEE, MMM dd";
        String dateString = DateFormat.format(dateFormat,
                        UDaily_Activity.getUdate()).toString();
        return  dateString;

    }
    private void updatePICTUREView() {
        if (Upicturefolder == null ||
                !Upicturefolder.exists()) {

            UPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap =
                    PhotoUtils.get_bitmap(Upicturefolder.getPath(), getActivity());

            UPhotoView.setImageBitmap(bitmap);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup DislpayArea,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_fragment, DislpayArea, false);
        Utitle = (EditText) v.findViewById(R.id.text1);
        Utitle.setText(UDaily_Activity.getUtitle());
        Utitle.setText(UDaily_Activity.getUtitle());
        Utitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int begin, int past, int calculate) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int begin, int calculate, int later) {
                UDaily_Activity.setUtitle(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable after_text_change) {

            }
        });
        Uplace = (EditText) v.findViewById(R.id.text7);
        Uplace.setText(UDaily_Activity.getUplace());
        Uplace.setText(UDaily_Activity.getUplace());
        Uplace.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int begin, int past, int calculate) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int begin, int calculate, int later) {
                UDaily_Activity.setUplace(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable after_text_change) {

            }
        });
        Udetail = (EditText) v.findViewById(R.id.text3);
        Udetail.setText(UDaily_Activity.getUdetail());
        Udetail.setText(UDaily_Activity.getUdetail());
        Udetail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int begin, int past, int calculate) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int begin, int calculate, int later) {
                UDaily_Activity.setUdetail(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable after_text_change) {

            }
        });
        Udate = (Button) v.findViewById(R.id.text12);
        updateDate();
        Udate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                datepicking_calender_fragment dialog = datepicking_calender_fragment.newInstance(UDaily_Activity.getUdate());
                dialog.setTargetFragment(ActivityFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        Ucancel = (Button) v.findViewById(R.id.button2);
        Ucancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Routines.get(getActivity()).delete_dailyactivity(UDaily_Activity);
                startActivity(Activity_In_SingleFragment.newIntent1(getActivity()));


            }
        });
        Usave = (Button) v.findViewById(R.id.button1);
        Usave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Routines.get(getActivity()).update_dailyactivity(UDaily_Activity);
                startActivity(Activity_In_SingleFragment.newIntent1(getActivity()));
            }
        });
        PackageManager packageManager = getActivity().getPackageManager();

        Umap_button =(Button) v.findViewById(R.id.button3);
        Umap_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(Daily_Activity_MapActivity.newIntent(getActivity()));

            }
        });

        UPhotoButton = (ImageButton)
                v.findViewById(R.id.imageButton);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        boolean canTakePhoto = Upicturefolder != null && captureImage.resolveActivity(packageManager) != null;
        UPhotoButton.setEnabled(canTakePhoto);
        UPhotoButton.setOnClickListener(new
                                                View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        Uri uri = FileProvider.getUriForFile(getActivity(),
                                                                "com.example.daily_activity.fileprovider", Upicturefolder);

                                                        captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                                                        List<ResolveInfo> cameraActivities = getActivity()
                                                                .getPackageManager().queryIntentActivities(captureImage,
                                                                        PackageManager.MATCH_DEFAULT_ONLY);
                                                        for (ResolveInfo activity : cameraActivities) {
                                                            getActivity().grantUriPermission(activity.activityInfo.packageName, uri,
                                                                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                                                        }

                                                        startActivityForResult(captureImage,

                                                                REQUEST_PHOTO);
                                                    }
                                                });
        UPhotoView = (ImageView)
                v.findViewById(R.id.imageView2);
        updatePICTUREView();



            return v;
    }
}




