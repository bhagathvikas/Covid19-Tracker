package com.example.covid;

import android.app.Notification;
import android.app.NotificationManager;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class NotificationService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived( remoteMessage );


        showNotification(remoteMessage.getNotification().getTitle() , remoteMessage.getNotification().getBody());
    }

    private void showNotification(String title, String message ) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder( this, "MyNotificaton" )
                .setContentTitle( title )
                .setSmallIcon( R.drawable.cvvdv)
                .setAutoCancel( true )
                .setContentText( message )

                ;

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from( this );
        managerCompat.notify( 999, builder.build() );


    }

}
