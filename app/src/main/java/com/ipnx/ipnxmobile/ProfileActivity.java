package com.ipnx.ipnxmobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.userProfile;

public class ProfileActivity extends BaseActivity {
    public final String ProfilePREFERENCES = "ProfilePrefs" ;
    public final String PictureLocation = "ProfilePictureLocation";

    TextView name, phone, number, email, address;
    CircleImageView image;

    TextView bottomNavProfileIcon;

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        image = findViewById(R.id.profile_picture);
        name = findViewById(R.id.profile_name);
        phone = findViewById(R.id.profile_phoneNumber);
        number = findViewById(R.id.profile_number);
        email = findViewById(R.id.profile_email);
        address = findViewById(R.id.profile_address);
        bottomNavProfileIcon = findViewById(R.id.bottomNav_profile);

        sharedpreferences = getSharedPreferences(ProfilePREFERENCES, Context.MODE_PRIVATE);

        loadProfilePicture();
        name.setText(userProfile.getFullName());
        phone.setText(userProfile.getPhoneNumber());
        number.setText(userProfile.getCustomerNumber());
        email.setText(userProfile.getEmailAddress().toString());
        address.setText(userProfile.getAddress());

        bottomNavProfileIcon.setTextColor(getResources().getColor(R.color.red_button));
        Drawable img = getResources().getDrawable( R.drawable.ic_user3 );
        bottomNavProfileIcon.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
    }

    public void onBackClicked(View view){
        finish();
    }

    public void onChangeImageClicked(View view){
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setFixAspectRatio(true)
                .setAspectRatio(1, 1)
                .setOutputCompressFormat(Bitmap.CompressFormat.JPEG)
                .setOutputCompressQuality(60)
                .start(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                editor = sharedpreferences.edit();
                editor.putString(PictureLocation, resultUri.toString());
                editor.apply();
                loadProfilePicture();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void loadProfilePicture() {
        String uriString = sharedpreferences.getString(PictureLocation, "");
        if (!uriString.equals("")){
            Uri uri = Uri.parse(uriString);
            image.setImageURI(uri);
        }
    }

    @Override
    public void onBottomNavItemClicked(View view) {
        // All bottom nav item except the profile item should
        // be clickable
        if (view.getId() != R.id.bottomNav_profile){
            super.onBottomNavItemClicked(view);
        }
    }
}
