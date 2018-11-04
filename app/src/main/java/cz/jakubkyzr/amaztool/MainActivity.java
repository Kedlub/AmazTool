package cz.jakubkyzr.amaztool;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cz.jakubkyzr.amaztool.ui.Adapter;
import cz.jakubkyzr.amaztool.ui.BaseButton;
import cz.jakubkyzr.amaztool.ui.Header;
import cz.jakubkyzr.amaztool.ui.IconButton;

public class MainActivity extends AppCompatActivity {

    Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        context = this;

        RecyclerView root = new RecyclerView(this);
        List<BaseButton> settings = new ArrayList<>();

        settings.add(new Header(getString(R.string.main_header)));

        settings.add(new IconButton(getDrawable(R.drawable.keyboard), getString(R.string.set_keyboard), getString(R.string.set_keyboard_desc), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommandUtil.executeCommand(context, "adb shell ime enable com.whirlscape.minuum/.MinuumKeyboardService mSettingsActivityName=com.whirlscape.minuum.MinuumPreferenceActivity && adb shell ime set com.whirlscape.minuum/.MinuumKeyboardService mSettingsActivityName=com.whirlscape.minuum.MinuumPreferenceActivity && adb shell ime disable com.huami.watch.input/.HuamiIME mSettingsActivityName=com.huami.watch.input.SetupActivity");
            }
        }));

        settings.add(new IconButton(getDrawable(R.drawable.apps), getString(R.string.enable_applist), getString(R.string.enable_applist_desc), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommandUtil.executeCommand(context, "touch /sdcard/launcher_config.ini");
            }
        }));

        settings.add(new IconButton(getDrawable(R.drawable.apps), getString(R.string.disable_applist), getString(R.string.disable_applist_desc), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommandUtil.executeCommand(context, "rm /sdcard/launcher_config.ini");
            }
        }));

        settings.add(new IconButton(getDrawable(R.drawable.ic_power_settings), getString(R.string.reboot_launcher), getString(R.string.reboot_launcher_desc), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommandUtil.executeCommand(context, "adb shell am force-stop com.huami.watch.launcher");
                context.finish();
            }
        }));

        settings.add(new IconButton(getDrawable(R.drawable.ic_power_settings), getString(R.string.reboot), getString(R.string.reboot_desc), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommandUtil.executeCommand(context, "reboot");
            }
        }));

        root.setLayoutManager(new LinearLayoutManager(this));
        root.setAdapter(new Adapter(this, settings));
        root.setPadding((int) getResources().getDimension(R.dimen.padding_round_small), 0, (int) getResources().getDimension(R.dimen.padding_round_small), (int) getResources().getDimension(R.dimen.padding_round_large));
        root.setClipToPadding(false);
        setContentView(root);
    }
}
