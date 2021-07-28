package com.reelsdownloder.activity;

import android.content.ClipboardManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.reelsdownloder.R;
import com.reelsdownloder.databinding.ActivityMainBinding;
import com.reelsdownloder.util.AppLangSessionManager;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    MainActivity activity;
    ActivityMainBinding binding;
    //ViewDataBinding binding;
    boolean doubleBackToExitPressedOnce = false;
    private FirebaseAnalytics mFirebaseAnalytics;
    private ClipboardManager clipBoard;
   // private Handler bhandle =new Handler();
   /* String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    String CopyKey = "";
    String CopyValue = "";*/

    AppLangSessionManager appLangSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activity = this;

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

             /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent refresh = new Intent(MainActivity.this, InstagramActivity.class);
                startActivity(refresh);
                finish();
            }
        }, 7000);

        appLangSessionManager = new AppLangSessionManager(activity);

        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        activity = this;
        assert activity != null;
        clipBoard = (ClipboardManager) activity.getSystemService(CLIPBOARD_SERVICE);
    }

    public void initViews() {
        clipBoard = (ClipboardManager) activity.getSystemService(CLIPBOARD_SERVICE);
        if (activity.getIntent().getExtras() != null) {
            for (String key : activity.getIntent().getExtras().keySet()) {
                CopyKey = key;
                String value = activity.getIntent().getExtras().getString(CopyKey);
                if (CopyKey.equals("android.intent.extra.TEXT")) {
                    CopyValue = activity.getIntent().getExtras().getString(CopyKey);
                    callText(value);
                } else {
                    CopyValue = "";
                    callText(value);
                }
            }
        }
        if (clipBoard != null) {
            clipBoard.addPrimaryClipChangedListener(new ClipboardListener() {
                @Override
                public void onPrimaryClipChanged() {
                    try {
                        showNotification(Objects.requireNonNull(clipBoard.getPrimaryClip().getItemAt(0).getText()).toString());
                    } catch (
                            Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        if (Build.VERSION.SDK_INT >= 23) {
            checkPermissions(0);
        }

        binding.rvLike.setOnClickListener(this);
        binding.rvInsta.setOnClickListener(this);
        binding.rvWhatsApp.setOnClickListener(this);
        binding.rvTikTok.setOnClickListener(this);
        binding.rvFB.setOnClickListener(this);
        binding.rvTwitter.setOnClickListener(this);
        binding.rvGallery.setOnClickListener(this);
        binding.rvAbout.setOnClickListener(this);
        binding.rvShareApp.setOnClickListener(this);
        binding.rvRateApp.setOnClickListener(this);
        binding.rvMoreApp.setOnClickListener(this);
        binding.rvSnack.setOnClickListener(this);
        binding.rvShareChat.setOnClickListener(this);
        binding.rvRoposo.setOnClickListener(this);


    /*    buttn1.setOnClickListener(this);
        buttn2.setOnClickListener(this);
        buttn3.setOnClickListener(this);
        buttn4.setOnClickListener(this);
        buttn5.setOnClickListener(this);
        buttn6.setOnClickListener(this);
        buttn7.setOnClickListener(this);
        buttn8.setOnClickListener(this);
        buttn9.setOnClickListener(this);
        buttn10.setOnClickListener(this);
       // buttn11.setOnClickListener(this);
        //buttn12.setOnClickListener(this);
        //buttn13.setOnClickListener(this);
        //buttn14.setOnClickListener(this);

        //TODO :  Change Language Dialog Open
        binding.rvChangeLang.setOnClickListener(v -> {
            final BottomSheetDialog dialogSortBy = new BottomSheetDialog(MainActivity.this, R.style.SheetDialog);
            dialogSortBy.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogSortBy.setContentView(R.layout.dialog_language);
            final TextView tv_english = dialogSortBy.findViewById(R.id.tv_english);
            final TextView tv_hindi = dialogSortBy.findViewById(R.id.tv_hindi);
            final TextView tv_cancel = dialogSortBy.findViewById(R.id.tv_cancel);
            final TextView tvArabic = dialogSortBy.findViewById(R.id.tvArabic);
            dialogSortBy.show();
            tv_english.setOnClickListener(view -> {
                setLocale("en");
                appLangSessionManager.setLanguage("en");
            });
            tv_hindi.setOnClickListener(view -> {
                setLocale("hi");
                appLangSessionManager.setLanguage("hi");
            });
            tvArabic.setOnClickListener(view -> {
                setLocale("ar");
                appLangSessionManager.setLanguage("ar");
            });
            tv_cancel.setOnClickListener(view -> dialogSortBy.dismiss());

        });

        createFileFolder();

    }

    private void callText(String CopiedText) {
        try {
            if (CopiedText.contains("likee")) {
                if (Build.VERSION.SDK_INT >= 23) {
                    checkPermissions(100);
                } else {
                    callLikeeActivity();
                }
            } else if (CopiedText.contains("instagram.com")) {
                if (Build.VERSION.SDK_INT >= 23) {
                    checkPermissions(101);
                } else {
                    callInstaActivity();
                }
            } else if (CopiedText.contains("facebook.com")) {
                if (Build.VERSION.SDK_INT >= 23) {
                    checkPermissions(104);
                } else {
                    callFacebookActivity();
                }
            } else if (CopiedText.contains("tiktok.com")) {
                if (Build.VERSION.SDK_INT >= 23) {
                    checkPermissions(103);
                } else {
                    callTikTokActivity();
                }
            } else if (CopiedText.contains("twitter.com")) {
                if (Build.VERSION.SDK_INT >= 23) {
                    checkPermissions(106);
                } else {
                    callTwitterActivity();
                }
            }else if (CopiedText.contains("sharechat")) {
                if (Build.VERSION.SDK_INT >= 23) {
                    checkPermissions(107);
                } else {
                    callShareChatActivity();
                }
            }
            else if (CopiedText.contains("roposo")) {
                if (Build.VERSION.SDK_INT >= 23) {
                    checkPermissions(108);
                } else {
                    callRoposoActivity();
                }
            }else if (CopiedText.contains("snackvideo")||CopiedText.contains("sck.io")) {
                if (Build.VERSION.SDK_INT >= 23) {
                    checkPermissions(109);
                } else {
                    callSnackVideoActivity();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        Intent i = null;

        switch (v.getId()) {
            case R.id.rvLike:
                if (Build.VERSION.SDK_INT >= 23) {
                    checkPermissions(100);
                } else {
                    callLikeeActivity();
                }
                break;
            case R.id.rvInsta:
                if (Build.VERSION.SDK_INT >= 23) {
                    checkPermissions(101);
                } else {
                    callInstaActivity();
                }
                break;

            case R.id.rvWhatsApp:
                if (Build.VERSION.SDK_INT >= 23) {
                    checkPermissions(102);
                } else {
                    callWhatsappActivity();
                }
                break;
            case R.id.rvTikTok:
                if (Build.VERSION.SDK_INT >= 23) {
                    checkPermissions(103);
                } else {
                    callTikTokActivity();
                }
                break;
            case R.id.rvFB:
                if (Build.VERSION.SDK_INT >= 23) {
                    checkPermissions(104);
                } else {
                    callFacebookActivity();
                }
                break;
            case R.id.rvGallery:
                if (Build.VERSION.SDK_INT >= 23) {
                    checkPermissions(105);
                } else {
                    callGalleryActivity();
                }
                break;
            case R.id.rvTwitter:
                if (Build.VERSION.SDK_INT >= 23) {
                    checkPermissions(106);
                } else {
                    callTwitterActivity();
                }
                break;
            case R.id.rvAbout:
                i = new Intent(activity, AboutUsActivity.class);
                startActivity(i);
                break;
            case R.id.rvShareApp:
                Utils.ShareApp(activity);
                break;

            case R.id.rvRateApp:
                Utils.RateApp(activity);
                break;
            case R.id.rvMoreApp:
                Utils.MoreApp(activity);
                break;
            case R.id.rvShareChat:
                if (Build.VERSION.SDK_INT >= 23) {
                    checkPermissions(107);
                } else {
                    callShareChatActivity();
                }
                break;
            case R.id.rvRoposo:
                if (Build.VERSION.SDK_INT >= 23) {
                    checkPermissions(108);
                } else {
                    callRoposoActivity();
                }
                break;
            case R.id.rvSnack:
                if (Build.VERSION.SDK_INT >= 23) {
                    checkPermissions(109);
                } else {
                    callSnackVideoActivity();
                }
                break;

        }
    }


    public void callLikeeActivity() {
        Intent i = new Intent(activity, LikeeActivity.class);
        i.putExtra("CopyIntent", CopyValue);
        startActivity(i);
    }

    public void callInstaActivity() {
        Intent i = new Intent(activity, InstagramActivity.class);
        i.putExtra("CopyIntent", CopyValue);
        startActivity(i);
    }


    public void callWhatsappActivity() {
        Intent i = new Intent(activity, WhatsappActivity.class);
        startActivity(i);
    }

    public void callTikTokActivity() {
        Intent i = new Intent(activity, TikTokActivity.class);
        i.putExtra("CopyIntent", CopyValue);
        startActivity(i);
    }

    public void callFacebookActivity() {
        Intent i = new Intent(activity, FacebookActivity.class);
        i.putExtra("CopyIntent", CopyValue);
        startActivity(i);

    }

    public void callTwitterActivity() {
        Intent i = new Intent(activity, TwitterActivity.class);
        i.putExtra("CopyIntent", CopyValue);
        startActivity(i);
    }


    public void callGalleryActivity() {
        Intent i = new Intent(activity, GalleryActivity.class);
        startActivity(i);
    }

    public void callRoposoActivity() {
        Intent i = new Intent(activity, RoposoActivity.class);
        i.putExtra("CopyIntent", CopyValue);
        startActivity(i);
    }

    public void callShareChatActivity() {
        Intent i = new Intent(activity, ShareChatActivity.class);
        i.putExtra("CopyIntent", CopyValue);
        startActivity(i);
    }

    public void callSnackVideoActivity() {
        Intent i = new Intent(activity, SnackVideoActivity.class);
        i.putExtra("CopyIntent", CopyValue);
        startActivity(i);
    }

    public void showNotification(String Text) {
        if (Text.contains("instagram.com") || Text.contains("facebook.com") || Text.contains("tiktok.com")
                || Text.contains("twitter.com") || Text.contains("likee")
                || Text.contains("sharechat")|| Text.contains("roposo")|| Text.contains("snackvideo")|| Text.contains("sck.io")) {
            Intent intent = new Intent(activity, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("Notification", Text);
            PendingIntent pendingIntent = PendingIntent.getActivity(activity, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel mChannel = new NotificationChannel(getResources().getString(R.string.app_name),
                        getResources().getString(R.string.app_name), NotificationManager.IMPORTANCE_HIGH);
                mChannel.enableLights(true);
                mChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
                notificationManager.createNotificationChannel(mChannel);
            }
            NotificationCompat.Builder notificationBuilder;
            notificationBuilder = new NotificationCompat.Builder(activity, getResources().getString(R.string.app_name))
                    .setAutoCancel(true)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setColor(getResources().getColor(R.color.black))
                    .setLargeIcon(BitmapFactory.decodeResource(activity.getResources(),
                            R.mipmap.ic_launcher_round))
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentTitle("Copied text")
                    .setContentText(Text)
                    .setChannelId(getResources().getString(R.string.app_name))
                    .setFullScreenIntent(pendingIntent, true);
            notificationManager.notify(1, notificationBuilder.build());
        }
    }

    private boolean checkPermissions(int type) {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(activity, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions((Activity) (activity),
                    listPermissionsNeeded.toArray(new
                            String[listPermissionsNeeded.size()]), type);
            return false;
        } else {
            if (type == 100) {
                callLikeeActivity();
            } else if (type == 101) {
                callInstaActivity();
            } else if (type == 102) {
                callWhatsappActivity();
            } else if (type == 103) {
                callTikTokActivity();
            } else if (type == 104) {
                callFacebookActivity();
            } else if (type == 105) {
                callGalleryActivity();
            } else if (type == 106) {
                callTwitterActivity();
            }else if (type == 107) {
                callShareChatActivity();
            }else if (type==108){
                callRoposoActivity();
            }else if (type == 109) {
                callSnackVideoActivity();
            }

        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callLikeeActivity();
            } else {
            }
            return;
        } else if (requestCode == 101) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callInstaActivity();
            } else {
            }
            return;
        } else if (requestCode == 102) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callWhatsappActivity();
            } else {
            }
            return;
        } else if (requestCode == 103) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callTikTokActivity();
            } else {
            }
            return;
        } else if (requestCode == 104) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callFacebookActivity();
            } else {
            }
            return;
        } else if (requestCode == 105) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callGalleryActivity();
            } else {
            }
            return;
        } else if (requestCode == 106) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callTwitterActivity();
            } else {
            }
            return;
        } else if (requestCode == 107) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callShareChatActivity();
            } else {
            }
            return;
        }
        else if (requestCode == 108) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callRoposoActivity();
            } else {
            }
            return;
        }else if (requestCode == 109) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callSnackVideoActivity();
            } else {
            }
            return;
        }

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        this.doubleBackToExitPressedOnce = true;
        Utils.setToast(activity, getResources().getString(R.string.pls_bck_again));

       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);*/
    }
    public void setLocale(String lang) {

        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);



}
}
