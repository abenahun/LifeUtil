package club.hutcwp.lifeutil.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.color.ColorChooserDialog;

import org.greenrobot.eventbus.EventBus;

import club.hutcwp.lifeutil.R;
import club.hutcwp.lifeutil.app.AppGlobal;
import club.hutcwp.lifeutil.event.ThemeChangedEvent;
import club.hutcwp.lifeutil.ui.base.BaseActivity;
import club.hutcwp.lifeutil.util.ThemeUtil;

public class SettingActivity extends BaseActivity implements ColorChooserDialog.ColorCallback {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }


    @Override
    protected void initViews(Bundle savedInstanceState) {

        setDisplayHomeAsUpEnabled(true);

        getFragmentManager().beginTransaction().replace(R.id.contentLayout, new SettingFragment()).commit();
    }

    @Override
    protected void loadData() {

    }

    public static void createActivity(Context context) {

        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onColorSelection(@NonNull ColorChooserDialog dialog, @ColorInt int selectedColor) {

        if (selectedColor == ThemeUtil.getThemeColor(this, R.attr.colorPrimary))
            return;
        toolbar.setBackgroundColor(selectedColor);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(selectedColor);
        }
        if (selectedColor == getResources().getColor(R.color.lapis_blue)) {
            setTheme(R.style.LapisBlueTheme);
            saveTheme(0);
        } else if (selectedColor == getResources().getColor(R.color.pale_dogwood)) {
            setTheme(R.style.PaleDogwoodTheme);
            saveTheme(1);
        } else if (selectedColor == getResources().getColor(R.color.greenery)) {
            setTheme(R.style.GreeneryTheme);
            saveTheme(2);
        } else if (selectedColor == getResources().getColor(R.color.primrose_yellow)) {
            setTheme(R.style.PrimroseYellowTheme);
            saveTheme(3);
        } else if (selectedColor == getResources().getColor(R.color.flame)) {
            setTheme(R.style.FlameTheme);
            saveTheme(4);
        } else if (selectedColor == getResources().getColor(R.color.island_paradise)) {
            setTheme(R.style.IslandParadiseTheme);
            saveTheme(5);
        } else if (selectedColor == getResources().getColor(R.color.kale)) {
            setTheme(R.style.KaleTheme);
            saveTheme(6);
        } else if (selectedColor == getResources().getColor(R.color.pink_yarrow)) {
            setTheme(R.style.PinkYarrowTheme);
            saveTheme(7);
        } else if (selectedColor == getResources().getColor(R.color.niagara)) {
            setTheme(R.style.NiagaraTheme);
            saveTheme(8);
        }
        //重新装载Fragment
        getFragmentManager().beginTransaction().replace(R.id.contentLayout, new SettingFragment()).commit();
        EventBus.getDefault().post(new ThemeChangedEvent(selectedColor));

    }

    private void saveTheme(int theme) {
        SharedPreferences pf = getSharedPreferences(AppGlobal.FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pf.edit();
        setTheme(R.style.NiagaraTheme);
        editor.putInt("theme", theme);
        editor.apply();
    }

}
