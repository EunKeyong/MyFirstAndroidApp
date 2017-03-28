package com.example.byg.exam_0120.activities;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.databinding.ActivityPaletteBinding;

public class PaletteActivity extends AppCompatActivity {

    private ActivityPaletteBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_palette);

        BitmapFactory.Options options = new BitmapFactory.Options();
        // 비트맵 샘플링(용량줄이기) 2의 배수
        options.inSampleSize = 2;
       // options.inMutable = true;

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.beach, options);
        createPaletteAsync(bitmap);

    }

    public void createPaletteAsync(Bitmap bitmap) {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette p) {
                // Use generated instance

                Palette.Swatch vibrantSwatch = p.getVibrantSwatch();
                if (vibrantSwatch != null) {
                    mBinding.layout1.setBackgroundColor(vibrantSwatch.getRgb());
                    mBinding.title1.setTextColor(vibrantSwatch.getTitleTextColor());
                    mBinding.content1.setTextColor(vibrantSwatch.getBodyTextColor());
                }
                Palette.Swatch vibrantLightSwatch = p.getLightVibrantSwatch();
                if (vibrantLightSwatch != null) {
                    mBinding.layout2.setBackgroundColor(vibrantSwatch.getRgb());
                    mBinding.title2.setTextColor(vibrantSwatch.getTitleTextColor());
                    mBinding.content2.setTextColor(vibrantSwatch.getBodyTextColor());
                }
                Palette.Swatch vibrantDarkSwatch = p.getDarkVibrantSwatch();
                if (vibrantDarkSwatch != null) {
                    mBinding.layout3.setBackgroundColor(vibrantSwatch.getRgb());
                    mBinding.title3.setTextColor(vibrantSwatch.getTitleTextColor());
                    mBinding.content3.setTextColor(vibrantSwatch.getBodyTextColor());
                }
                Palette.Swatch lightMutedSwatch = p.getLightMutedSwatch();
                if (lightMutedSwatch != null) {
                    mBinding.layout4.setBackgroundColor(vibrantSwatch.getRgb());
                    mBinding.title4.setTextColor(vibrantSwatch.getTitleTextColor());
                    mBinding.content4.setTextColor(vibrantSwatch.getBodyTextColor());
                }
                Palette.Swatch mutedSwatch = p.getMutedSwatch();
                if (mutedSwatch != null) {
                    mBinding.layout5.setBackgroundColor(vibrantSwatch.getRgb());
                    mBinding.title5.setTextColor(vibrantSwatch.getTitleTextColor());
                    mBinding.content5.setTextColor(vibrantSwatch.getBodyTextColor());
                }
                Palette.Swatch darkMutedSwatch = p.getDarkMutedSwatch();
                if (darkMutedSwatch != null) {
                    mBinding.layout6.setBackgroundColor(vibrantSwatch.getRgb());
                    mBinding.title6.setTextColor(vibrantSwatch.getTitleTextColor());
                    mBinding.content6.setTextColor(vibrantSwatch.getBodyTextColor());
                }
                Palette.Swatch dominantSwatch = p.getDominantSwatch();
                if (dominantSwatch != null) {
                    mBinding.layout7.setBackgroundColor(vibrantSwatch.getRgb());
                    mBinding.title7.setTextColor(vibrantSwatch.getTitleTextColor());
                    mBinding.content7.setTextColor(vibrantSwatch.getBodyTextColor());
                }
            }
        });
    }

//    private Palette.Swatch checkVibrantSwatch(Palette p) {
//        Palette.Swatch vibrant = p.getVibrantSwatch();
//        if (vibrant != null) {
//            return vibrant;
//        }
//        // Throw error
//    }
}
