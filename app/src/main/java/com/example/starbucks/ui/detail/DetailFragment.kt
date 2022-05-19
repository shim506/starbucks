package com.example.starbucks.ui.detail


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.starbucks.R
import com.example.starbucks.common.MainActivity
import com.example.starbucks.databinding.FragmentDetail2Binding
import com.example.starbucks.ui.SecondActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDetail2Binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail2, container, false)

        val args: DetailFragmentArgs by navArgs()
        binding.textviewDetailName.text = args.title
//        binding.buttonOrder.setOnClickListener {
//            val intent = Intent(requireContext(), MainActivity::class.java).apply {
//                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            }
//            val pendingIntent: PendingIntent =
//                PendingIntent.getActivity(requireContext(), 0, intent, 0)
//            var builder =
//                androidx.core.app.NotificationCompat.Builder(requireContext(), "channelId")
//                    .setSmallIcon(R.drawable.ic_baseline_arrow_back_ios_24)
//                    .setContentTitle("My notification")
//                    .setContentText("Much longer text that cannot fit one line...")
//                    .setStyle(
//                        androidx.core.app.NotificationCompat.BigTextStyle()
//                            .bigText("Much longer text that cannot fit one line...")
//                    )
//                    .setPriority(androidx.core.app.NotificationCompat.PRIORITY_DEFAULT)
//
//
//            with(NotificationManagerCompat.from(requireContext())) {
//                // notificationId is a unique int for each notification that you must define
//                notify(1, builder.build())
//            }
//
//
//        }

        binding.buttonOrder.setOnClickListener {
//            createNotificationChannel(requireContext(), 1, true, "test", "this is test")
            createNotificationChannel(
                requireContext(), NotificationManagerCompat.IMPORTANCE_DEFAULT,
                false, getString(R.string.app_name), "App notification channel"
            ) // 1

            val channelId = "${getString(R.string.app_name)}"//2
            val title = "Android Developer"
            val content = "Notifications in Android P"

            val intent = Intent(requireContext(), SecondActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            val pendingIntent = PendingIntent.getActivity(
                requireContext(), 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT
            )    // 3

            val builder = NotificationCompat.Builder(requireContext(), channelId)  // 4
            builder.setSmallIcon(R.drawable.ic_baseline_coffee_24)    // 5
            builder.setContentTitle(title)    // 6
            builder.setContentText(content)    // 7
            builder.priority = NotificationCompat.PRIORITY_DEFAULT    // 8
            builder.setAutoCancel(true)   // 9
            builder.setContentIntent(pendingIntent)   // 10

            val notificationManager = NotificationManagerCompat.from(requireContext())
            lifecycleScope.launch {
                delay(3000)
                notificationManager.notify(1, builder.build())    // 11
            }
        }
        return binding.root
    }

    private fun createNotificationChannel(
        context: Context, importance: Int, showBadge: Boolean,
        name: String, description: String
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "$name"
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            channel.setShowBadge(showBadge)

            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}


