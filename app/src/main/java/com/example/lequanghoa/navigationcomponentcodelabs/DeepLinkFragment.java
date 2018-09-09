package com.example.lequanghoa.navigationcomponentcodelabs;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class DeepLinkFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.deeplink_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv = view.findViewById(R.id.text);
        tv.setText(getArguments().getString("myarg"));

        Button b = view.findViewById(R.id.send_notification);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editArgs = view.findViewById(R.id.edit_args);
                Bundle args = new Bundle();
                args.putString("myarg", editArgs.getText().toString());
                PendingIntent deepLink = Navigation.findNavController(view).createDeepLink()
                        .setDestination(R.id.android)
                        .setArguments(args)
                        .createPendingIntent();

                NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationManager.createNotificationChannel(new NotificationChannel(
                            "deeplink", "Deep Links", NotificationManager.IMPORTANCE_HIGH
                    ));
                }

                NotificationCompat.Builder builder = new NotificationCompat.Builder(
                        getContext(), "deeplink")
                        .setContentTitle("Navigation")
                        .setSmallIcon(R.drawable.ic_android)
                        .setContentIntent(deepLink)
                        .setAutoCancel(true);
                notificationManager.notify(0, builder.build());

            }
        });
    }
}
