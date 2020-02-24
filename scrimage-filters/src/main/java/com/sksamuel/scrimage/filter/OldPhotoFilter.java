package com.sksamuel.scrimage.filter;

import com.sksamuel.scrimage.ImmutableImage;
import com.sksamuel.scrimage.ScaleMethod;
import thirdparty.misc.DaisyFilter;
import thirdparty.romainguy.BlendComposite;
import thirdparty.romainguy.BlendingMode;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OldPhotoFilter implements Filter {

    @Override
    public void apply(ImmutableImage image) throws IOException {

        DaisyFilter daisy = new DaisyFilter();
        BufferedImage filtered = daisy.filter(image.awt());

        Graphics2D g2 = (Graphics2D) image.awt().getGraphics();
        g2.drawImage(filtered, 0, 0, null);

        final ImmutableImage film = ImmutableImage.fromResource("/com/sksamuel/scrimage/filter/film1.jpg", ImmutableImage.CANONICAL_DATA_TYPE);
        BufferedImage filmSized = film.scaleTo(image.width, image.height, ScaleMethod.Bicubic).awt();
        BufferedImage filmSizedSameType = ImmutableImage.fromAwt(filmSized, image.awt().getType()).awt();

        g2.setComposite(BlendComposite.getInstance(BlendingMode.INVERSE_COLOR_DODGE, 0.30f));
        g2.drawImage(filmSizedSameType, 0, 0, null);
        g2.dispose();
    }
}

